package com.example.pam_tr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ListAdapter listAdapter;

    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.rv);
        int spanCount = 2;
        int spacing = 30;
        boolean includeEdge = true;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

//        RecyclerView.LayoutManager column = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(column);

        listAdapter = new ListAdapter(this, getMyList());
        recyclerView.setAdapter(listAdapter);

    }

    private ArrayList<DataModel> getMyList() {
        final ArrayList<DataModel> models = new ArrayList<>();

        reff = FirebaseDatabase.getInstance().getReference().child("data");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    DataModel model = new DataModel();
                    model.setIndeks(Integer.toString(i));
                    model.setName(ds.child("name").getValue().toString());
                    model.setDesc_sm(ds.child("desc_sm").getValue().toString());
                    model.setDesc_lg(ds.child("desc_lg").getValue().toString());
                    model.setImg(ds.child("img").getValue().toString());
                    model.setLatitude(ds.child("latitude").getValue(Double.class));
                    model.setLongitude(ds.child("longitude").getValue(Double.class));
                    models.add(model);
                    i++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return models;
    }
}
