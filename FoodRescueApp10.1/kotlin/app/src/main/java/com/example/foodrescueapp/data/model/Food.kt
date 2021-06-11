package com.example.foodrescueapp.data.model

class Food {

    var id: Int = 0
    lateinit var title: String
    lateinit var description: String
    lateinit var location: String
    lateinit var quantity: String
    lateinit var date: String
    lateinit var time: String
    var path: String = ""
    lateinit var timeStamp: String

    companion object {
        internal const val COLUMN_TITLE = "title"
        internal const val COLUMN_DESCRIPTION = "description"
        internal const val COLUMN_LOCATION = "location"
        internal const val COLUMN_QUANTITY = "quantity"
        internal const val COLUMN_ID = "id"
        internal const val COLUMN_USER_ID = "user_id"
        internal const val TABLE_NAME = "Note"
        internal const val COLUMN_DATE = "date"
        internal const val COLUMN_TIME = "time"
        internal const val COLUMN_PATH = "path"
        internal const val COLUMN_TIMESTAMP = "timestamp"

        var CREATE_TABLE = ("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_ID + " INTEGER DEFAULT 1,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_LOCATION + " TEXT,"
                + COLUMN_QUANTITY + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_TIME + " TEXT,"
                + COLUMN_PATH + " TEXT,"
                + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP" + ")")

        var cartList: ArrayList<Food> = arrayListOf()
        lateinit var food: Food
    }

}