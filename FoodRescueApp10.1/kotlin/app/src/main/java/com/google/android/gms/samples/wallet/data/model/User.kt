package com.google.android.gms.samples.wallet.data.model

class User {

    lateinit var name: String
    lateinit var phoneNumber: String
    lateinit var address: String
    lateinit var emailAddress: String
    lateinit var password: String
    lateinit var timeStamp: String

    companion object {
        internal const val COLUMN_NAME = "name"
        internal const val COLUMN_PHONE_NUMBER = "phone_number"
        internal const val COLUMN_ADDRESS = "address"
        internal const val COLUMN_ID = "id"
        internal const val TABLE_NAME = "User"
        internal const val COLUMN_PASSWORD = "password"
        internal const val COLUMN_EMAIL_ADDRESS = "email_address"
        internal const val COLUMN_TIMESTAMP = "timestamp"

        var CREATE_TABLE = ("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_PHONE_NUMBER + " TEXT,"
                + COLUMN_ADDRESS + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_EMAIL_ADDRESS + " TEXT,"
                + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP" + ")")
    }
}