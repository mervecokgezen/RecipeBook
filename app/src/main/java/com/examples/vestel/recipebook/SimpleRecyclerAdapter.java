package com.examples.vestel.recipebook;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vestel on 15.03.2018.
 */

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_foodname;
        public TextView tv_currentby;
        public CardView card_view;


        public ViewHolder(View view) {
            super(view);

            card_view    = (CardView)view.findViewById(R.id.card_view);
            tv_foodname  = (TextView)view.findViewById(R.id.tv_foodname);
            tv_currentby = (TextView)view.findViewById(R.id.tv_currenuser);
        }
    }

    List<Food> list_food;
    Food.CustomItemClickListener listener;
    public SimpleRecyclerAdapter(List<Food> list_food, Food.CustomItemClickListener listener) {

        this.list_food = list_food;
        this.listener  = listener;
    }


    @Override
    public SimpleRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, view_holder.getPosition());
            }
        });

        return view_holder;
    }

    @Override
    public void onBindViewHolder(SimpleRecyclerAdapter.ViewHolder holder, int position) {
        holder.tv_foodname.setText(list_food.get(position).getFood_name());
        holder.tv_currentby.setText(list_food.get(position).getCurrentby());
    }

    @Override
    public int getItemCount() {
        return list_food.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
