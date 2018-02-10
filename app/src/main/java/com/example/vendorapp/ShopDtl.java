package com.example.vendorapp;

/**
 * Created by ayanc on 1/26/2018.
 */

public class ShopDtl {
    private String vendorname,vendoraddr,shopnm,shoptyp,vendorphno,picuri1,picuri2,picuri3;

    public ShopDtl(){}
    public ShopDtl(String s1,String s2,String s3,String s4,String s5,String s6,String s7,String s8){
        setVendorname(s1);
        setShopnm(s2);
        setShoptyp(s3);
        setVendoraddr(s4);
        setVendorphno(s5);
        setPicuri1(s6);
        setPicuri2(s7);
        setPicuri3(s8);
    }

    public String getPicuri1() {
        return picuri1;
    }

    public void setPicuri1(String picuri1) {
        this.picuri1 = picuri1;
    }

    public String getPicuri2() {
        return picuri2;
    }

    public void setPicuri2(String picuri2) {
        this.picuri2 = picuri2;
    }

    public String getPicuri3() {
        return picuri3;
    }

    public void setPicuri3(String picuri3) {
        this.picuri3 = picuri3;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getVendoraddr() {
        return vendoraddr;
    }

    public void setVendoraddr(String vendoraddr) {
        this.vendoraddr = vendoraddr;
    }

    public String getShopnm() {
        return shopnm;
    }

    public void setShopnm(String shopnm) {
        this.shopnm = shopnm;
    }

    public String getShoptyp() {
        return shoptyp;
    }

    public void setShoptyp(String shoptyp) {
        this.shoptyp = shoptyp;
    }

    public String getVendorphno() {
        return vendorphno;
    }

    public void setVendorphno(String vendorphno) {
        this.vendorphno = vendorphno;
    }
}
