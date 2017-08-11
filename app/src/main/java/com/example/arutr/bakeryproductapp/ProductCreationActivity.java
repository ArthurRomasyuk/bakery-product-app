package com.example.arutr.bakeryproductapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.CollapsibleActionView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arutr.bakeryproductapp.models.Product;
import com.example.arutr.bakeryproductapp.utils.Database;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductCreationActivity extends AppCompatActivity {

    @BindView(R.id.et_product_creation_name)
    EditText productName;

    @BindView(R.id.et_product_creation_description)
    EditText productDescription;

    @BindView(R.id.et_product_creation_weight)
    EditText productWeight;

    @BindView(R.id.et_product_creation_price)
    EditText productPrice;

    @BindView(R.id.et_product_creation_category)
    EditText productCategory;

    @BindView(R.id.et_product_creation_image_url)
    EditText productImageUrl;

    @BindView(R.id.accept_creation)
    Button acceptCreationBtn;

    @BindView(R.id.accept_update)
    Button acceptUpdateBtn;

    private Database db;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_creation);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db = new Database(this);
        db.open();
        context = this;
        switch (getIntent().getStringExtra("action")) {
            case "create":
                acceptUpdateBtn.setVisibility(View.GONE);
                acceptCreationBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(productName.getText().toString())) {
                            productName.requestFocus();
                            Toast.makeText(context, "Name can't be empty", Toast.LENGTH_LONG).show();
                        } else if (TextUtils.isEmpty(productCategory.getText().toString())) {
                            productCategory.requestFocus();
                            Toast.makeText(context, "Category can't be empty", Toast.LENGTH_LONG).show();
                        } else {
                            int weight;
                            try {
                                weight = Integer.parseInt(productWeight.getText().toString());
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                weight = 0;
                            }
                            int price;
                            try {
                                price = Integer.parseInt(productPrice.getText().toString());
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                price = 0;
                            }
                            db.addRec(new Product(productName.getText().toString(),
                                    productDescription.getText().toString(),
                                    weight,
                                    price,
                                    productCategory.getText().toString(),
                                    productImageUrl.getText().toString()));
                            startActivity(new Intent(context, CategoryListActivity.class));
                        }
                    }
                });
                break;
            case "update":
                acceptCreationBtn.setVisibility(View.GONE);
                Product productForUpdate = db.getProductByName(getIntent().getStringExtra("name"));
                fillFieldsForUpdate(productForUpdate);
                productName.setEnabled(false);
                acceptUpdateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(productName.getText().toString())) {
                            productName.requestFocus();
                            Toast.makeText(context, "Name can't be empty", Toast.LENGTH_LONG).show();
                        } else if (TextUtils.isEmpty(productCategory.getText().toString())) {
                            productCategory.requestFocus();
                            Toast.makeText(context, "Category can't be empty", Toast.LENGTH_LONG).show();
                        } else {
                            int weight;
                            try {
                                weight = Integer.parseInt(productWeight.getText().toString());
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                weight = 0;
                            }
                            int price;
                            try {
                                price = Integer.parseInt(productPrice.getText().toString());
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                price = 0;
                            }
                            db.updateRec(new Product(productName.getText().toString(),
                                    productDescription.getText().toString(),
                                    weight,
                                    price,
                                    productCategory.getText().toString(),
                                    productImageUrl.getText().toString()));
                            startActivity(new Intent(context, CategoryListActivity.class));
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    private void fillFieldsForUpdate(Product productForUpdate) {
        productName.setText(productForUpdate.getName());
        productDescription.setText(productForUpdate.getDescription());
        productWeight.setText(String.valueOf(productForUpdate.getWeight()));
        productPrice.setText(String.valueOf(productForUpdate.getPrice()));
        productCategory.setText(productForUpdate.getCategory());
        productImageUrl.setText(productForUpdate.getImageUrl());
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
    }

    @Override
    protected void onPause() {
        super.onPause();
        db.close();
    }
}
