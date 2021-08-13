package com.example.ngosocialapp.ModelClasses;

public class TransactionEntry {

//    @PrimaryKey(autoGenerate = true)

    int amount;
    String ngo;
    String transaction_id;

    public TransactionEntry() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNgo() {
        return ngo;
    }

    public void setNgo(String ngo) {
        this.ngo = ngo;
    }

    public String gettransaction_id() {
        return transaction_id;
    }

    public void setransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public TransactionEntry(int amount, String ngo, String transaction_id) {
        this.amount = amount;
        this.ngo = ngo;
        this.transaction_id = transaction_id;
    }
// private int amount;
//    private int id;
//    private String category;
//    private String description;
//    private Date date;                  // COMPLETED: 13-09-2018 Add appropriate type converter
//    private String transactionType; //to decide whether income or expense
//
////    @Ignore
//    public TransactionEntry(int amount,String category,String description,Date date,String transactionType){
//        this.amount = amount;
//        this.category = category;
//        this.description = description;
//        this.date = date;
//        this.transactionType=transactionType;
//    }
//
//
//    public TransactionEntry(int id,int amount,String category,String description,Date date,String transactionType){
//        this.id = id;
//        this.amount = amount;
//        this.category = category;
//        this.description = description;
//        this.date = date;
//        this.transactionType=transactionType;
//    }
//
//
//
//    public int getAmount() {
//        return amount;
//    }
//
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTransactionType() {
//        return transactionType;
//    }
//
//    public void setTransactionType(String transactionType) {
//        this.transactionType = transactionType;
//    }
}