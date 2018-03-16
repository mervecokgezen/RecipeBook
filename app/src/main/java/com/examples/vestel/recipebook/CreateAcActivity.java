package com.examples.vestel.recipebook;

import android.content.Intent;
import android.support.annotation.MainThread;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAcActivity extends AppCompatActivity {

    private String namesurname, email, pass;
    EditText edtnamesurname, edtemail, edtpass;
    TextView backlogin;
    Button signup;
    String uuserid;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ac);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        edtnamesurname =(EditText)findViewById(R.id.edtnamesurname);
        edtemail       = (EditText)findViewById(R.id.edtemail);
        edtpass        = (EditText)findViewById(R.id.edtpass);

        signup      = (Button)findViewById(R.id.btnsignup);
        backlogin   = (TextView)findViewById(R.id.tvbacktologin);
        uuserid = "";

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namesurname = edtnamesurname.getText().toString();
                email       = edtemail.getText().toString();
                pass        = edtpass.getText().toString();

                if(email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill in the required fields!",Toast.LENGTH_SHORT).show();
                }else{
                    UserRegister();
                    AddUser(namesurname, email, pass);
                }


            }
        });

        backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateAcActivity.this, MainActivity.class));
            }
        });
    }

    public void UserRegister(){
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(CreateAcActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(CreateAcActivity.this, RecipesActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void AddUser(String anamesurname, String amail, String apass ){

        databaseReference = FirebaseDatabase.getInstance().getReference();
        //String userid = firebaseAuth.getCurrentUser().getUid();
        String ContactsIDFromServer = databaseReference.push().getKey();
        User user = new User(anamesurname, amail, apass,ContactsIDFromServer);
        databaseReference.child("Users").child(ContactsIDFromServer).setValue(user);


    }
}
