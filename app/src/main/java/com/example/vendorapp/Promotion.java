package com.example.vendorapp;

import android.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class Promotion extends AppCompatActivity {

    private BottomBar bottomBar;

    private Button coupons,scanqr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotion);

        coupons=(Button)findViewById(R.id.coupons);
        scanqr=(Button)findViewById(R.id.scanqr);

        bottomBar=BottomBar.attach(this,savedInstanceState);
        bottomBar.setItems(R.menu.bottombars_menu);
        bottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(int menuItemId) {
                if (menuItemId==R.id.tab_account){
                    Toast.makeText(getApplicationContext(),"Accounts",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Promotion.this,VendorAccount.class));
                } else if (menuItemId==R.id.tab_details){
                    Toast.makeText(getApplicationContext(),"Customer Details",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),ShowCustomer.class));
                } else if (menuItemId==R.id.tab_coupons) {
                    Toast.makeText(getApplicationContext(),"Coupons",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onMenuTabReSelected(int menuItemId) {

            }
        });

        // Setting colors for different tabs when there's more than three of them.
        // You can set colors for tabs in three different ways as shown below.
        bottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
        bottomBar.mapColorForTab(1, 0xFF5D4037);
        bottomBar.mapColorForTab(2, "#7B1FA2");

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        bottomBar.onSaveInstanceState(outState);
    }

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
