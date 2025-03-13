package com.example.afinal;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Rv_Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rv_category);

        RecyclerView rv_item = findViewById(R.id.CategoryViewLayout);
        rv_item.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv_item.setLayoutManager(layoutManager);


        Bundle bundle = getIntent().getExtras();
        String selectedCategory = "";
        if (bundle != null) {
            selectedCategory = bundle.getString("selectedCategory", "");
        }


        List<Item> filteredItems = new ArrayList<>();
        for (Item item : getAllItems()) {
            if (item.getCategory().equals(selectedCategory)) {
                filteredItems.add(item);
            }
        }

        ItemAdapter adapter = new ItemAdapter(filteredItems);
        rv_item.setAdapter(adapter);



    }


    private List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Kelly 28", "HERMES", "#42151C", "Bags", R.drawable.kelly, "13,900" +"$"));
        items.add(new Item("Birkin", "HERMES", "#2A160B", "Bags", R.drawable.birkin, "80,000" +"$"));
        items.add(new Item("Bisou", "JACQUEMUS", "#321D29" ,"Bags", R.drawable.bisou1, "1,300" +"$"));
        items.add(new Item("Loulou", "YSL", "#151515" ,"Bags", R.drawable.loulou, "3,000" +"$"));
        items.add(new Item("Dionysus", "GUCCI", "#1E1E1E" ,"Bags", R.drawable.dionysus1, "3,200" +"$"));
        items.add(new Item("Jodie mini", "BOTTEGA VENETA", "#4A3F3D" ,"Bags", R.drawable.minijodie, "2,800" +"$"));
        items.add(new Item("Le 5 à 7", "YSL", "#151515" ,"Bags", R.drawable.minile5a71, "1,700" +"$"));
        items.add(new Item("Suede", "MIUMIU", "#311D16" ,"Bags", R.drawable.suede1, "270" +"$"));
        items.add(new Item("MU 11ws", "MIUMIU", "#151515" ,"Sunglass", R.drawable.mu11ws, "200" +"$"));
        items.add(new Item("Leather loose crop jacket", "ST. AGNI", "#4A3B38" ,"Clothing", R.drawable.real_leather_loose_crop_jacket___brown_1, "750" +"$"));
        items.add(new Item("Shacklewell hopea", "VIVIENNE WEATWOOD", "#B9B9B9" ,"Watch", R.drawable.shacklewell_hopea, "290" +"$"));
        items.add(new Item("ZW leather jacket", "ZARA", "#AC6F39" ,"Clothing", R.drawable.zw_leather_jacket, "150" +"$"));
        items.add(new Item("Black glasses", "PRADA", "#151515" ,"Sunglass", R.drawable.prada_sunglass, "270" +"$"));
        items.add(new Item("Burgandy blazer", "ZARA", "#271F26" ,"Clothing", R.drawable.blazer, "150" +"$"));
        items.add(new Item("Sunglass", "CELINE", "#151515" ,"Sunglass", R.drawable.celin_sunglass, "300" +"$"));
        items.add(new Item("501 Jeans", "LEVI'S", "#7C8CA5" ,"Clothing", R.drawable.blu_jeans, "90" +"$"));
        items.add(new Item("Black loafer", "PRADA", "#151515" ,"Shoes", R.drawable.loafer, "250" +"$"));


        return items;
    }
}