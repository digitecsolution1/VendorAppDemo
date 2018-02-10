package com.example.vendorapp;

import android.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.juliomarcos.ImageViewPopUpHelper;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;
import java.util.List;

public class Promotion extends AppCompatActivity {

    private BottomBar bottomBar;

    private Button coupons,scanqr,ins_info,upd_info;

    DatabaseReference myRef,ref;
    ListView cstmrlistVw;
    ProgressBar pbar;

    int selId;
    TextView t1,t2,t3,t4,t5;

    RelativeLayout rlot;
    LinearLayout lot,cust_lot;

    FirebaseAuth mAuth;

    ImageView i1,i2,i3;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.tab_account:
                    Toast.makeText(getApplicationContext(),"Account Selected",Toast.LENGTH_SHORT).show();
                    lot.setVisibility(View.INVISIBLE);
                    cust_lot.setVisibility(View.INVISIBLE);
                    rlot.setVisibility(View.VISIBLE);
                    return true;
                case R.id.tab_details:
                    Toast.makeText(getApplicationContext(),"Customer Details",Toast.LENGTH_SHORT).show();
                  //  cust_dtl();
                    lot.setVisibility(View.INVISIBLE);
                    rlot.setVisibility(View.INVISIBLE);
                    cust_lot.setVisibility(View.VISIBLE);
                    return true;
                case R.id.tab_coupons:
                    Toast.makeText(getApplicationContext(),"Coupons",Toast.LENGTH_SHORT).show();
                    lot.setVisibility(View.VISIBLE);
                    rlot.setVisibility(View.INVISIBLE);
                    cust_lot.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.promotion);

        Intent intent=getIntent();
        selId=intent.getIntExtra("ID",0);


        mAuth=FirebaseAuth.getInstance();

        coupons=(Button)findViewById(R.id.coupons);
        scanqr=(Button)findViewById(R.id.scanqr);

        ins_info=findViewById(R.id.saveButton);
        upd_info=findViewById(R.id.updButton);

         rlot=findViewById(R.id.account_dtl);
         lot=findViewById(R.id.prmt);
         cust_lot=findViewById(R.id.cust_dtl);


        t1=findViewById(R.id.vendorname);
        t2=findViewById(R.id.shpnm);
        t3=findViewById(R.id.shptyp);
        t4=findViewById(R.id.vendoradd);
        t5=findViewById(R.id.vendorphno);

        pbar=findViewById(R.id.progressBar);

        i1=findViewById(R.id.displaypicture);
        i2=findViewById(R.id.displaypicture2);
        i3=findViewById(R.id.displaypicture3);
