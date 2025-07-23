package com.example.afinal;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class FullDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_detail_activity);

        Item item = (Item) getIntent().getSerializableExtra("item");

        if (item != null) {

            TextView name = findViewById(R.id.name_item);
            TextView brand = findViewById(R.id.name_brand);
            TextView price = findViewById(R.id.price_item);
            ImageView image = findViewById(R.id.image_item);
            View color = findViewById(R.id.color_of_item);

            name.setText(item.getName());
            brand.setText(item.getBrand());
            price.setText(item.getPrice());

            Glide.with(this).load(item.getImage()).into(image);

            try {
                color.setBackgroundColor(Color.parseColor(item.getColor()));
            } catch (Exception e) {
                color.setBackgroundColor(Color.GRAY);
            }
        }
    }
}
