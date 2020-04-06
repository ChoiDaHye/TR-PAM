package com.example.pam_tr;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView mIMG;
    TextView mTITLE, mDESC;
    ListItemClick itemClick;

    ListHolder(@NonNull View itemView) {
        super(itemView);

        this.mIMG = itemView.findViewById(R.id.img_row);
        this.mTITLE = itemView.findViewById(R.id.txt_title_row);
        this.mDESC = itemView.findViewById(R.id.txt_desc_sm_row);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClick.onListItemClick(v, getLayoutPosition());
    }

    public void setListItemClick(ListItemClick ic){
        this.itemClick = ic;
    }
}
