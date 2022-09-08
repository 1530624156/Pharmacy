package com.mavis.entity;


public class User {

  private String username;
  private long uid;
  private String password;
  private String email;
  private String name;
  private String tel;

  public User(String username, long uid, String password, String email, String name, String tel) {
    this.username = username;
    this.uid = uid;
    this.password = password;
    this.email = email;
    this.name = name;
    this.tel = tel;
  }

  public User() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  @Override
  public String toString() {
    return "User{" +
            "username='" + username + '\'' +
            ", uid=" + uid +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", name='" + name + '\'' +
            ", tel='" + tel + '\'' +
            '}';
  }
}
