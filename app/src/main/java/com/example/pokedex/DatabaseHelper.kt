package com.example.pokedex

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(private val context: Context):
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){


    companion object{
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "data"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
        private const val TABLE_NAME2 = "favorites"
        private const val COLUMN_POKEMON_ID = "pokemon_id"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createUserTableQuery = (
                "CREATE TABLE $TABLE_NAME (" +
                        "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "$COLUMN_USERNAME TEXT, " +
                        "$COLUMN_PASSWORD TEXT)"
                )
        db?.execSQL(createUserTableQuery)

        val createTableQueryFavoritos = (
                "CREATE TABLE $TABLE_NAME2 (" +
                        "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "$COLUMN_POKEMON_ID TEXT)"
                )
        db?.execSQL(createTableQueryFavoritos)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)

        val dropTableQueryFavoritos = "DROP TABLE IF EXISTS $TABLE_NAME2"
        db?.execSQL(dropTableQueryFavoritos)
        onCreate(db)
    }

    fun insertUser(username: String, password: String): Long{
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD, password)
        }
        val db = writableDatabase
        return db.insert(TABLE_NAME, null, values)
    }

    fun insertFavorite(id: String): Long{
        val values = ContentValues().apply {
            put(COLUMN_POKEMON_ID, id)
        }
        val db = writableDatabase
        return db.insert(TABLE_NAME2, null, values)
    }


    fun readUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query(TABLE_NAME, null,selection, selectionArgs, null, null, null)

        val userExists = cursor.count >0
        cursor.close()
        return userExists
    }


    fun readFavorite(id: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_POKEMON_ID = ? "
        val selectionArgs = arrayOf(id)
        val cursor = db.query(TABLE_NAME2, null,selection, selectionArgs, null, null, null)

        val userExists = cursor.count >0
        cursor.close()
        return userExists
    }

    fun deleteFavorite(pokemonId: String): Int {
        val db = writableDatabase
        val selection = "$COLUMN_POKEMON_ID = ?"
        val selectionArgs = arrayOf(pokemonId)
        return db.delete(TABLE_NAME2, selection, selectionArgs)
    }

}