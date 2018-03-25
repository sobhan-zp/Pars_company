package com.pars.company.Model;

/**
 * Created by Maziar on 11/25/2017.
 */

public class Users {
    private String id;
    private String email;
    private String company_name;
    private String fullname;
    private String mobile;
    private String tel;
    private String address;

    public Users() {
    }

    public Users(String id, String email, String company_name, String fullname, String mobile, String tel, String address) {
        this.id = id;
        this.email = email;
        this.company_name = company_name;
        this.fullname = fullname;
        this.mobile = mobile;
        this.tel = tel;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
