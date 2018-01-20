package com.example.vendorapp;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;
import java.util.List;

public class ShowCustomer extends AppCompatActivity {
    DatabaseReference myRef;
    ListView cstmrlistVw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_customer);




        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("VendorDtl").child("vendor1").child("CUSTOMERS");
//HERE REPLACE "vendor1" WITH CURRENT VENDOR ID*************************************
        cstmrlistVw=(ListView)findViewById(R.id.cstmrListView);
    }


    @Override
    protected void onStart() {
        super.onStart();
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
                CstmrAdp cpnAdp = new CstmrAdp(ShowCustomer.this, crlist);
                //attaching adapter to the listview
                cstmrlistVw.setAdapter(cpnAdp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"EROrrr Fetching value ... ",Toast.LENGTH_SHORT).show();
            }
        });



    }

}
