package com.example.ngosocialapp;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

/**.
 *
 * Fragment for new post , this will be only available for only NGO
 */
public class NewPost_fragment extends Fragment {
    ImageView dp;
    Button post_btn;
    EditText caption;
    View parentHolder;
    int SELECT_PICTURE = 200;
    Context mContext;
    Uri filePath;
    Boolean isDownloaduriNull = false;
    Uri downloadUri;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference ,getting_ngo_name,getting_postcnt,databaseReference2;
    FirebaseStorage storage;
    StorageReference storageReference;
    private StorageTask uploading;
    Post current_post;
    public static final int PICK_IMAGE = 1;

    NGO u;
    String ngoName,userId;
    int postcnt;


    public NewPost_fragment() {
        // Required empty public constructor
        userId= FirebaseAuth.getInstance().getCurrentUser().getUid();
        getting_ngo_name=FirebaseDatabase.getInstance().getReference("NGO").child(userId).child("name");
        getting_postcnt=FirebaseDatabase.getInstance().getReference("NGO").child(userId).child("postcnt");

        getting_ngo_name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ngoName=snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        getting_postcnt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postcnt=snapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onAttach(@NonNull @org.jetbrains.annotations.NotNull Context context) {
        super.onAttach(context);
        mContext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentHolder = inflater.inflate(R.layout.fragment_new_post, container, false);

        dp = parentHolder.findViewById(R.id.dp);
        post_btn = parentHolder.findViewById(R.id.post);
        caption = parentHolder.findViewById(R.id.post_desc);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        databaseReference= FirebaseDatabase.getInstance().getReference("Posts");

        databaseReference2= FirebaseDatabase.getInstance().getReference("NGO");


        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                try {
                    startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
                }
                catch(Exception e){
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new home();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();

            }
        });



        return parentHolder;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                filePath = data.getData();
                if (null != filePath) {
                    dp.setImageURI(filePath);
                }
            }
        }
    }

    private String getFileExtension(Uri uri)
    {
        ContentResolver cR= mContext.getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadImage()
    {
        if(filePath!=null)
        {
            postcnt=postcnt+1;

            StorageReference sR= storageReference.child(ngoName+postcnt+"."+getFileExtension(filePath));
            databaseReference2.child(userId).child("postcnt").setValue(postcnt);
            uploading=sR.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ;
                        }
                    }, 0);
                    Toast.makeText(mContext, "Upload Successful", Toast.LENGTH_SHORT).show();
                    isDownloaduriNull = true;
                    sR.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadUri=uri;
                            current_post=new Post(downloadUri.toString(),caption.getText().toString(),ngoName);
                            databaseReference.child(userId).child(ngoName+postcnt).setValue(current_post);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(mContext, "Please Upload Again", Toast.LENGTH_SHORT).show();
                        }
                    })
                    ;
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    double progress=(100.0 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                }
            });
        }
        else
        {
            Toast.makeText(mContext, "No image selected", Toast.LENGTH_SHORT).show();
        }
    }
}