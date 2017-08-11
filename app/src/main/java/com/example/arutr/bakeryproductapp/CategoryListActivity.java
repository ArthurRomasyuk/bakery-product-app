package com.example.arutr.bakeryproductapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.arutr.bakeryproductapp.adapters.CategoryListRecyclerAdapter;
import com.example.arutr.bakeryproductapp.listeners.OnCategoryRecyclerItemClickListener;
import com.example.arutr.bakeryproductapp.models.Product;
import com.example.arutr.bakeryproductapp.models.ProductBase;
import com.example.arutr.bakeryproductapp.utils.Database;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryListActivity extends AppCompatActivity {

    private Database db;
    private RecyclerView.Adapter adapter;

    @BindView(R.id.rv_categories)
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        ButterKnife.bind(this);

        ArrayList<Product> products = new ProductBase().getProducts();
        db = new Database(this);
        db.open();
        Cursor allData = db.getAllData();
        boolean b = !allData.moveToFirst();
        if (b) {
            db.addRecList(products);
        }
        final Context context = this;
        adapter = new CategoryListRecyclerAdapter(db.getAllCategories(), new OnCategoryRecyclerItemClickListener() {
            @Override
            public void onItemClick(String category) {
                Intent intent = new Intent(context, ProductListActivity.class);
                startActivity(intent.putExtra("category", category));
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        db.open();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        db.close();
    }

}
