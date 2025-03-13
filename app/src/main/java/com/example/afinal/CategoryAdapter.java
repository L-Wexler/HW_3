package com.example.afinal;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<Category> Categories;

    public CategoryAdapter(){
        Categories = new ArrayList<>();
        Categories.add(new Category("Bags", R.drawable.bag));
        Categories.add(new Category("Clothing", R.drawable.trouser));
        Categories.add(new Category("Sunglass", R.drawable.eyeglasses_));
        Categories.add(new Category("Shoes", R.drawable.shoes));
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.category_view,parent,false);
        CategoryViewHolder viewHolder = new CategoryViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        Category category_view = Categories.get(position);
        holder.Category.setText(category_view.getCategory());
        holder.Image_Category.setImageResource(category_view.getImage());
        holder.CategoryViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Rv_Category.class);
                Bundle bundle = new Bundle();
                bundle.putString("selectedCategory", category_view.getCategory());
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return Categories.size();
    }
}
