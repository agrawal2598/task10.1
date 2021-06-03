package com.google.android.gms.samples.wallet.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.google.android.gms.samples.wallet.data.model.Food
import com.google.android.gms.samples.wallet.data.model.User

class DatabaseHelper private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {
        private var instance: DatabaseHelper? = null

        // Database Version
        private const val DATABASE_VERSION = 1

        // Database Name
        private const val DATABASE_NAME = "notes_app"

        fun getInstance(context: Context): DatabaseHelper? {
            if (instance == null) {
                synchronized(DatabaseHelper::class.java) {
                    if (instance == null) {
                        instance = DatabaseHelper(context)
                    }
                }
            }
            return instance
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Food.CREATE_TABLE)
        db?.execSQL(User.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertFood(food: Food) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(Food.COLUMN_TITLE, food.title)
        values.put(Food.COLUMN_DESCRIPTION, food.description)
        values.put(Food.COLUMN_LOCATION, food.location)
        values.put(Food.COLUMN_QUANTITY, food.quantity)
        values.put(Food.COLUMN_DATE, food.date)
        values.put(Food.COLUMN_TIME, food.time)
        values.put(Food.COLUMN_PATH, food.path)
        db.insert(Food.TABLE_NAME, null, values)
    }

    fun getFood(id: Int): List<Food> {
        val db = this.readableDatabase
        val noteArrayList = java.util.ArrayList<Food>()
        val query =
            "SELECT * FROM " + Food.TABLE_NAME + " WHERE " + Food.COLUMN_USER_ID + "='" + id + "'"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val note = Food()
                note.id = cursor.getInt(cursor.getColumnIndexOrThrow(Food.COLUMN_ID))
                note.title = cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_TITLE))
                note.description =
                    cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_DESCRIPTION))
                note.location = cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_LOCATION))
                note.path = cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_PATH))
                note.quantity = cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_QUANTITY))
                note.date = cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_DATE))
                note.time = cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_TIME))
                note.timeStamp =
                    cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_TIMESTAMP))
                noteArrayList.add(note)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return noteArrayList
    }


    fun getFood(): List<Food> {
        val db = this.readableDatabase
        val noteArrayList = java.util.ArrayList<Food>()
        val query =
            "SELECT * FROM " + Food.TABLE_NAME
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val note = Food()
                note.id = cursor.getInt(cursor.getColumnIndexOrThrow(Food.COLUMN_ID))
                note.title = cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_TITLE))
                note.description =
                    cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_DESCRIPTION))
                note.location = cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_LOCATION))
                note.path = cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_PATH))
                note.quantity = cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_QUANTITY))
                note.date = cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_DATE))
                note.time = cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_TIME))
                note.timeStamp =
                    cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_TIMESTAMP))
                noteArrayList.add(note)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return noteArrayList
    }

    fun insertUser(user: User) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(User.COLUMN_NAME, user.name)
        values.put(User.COLUMN_PHONE_NUMBER, user.phoneNumber)
        values.put(User.COLUMN_PASSWORD, user.password)
        values.put(User.COLUMN_ADDRESS, user.address)
        values.put(User.COLUMN_EMAIL_ADDRESS, user.emailAddress)
        db.insert(User.TABLE_NAME, null, values)
    }

    fun getUser(email: String): List<User> {
        val db = this.readableDatabase
        val noteArrayList = java.util.ArrayList<User>()
        val query =
            "SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_EMAIL_ADDRESS + "='" + email + "'"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val note = User()
                note.timeStamp =
                    cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_TIMESTAMP))
                noteArrayList.add(note)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return noteArrayList
    }

    fun getUser(email: String, password: String): List<User> {
        val db = this.readableDatabase
        val noteArrayList = java.util.ArrayList<User>()
        val query =
            "SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COLUMN_EMAIL_ADDRESS + "='" + email + "' AND " + User.COLUMN_PASSWORD + "='" + password + "'"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val note = User()
                note.timeStamp =
                    cursor.getString(cursor.getColumnIndexOrThrow(Food.COLUMN_TIMESTAMP))
                noteArrayList.add(note)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return noteArrayList
    }

    fun updateFood(food: Food) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(Food.COLUMN_USER_ID, 0)
        db.update(Food.TABLE_NAME, values, Food.COLUMN_ID + "= " + food.id, null)
    }
    /* fun insertNote(text: String) {
         val db = this.writableDatabase
         val values = ContentValues()
         values.put(Note.COLUMN_TEXT, text)
         db.insert(Note.TABLE_NAME, null, values)
     }

     fun getNotes(): List<Note> {
         val db = this.readableDatabase
         val noteArrayList = java.util.ArrayList<Note>()
         val query = "SELECT * FROM " + Note.TABLE_NAME
         val cursor = db.rawQuery(query, null)
         if (cursor.moveToFirst()) {
             do {
                 val note = Note()
                 note.id = cursor.getInt(cursor.getColumnIndexOrThrow(Note.COLUMN_ID))
                 note.text = cursor.getString(cursor.getColumnIndexOrThrow(Note.COLUMN_TEXT))
                 note.timeStamp =
                     cursor.getString(cursor.getColumnIndexOrThrow(Note.COLUMN_TIMESTAMP))
                 noteArrayList.add(note)
             } while (cursor.moveToNext())
         }
         cursor.close()
         return noteArrayList
     }

     fun updateNote(text: String, id: String) {
         val db = this.writableDatabase
         val values = ContentValues()
         values.put(Note.COLUMN_TEXT, text)
         db.update(Note.TABLE_NAME, values, Note.COLUMN_ID + "= " + id, null);
     }

     fun deleteNote(id: String) {
         val db = this.writableDatabase
         db.execSQL("Delete from " + Note.TABLE_NAME + " where " + Note.COLUMN_ID + " ='$id'")
     }*/

}