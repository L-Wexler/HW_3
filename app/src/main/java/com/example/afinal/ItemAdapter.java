package com.example.afinal;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<Item> items;

    public ItemAdapter(){
        items = new ArrayList<>();
    }

    public ItemAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.item_view,parent,false);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.name_item.setText(item.getName());
        holder.brand_name.setText(item.getBrand());
        holder.color.setBackgroundColor(android.graphics.Color.parseColor(item.getColor()));
        holder.image_item.setImageResource(item.getImage());
        holder.price_item.setText(item.getPrice());




    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
