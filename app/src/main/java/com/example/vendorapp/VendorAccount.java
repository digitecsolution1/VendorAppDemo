package com.example.vendorapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VendorAccount extends AppCompatActivity {

    private ImageView displayPic;
    private EditText name,addr,shpnm,shptyp,phno;
    private Button save;
    private Button image1,image2,image3;
    String vendorname,vendoraddr,shopnm,shoptyp,vendorphno;
    FirebaseDatabase fdb;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_account);

        displayPic=(ImageView)findViewById(R.id.displaypicture);
        image1=(Button) findViewById(R.id.image1);
        image2=(Button) findViewById(R.id.image2);
        image3=(Button) findViewById(R.id.image3);

        save=(Button)findViewById(R.id.saveButton);
        name=(EditText)findViewById(R.id.vendorname);
        shpnm=(EditText)findViewById(R.id.shpnm);
        shptyp=(EditText)findViewById(R.id.shptyp);
        addr=(EditText)findViewById(R.id.vendoradd);
        phno=(EditText)findViewById(R.id.vendorphno);

        displayPic.setOnClickListener(choosePic);
        image1.setOnClickListener(choosePic);
        image2.setOnClickListener(choosePic);
        image3.setOnClickListener(choosePic);


        ref=FirebaseDatabase.getInstance().getReference().child("VendorDtl").child("vendor1");
        //************replace vendor1 with signed in vendor id

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vendorname=name.getText().toString();
                vendoraddr=addr.getText().toString();
                shopnm=shpnm.getText().toString();
                shoptyp=shptyp.getText().toString();
                vendorphno=phno.getText().toString();

                ShopDtl sdtl=new ShopDtl(vendorname,shopnm,shoptyp,vendoraddr,vendorphno);
                ref.child("ShopDtl").setValue(sdtl);
                Toast.makeText(getApplicationContext(),"Saved in database",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public View.OnClickListener choosePic=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            final int ACTIVITY_SELECT_IMAGE = 1234;
            startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
        }
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1234:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();
                    Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
                }
            }
    }
}
