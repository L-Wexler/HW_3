package com.example.afinal;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private RecyclerView rv_item;
    private ItemAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.profile_activity);


        TextView tv_fullname = findViewById(R.id.tv_fullname);
        ImageView profileImage = findViewById(R.id.profile_image);
        profileImage.setImageResource(R.drawable.blazer);


        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            String fullName = account.getDisplayName();
            tv_fullname.setText(fullName);
        }

        rv_item = findViewById(R.id.AllItemView);
        rv_item.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv_item.setLayoutManager(layoutManager);

        adapter = new ItemAdapter();
        rv_item.setAdapter(adapter);


    }
}
