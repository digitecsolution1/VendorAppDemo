package com.example.vendorapp;

/**
 * Created by ayanc on 1/18/2018.
 */

public class CouponDetails {
    String scnm,scdesc,scsts,scval_from,scval_to,sc_percentage,sc_catg;

    public CouponDetails(){}

    public CouponDetails(String s1, String s2, String s3, String s4, String s5, String s6, String s7){
        setScnm(s1);
        setScdesc(s2);
        setScsts(s3);
        setScval_from(s4);
        setScval_to(s5);
        setSc_percentage(s6);
        setSc_catg(s7);
    }

    public String getScnm() {
        return scnm;
    }

    public void setScnm(String scnm) {
        this.scnm = scnm;
    }

    public String getScdesc() {
        return scdesc;
    }

    public void setScdesc(String scdesc) {
        this.scdesc = scdesc;
    }

    public String getScsts() {
        return scsts;
    }

    public void setScsts(String scsts) {
        this.scsts = scsts;
    }

    public String getScval_from() {
        return scval_from;
    }

    public void setScval_from(String scval_from) {
        this.scval_from = scval_from;
    }

    public String getScval_to() {
        return scval_to;
    }

    public void setScval_to(String scval_to) {
        this.scval_to = scval_to;
    }

    public String getSc_percentage() {
        return sc_percentage;
    }

    public void setSc_percentage(String sc_percentage) {
        this.sc_percentage = sc_percentage;
    }

    public String getSc_catg() {
        return sc_catg;
    }

    public void setSc_catg(String sc_catg) {
        this.sc_catg = sc_catg;
    }
}
