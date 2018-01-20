package com.example.vendorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class qrinfo extends AppCompatActivity {
    DatabaseReference ref;
    String[] words;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrinfo);

        Intent intent=getIntent();
        String info=intent.getStringExtra("qrvalue");


         words = info.split(",");

        TextView nm,phn,ct;
        nm=findViewById(R.id.textView);
        phn=findViewById(R.id.textView2);
        ct=findViewById(R.id.textView3);

     nm.setText(words[0]);
        phn.setText(words[1]);
        ct.setText(words[2]);


         ref = FirebaseDatabase.getInstance().getReference("VendorDtl");

    }

    public void saveCustomer(View view) {
     /*   ref.child("vendor1").child("CUSTOMERS").child(words[0]).child("Name").setValue(words[0]);
        ref.child("vendor1").child("CUSTOMERS").child(words[0]).child("Phone").setValue(words[1]);
        ref.child("vendor1").child("CUSTOMERS").child(words[0]).child("City").setValue(words[2]);
        */
        //here id of a vendor will be used (instead of hardcoded 'vendor1')
        //here id/name of a custommer will be used (instead of hardcoded 'c1')
        Customer cst=new Customer(words[0],words[1],words[2]);
        ref.child("vendor1").child("CUSTOMERS").child(words[0]).setValue(cst);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(),QRScan.class));
    }
}
