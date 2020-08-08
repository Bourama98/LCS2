/*
 * CS-360-R4495 Mobile Architect & Programming
 * 7-2 Final Project Submission
 * Bourama Mangara
 * 19 April 2020
 */
package com.example.lcs2.DBManagement;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.lcs2.DBManagement.LCSDatabaseSchema.*;

//import com.example.lcs2.Product;
//import com.example.lcs2.User;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 6;
    private static final String DATABASE_NAME = "local_coffee_shop.db";

    public static final String TABLE_ORDER = "order_table";
    public static final String COLUMN_OrderID = "order_id";
    public static final String COLUMN_OrderDate = "order_date";
    public static final String COLUMN_Status = "status";
    public static final String COLUMN_OrderDetailID = "order_detail_id";
    public static final String COLUMN_UserID = "user_id";



////    // Creating Order Table
    public static String CreateOrder1 = "CREATE TABLE " + TABLE_ORDER.toString() +"(" +
            COLUMN_OrderID + "  INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_OrderDate + " DATE," +
            COLUMN_Status + " TEXT," +
            COLUMN_UserID + "INTEGER," +
            COLUMN_OrderDetailID + " INTEGER);";


//    // Creating Product table creation
//
//
    public static String CreateProduct = "CREATE TABLE " + ProductTables.PRODUCT + "(" +
            ProductTables.ProductCols.ProductID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ProductTables.ProductCols.ProductName + " TEXT," +
            ProductTables.ProductCols.ProductDescription + " TEXT,"+
            ProductTables.ProductCols.Price + " INTEGER," +
            ProductTables.ProductCols.Quantity + " TEXT)";


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creating user Table
        String CREATE_USER_TABLE = "CREATE TABLE " + UserTables.USER + "(" +
                UserTables.UserCols.UserID + "  INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserTables.UserCols.FirstName + " TEXT," +
                UserTables.UserCols.LastName + " TEXT," +
                UserTables.UserCols.Email + " TEXT UNIQUE," +
                UserTables.UserCols.Address + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
// Creating Product table creation


        String CreateProduct = "CREATE TABLE " + ProductTables.PRODUCT + "(" +
               ProductTables.ProductCols.ProductID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
               ProductTables.ProductCols.ProductName + " TEXT," +
               ProductTables.ProductCols.ProductDescription + " TEXT,"+
                ProductTables.ProductCols.Price + " INTEGER," +
                ProductTables.ProductCols.Image + " TEXT," +
               ProductTables.ProductCols.Quantity + " INTEGER)";

        db.execSQL(CreateProduct);


        // Creating Order Table
     String CreateOrder = "CREATE TABLE " + OrderTables.ORDER +"(" +
             OrderTables.OrderCols.OrderID + "  INTEGER PRIMARY KEY AUTOINCREMENT," +
             OrderTables.OrderCols.OrderDate + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
             OrderTables.OrderCols.Status + " TEXT," +
             OrderTables.OrderCols.UserID + " INTEGER," +
             "FOREIGN KEY(" + OrderTables.OrderCols.UserID + ") REFERENCES " + UserTables.USER +" ("+ UserTables.UserCols.UserID +"))";

     db.execSQL(CreateOrder);

//        // Creating OrderDetail Table
        String CreateOrderDetail = "CREATE TABLE " + OrderDetailTables.ORDER_DETAIL + "(" +
                OrderDetailTables.OrderDetailCols.OrderDetailID  + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                OrderDetailTables.OrderDetailCols.OrderID + " INTEGER," +
                OrderDetailTables.OrderDetailCols.QuantityOrdered + " INTEGER," +
                OrderDetailTables.OrderDetailCols.UnitPrice + " INTEGER," +
                OrderDetailTables.OrderDetailCols.Product_ID + "," +
                OrderDetailTables.OrderDetailCols.Total + " INTEGER,"+
                "FOREIGN KEY(" + OrderDetailTables.OrderDetailCols.Product_ID + ") REFERENCES " + UserTables.USER +" ("+ ProductTables.ProductCols.ProductID +")," +
                "FOREIGN KEY(" + OrderDetailTables.OrderDetailCols.OrderID + ") REFERENCES "+ OrderTables.ORDER + " ("+OrderTables.OrderCols.OrderID+ "))";

        db.execSQL(CreateOrderDetail);



        // Creating Product Rating Table
        String CreateProductRating = "CREATE TABLE " + ProductRatingTables.Product_Rating +"(" +
                ProductRatingTables.ProductRatingCols.ProductRatingID + "  INTEGER PRIMARY KEY AUTOINCREMENT," +
                ProductRatingTables.ProductRatingCols.ProductID + " INTEGER," +
                ProductRatingTables.ProductRatingCols.NumberOfStart + " NUMERRIC DEFAULT 0," +
                "FOREIGN KEY(" + ProductRatingTables.ProductRatingCols.ProductID  + ") REFERENCES " + ProductRatingTables.Product_Rating +" ("+ ProductTables.ProductCols.ProductID +"))";

        db.execSQL(CreateProductRating);

        // Creating Product Rating Table
        String CreateAppRating = "CREATE TABLE " + AppRatingTables.App_Rating +"(" +
                AppRatingTables.AppRatingCols.AppRatingID + "  INTEGER PRIMARY KEY AUTOINCREMENT," +
                ProductRatingTables.ProductRatingCols.NumberOfStart + " NUMERRIC DEFAULT 0)";
        db.execSQL(CreateAppRating);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserTables.USER.toString());
        db.execSQL("DROP TABLE IF EXISTS " + ProductTables.PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + OrderDetailTables.ORDER_DETAIL);
        db.execSQL("DROP TABLE IF EXISTS " + OrderTables.ORDER);
        db.execSQL("DROP TABLE IF EXISTS " + AppRatingTables.App_Rating);
        db.execSQL("DROP TABLE IF EXISTS " + ProductRatingTables.Product_Rating);
        onCreate(db);
    }
