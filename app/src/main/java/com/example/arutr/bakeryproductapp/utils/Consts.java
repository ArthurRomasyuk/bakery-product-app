package com.example.arutr.bakeryproductapp.utils;

/**
 * @author Artur Romasiuk
 */

public class Consts {

    // Configuration of a CursorLoader
    public final static int LOADER_ID = 0;

    // Configuration of a database
    public final static String DB_NAME = "bakery_product_app_db";
    public final static String DB_TABLE_NAME = "products";
    public final static String DB_COL_ID_PRIMARY = "_id";
    public final static String DB_COL_NAME = "productName";
    public final static String DB_COL_DESCRIPTION = "productDescription";
    public final static String DB_COL_IMAGE_URL = "productImageURL";
    public final static String DB_COL_WEIGHT = "productWeight";
    public final static String DB_COL_PRICE = "productPrice";
    public final static String DB_COL_CATEGORY = "productCategory";

    public final static int DB_VERSION = 1;

    // SQL Query
    public static final String DB_CREATE =
            "create table " + DB_TABLE_NAME + "(" +
                    DB_COL_ID_PRIMARY + " integer primary key autoincrement, " +
                    DB_COL_NAME + " text," +
                    DB_COL_DESCRIPTION + " text, " +
                    DB_COL_IMAGE_URL + " text," +
                    DB_COL_WEIGHT + " integer, " +
                    DB_COL_PRICE + " integer, " +
                    DB_COL_CATEGORY + " text," +
                    " UNIQUE ( " + DB_COL_NAME + " ) ON CONFLICT IGNORE" +
                    ");";

    public static final String DB_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DB_TABLE_NAME;


}
