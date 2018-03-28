package com.examples.vestel.recipebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllRecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Food> list_food;
    private DatabaseReference databaseReference ;
    private FirebaseDatabase firebaseDatabase ;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String ContactIDFromServer;
    private Food f;

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

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list_food = new ArrayList<Food>();
                for(DataSnapshot ds :dataSnapshot.getChildren())
                {
                    f = new Food(ds.child("food_name").getValue().toString(),ds.child("food_items").getValue().toString(),ds.child("cooking").getValue().toString(), ds.child("currentby").getValue().toString(), ds.child("supplementary").getValue().toString());
                    list_food.add(f);


                }
                recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AllRecipesActivity.this);

                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                linearLayoutManager.scrollToPosition(0);

                recyclerView.setLayoutManager(linearLayoutManager);

                SimpleRecyclerAdapter adapter_items = new SimpleRecyclerAdapter(list_food, new Food.CustomItemClickListener(){

                    @Override
                    public void onItemClick(View v, int position) {
                        //Log.d("position", "Tıklanan Pozisyon:" + position);
                        Food food = list_food.get(position);
                        Intent i  = new Intent(AllRecipesActivity.this, FoodDetailActivity.class);

                        i.putExtra("tv_foodname", food.getFood_name());
                        i.putExtra("tv_fooditems", food.getFood_items());
                        i.putExtra("tv_cooking", food.getCooking());
                        i.putExtra("tv_currentby", food.getCurrentby());
                        i.putExtra("supplementary", food.getSupplementary());
                        startActivity(i);

                    }
                });
                recyclerView.setAdapter(adapter_items);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