//    public void add(Product product){
//        ContentValues values = new ContentValues();
//
//        values.put(ProductTables.ProductCols.ProductName, product.getProductName());
//        values.put(ProductTables.ProductCols.ProductDescription, product.getProductDescription());
//        values.put(ProductTables.ProductCols.Price, product.getPrice());
//        values.put(ProductTables.ProductCols.Quantity, product.getQuantityInStock());
//        values.put(ProductTables.ProductCols.Image, product.getImage());
//
////        Order order = new Order();
////        order.setOrder_date("01/11/2020");
////        order.setOrder_status("Pending");
////        order.setOrder_detailID(1);
////        order.setUser_id(1);
////        ContentValues values1 = new ContentValues();
////        values1.put(OrderTables.OrderCols.OrderDate, "order.getOrder_date()");
////        values1.put(OrderTables.OrderCols.Status, "order.getOrder_status()");
////        values1.put(OrderTables.OrderCols.UserID, "1");
////        values1.put(OrderTables.OrderCols.OrderDetailID, "1");
//
//
//
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        //SQLiteDatabase db1 = this.getWritableDatabase();
//        db.insert(ProductTables.PRODUCT, null, values);
//        //db1.insert(OrderTables.ORDER, null, values1);
//        db.close();
//       // db1.close();
//
//    }
//    //Implement the searchOne and update
//    public Product searchOne(String productName) {
//        String query = "SELECT * FROM " + ProductTables.PRODUCT + " WHERE " + ProductTables.ProductCols.ProductName +" = \"" + productName + "\"";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        Product product = new Product();
//        if (cursor.moveToFirst()) {
//            cursor.moveToFirst();
//            product.setProductID(Integer.parseInt(cursor.getString(0)));
//            product.setProductName(cursor.getString(1));
//            product.setProductDescription(cursor.getString(2));
//            product.setPrice(Integer.parseInt(cursor.getString(3)));
//            product.setQuantityInStock(Integer.parseInt(cursor.getString(5)));
//            product.setImage(cursor.getString(4));
//            cursor.close();
//        }
//        else {
//            product = null;
//        }
//        db.close();
//        return product;
//    }
//    // Implements Search One by ID
//    public Product searchOneByID(int id) {
//        String query = "SELECT * FROM " +ProductTables.PRODUCT + " WHERE " + ProductTables.ProductCols.ProductID +" = \"" + id + "\"";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        Product product = new Product();
//        if (cursor.moveToFirst()) {
//            cursor.moveToFirst();
//            product.setProductID(Integer.parseInt(cursor.getString(0)));
//            product.setProductName(cursor.getString(1));
//            product.setProductDescription(cursor.getString(2));
//            product.setPrice(Integer.parseInt(cursor.getString(3)));
//            product.setImage(cursor.getString(4));
//            product.setQuantityInStock(Integer.parseInt(cursor.getString(5)));
//            cursor.close();
//        }
//        else {
//            product = null;
//        }
//        db.close();
//        return product;
//    }
//    //implements the search/find functionality
//    public ArrayList<Product> search(String productName) {
//        ArrayList<Product> arrayList = new ArrayList<>();
//        String query = "SELECT * FROM " + ProductTables.PRODUCT + " WHERE " + ProductTables.ProductCols.ProductName +" = \"" + productName + "\"";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        Product product;
//        while (cursor.moveToNext()) {
//            if (cursor.getCount()== 0) {
//                arrayList.isEmpty();
//            }
//            else {
//
//                //cursor.moveToFirst();
//                int id = cursor.getInt(0);
//                String name = cursor.getString(1);
//                int price = cursor.getInt(3);
//                String image = cursor.getString(4);
//                product = new Product(id, name, price, image);
//                arrayList.add(product);
//            }
//        }
//            cursor.close();
//            db.close();
//
//        return arrayList;
//    }
//
//    public ArrayList<Product> getAllData(){
//            ArrayList<Product> arrayList = new ArrayList<>();
//            SQLiteDatabase database = this.getReadableDatabase();
//            Cursor cursor = database.rawQuery("SELECT * FROM product LIMIT 6", null);
//
//            while ( cursor.moveToNext()){
//
//                int id = cursor.getInt(0);
//                String name = cursor.getString(1);
//                int price = cursor.getInt(3);
//                String image = cursor.getString(4);
//                Product product = new Product(id, name, price, image);
//                arrayList.add(product);
//            }
//
//        return arrayList;
//    }
//
//    // implements the delete dog functionality
//    public boolean deleteProduct(String productName) {
//        boolean result = false;
//        String query = "SELECT * FROM " + ProductTables.PRODUCT +" WHERE " + ProductTables.ProductCols.ProductName + " = \"" + productName + "\"";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        Product product = new Product();
//        if (cursor.moveToFirst()) {
//            product.setProductID(Integer.parseInt(cursor.getString(0)));
//            db.delete(ProductTables.PRODUCT,  ProductTables.ProductCols.ProductID+ " = ?",new String[] { String.valueOf(product.getProductID())});
//            cursor.close();
//            result = true;
//        }
//        db.close();
//        return result;
//    }
//
//    public boolean updateProduct(Product product) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(ProductTables.ProductCols.ProductName, product.getProductName());
//        values.put(ProductTables.ProductCols.ProductDescription, product.getProductDescription());
//        values.put(ProductTables.ProductCols.Price, product.getPrice());
//        values.put(ProductTables.ProductCols.Quantity, product.getQuantityInStock());
//        values.put(ProductTables.ProductCols.Image, product.getImage());
//
//
//            db.update(ProductTables.PRODUCT, values ,ProductTables.ProductCols.ProductID+ " = ?",new String[] { String.valueOf(product.getProductID())});
//
//        db.close();
//        return true;
//    }

    public void addAppRating(int rating){
        ContentValues values = new ContentValues();
        values.put(AppRatingTables.AppRatingCols.NumberOfStart, rating);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(AppRatingTables.App_Rating, null, values);
        db.close();

    }
