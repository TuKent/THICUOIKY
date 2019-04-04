package com.example.spendingmanagementapplication;

public class UserObject {
    private int IDUser;
    private String UserName;
    private String PassWord;
    private int SoDu;
    private int SoDuHienTai;
    private int TongSoDu;

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    // check đăng nhập
    private boolean Status;

public UserObject(int IDUser, String userName, String passWord, int soDu, int soDuHienTai, int tongSoDu) {
        this.IDUser = IDUser;
        UserName = userName;
        PassWord = passWord;
        SoDu = soDu;
        SoDuHienTai = soDuHienTai;
        TongSoDu = tongSoDu;
    }


    public UserObject() {

    }

    public int getIDUser() {
        return IDUser;
    }

    public void setIDUser(int IDUser) {
        this.IDUser = IDUser;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public int getSoDu() {
        return SoDu;
    }

    public void setSoDu(int soDu) {
        SoDu = soDu;
    }
    public int getSoDuHienTai() {
        return SoDuHienTai;
    }

    public void setSoDuHienTai(int soDuHienTai) {
        SoDuHienTai = soDuHienTai;
    }

    public int getTongSoDu() {
        return TongSoDu;
    }

    public void setTongSoDu(int tongSoDu) {
        TongSoDu = tongSoDu;
    }

}
