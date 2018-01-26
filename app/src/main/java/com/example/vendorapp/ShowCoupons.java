package com.example.vendorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowCoupons extends AppCompatActivity {
    private static final String TAG ="showCoupons" ;
    private CouponAdp coupAdap;
    private ChildEventListener cLsn;
    String uid;
    DatabaseReference myRef;
    ListView couplist;
   // List<CouponDetails> cpnlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_coupons);

        //Toast.makeText(getApplicationContext(),"ShowCoupons called",Toast.LENGTH_LONG).show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("VendorDtl").child("vendor1").child("Coupons");
//HERE REPLACE "vendor1" WITH CURRENT VENDOR ID*************************************
        couplist=(ListView)findViewById(R.id.coupListView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
  myRef.addValueEventListener(new ValueEventListener() {
      List<CouponDetails> cpnlist = new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                cpnlist.clear();

                //iterating through all the nodes
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.i("valueeventDb","tHIS IS CALLED");
                    //getting artist
                    CouponDetails coups= ds.getValue(CouponDetails.class);
                    //adding artist to the list
                    cpnlist.add(coups);

                    Log.i("InsideValueDataChange",coups.getScnm());
                    String cnm=coups.getScnm();
                    String cdsc=coups.getScdesc();
                    String csts=coups.getScsts();
                    String cvfrm=coups.getScval_from();
                    String cvto=coups.getScval_to();
                    String cctg=coups.getSc_catg();
                    String cval=coups.getSc_percentage();
                   // Toast.makeText(getApplicationContext(),"Shop name "+snm+"\n"+"Shop type "+typ+"\n"+"Shop owner "+onm,Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Coupon Nmae : "+cnm,Toast.LENGTH_SHORT).show();


                }

                //creating adapter
                CouponAdp cpnAdp = new CouponAdp(ShowCoupons.this, cpnlist);
                //attaching adapter to the listview
                couplist.setAdapter(cpnAdp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"EROrrr Fetching value ... ",Toast.LENGTH_SHORT).show();
            }
        });



    }
}
