/*
 * CS-360-R4495 Mobile Architect & Programming
 * 7-2 Final Project Submission
 * Bourama Mangara
 * 19 April 2020
 */
package com.example.lcs2.DBManagement;

// The Local coffee shop database schema
public class LCSDatabaseSchema {
    public static  final class UserTables{
        public static final String USER = "user";

        public static final class UserCols{
            public static final String UserID = "id";
            public static final String FirstName = "first_name";
            public static final String LastName = "last_name";
            public static final String Email = "email";
            public static final String Address = "address";



        }}


    public static  final class ProductTables{

        public static final String PRODUCT = "product";

        public static final class ProductCols{
            public static final String ProductID = "id";
            public static final String ProductName = "product_name";
            public static final String ProductDescription = "product_description";
            public static final String Price = "unit_price";
            public static final String Quantity = "quantity_in_stock";
            public static final String Image = "product_image";
        }}

    public static  final class OrderTables{

        public static final String ORDER = "order_table";

        public static final class OrderCols{
            public static final String OrderID = "order_id";
            public static final String OrderDate = "order_date";
            public static final String Status = "status";
            public static final String OrderDetailID = "order_detail_id";
            public static final String UserID = "user_id";
        }}

    public static  final class OrderDetailTables{

            public static final String ORDER_DETAIL = "order_detail";

            public static final class OrderDetailCols{
                public static final String OrderDetailID = "order_detail_id";
                public static final String OrderID = "order_id";
                public static final String Product_ID = "product_id";

                public static final String QuantityOrdered = "quantity_ordered";
                public static final String UnitPrice = "unit_price";
                public static final String Total = "total";
            }}

        public static  final class ProductRatingTables{

            public static final String Product_Rating = "product_rating";

            public static final class ProductRatingCols{
                public static final String ProductRatingID = "id";
                public static final String ProductID = "product_id";
                public static final String NumberOfStart = "number_of_start";

            }

        }


        public static  final class AppRatingTables{

            public static final String App_Rating = "app_rating";

            public static final class AppRatingCols{
                public static final String AppRatingID = "id";
                public static final String NumberOfStart = "number_of_start";

            }

    }
}





