package com.example.afinal;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    public CardView CategoryViewLayout;

    public TextView Category;

    public ImageView Image_Category;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        this.CategoryViewLayout = itemView.findViewById(R.id.CategoryViewLayout);
        this.Category = itemView.findViewById(R.id.Category);
        this.Image_Category = itemView.findViewById(R.id.ImageCategory);

    }
}
