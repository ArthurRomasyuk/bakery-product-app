package com.example.arutr.bakeryproductapp;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.arutr.bakeryproductapp.adapters.ProductListRecyclerAdapter;
import com.example.arutr.bakeryproductapp.listeners.OnProductRecyclerItemClickListener;
import com.example.arutr.bakeryproductapp.models.Product;
import com.example.arutr.bakeryproductapp.utils.Database;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListActivity extends AppCompatActivity {

    private Database db;
    private RecyclerView.Adapter adapter;

    @BindView(R.id.rv_products)
    RecyclerView recycler;

    @BindView(R.id.add_product)
    FloatingActionButton addProductBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new Database(this);
        db.open();
        final Context context = this;
        ArrayList<Product> productsByCategory = db.getProductsByCategory(getIntent().getStringExtra("category"));
        adapter = new ProductListRecyclerAdapter(productsByCategory, new OnProductRecyclerItemClickListener() {
            @Override
            public void onItemClick(String productName) {
                Intent intent = new Intent(context, ProductInfoActivity.class);
                startActivity(intent.putExtra("productName",productName) );
            }
        });
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductCreationActivity.class);
                intent.putExtra("action", "create");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        db.open();
//        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        db.close();
    }
}
