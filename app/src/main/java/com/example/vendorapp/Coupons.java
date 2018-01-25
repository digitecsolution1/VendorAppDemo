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

    private BottomBar bottomBar;
    private Button createnew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupons_creation);

        createnew=(Button)findViewById(R.id.createCoup);

        bottomBar=BottomBar.attach(this,savedInstanceState);
        bottomBar.setItems(R.menu.bottombars_menu);
        bottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(int menuItemId) {
                if (menuItemId==R.id.tab_account){
                    Toast.makeText(getApplicationContext(),"Accounts",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Coupons.this,VendorAccount.class));
                } else if (menuItemId==R.id.tab_details){
                    Toast.makeText(getApplicationContext(),"Customer Details",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),ShowCustomer.class));
                } else if (menuItemId==R.id.tab_coupons) {
                    Toast.makeText(getApplicationContext(),"Coupons",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Coupons.this,ShowCoupons.class));
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

        createnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Coupons.this,CouponsNew.class));
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
}
