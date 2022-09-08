package com.mavis.entity;


public class Orders {

  private String oid;
  private String uname;
  private String mid;
  private String time;

  public Orders(String oid, String uname, String mid, String time) {
    this.oid = oid;
    this.uname = uname;
    this.mid = mid;
    this.time = time;
  }

  public Orders() {
  }

  public String getOid() {
    return oid;
  }

  public void setOid(String oid) {
    this.oid = oid;
  }


  public String getUname() {
    return uname;
  }

  public void setUname(String uname) {
    this.uname = uname;
  }


  public String getMid() {
    return mid;
  }

  public void setMid(String mid) {
    this.mid = mid;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  @Override
  public String toString() {
    return "Order{" +
            "oid=" + oid +
            ", uname='" + uname + '\'' +
            ", mid=" + mid +
            ", time='" + time + '\'' +
            '}';
  }
}
