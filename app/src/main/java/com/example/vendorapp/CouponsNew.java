package com.example.vendorapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CouponsNew extends AppCompatActivity {
    DatabaseReference ref;
    public static EditText cnm, cdesc, csts, cval_from, cval_to, c_percentage, c_catg;
    public static String scnm, scdesc, scsts, scval_from, scval_to, sc_percentage, sc_catg;
    public static String temp;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons_new);
        context = getApplicationContext();
        cval_from = (EditText) findViewById(R.id.validity1);
        cval_to = (EditText) findViewById(R.id.validity2);

        ref= FirebaseDatabase.getInstance().getReference().child("VendorDtl");

        cval_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFromDatePickerDailog(v);
            }
        });

        cval_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToDatePickerDialog(v);
            }
        });
    }

    public void showFromDatePickerDailog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void showToDatePickerDialog(View v) {
        DialogFragment newFragment = new ToDatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }


    public void saveCoupon(View view) {

        cnm = findViewById(R.id.nameCoup);
        cdesc = findViewById(R.id.descCoup);
        csts = findViewById(R.id.statsCoup);
        c_percentage = findViewById(R.id.valueCoup);
        c_catg = findViewById(R.id.categCoup);
        scnm = cnm.getText().toString();
        scdesc = cdesc.getText().toString();
        scsts = csts.getText().toString();
        scval_from = cval_from.getText().toString();
        scval_to = cval_to.getText().toString();
        sc_percentage = c_percentage.getText().toString();
        sc_catg = c_catg.getText().toString();
/*
        ref.child("vendor1").child("Coupons").child(scnm).child("CouponName").setValue(scnm);
        ref.child("vendor1").child("Coupons").child(scnm).child("CouponDesc").setValue(scdesc);
        ref.child("vendor1").child("Coupons").child(scnm).child("CouponStatus").setValue(scsts);
        ref.child("vendor1").child("Coupons").child(scnm).child("CouponvalidFrom").setValue(scval_from);
        ref.child("vendor1").child("Coupons").child(scnm).child("CouponvalidTo").setValue(scval_to);
        ref.child("vendor1").child("Coupons").child(scnm).child("CouponValue").setValue(sc_percentage);
        ref.child("vendor1").child("Coupons").child(scnm).child("CouponCategory").setValue(sc_catg);
*/

        scval_from="tst1";scval_to="tst2";
        CouponDetails coupDtls = new CouponDetails(scnm, scdesc, scsts, scval_from, scval_to, sc_percentage, sc_catg);
       try {
           ref.child("vendor1").child("Coupons").child(scnm).setValue(coupDtls);
           Toast.makeText(getApplicationContext(),"Coupon saved successfully",Toast.LENGTH_SHORT).show();
           startActivity(new Intent(getApplicationContext(),Coupons.class));
       }
       catch (Exception e){
           Toast.makeText(getApplicationContext(),"Error saving couppon",Toast.LENGTH_SHORT).show();
       }


    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH)+1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog;
            datePickerDialog = new DatePickerDialog(getActivity(), this, year,
                    month, day);
            return datePickerDialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            int selectedmonth=month+1;
            cval_from.setText(day + "/" + selectedmonth + "/" + year);
        }
    }

    public static class ToDatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        // Calendar startDateCalendar=Calendar.getInstance();
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            /*String getfromdate = cval_from.getText().toString().trim();
            String getfrom[] = getfromdate.split("/");
            int year, month, day;
            year = Integer.parseInt(getfrom[2]);
            month = Integer.parseInt(getfrom[1]);
            day = Integer.parseInt(getfrom[0]);
            final Calendar c = Calendar.getInstance();
            c.set(year, month, day + 1);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
            return datePickerDialog;*/

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH)+1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog;
            datePickerDialog = new DatePickerDialog(getActivity(), this, year,
                    month, day);
            return datePickerDialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            int selectedmonth=month+1;
            cval_to.setText(day + "/" + selectedmonth + "/" + year);
        }
    }
}
