package com.mavis.entity;


public class Type {

  private long tid;
  private String tname;
  private String note;

  public Type(long tid, String tname, String note) {
    this.tid = tid;
    this.tname = tname;
    this.note = note;
  }

  public Type() {
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public long getTid() {
    return tid;
  }

  public void setTid(long tid) {
    this.tid = tid;
  }


  public String getTname() {
    return tname;
  }

  public void setTname(String tname) {
    this.tname = tname;
  }

  @Override
  public String toString() {
    return "Type{" +
            "tid=" + tid +
            ", tname='" + tname + '\'' +
            ", note='" + note + '\'' +
            '}';
  }
}
