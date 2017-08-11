package com.example.arutr.bakeryproductapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arutr.bakeryproductapp.models.Product;
import com.example.arutr.bakeryproductapp.utils.Database;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductInfoActivity extends AppCompatActivity {

    private Database db;
    private Product product;
    private Context context;

    @BindView(R.id.tv_product_info_description)
    TextView productDescription;

    @BindView(R.id.tv_product_info_weight)
    TextView productWeight;

    @BindView(R.id.tv_product_info_price)
    TextView productPrice;

    @BindView(R.id.tv_product_info_category)
    TextView productCategory;

    @BindView(R.id.iv_product_image)
    ImageView productImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        ButterKnife.bind(this);
        db = new Database(this);
        db.open();
        product = db.getProductByName(getIntent().getStringExtra("productName"));
        fillFields(product);
        context = this;
        String imageUrl = product.getImageUrl();
        if (TextUtils.isEmpty(imageUrl)) {
            imageUrl = "http://";
        }
        Picasso.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.reload)   // optional
                .error(R.drawable.alert)      // optional
                .resize(400, 400)                        // optional
                .into(productImage);

        try {
            getSupportActionBar().setTitle(product.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                db.delRec(product.getName());
                startActivity(new Intent(context, CategoryListActivity.class));
                break;
            case R.id.action_update:
                Intent updateIntent = new Intent(context, ProductCreationActivity.class);
                updateIntent.putExtra("action", "update");
                updateIntent.putExtra("name", product.getName());
                startActivity(updateIntent);
                break;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillFields(Product productForUpdate) {
        productDescription.setText(productForUpdate.getDescription());
        productWeight.setText(getString(R.string.weigt_info) + String.valueOf(productForUpdate.getWeight()) + getString(R.string.grams_info));
        productPrice.setText(getString(R.string.price_info) + String.valueOf(productForUpdate.getPrice()) + getString(R.string.currency_info));
        productCategory.setText(getString(R.string.category_info) + productForUpdate.getCategory());
    }

    @Override
    protected void onResume() {
        super.onResume();
        db.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        db.close();
    }

}
