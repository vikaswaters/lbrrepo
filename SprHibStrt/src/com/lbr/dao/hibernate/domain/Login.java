package com.lbr.dao.hibernate.domain;

import java.io.Serializable;

public class Login implements Serializable {

    /** identifier field */
    private Integer id;

    /** persistent field */
    private String loginid;

    /** persistent field */
    private String password;
    
    /** persistent field */
    private String email;
    
    
    /** persistent field */
    private String address;
    
    
    /** persistent field */
    private int phno;

    /** full constructor */
    public Login(Integer id, String loginid, String password, String email, String address, int phno) {
        this.id = id;
        this.loginid = loginid;
        this.password = password;
        this.address = address;
        this.phno = phno;
        this.email = email;
    }

    /** default constructor */
    public Login() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginid() {
        return this.loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhno() {
		return phno;
	}

	public void setPhno(int phno) {
		this.phno = phno;
	}

}