/*
        bottomBar=BottomBar.attach(this,savedInstanceState);
        bottomBar.setItems(R.menu.bottombars_menu);
        bottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(int menuItemId) {
                if (menuItemId==R.id.tab_account){
                    Toast.makeText(getApplicationContext(),"Accounts",Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(Promotion.this,VendorAccount.class));
                    lot.setVisibility(View.INVISIBLE);
                    cust_lot.setVisibility(View.INVISIBLE);
                    rlot.setVisibility(View.VISIBLE);
                } else if (menuItemId==R.id.tab_details){
                    Toast.makeText(getApplicationContext(),"Customer Details",Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(getApplicationContext(),ShowCustomer.class));
                    lot.setVisibility(View.INVISIBLE);
                    rlot.setVisibility(View.INVISIBLE);
                    cust_lot.setVisibility(View.VISIBLE);
                } else if (menuItemId==R.id.tab_coupons) {
                    Toast.makeText(getApplicationContext(),"Coupons",Toast.LENGTH_SHORT).show();
                    lot.setVisibility(View.VISIBLE);
                    rlot.setVisibility(View.INVISIBLE);
                    cust_lot.setVisibility(View.INVISIBLE);
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
*/

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        switch (selId){
            case 0:
                navigation.setSelectedItemId(R.id.tab_account);
                break;
            case 1:
                navigation.setSelectedItemId(R.id.tab_details);
                break;
            case 2:
                navigation.setSelectedItemId(R.id.tab_coupons);
                break;
        }


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

        ins_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Promotion.this,VendorAccount.class));
            }
        });

        upd_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"WORK IN PROGRESS",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Promotion.this,updateVendorInfo.class));
            }
        });


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("VendorDtl").child("vendor1").child("CUSTOMERS");
//HERE REPLACE "vendor1" WITH CURRENT VENDOR ID*************************************
        cstmrlistVw=(ListView)findViewById(R.id.cstmrListView);

        ref=database.getReference().child("VendorDtl").child("vendor1");
    }


    public void cust_dtl(){
        Toast.makeText(getApplicationContext()," calling method Customer Details",Toast.LENGTH_SHORT).show();
        lot.setVisibility(View.INVISIBLE);
        rlot.setVisibility(View.INVISIBLE);
        cust_lot.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onStart() {
        super.onStart();

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                 try {
                     ShopDtl sdtl = ds.getValue(ShopDtl.class);

                     t1.setText(sdtl.getVendorname());
                     t2.setText(sdtl.getShopnm());
                     t3.setText(sdtl.getShoptyp());
                     t4.setText(sdtl.getVendoraddr());
                     t5.setText(sdtl.getVendorphno());

                     Glide.with(getApplicationContext()).load(sdtl.getPicuri1()).into(i1);
                     Glide.with(getApplicationContext()).load(sdtl.getPicuri2()).into(i2);
                     Glide.with(getApplicationContext()).load(sdtl.getPicuri3()).into(i3);

                     i1.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                         //    ImageViewPopUpHelper.enablePopUpOnClick(Promotion.this,i1);
                             i1.buildDrawingCache();
                             Bitmap bitmap = i1.getDrawingCache();

                             Intent intent = new Intent(Promotion.this, FullImg.class);
                             Toast.makeText(getApplicationContext(),"Opening in full view",Toast.LENGTH_SHORT).show();
                             intent.putExtra("BitmapImage", bitmap);
                             startActivity(intent);
                         }
                     });

                     i2.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                             //    ImageViewPopUpHelper.enablePopUpOnClick(Promotion.this,i1);
                             i2.buildDrawingCache();
                             Bitmap bitmap = i2.getDrawingCache();

                             Intent intent = new Intent(Promotion.this, FullImg.class);
                             Toast.makeText(getApplicationContext(),"Opening in full view",Toast.LENGTH_SHORT).show();
                             intent.putExtra("BitmapImage", bitmap);
                             startActivity(intent);
                         }
                     });

                     i3.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                             //    ImageViewPopUpHelper.enablePopUpOnClick(Promotion.this,i1);
                             i3.buildDrawingCache();
                             Bitmap bitmap = i3.getDrawingCache();

                             Intent intent = new Intent(Promotion.this, FullImg.class);
                             Toast.makeText(getApplicationContext(),"Opening in full view",Toast.LENGTH_SHORT).show();
                             intent.putExtra("BitmapImage", bitmap);
                             startActivity(intent);
                         }
                     });



                     pbar.setVisibility(View.INVISIBLE);
                  //   ins_info.setVisibility(View.INVISIBLE);
                   //  upd_info.setVisibility(View.VISIBLE);
                 }
                 catch (Exception e){
                    // ins_info.setVisibility(View.VISIBLE);
                    // upd_info.setVisibility(View.INVISIBLE);
                 }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //attaching value event listener
        myRef.addValueEventListener(new ValueEventListener() {
            List<Customer> crlist = new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                crlist.clear();

                //iterating through all the nodes
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.i("valueeventDb","tHIS IS CALLED");
                    //getting artist
                    Customer coups= ds.getValue(Customer.class);
                    //adding artist to the list
                    crlist.add(coups);

                    Log.i("InsideValueDataChange",coups.getName());

                }

                //creating adapter
                CstmrAdp cpnAdp = new CstmrAdp(Promotion.this, crlist);
                //attaching adapter to the listview
                cstmrlistVw.setAdapter(cpnAdp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"EROrrr Fetching value ... ",Toast.LENGTH_SHORT).show();
            }
        });



    }



/*    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        bottomBar.onSaveInstanceState(outState);
    }
*/
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

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    public void Logout(View view) {


        AlertDialog alertDialog = new AlertDialog.Builder(Promotion.this).create();
        alertDialog.setTitle("Do Tou Really Want to Log out?!");
        alertDialog.setMessage("Hey There,!!"+"\n"+"Logging out will take you to login screen Again?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        FirebaseAuth.getInstance().signOut();    //LOGGING OUT

                        Toast.makeText(getApplicationContext(),"Logged OUT !! ",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Promotion.this, Login.class));
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
        alertDialog.show();

    /*
    **extra testing measure taken to ensure logging out success
      try {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            Toast.makeText(getApplicationContext(),"Welcome Back !! "+currentUser.getEmail(),Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Logged OUT !! ",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Promotion.this, Login.class));
        }
        */
    }

}
