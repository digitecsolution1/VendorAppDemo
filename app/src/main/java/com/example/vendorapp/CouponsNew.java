package com.example.vendorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CouponsNew extends AppCompatActivity {
    DatabaseReference ref;
    EditText cnm,cdesc,csts,cval_from,cval_to,c_percentage,c_catg;
    String scnm,scdesc,scsts,scval_from,scval_to,sc_percentage,sc_catg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons_new);

        ref= FirebaseDatabase.getInstance().getReference().child("VendorDtl");


    }

    public void saveCoupon(View view) {

        cnm=findViewById(R.id.nameCoup);
        cdesc=findViewById(R.id.descCoup);
        csts=findViewById(R.id.statsCoup);
        cval_from=findViewById(R.id.validity1);
        cval_to=findViewById(R.id.validity2);
        c_percentage=findViewById(R.id.valueCoup);
        c_catg=findViewById(R.id.categCoup);

        scnm=cnm.getText().toString();
        scdesc=cdesc.getText().toString();
        scsts=csts.getText().toString();
        scval_from=cval_from.getText().toString();
        scval_to=cval_to.getText().toString();
        sc_percentage=c_percentage.getText().toString();
        sc_catg=c_catg.getText().toString();
/*
        ref.child("vendor1").child("Coupons").child(scnm).child("CouponName").setValue(scnm);
        ref.child("vendor1").child("Coupons").child(scnm).child("CouponDesc").setValue(scdesc);
        ref.child("vendor1").child("Coupons").child(scnm).child("CouponStatus").setValue(scsts);
        ref.child("vendor1").child("Coupons").child(scnm).child("CouponvalidFrom").setValue(scval_from);
        ref.child("vendor1").child("Coupons").child(scnm).child("CouponvalidTo").setValue(scval_to);
        ref.child("vendor1").child("Coupons").child(scnm).child("CouponValue").setValue(sc_percentage);
        ref.child("vendor1").child("Coupons").child(scnm).child("CouponCategory").setValue(sc_catg);
*/


        CouponDetails coupDtls=new CouponDetails(scnm,scdesc,scsts,scval_from,scval_to,sc_percentage,sc_catg);
        ref.child("vendor1").child("Coupons").child(scnm).setValue(coupDtls);

    }
}
