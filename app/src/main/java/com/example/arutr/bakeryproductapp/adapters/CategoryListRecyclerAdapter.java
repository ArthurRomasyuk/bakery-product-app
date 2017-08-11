package com.example.arutr.bakeryproductapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arutr.bakeryproductapp.R;
import com.example.arutr.bakeryproductapp.listeners.OnCategoryRecyclerItemClickListener;
import com.example.arutr.bakeryproductapp.utils.Consts;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Artur Romasiuk
 */

public class CategoryListRecyclerAdapter extends RecyclerView.Adapter<CategoryListRecyclerAdapter.ViewHolder> {

    private ArrayList<String> allCategories;
    private OnCategoryRecyclerItemClickListener listener;


    public CategoryListRecyclerAdapter() {
    }

    public CategoryListRecyclerAdapter(ArrayList<String> allCategories, OnCategoryRecyclerItemClickListener listener) {
        this.allCategories = allCategories;
        this.listener = listener;
    }

    @Override
    public CategoryListRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false);
        final CategoryListRecyclerAdapter.ViewHolder viewHolder = new CategoryListRecyclerAdapter.ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(allCategories.get(viewHolder.getAdapterPosition()));
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryListRecyclerAdapter.ViewHolder holder, int position) {
        holder.categoryName.setText(allCategories.get(position));

    }

    @Override
    public int getItemCount() {
        return allCategories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_category_name)
        TextView categoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.e("CategoryRecyclerAdapter", "finding views!");
            ButterKnife.bind(this, itemView);
        }
    }
}
