package com.example.vendorapp;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class QRScan extends AppCompatActivity {
    private static final String LOG_TAG ="MainQrScnner" ;
    SurfaceView cameraView;
    TextView barcodeInfo;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);
        Intent intent=getIntent();
        String usr=intent.getStringExtra("user");
        //Toast.makeText(getApplicationContext(),"Welcome..  "+usr,Toast.LENGTH_SHORT).show();

       // configure_button();

        Toast.makeText(getApplicationContext(),"QRScan called",Toast.LENGTH_LONG).show();
        cameraView = (SurfaceView) findViewById(R.id.camera_view);
        barcodeInfo = (TextView) findViewById(R.id.code_info);

        barcodeDetector =
                new BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.QR_CODE)
                        .build();

        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(QRScan.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException ie) {
                    Log.e("CAMERA SOURCE", ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();


                if (barcodes.size() != 0) {
                    barcodeInfo.post(new Runnable() {    // Use the post method of the TextView
                        public void run() {
                            int typ=barcodes.valueAt(0).valueFormat;
                            Barcode code = barcodes.valueAt(0);

                            switch (typ) {
                                case Barcode.CONTACT_INFO:
                                    Log.i(LOG_TAG, "Contact Info "+code.contactInfo.title);
                                    break;
                                case Barcode.EMAIL:
                                    Log.i(LOG_TAG, "Email "+code.email.address);
                                    break;
                                case Barcode.ISBN:
                                    Log.i(LOG_TAG, code.rawValue);
                                    break;
                                case Barcode.PHONE:
                                    Log.i(LOG_TAG, "Phone "+code.phone.number);
                                    break;
                                case Barcode.PRODUCT:
                                    Log.i(LOG_TAG, code.rawValue);
                                    break;
                                case Barcode.SMS:
                                    Log.i(LOG_TAG, "sms "+code.sms.message);
                                    break;
                                case Barcode.TEXT:
                                    Log.i(LOG_TAG, "simple text "+code.rawValue);
                                    break;
                                case Barcode.URL:
                                    Log.i(LOG_TAG, "url: " + code.url.url);
                                    break;
                                case Barcode.WIFI:
                                    Log.i(LOG_TAG, code.wifi.ssid);
                                    break;
                                case Barcode.GEO:
                                    Log.i(LOG_TAG, "location "+code.geoPoint.lat + ":" + code.geoPoint.lng);
                                    break;
                                case Barcode.CALENDAR_EVENT:
                                    Log.i(LOG_TAG, code.calendarEvent.description);
                                    break;
                                case Barcode.DRIVER_LICENSE:
                                    Log.i(LOG_TAG, code.driverLicense.licenseNumber);
                                    break;
                                default:
                                    Log.i(LOG_TAG, code.rawValue);
                                    break;
                            }

                            barcodeInfo.setText(    // Update the TextView
                                    barcodes.valueAt(0).displayValue
                            );

                        }
                    });

                    Intent intent=new Intent(getApplicationContext(),qrinfo.class);
                    intent.putExtra("qrvalue",barcodes.valueAt(0).displayValue.toString());
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(getApplicationContext(),Promotion.class));
    }
}
