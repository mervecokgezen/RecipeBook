package com.examples.vestel.recipebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFoodActivity extends AppCompatActivity {

    EditText edt_foodname, edt_foodmetarials, edt_cooking;
    String foodname, foodmetarials, cooking, currentby;
    Button btn_addfood;
    TextView gorecipes;


    DatabaseReference databaseReference, dbRef;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    Button btn_recipe,  btn_profil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        edt_foodname        = (EditText)findViewById(R.id.edt_foodname);
        edt_foodmetarials   = (EditText)findViewById(R.id.edt_foodmetarials);
        edt_cooking         = (EditText)findViewById(R.id.edt_cooking);
        btn_addfood         = (Button)findViewById(R.id.btn_addfood);
        //gorecipes           = (TextView)findViewById(R.id.tv_backreceips);

        databaseReference = FirebaseDatabase.getInstance().getReference("Recipes");
        dbRef = FirebaseDatabase.getInstance().getReference("Users");
        firebaseAuth      = FirebaseAuth.getInstance();
        firebaseUser      = firebaseAuth.getCurrentUser();

        btn_addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                foodname        = edt_foodname.getText().toString();
                foodmetarials   = edt_foodmetarials.getText().toString();
                cooking         = edt_cooking.getText().toString();
                currentby = "Ekleyen : Merve";

                AddFood(foodname, foodmetarials, cooking);

                Toast.makeText(AddFoodActivity.this,"Add Succes!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(AddFoodActivity.this, RecipesActivity.class));

            }
        });

        btn_recipe = (Button)findViewById(R.id.btn_recipe);
        btn_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddFoodActivity.this, RecipesActivity.class));
            }
        });
        btn_profil = (Button)findViewById(R.id.btn_profil);
        btn_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddFoodActivity.this, ProfilActivity.class));
            }
        });
    }

    public void AddFood(String food_name, String food_metarials, String food_make){


        String ContactsIDFromServer = databaseReference.push().getKey();
        String userid = firebaseAuth.getCurrentUser().getUid();

        String supplementary = dbRef.child(userid).child("namesurname").toString();

        Food food = new Food(food_name, food_metarials, food_make, ContactsIDFromServer, supplementary);
        databaseReference.child(userid).child(ContactsIDFromServer).setValue(food);

    }
}
