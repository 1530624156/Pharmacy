package com.mavis.entity;

import java.util.Date;

public class Inventory {

  private long id;
  private long mid;
  private long addnum;
  private String note;
  private String type;
  private String time;

  public Inventory(long id, long mid, long addnum, String note, String type, String time) {
    this.id = id;
    this.mid = mid;
    this.addnum = addnum;
    this.note = note;
    this.type = type;
    this.time = time;
  }

  public Inventory() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getMid() {
    return mid;
  }

  public void setMid(long mid) {
    this.mid = mid;
  }


  public long getAddnum() {
    return addnum;
  }

  public void setAddnum(long addnum) {
    this.addnum = addnum;
  }


  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  @Override
  public String toString() {
    return "Inventory{" +
            "id=" + id +
            ", mid=" + mid +
            ", addnum=" + addnum +
            ", note='" + note + '\'' +
            ", type='" + type + '\'' +
            ", time=" + time +
            '}';
  }
}
