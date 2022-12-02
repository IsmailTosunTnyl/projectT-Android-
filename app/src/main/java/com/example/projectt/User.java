package com.example.projectt;

public class User {
/*
"ID": 62,
        "NationalID": "444",
        "Mail": "aa",
        "Password": "MVeSXkuisFJn2sG/KYzlDQ==",
        "Name": "sadasd",
        "LastName": "asdsad",
        "Phone": "ddd",
        "Adress": "444",
        "Balance": 0.0,
        "Star": 0.0
 */
    private int id;
    private String nationalID;
    private String mail;
    private String password;
    private String name;
    private String lastname;
    private String phone;
    private String adress;
    private double balance;
    private double star;

    public User(int id, String nationalID, String mail, String password, String name, String lastname, String phone, String adress, double balance, double star) {
        this.id = id;
        this.nationalID = nationalID;
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.adress = adress;
        this.balance = balance;
        this.star = star;
    }


    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }
}
