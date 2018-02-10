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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class VendorAccount extends AppCompatActivity {

    private ImageView displayPic;
    private EditText name,addr,shpnm,shptyp,phno;
    private Button save;
    ImageView image1,image2,image3;
    String vendorname,vendoraddr,shopnm,shoptyp,vendorphno;
    FirebaseDatabase fdb;
    DatabaseReference ref;

    String pic1,pic2,pic3;

    ProgressBar pbar;

    StorageReference photoref,photoref2,photoref3;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    private static final int RC_PHOTO_PICKER =  2,RC_PHOTO_PICKER2 =  3,RC_PHOTO_PICKER3 =  4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_account);

    /*    displayPic=(ImageView)findViewById(R.id.displaypicture);
        image1=(Button) findViewById(R.id.image1);
        image2=(Button) findViewById(R.id.image2);
        image3=(Button) findViewById(R.id.image3);
*/
        save=(Button)findViewById(R.id.saveButton);
        name=(EditText)findViewById(R.id.vendorname);
        shpnm=(EditText)findViewById(R.id.shpnm);
        shptyp=(EditText)findViewById(R.id.shptyp);
        addr=(EditText)findViewById(R.id.vendoradd);
        phno=(EditText)findViewById(R.id.vendorphno);


        image1=findViewById(R.id.imageView1);
        image2=findViewById(R.id.imageView2);
        image3=findViewById(R.id.imageView3);



        pbar=findViewById(R.id.progressBar);

        ref=FirebaseDatabase.getInstance().getReference().child("VendorDtl").child("vendor1");
        //************replace vendor1 with signed in vendor id

        firebaseStorage= FirebaseStorage.getInstance();

        storageReference=firebaseStorage.getReference().child("shop_pics");


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vendorname=name.getText().toString();
                vendoraddr=addr.getText().toString();
                shopnm=shpnm.getText().toString();
                shoptyp=shptyp.getText().toString();
                vendorphno=phno.getText().toString();

                ShopDtl sdtl=new ShopDtl(vendorname,shopnm,shoptyp,vendoraddr,vendorphno,pic1,pic2,pic3);
                ref.child("ShopDtl").setValue(sdtl);
                Toast.makeText(getApplicationContext(),"Saved in database",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(),Promotion.class));
            }
        });
    }
/*
    public View.OnClickListener choosePic=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            final int ACTIVITY_SELECT_IMAGE = 1234;
            startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
        }
    };
*/
    public void pick1(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
        pbar.setVisibility(View.VISIBLE);

    }
    public void pick2(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);

        startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER2);
        pbar.setVisibility(View.VISIBLE);

    }
    public void pick3(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER3);
        pbar.setVisibility(View.VISIBLE);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

     /*   switch(requestCode) {
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
*/
            if (requestCode == RC_PHOTO_PICKER && resultCode == RESULT_OK){
                final Uri selectedImageUri=data.getData();
                final Bitmap yourSelectedImage = BitmapFactory.decodeFile(selectedImageUri.getPath());

               // pictxtvw1.setText(selectedImageUri.getLastPathSegment());
                photoref=storageReference.child(selectedImageUri.getLastPathSegment());

                photoref.putFile(selectedImageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downldUri=taskSnapshot.getDownloadUrl();
                        pic1=downldUri.toString();
                     //   pictxtvw1.setText(selectedImageUri.toString());
                        image1.setImageURI(selectedImageUri);


                        pbar.setVisibility(View.INVISIBLE);

                    }
                });
            }

            if(requestCode==RC_PHOTO_PICKER2 && resultCode==RESULT_OK){
                final Uri imguri=data.getData();
              //  pictxtvw2.setText(imguri.getLastPathSegment());
                photoref2=storageReference.child(imguri.getLastPathSegment());

                photoref2.putFile(imguri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri duri=taskSnapshot.getDownloadUrl();
                        pic2=duri.toString();
                     //   pictxtvw2.setText(duri.toString());
                        image2.setImageURI(imguri);


                        pbar.setVisibility(View.INVISIBLE);

                    }
                }) ;
            }

            if(requestCode==RC_PHOTO_PICKER3 && resultCode==RESULT_OK){
                final Uri imuri=data.getData();
             //   pictxtvw3.setText(imuri.getLastPathSegment());
                photoref3=storageReference.child(imuri.getLastPathSegment());

                photoref3.putFile(imuri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri iuri=taskSnapshot.getDownloadUrl();
                        pic3=iuri.toString();
                      //  pictxtvw3.setText(iuri.toString());
                        image3.setImageURI(imuri);


                        pbar.setVisibility(View.INVISIBLE);

                    }
                });
            }

        }

}
