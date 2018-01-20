package com.example.vendorapp;

/**
 * Created by ayanc on 1/20/2018.
 */

public class Customer {
    String name,phone,city;
    public Customer(){}
    public  Customer(String s1,String s2,String s3){
        setName(s1);
        setPhone(s2);
        setCity(s3);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
