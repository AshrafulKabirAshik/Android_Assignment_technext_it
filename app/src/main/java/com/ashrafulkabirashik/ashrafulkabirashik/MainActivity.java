package com.ashrafulkabirashik.ashrafulkabirashik;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashrafulkabirashik.ashrafulkabirashik.adapters.RecyclerAdapter;
import com.ashrafulkabirashik.ashrafulkabirashik.adapters.RecyclerAdapterRoom;
import com.ashrafulkabirashik.ashrafulkabirashik.models.Blog;
import com.ashrafulkabirashik.ashrafulkabirashik.models.RoomDB.RoomDB;
import com.ashrafulkabirashik.ashrafulkabirashik.models.RoomDB.RoomModels;
import com.ashrafulkabirashik.ashrafulkabirashik.viewmodels.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    List<Blog> blogList;
    MainActivityViewModel viewModel;
    TextView resultMassage, status;
    ProgressBar progressBar;
    FloatingActionButton addFab;

    private List<RoomModels> items = new ArrayList<>();
    private RoomDB database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        resultMassage = findViewById(R.id.resultMassage);
        progressBar = findViewById(R.id.progress_circular);
        addFab = findViewById(R.id.addFab);
        status = findViewById(R.id.status);

        database = RoomDB.getInstance(this);

        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter(this, blogList);
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.getBlogListObserver().observe(this, new Observer<List<Blog>>() {
            @Override
            public void onChanged(List<Blog> blogs) {
                if (blogs != null) {
                    progressBar.setVisibility(View.GONE);
                    blogList = blogs;
                    adapter.setBlogList(blogs);
                    status.setText("Online");
                } else {
                    progressBar.setVisibility(View.GONE);
                    status.setText("Offline");
                    addFab.setVisibility(View.VISIBLE);
                    if (!database.roomDao().getAll().isEmpty()) {
                        items.clear();
                        items.addAll(database.roomDao().getAll());
                        RecyclerAdapterRoom adapter = new RecyclerAdapterRoom(MainActivity.this, items);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    } else {
                        resultMassage.setVisibility(View.VISIBLE);
                    }

                    addFab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            viewModel.addInformation(MainActivity.this);
                            items.clear();
                            items.addAll(database.roomDao().getAll());
                            RecyclerAdapterRoom adapter = new RecyclerAdapterRoom(MainActivity.this, items);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });

        viewModel.makeApiCall(this);

    }
}