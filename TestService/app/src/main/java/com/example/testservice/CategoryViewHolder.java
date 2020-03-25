package com.example.testservice;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements android.view.View.OnClickListener {

    public TextView txtUserName,txtlat,txtlon;


    public CategoryViewHolder(@NonNull android.view.View itemView) {
        super(itemView);
        txtUserName = (TextView)itemView.findViewById(R.id.username);
        txtlat = (TextView)itemView.findViewById(R.id.lat);
        txtlon = (TextView)itemView.findViewById(R.id.lon);
    }

    @Override
    public void onClick(View v) {

    }
}
