package com.example.pam_tr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Frag_2 extends Fragment {
    DatabaseReference reff;
    public Frag_2(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        MapActivity ma = (MapActivity) getActivity();
//        String data = ma.getmydata();
        //String myStr = this.getArguments().getString("my_key");
        Bundle b3 = this.getArguments();
        String ids = b3.getString("id");
        load0(ids);
        return inflater.inflate(R.layout.frag_det, container, false);
    }


    public void load0(String ab) {
        reff = FirebaseDatabase.getInstance().getReference().child("data").child(ab);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TextView a = (TextView)getView().findViewById(R.id.title0);
                TextView b = (TextView)getView().findViewById(R.id.ds0);
                TextView c = (TextView)getView().findViewById(R.id.dsp0);
                ImageView im = (ImageView)getView().findViewById(R.id.im0);

                String name = dataSnapshot.child("name").getValue().toString();
                String img = dataSnapshot.child("img").getValue().toString();
                String mini = dataSnapshot.child("desc_sm").getValue().toString();
                String pjg = dataSnapshot.child("desc_lg").getValue().toString();


                a.setText(name);
                b.setText(mini);
                c.setText(pjg);
                Picasso.get().load(img).into(im);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
