package com.example.arutr.bakeryproductapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arutr.bakeryproductapp.R;
import com.example.arutr.bakeryproductapp.listeners.OnProductRecyclerItemClickListener;
import com.example.arutr.bakeryproductapp.models.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Artur Romasiuk
 */

public class ProductListRecyclerAdapter extends RecyclerView.Adapter<ProductListRecyclerAdapter.ViewHolder> {

    private ArrayList<Product> productsByCategory;
    private OnProductRecyclerItemClickListener listener;


    public ProductListRecyclerAdapter() {
    }

    public ProductListRecyclerAdapter(ArrayList<Product> productsByCategory, OnProductRecyclerItemClickListener listener) {
        this.productsByCategory = productsByCategory;
        this.listener = listener;
    }

    @Override
    public ProductListRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
        final ProductListRecyclerAdapter.ViewHolder viewHolder = new ProductListRecyclerAdapter.ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(productsByCategory.get(viewHolder.getAdapterPosition()).getName());
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductListRecyclerAdapter.ViewHolder holder, int position) {
        holder.productName.setText(productsByCategory.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return productsByCategory.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_product_name)
        TextView productName;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.e("CategoryRecyclerAdapter", "finding views!");
            ButterKnife.bind(this, itemView);
        }
    }
}
