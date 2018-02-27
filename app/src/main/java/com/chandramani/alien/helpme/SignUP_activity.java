package com.chandramani.alien.helpme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUP_activity extends AppCompatActivity {
     private FirebaseAuth auth;
    private EditText mEmailView;
    private EditText mPasswordView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("HelpMe: Sign Up");

        auth = FirebaseAuth.getInstance();

        mEmailView = (EditText) findViewById(R.id.emailsingnup);
        mPasswordView = (EditText) findViewById(R.id.passwordsignup);
        progressBar = (ProgressBar) findViewById(R.id.signu_progress);

        Button mEmailSignUpButton = (Button) findViewById(R.id.email_sign_up_button);
        auth = FirebaseAuth.getInstance();
        mEmailSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmailView.getText().toString().trim();
                String password = mPasswordView.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

             progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUP_activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                       //         Toast.makeText(SignUP_activity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignUP_activity.this, "Authentication failed.",        //+ task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(),"Registration successful",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(SignUP_activity.this, MainActivity.class));
                                }
                            }
                        });

            }
        });
    }
    public void LonginTextclick(View v){
        TextView tt = (TextView) v;
        Intent  lgninn = new Intent(SignUP_activity.this , LoginActivity.class);
        startActivity(lgninn);
    }
    public void onSkipTextClick(View v)
    {
        TextView tt1 = (TextView) v;
        Intent skipp = new Intent(SignUP_activity.this,MainActivity.class);
        startActivity(skipp);
    }


}
