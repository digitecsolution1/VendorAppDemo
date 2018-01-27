package com.example.vendorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateVendorInfo extends AppCompatActivity {
    DatabaseReference myRef;
    TextView t1, t2, t3, t4, t5;
    String vendorname,vendoraddr,shopnm,shoptyp,vendorphno;
    private EditText name,addr,shpnm,shptyp,phno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_vendor_info);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("VendorDtl").child("vendor1");



        name=(EditText)findViewById(R.id.vendorname);
        shpnm=(EditText)findViewById(R.id.shpnm);
        shptyp=(EditText)findViewById(R.id.shptyp);
        addr=(EditText)findViewById(R.id.vendoradd);
        phno=(EditText)findViewById(R.id.vendorphno);


        Button updBtn=findViewById(R.id.updinfo);
        updBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*  vendorname=t2.getText().toString().trim();
                vendoraddr=t5.getText().toString().trim();
                shopnm=t1.getText().toString().trim();
                shoptyp=t3.getText().toString().trim();
                vendorphno=t4.getText().toString().trim();

                vendorname="tstt";
                vendoraddr="tstt";
                shopnm="tstt";
                shoptyp="tstt";
                vendorphno="tstt";
*/
                vendorname=name.getText().toString();
                vendoraddr=addr.getText().toString();
                shopnm=shpnm.getText().toString();
                shoptyp=shptyp.getText().toString();
                vendorphno=phno.getText().toString();


                ShopDtl shpdtl=new ShopDtl(vendorname,shopnm,shoptyp,vendoraddr,vendorphno);
                myRef.child("ShopDtl").setValue(shpdtl);
                Toast.makeText(getApplicationContext(),"value updated",Toast.LENGTH_SHORT).show();
            /*
                Toast.makeText(getApplicationContext(),t2.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), t5.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), t1.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), t3.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), t4.getText().toString().trim(),Toast.LENGTH_SHORT).show();
            */

            Toast.makeText(getApplicationContext(),"button clicked",Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        ShopDtl sdtl = ds.getValue(ShopDtl.class);

                        name.setText(sdtl.getVendorname());
                        shpnm.setText(sdtl.getShopnm());
                        shptyp.setText(sdtl.getShoptyp());
                        addr.setText(sdtl.getVendoraddr());
                        phno.setText(sdtl.getVendorphno());


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