//    public void addUser(User user){
//        ContentValues values = new ContentValues();
//        values.put(UserTables.UserCols.FirstName, user.getFirstName());
//        values.put(UserTables.UserCols.LastName, user.getLastName());
//        values.put(UserTables.UserCols.Email, user.getEmail());
//        values.put(UserTables.UserCols.Address, user.getAddress());
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        db.insert(UserTables.USER, null, values);
//
//        db.close();
//
//    }
    public boolean searchUser(String email){
        boolean isUser = false;
        String query = "SELECT * FROM " + UserTables.USER + " WHERE " + UserTables.UserCols.Email +" = \"" + email+ "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (!(cursor.getCount() ==0)){
            isUser = true;
            cursor.close();
        }
        return isUser;
    }


     // Get App rating
    public ArrayList<Integer> getAllAppRating(){
        ArrayList<Integer> ratings = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM app_rating", null);
        while ( cursor.moveToNext()){


            int rating = cursor.getInt(1);

            ratings.add(rating);
        }

        return ratings;

    }
    public void addProductRating(int productID, int rating ){
        ContentValues values = new ContentValues();
        values.put(ProductRatingTables.ProductRatingCols.ProductID, productID);
        values.put(ProductRatingTables.ProductRatingCols.NumberOfStart, rating);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ProductRatingTables.Product_Rating, null, values);
        db.close();

    }
//    public void addOrder( Order order){
//        ContentValues values = new ContentValues();
//        values.put(OrderTables.OrderCols.Status, order.getOrder_status());
//        values.put(OrderTables.OrderCols.UserID, order.getUser_id());
//
//
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        db.insert(OrderTables.ORDER, null, values);
//
//
//        db.close();
//
//    }
//    public void addOrderDetail( Order order){
//
//
//        ContentValues values2 = new ContentValues();
//        values2.put(OrderDetailTables.OrderDetailCols.OrderID, order.getOrder_id());
//        values2.put(OrderDetailTables.OrderDetailCols.Product_ID, order.getProductID());
//        values2.put(OrderDetailTables.OrderDetailCols.QuantityOrdered, order.getQuantity());
//        values2.put(OrderDetailTables.OrderDetailCols.UnitPrice, order.getUnitPrice());
//        values2.put(OrderDetailTables.OrderDetailCols.Total, order.getOrderTotal());
//        SQLiteDatabase db = this.getWritableDatabase();
//        //SQLiteDatabase db1 = this.getWritableDatabase();
//        db.setForeignKeyConstraintsEnabled(false);
//        db.insert(OrderDetailTables.ORDER_DETAIL, "SELECT * FROM order_table", values2);
//        //db1.insert(OrderTables.ORDER, null, values1);
//        db.close();
//
//    }
}
