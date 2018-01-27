package com.example.vendorapp;

/**
 * Created by ayanc on 1/26/2018.
 */

public class ShopDtl {
    private String vendorname,vendoraddr,shopnm,shoptyp,vendorphno;

    public ShopDtl(){}
    public ShopDtl(String s1,String s2,String s3,String s4,String s5){
        setVendorname(s1);
        setShopnm(s2);
        setShoptyp(s3);
        setVendoraddr(s4);
        setVendorphno(s5);
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
