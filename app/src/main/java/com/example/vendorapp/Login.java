package com.example.vendorapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private static final String TAG = "Login";
    private FirebaseAuth mAuth;
    private Button loginButton,signupButton;
    EditText eml,pass;
    String email,password;

    int f=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isFirstTime()) {
           f=1;
        }

       setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();




        loginButton=(Button)findViewById(R.id.btn_login);
    //    signupButton=(Button)findViewById(R.id.newuser);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eml = findViewById(R.id.edt_email);
                pass = findViewById(R.id.edt_password);

                email = eml.getText().toString();
                password = pass.getText().toString();

                if (email.equals("") || pass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please FillUp all the fields !!", Toast.LENGTH_SHORT).show();
                } else {
                    // startActivity(new Intent(Login.this,Promotion.class));
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        if (f == 1)
                                            startActivity(new Intent(Login.this, VendorAccount.class));
                                        else
                                            startActivity(new Intent(Login.this, Promotion.class));
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        //updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        pass.setText("");
                                        Toast.makeText(Login.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                    // ...
                                }
                            });
                }
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        try {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                //user is logged
                FirebaseUser currentUser = mAuth.getCurrentUser();
                Toast.makeText(getApplicationContext(),"Welcome Back !! "+currentUser.getEmail(),Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this, Promotion.class));
                //updateUI(currentUser);
            } else {
                //prompt logg to the user
            }


        }
        catch (Exception e){

        }

    }

    private boolean isFirstTime()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }
}
