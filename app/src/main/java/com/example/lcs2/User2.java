package com.example.lcs2;

class User2 {
    private String userID;
    private String fullName;
    private String phone;

    public User2() {
    }

    public User2(String userID, String fullName, String phone) {
        this.userID = userID;
        this.fullName = fullName;
        this.phone = phone;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
