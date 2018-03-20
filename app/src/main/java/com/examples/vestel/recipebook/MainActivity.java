package com.examples.vestel.recipebook;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText edtmail, edtpass;
    TextView tvcreateac;
    Button btnlogin;

    private String mail, pass;

    FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;

    private PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PowerManager powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE,"Test");
        wakeLock.acquire();

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        edtmail    = (EditText)findViewById(R.id.edtmail);
        edtpass    = (EditText)findViewById(R.id.edtsifre);
        btnlogin   = (Button)findViewById(R.id.btnlogin);
        tvcreateac = (TextView)findViewById(R.id.tvcreateac);

        tvcreateac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateAcActivity.class));
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail = edtmail.getText().toString();
                pass = edtpass.getText().toString();

                if(mail.isEmpty() || pass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Mail veya Şifre alanı boş olamaz!..", Toast.LENGTH_LONG).show();
                }else{
                    LoginFunc();
                }
            }
        });

        if (firebaseUser != null) {
           startActivity(new Intent(MainActivity.this, RecipesActivity.class));
        } else {
            Toast.makeText(MainActivity.this, "Kullanıcı girişi yapınız!..", Toast.LENGTH_LONG).show();
        }


    }

    public void LoginFunc(){
        mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, RecipesActivity.class));
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
