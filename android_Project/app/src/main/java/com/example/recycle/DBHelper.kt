package com.example.recycle

import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast
import java.io.Serializable
import java.security.AccessController.getContext

class DBHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VER), Serializable{

    companion object {
        val DATABASE_VER = 1
        val DATABASE_NAME = "test.db"

        val TABLE_NAME = "test"

        val COLUMN_ID = "id"               //at creation time
        val COLUMN_SERIAL = "serial"       //system serial number
        val COLUMN_NAME = "name"           // user defined name
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "create table if not exists ${TABLE_NAME} ( ${COLUMN_ID} text primary key, ${COLUMN_SERIAL} text, ${COLUMN_NAME} text);"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    public fun insert(db: SQLiteDatabase?, column: Column?): Boolean{
        if(db == null) {
            return false
        }

        var query = "insert into ${TABLE_NAME} ( ${COLUMN_ID}, ${COLUMN_SERIAL}, ${COLUMN_NAME}) "
        query += "values( '${column!!.id}', '${column!!.serial}', '${column!!.name}');"

        db.execSQL(query)
        return true
    }

    public fun select(db: SQLiteDatabase?): Cursor?{
        if(db == null)
            return null

        var query = "select * from ${TABLE_NAME};"
        return db!!.rawQuery(query, null)
    }

    public fun update(db: SQLiteDatabase?, column: Column?){
        // retrieve from server
    }

    public fun delete(db: SQLiteDatabase?, id: String){

        var query:String = "delete from ${TABLE_NAME} where ${COLUMN_ID} = '${id}';"
        db!!.execSQL(query)
    }
}
