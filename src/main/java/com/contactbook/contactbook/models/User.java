package com.contactbook.contactbook.models;

public class User {
    private int id;
    private String fullname;
    private String cellno;
    private String email;

    public User(){}

    public User(int id, String fullname, String cellno, String email) {
        this.id = id;
        this.fullname = fullname;
        this.cellno = cellno;
        this.email = email;
    }

    public User(String fullname, String cellno, String email) {
        this.fullname = fullname;
        this.cellno = cellno;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCellno() {
        return cellno;
    }

    public void setCellno(String cellno) {
        this.cellno = cellno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString(){
        return "User { " + "Fullname: " + fullname + ", Cellno: " + cellno + ", Email: " + email + "}";
    }
}
