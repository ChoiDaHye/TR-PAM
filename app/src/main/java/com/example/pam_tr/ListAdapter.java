package com.example.pam_tr;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListHolder> {
    Context context;
    ArrayList<DataModel> model;

    public ListAdapter(Context context, ArrayList<DataModel> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int i) {
        holder.mTITLE.setText(model.get(i).getName());
        holder.mDESC.setText(model.get(i).getDesc_sm());
        Picasso.get().load(model.get(i).getImg()).into(holder.mIMG);

        holder.setListItemClick(new ListItemClick() {
            @Override
            public void onListItemClick(View v, int position) {
                String idx = model.get(position).getIndeks();
                String nama3 = model.get(position).getName();
                String lat = String.valueOf(model.get(position).getLatitude());
                String lon = String.valueOf(model.get(position).getLongitude());
                Intent i = new Intent(context, MapActivity.class);
                i.putExtra("index", idx);
                i.putExtra("lat",lat);
                i.putExtra("lon",lon);
                i.putExtra("nama3",nama3);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }
}
