package com.examples.vestel.recipebook;

import android.view.View;

/**
 * Created by vestel on 15.03.2018.
 */

public class Food {
    private String food_name;
    private String food_items;
    private String cooking;

    public String getCurrentby() {
        return currentby;
    }

    public void setCurrentby(String currentby) {
        this.currentby = currentby;
    }

    private String currentby;

    public Food(String food_name, String food_items, String cooking, String currentby) {
        this.food_name = food_name;
        this.food_items = food_items;
        this.cooking = cooking;
        this.currentby = currentby;
    }

    public String getFood_name() {

        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_items() {
        return food_items;
    }

    public void setFood_items(String food_items) {
        this.food_items = food_items;
    }

    public String getCooking() {
        return cooking;
    }

    public void setCooking(String cooking) {
        this.cooking = cooking;
    }

    public interface CustomItemClickListener {
        void onItemClick(View v, int position);
    }
}
