package com.example.vendorapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class updateVendorInfo extends AppCompatActivity {
    DatabaseReference myRef;
    TextView t1, t2, t3, t4, t5;
    String vendorname,vendoraddr,shopnm,shoptyp,vendorphno;
    private EditText name,addr,shpnm,shptyp,phno;

    String pic1,pic2,pic3;

    ImageView image1,image2,image3;

    StorageReference photoref,photoref2,photoref3;


    ProgressBar pbar;
    ProgressDialog pdialog;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    private static final int RC_PHOTO_PICKER =  2,RC_PHOTO_PICKER2 =  3,RC_PHOTO_PICKER3 =  4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_vendor_info);

        pdialog = new ProgressDialog(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("VendorDtl").child("vendor1");



        name=(EditText)findViewById(R.id.vendorname);
        shpnm=(EditText)findViewById(R.id.shpnm);
        shptyp=(EditText)findViewById(R.id.shptyp);
        addr=(EditText)findViewById(R.id.vendoradd);
        phno=(EditText)findViewById(R.id.vendorphno);

        pbar=findViewById(R.id.progressBar);

        image1=findViewById(R.id.imageView1);
        image2=findViewById(R.id.imageView2);
        image3=findViewById(R.id.imageView3);


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




                ShopDtl shpdtl=new ShopDtl(vendorname,shopnm,shoptyp,vendoraddr,vendorphno,pic1,pic2,pic3);
                myRef.child("ShopDtl").setValue(shpdtl);
                Toast.makeText(getApplicationContext(),"value updated",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Promotion.class));
            /*
                Toast.makeText(getApplicationContext(),t2.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), t5.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), t1.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), t3.getText().toString().trim(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), t4.getText().toString().trim(),Toast.LENGTH_SHORT).show();
            */

            //Toast.makeText(getApplicationContext(),"button clicked",Toast.LENGTH_SHORT).show();
            }
        });


        firebaseStorage= FirebaseStorage.getInstance();

        storageReference=firebaseStorage.getReference().child("shop_pics");

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

                   //     image1.setImageURI(Uri.parse(sdtl.getPicuri1()));
                   //     image2.setImageURI(Uri.parse(sdtl.getPicuri2()));
                   //     image3.setImageURI(Uri.parse(sdtl.getPicuri3()));

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void pick1(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);

     //   pbar.setVisibility(View.VISIBLE);


    }
    public void pick2(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER2);
      //  pbar.setVisibility(View.VISIBLE);

    }
    public void pick3(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER3);
      //  pbar.setVisibility(View.VISIBLE);

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

           pdialog.setMessage("UPLOADING...");
            pdialog.show();

            // pictxtvw1.setText(selectedImageUri.getLastPathSegment());
            photoref=storageReference.child(selectedImageUri.getLastPathSegment());

            photoref.putFile(selectedImageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downldUri=taskSnapshot.getDownloadUrl();
                    pic1=downldUri.toString();
                    //   pictxtvw1.setText(selectedImageUri.toString());
                    image1.setImageURI(selectedImageUri);



                    pdialog.dismiss();

                }
            });
        }

        if(requestCode==RC_PHOTO_PICKER2 && resultCode==RESULT_OK){
            final Uri imguri=data.getData();
            pdialog.setMessage("UPLOADING...");
            pdialog.show();
            //  pictxtvw2.setText(imguri.getLastPathSegment());
            photoref2=storageReference.child(imguri.getLastPathSegment());

            photoref2.putFile(imguri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri duri=taskSnapshot.getDownloadUrl();
                    pic2=duri.toString();
                    //   pictxtvw2.setText(duri.toString());
                    image2.setImageURI(imguri);


                    pdialog.dismiss();

                }
            }) ;
        }

        if(requestCode==RC_PHOTO_PICKER3 && resultCode==RESULT_OK){
            final Uri imuri=data.getData();
            pdialog.setMessage("UPLOADING...");
            pdialog.show();
            //   pictxtvw3.setText(imuri.getLastPathSegment());
            photoref3=storageReference.child(imuri.getLastPathSegment());

            photoref3.putFile(imuri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri iuri=taskSnapshot.getDownloadUrl();
                    pic3=iuri.toString();
                    //  pictxtvw3.setText(iuri.toString());
                    image3.setImageURI(imuri);


                    pdialog.dismiss();

                }
            });
        }

    }

}
