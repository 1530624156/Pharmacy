package com.mavis.entity;


public class Medicine {

  private long mid;
  private String mname;
  private String type;
  private String note;
  private String symptom;
  private String used;
  private String mimg;
  private String taboo;
  private Double cost;
  private Double price;
  private Double profit;
  private long number;
  private String facturer;

  public Medicine() {
  }

  public Medicine(long mid, String mname, String type, String note, String symptom, String used, String mimg, String taboo, Double cost, Double price, Double profit, long number, String facturer) {
    this.mid = mid;
    this.mname = mname;
    this.type = type;
    this.note = note;
    this.symptom = symptom;
    this.used = used;
    this.mimg = mimg;
    this.taboo = taboo;
    this.cost = cost;
    this.price = price;
    this.profit = profit;
    this.number = number;
    this.facturer = facturer;
  }

  public long getMid() {
    return mid;
  }

  public void setMid(long mid) {
    this.mid = mid;
  }

  public String getMname() {
    return mname;
  }

  public void setMname(String mname) {
    this.mname = mname;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getSymptom() {
    return symptom;
  }

  public void setSymptom(String symptom) {
    this.symptom = symptom;
  }

  public String getUsed() {
    return used;
  }

  public void setUsed(String used) {
    this.used = used;
  }

  public String getMimg() {
    return mimg;
  }

  public void setMimg(String mimg) {
    this.mimg = mimg;
  }

  public String getTaboo() {
    return taboo;
  }

  public void setTaboo(String taboo) {
    this.taboo = taboo;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public long getNumber() {
    return number;
  }

  public void setNumber(long number) {
    this.number = number;
  }

  public Double getCost() {
    return cost;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }

  public Double getProfit() {
    return profit;
  }

  public void setProfit(Double profit) {
    this.profit = profit;
  }

  public String getFacturer() {
    return facturer;
  }

  public void setFacturer(String facturer) {
    this.facturer = facturer;
  }

  @Override
  public String toString() {
    return "Medicine{" +
            "mid=" + mid +
            ", mname='" + mname + '\'' +
            ", type='" + type + '\'' +
            ", note='" + note + '\'' +
            ", symptom='" + symptom + '\'' +
            ", used='" + used + '\'' +
            ", mimg='" + mimg + '\'' +
            ", taboo='" + taboo + '\'' +
            ", cost=" + cost +
            ", price=" + price +
            ", profit=" + profit +
            ", number=" + number +
            ", facturer='" + facturer + '\'' +
            '}';
  }
}
