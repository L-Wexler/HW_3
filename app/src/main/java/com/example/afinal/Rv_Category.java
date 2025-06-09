package com.example.afinal;

import android.media.RouteListingPreference;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Rv_Category extends AppCompatActivity {

    private RecyclerView rv_item;
    private ItemAdapter adapter;
    private List<Item> filteredItems = new ArrayList<>();
    private String selectedCategory = "";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rv_category);

        rv_item = findViewById(R.id.CategoryViewLayout);
        rv_item.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv_item.setLayoutManager(layoutManager);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            selectedCategory = bundle.getString("selectedCategory", "");
        }
        loadItemsByCategory();

        //Glide.with(rv_item).load(item.getImage()).into(rv_item);
    }

    private void loadItemsByCategory() {
        db.collection("Items")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Toast.makeText(Rv_Category.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (value != null) {
                            filteredItems.clear();
                            for (QueryDocumentSnapshot doc : value) {
                                String name = doc.getString("Name");
                                String brand = doc.getString("Brand");
                                String color = doc.getString("Color");
                                String category = doc.getString("Category");
                                String image = doc.getString("Image");
                                String price = doc.getString("Price");
                                String id = doc.getString("ID");

                                if (category != null && category.equalsIgnoreCase(selectedCategory)) {
                                    Item item = new Item(name, brand, color, category, image, price, id);
                                    filteredItems.add(item);
                                }
                            }

                            adapter.notifyDataSetChanged();

                            if (filteredItems.isEmpty()) {
                                Toast.makeText(Rv_Category.this, "No items found in this category", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}



