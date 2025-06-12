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

        adapter = new ItemAdapter(selectedCategory);
        rv_item.setAdapter(adapter);
    }
}