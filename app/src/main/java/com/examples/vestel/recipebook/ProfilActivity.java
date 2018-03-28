package com.examples.vestel.recipebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilActivity extends AppCompatActivity {

    private Button btn_addfood, btn_recipe;
    private TextView tv_kullaniciadi;
    private String kullanici_adi;

    private DatabaseReference databaseReference ;
    private FirebaseDatabase firebaseDatabase ;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        firebaseAuth      = FirebaseAuth.getInstance();
        firebaseUser      = firebaseAuth.getCurrentUser();
        String UserID     = firebaseAuth.getCurrentUser().getUid();

        firebaseDatabase    = FirebaseDatabase.getInstance();
        databaseReference   = firebaseDatabase.getReference().child("Users");



        btn_addfood = (Button)findViewById(R.id.btn_addfood);
        btn_recipe  = (Button)findViewById(R.id.btn_recipe);

        btn_addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfilActivity.this, AddFoodActivity.class));
            }
        });
        btn_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfilActivity.this, RecipesActivity.class));
            }
        });

        tv_kullaniciadi = (TextView)findViewById(R.id.tv_kullaniciadi);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    kullanici_adi = ds.child("namesurname").getValue().toString();
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        tv_kullaniciadi.setText(kullanici_adi);


    }
}
