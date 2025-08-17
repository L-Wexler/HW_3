package com.example.afinal;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Category> Categories;

    public CategoryAdapter(){
        Categories = new ArrayList<>();
        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Categories.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Category c = new Category(document.get("category").toString(), document.get("ID").toString(), document.get("image").toString());
                                Categories.add(c);

                            }
                            notifyDataSetChanged();
                        }
                    }
                });
        db.collection("Category").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                Categories.clear();
                for (QueryDocumentSnapshot document : value) {
                    Category c = new Category(document.get("category").toString(),  document.get("ID").toString(), document.get("image").toString());
                    Categories.add(c);
                }
                notifyDataSetChanged();
            }
        });
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
        Log.d("GlideCheck", "Image URL: " + category_view.getImage());
        holder.Category.setText(category_view.getCategory());
        //holder.Image_Category.setImageResource(category_view.getImage());
        Glide.with(holder.Image_Category).load(category_view.getImage()).into(holder.Image_Category);
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
