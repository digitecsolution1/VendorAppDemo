package com.example.vendorapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Coupons extends AppCompatActivity {


    private Button createnew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupons_creation);

        //Toast.makeText(getApplicationContext(),"Coupons called",Toast.LENGTH_LONG).show();
        createnew=(Button)findViewById(R.id.createCoup);
        createnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Coupons.this,CouponsNew.class));
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.tab_account:
                    Toast.makeText(getApplicationContext(),
                            "Accounts selected",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Coupons.this,VendorAccount.class));
                    return true;
                case R.id.tab_coupons:
                    Toast.makeText(getApplicationContext(),
                            "Coupons selected",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Coupons.this,ShowCoupons.class));
                    return true;
                case R.id.tab_details:
                    Toast.makeText(getApplicationContext(),
                            "Customer selected",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Coupons.this,ShowCustomer.class));
                    return true;
            }
            return false;
        }
    };

}
