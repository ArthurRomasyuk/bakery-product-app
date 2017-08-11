package com.example.arutr.bakeryproductapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.arutr.bakeryproductapp.models.Product;

import java.util.ArrayList;

import static com.example.arutr.bakeryproductapp.utils.Consts.DB_COL_CATEGORY;
import static com.example.arutr.bakeryproductapp.utils.Consts.DB_COL_DESCRIPTION;
import static com.example.arutr.bakeryproductapp.utils.Consts.DB_COL_IMAGE_URL;
import static com.example.arutr.bakeryproductapp.utils.Consts.DB_COL_NAME;
import static com.example.arutr.bakeryproductapp.utils.Consts.DB_COL_PRICE;
import static com.example.arutr.bakeryproductapp.utils.Consts.DB_COL_WEIGHT;
import static com.example.arutr.bakeryproductapp.utils.Consts.DB_TABLE_NAME;

/**
 * @author Artur Romasiuk
 */

public class Database {
    private final Context ctx;

    private DBHelper dbHelper;

    private SQLiteDatabase mDB;

    public Database(Context ctx) {
        this.ctx = ctx;
    }

    public void open() {
        dbHelper = new DBHelper(ctx, Consts.DB_NAME, null, Consts.DB_VERSION);
        mDB = dbHelper.getWritableDatabase();
    }

    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    public Cursor getAllData() {
        return mDB.query(DB_TABLE_NAME, null, null, null, null, null, null);
    }

    public void clearData() {
        mDB.delete(DB_TABLE_NAME, null, null);
    }

    public ArrayList<String> getAllCategories() {
        Cursor query = mDB.query(true, DB_TABLE_NAME, new String[]{DB_COL_CATEGORY}, null, null, null, null, null, null);
        ArrayList<String> categories = new ArrayList<>();
        while (query.moveToNext()) {
            categories.add(query.getString(query
                    .getColumnIndex(Consts.DB_COL_CATEGORY)));
        }
        query.close();
        return categories;
    }

    public ArrayList<Product> getProductsByCategory(String categoryName) {
        Cursor query = mDB.query(DB_TABLE_NAME, new String[]{DB_COL_NAME, DB_COL_DESCRIPTION,DB_COL_WEIGHT,
                        DB_COL_PRICE,DB_COL_CATEGORY, DB_COL_IMAGE_URL}, DB_COL_CATEGORY + " = ?",
                new String[]{categoryName}, null, null, null);
        ArrayList<Product> products = new ArrayList<>();
        while (query.moveToNext()) {
            products.add(new Product(
                            query.getString(query.getColumnIndex(DB_COL_NAME)),
                            query.getString(query.getColumnIndex(DB_COL_DESCRIPTION)),
                            query.getInt(query.getColumnIndex(DB_COL_WEIGHT)),
                            query.getInt(query.getColumnIndex(DB_COL_PRICE)),
                            query.getString(query.getColumnIndex(DB_COL_CATEGORY)),
                            query.getString(query.getColumnIndex(DB_COL_IMAGE_URL))
                    )
            );
        }
        query.close();
        return products;
    }

    public Product getProductByName (String productName) {
        Cursor query = mDB.query(DB_TABLE_NAME, new String[]{DB_COL_NAME, DB_COL_DESCRIPTION,DB_COL_WEIGHT,
                        DB_COL_PRICE,DB_COL_CATEGORY, DB_COL_IMAGE_URL}, DB_COL_NAME + " = ?",
                new String[]{productName}, null, null, null);
        query.moveToFirst();
        return new Product(
                query.getString(query.getColumnIndex(DB_COL_NAME)),
                query.getString(query.getColumnIndex(DB_COL_DESCRIPTION)),
                query.getInt(query.getColumnIndex(DB_COL_WEIGHT)),
                query.getInt(query.getColumnIndex(DB_COL_PRICE)),
                query.getString(query.getColumnIndex(DB_COL_CATEGORY)),
                query.getString(query.getColumnIndex(DB_COL_IMAGE_URL))
        );
    }

    public void addRec(Product product) {
        ContentValues cv = new ContentValues();
        cv.put(DB_COL_NAME, product.getName());
        cv.put(DB_COL_DESCRIPTION, product.getDescription());
        cv.put(DB_COL_IMAGE_URL, product.getImageUrl());
        cv.put(DB_COL_WEIGHT, product.getWeight());
        cv.put(DB_COL_PRICE, product.getPrice());
        cv.put(DB_COL_CATEGORY, product.getCategory());
        mDB.insert(DB_TABLE_NAME, null, cv);
    }

    public void updateRec (Product product){
        ContentValues cv = new ContentValues();
        cv.put(DB_COL_NAME, product.getName());
        cv.put(DB_COL_DESCRIPTION, product.getDescription());
        cv.put(DB_COL_IMAGE_URL, product.getImageUrl());
        cv.put(DB_COL_WEIGHT, product.getWeight());
        cv.put(DB_COL_PRICE, product.getPrice());
        cv.put(DB_COL_CATEGORY, product.getCategory());
        mDB.update(DB_TABLE_NAME, cv, DB_COL_NAME + " = ?", new String[]{product.getName()} );
    }

    public void addRecList(ArrayList<Product> productArrayList) {
        for (int i = 0; i < productArrayList.size(); i++) {
            addRec(productArrayList.get(i));
        }
    }

    public void delRec(String productName) {
        mDB.delete(DB_TABLE_NAME, DB_COL_NAME + " = ?", new String[]{productName});
    }


    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Consts.DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(Consts.DB_DELETE_ENTRIES);
            onCreate(db);
        }
    }
}
