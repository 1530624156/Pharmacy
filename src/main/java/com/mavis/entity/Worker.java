package com.mavis.entity;


public class Worker {

  private long wid;
  private String wpassword;
  private String wname;
  private String wtel;

  public Worker(long wid, String wpassword, String wname, String wtel) {
    this.wid = wid;
    this.wpassword = wpassword;
    this.wname = wname;
    this.wtel = wtel;
  }

  public Worker() {
  }

  public long getWid() {
    return wid;
  }

  public void setWid(long wid) {
    this.wid = wid;
  }


  public String getWpassword() {
    return wpassword;
  }

  public void setWpassword(String wpassword) {
    this.wpassword = wpassword;
  }


  public String getWname() {
    return wname;
  }

  public void setWname(String wname) {
    this.wname = wname;
  }


  public String getWtel() {
    return wtel;
  }

  public void setWtel(String wtel) {
    this.wtel = wtel;
  }

  @Override
  public String toString() {
    return "Worker{" +
            "wid=" + wid +
            ", wpassword='" + wpassword + '\'' +
            ", wname='" + wname + '\'' +
            ", wtel='" + wtel + '\'' +
            '}';
  }
}
