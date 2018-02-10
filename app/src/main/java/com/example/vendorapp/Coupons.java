package com.example.vendorapp;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class Coupons extends AppCompatActivity {

    private Button createnew,cdetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupons_creation);

        createnew=(Button)findViewById(R.id.createCoup);
        cdetails=(Button)findViewById(R.id.coupdets);


        createnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Coupons.this,CouponsNew.class));
            }
        });
        cdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Details",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Coupons.this,ShowCoupons.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),Promotion.class);
        intent.putExtra("ID",2);
        startActivity(intent);
    }
}
