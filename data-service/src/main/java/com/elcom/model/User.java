package com.elcom.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "userID")
    @GeneratedValue
    private Long userID;

    @Basic(optional = false)
    @Column(name = "userName")
    private String userName;

    @Basic(optional = false)
    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Basic(optional = false)
    @Column(name = "roleName")
    private String roleName;

    @Basic(optional = false)
    @Column(name = "fullName")
    private String fullName;

    @Basic(optional = false)
    @Column(name = "email")
    private String email;

    @Basic(optional = false)
    @Column(name = "createAt")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createAt;

    public User() {
    }

    public User(Long userID, String userName, String password, String roleName, String fullName, String email, Date createAt) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.roleName = roleName;
        this.fullName = fullName;
        this.email = email;
        this.createAt = createAt;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
