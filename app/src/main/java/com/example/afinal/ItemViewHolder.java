package com.example.afinal;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public ImageView image_item;
    public TextView name_item;
    public TextView brand_name;
    public TextView price_item;
    public View color;
    public CardView card;



    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.image_item = itemView.findViewById(R.id.image_item);
        this.name_item = itemView.findViewById(R.id.name_item);
        this.brand_name = itemView.findViewById(R.id.name_brand);
        this.price_item = itemView.findViewById(R.id.price_item);
        this.color = itemView.findViewById(R.id.color_of_item);
        this.card = itemView.findViewById(R.id.card);



    }
}
