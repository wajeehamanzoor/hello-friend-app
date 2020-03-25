package com.example.testservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class View extends AppCompatActivity {


    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<Modell, CategoryViewHolder> recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //Firebase init
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Modell");

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadData();
    }

    private void loadData()
    {
        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<Modell>()
                        .setQuery(databaseReference,Modell.class)
                        .build();

        recyclerAdapter = new FirebaseRecyclerAdapter<Modell, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder holder, int position, @NonNull  Modell model) {

                holder.txtUserName.setText(model.getName());
                holder.txtlat.setText(model.getLat());
                holder.txtlon.setText(model.getLon());

            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                android.view.View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_view,viewGroup,false);
                return new CategoryViewHolder(view);
            }
        };
        recyclerAdapter.notifyDataSetChanged();
        recyclerAdapter.startListening();
        recyclerView.setAdapter(recyclerAdapter);
    }
}
