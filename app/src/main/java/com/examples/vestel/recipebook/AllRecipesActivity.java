package com.examples.vestel.recipebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AllRecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Food> list_food;
    private DatabaseReference databaseReference ;
    private FirebaseDatabase firebaseDatabase ;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String ContactIDFromServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipes);

        firebaseAuth      = FirebaseAuth.getInstance();
        firebaseUser      = firebaseAuth.getCurrentUser();
        String userid     = firebaseAuth.getCurrentUser().getUid();

        firebaseDatabase    = FirebaseDatabase.getInstance();
        databaseReference   = firebaseDatabase.getReference().child("Recipes").child(userid);

        ContactIDFromServer = databaseReference.push().getKey();

    }
}
