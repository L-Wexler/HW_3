package com.example.afinal;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
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

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Item> items;

    private String SelectedCategory;

    public ItemAdapter(String selectedCategory) {
        items = new ArrayList<>();
        SelectedCategory = selectedCategory;
        db.collection("Items")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            items.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Item c = new Item(document.get("name").toString(),document.get("brand").toString(),document.get("color").toString(),document.get("category").toString(), document.get("image").toString(), document.get("price").toString());
                                if (c.getCategory().equals(selectedCategory)) {
                                    items.add(c);
                                }
                            }
                            notifyDataSetChanged();
                        }
                    }
                });
        db.collection("Items").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                items.clear();
                for (QueryDocumentSnapshot document : value) {
                    Item c = new Item(document.get("name").toString(),document.get("brand").toString(),document.get("color").toString(),document.get("category").toString(), document.get("image").toString(), document.get("price").toString());
                    if (c.getCategory().equals(selectedCategory)) {
                        items.add(c);
                    }
                }
                notifyDataSetChanged();
            }
        });


    }

    public ItemAdapter() {
        items = new ArrayList<>();

        db.collection("Items").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                items.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Item c = new Item(
                            document.get("name").toString(),
                            document.get("brand").toString(),
                            document.get("color").toString(),
                            document.get("category").toString(),
                            document.get("image").toString(),
                            document.get("price").toString()
                    );
                    items.add(c);
                }
                notifyDataSetChanged();
            }
        });
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
        //holder.image_item.setImageURI(Uri.parse(item.getImage()));
        Glide.with(holder.image_item).load(item.getImage()).into(holder.image_item);
        holder.price_item.setText(item.getPrice());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FullDetailActivity.class);
                intent.putExtra("item", item);
                v.getContext().startActivity(intent);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) v.getContext(),
                        holder.card,
                        "cardtransition"
                );
                v.getContext().startActivity(intent,options.toBundle());
            }
        });
    }






    @Override
    public int getItemCount() {
        return items.size();
    }
}