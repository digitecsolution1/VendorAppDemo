package com.example.vendorapp;

import android.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Promotion extends AppCompatActivity {


    private Button coupons,scanqr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotion);

        //Toast.makeText(getApplicationContext(),"Promotion called",Toast.LENGTH_LONG).show();

        coupons=(Button)findViewById(R.id.coupons);
        scanqr=(Button)findViewById(R.id.scanqr);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        coupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Promotion.this,Coupons.class));
            }
        });

        scanqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"QR scan",Toast.LENGTH_SHORT).show();
                configure_button();
            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.tab_account:
                    Toast.makeText(getApplicationContext(),
                            "Accounts selected",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Promotion.this,VendorAccount.class));
                    return true;
                case R.id.tab_coupons:
                    Toast.makeText(getApplicationContext(),
                            "Coupons selected",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Promotion.this,ShowCoupons.class));
                    return true;
                case R.id.tab_details:
                    Toast.makeText(getApplicationContext(),
                            "Customer selected",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Promotion.this,ShowCustomer.class));
                    return true;
            }
            return false;
        }
    };


    private void configure_button() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA}
                        , 0);
            }
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED ){
            startActivity(new Intent(Promotion.this,QRScan.class));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 0){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED ){

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        configure_button();
                        startActivity(new Intent(Promotion.this,QRScan.class));
                    }
                }).start();
            }else{
                Toast.makeText(getApplicationContext(), "Access Denied ! Plesae Choose Camera Access Manually ", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog alertDialog = new AlertDialog.Builder(Promotion.this).create();
        alertDialog.setTitle("System Message!!");
        alertDialog.setMessage("Hey There,!!"+"\n"+"Do Tou Really Want to Leave?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        alertDialog.show();
    }
}
