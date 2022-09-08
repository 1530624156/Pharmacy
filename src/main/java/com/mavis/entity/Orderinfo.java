package com.mavis.entity;

import java.util.Arrays;
import java.util.List;

/**
 * @program: Pharmacy
 * @description: 详细订单类
 * @author: Mavis
 * @create: 2022-09-07 23:54
 **/

public class Orderinfo {
    private String oid;
    private String time;
    private String uname;
    private Double totle;

    public Orderinfo() {
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Double getTotle() {
        return totle;
    }

    public void setTotle(Double totle) {
        this.totle = totle;
    }

    @Override
    public String toString() {
        return "Orderinfo{" +
                "oid='" + oid + '\'' +
                ", time='" + time + '\'' +
                ", uname='" + uname + '\'' +
                ", totle=" + totle +
                '}';
    }
}