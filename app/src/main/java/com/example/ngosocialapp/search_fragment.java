package com.example.ngosocialapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link search_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class search_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public search_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment search_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static search_fragment newInstance(String param1, String param2) {
        search_fragment fragment = new search_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Activity referenceActivity;
    View parentHolder;
    Button AddNewBtns;
    LinearLayout.LayoutParams lprams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
    );
    Integer ID =0 ;
    LinearLayout linearLL;
    Context mContext; // context of search fragment
    /*
    * As this will be interst coming from firebase we need to order and create buttons with thse interst per user
    * so user class should have ArrayList<String> where we will keep interst of him
    * As planned NGO will be inheriting user. This NGo will have same.
    * */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public View onCreateView(@org.jetbrains.annotations.NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        referenceActivity = getActivity();
        parentHolder = inflater.inflate(R.layout.fragment_search, container, false);
        AddNewBtns = parentHolder.findViewById(R.id.interst);
        linearLL = parentHolder.findViewById(R.id.linearLL);

        AddNewBtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn=  new Button(mContext);
            btn.setId(ID++);
            btn.setText(ID.toString());
            btn.setOnClickListener(btnclick);
            btn.setLayoutParams(lprams);
            btn.setBackgroundColor(Color.RED);
            linearLL.addView(btn);
            }
        });

        return parentHolder;
    }
//    public void addBtns(View v){
//        Button btn=  new Button(mContext);
//        btn.setId(ID++);
//        btn.setText(ID.toString());
//        btn.setOnClickListener(btnclick);
//        btn.setLayoutParams(lprams);
//        btn.setBackgroundColor(Color.RED);
//        linearLL.addView(btn);
//    }


    // on click listener on every button which will be dynamically create by firebase / add btn i.e. interst tab
    View.OnClickListener btnclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Drawable drawable = view.getBackground();
            ColorDrawable buttonColor = (ColorDrawable) view.getBackground();
            int btnColor = buttonColor.getColor();
            if(btnColor == Color.RED){
                // it's not selected, select it
                view.setBackgroundColor(Color.GREEN);
            }
            else {
                view.setBackgroundColor(Color.RED);
            }
        }
    };
}