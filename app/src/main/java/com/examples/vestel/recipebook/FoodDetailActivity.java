package com.examples.vestel.recipebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FoodDetailActivity extends AppCompatActivity {

    TextView tv_foodname, tv_fooditems, tv_cooking, tv_currentby;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    Button btn_recipe, btn_addfoodgo, btn_profil, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        firebaseAuth      = FirebaseAuth.getInstance();
        firebaseUser      = firebaseAuth.getCurrentUser();
        firebaseDatabase  = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Recipes");

        btn_delete   = (Button)findViewById(R.id.btn_delete);

        tv_foodname  = (TextView)findViewById(R.id.tv_foodname);
        tv_fooditems = (TextView)findViewById(R.id.tv_fooditems);
        tv_cooking   = (TextView)findViewById(R.id.tv_cooking);
        tv_currentby = (TextView)findViewById(R.id.tv_currentby);

        tv_foodname.setText(getIntent().getStringExtra("tv_foodname"));
        tv_fooditems.setText(getIntent().getStringExtra("tv_fooditems"));
        tv_cooking.setText(getIntent().getStringExtra("tv_cooking"));

        btn_recipe      = (Button)findViewById(R.id.btn_recipe);
        btn_addfoodgo   = (Button)findViewById(R.id.btn_addfood);
        btn_profil      = (Button)findViewById(R.id.btn_profil);

        btn_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FoodDetailActivity.this, RecipesActivity.class));
            }
        });

        btn_addfoodgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FoodDetailActivity.this, AddFoodActivity.class));
            }
        });

        btn_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FoodDetailActivity.this, ProfilActivity.class));
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String userid = firebaseAuth.getCurrentUser().getUid();
                        //String ContactsIDFromServer = databaseReference.getKey();// = Recipes

                        databaseReference = firebaseDatabase.getReference().child("Recipes").child(userid).child(getIntent().getStringExtra("tv_currentby"));
                        databaseReference.removeValue();

                        Toast.makeText(FoodDetailActivity.this, "Tarifi Sildiniz :)", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(FoodDetailActivity.this, RecipesActivity.class));
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
