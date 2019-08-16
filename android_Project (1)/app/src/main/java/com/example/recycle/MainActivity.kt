
package com.example.recycle

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.*
import java.lang.Exception


class MainActivity : AppCompatActivity(){
    companion object{
        val INSERT_ACTIVITY = 200
        val DELETE_ACTIVITY = 300
    }

    var dbHelper: DBHelper? = null
    var database: SQLiteDatabase? = null
    var scrollView: ListView? = null
    var addButton: FloatingActionButton? = null
    var adapter: CustomAdapter? = null
    var position: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        //DB에 접근하기위해 (내부저장소) 권한설정
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {

            } else {
                ActivityCompat.requestPermissions(this, arrayOf("Manifest.permission.READ_EXTERNAL_STORAGE"), 1)
            }
        }

        //get serial number
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {

            } else {
                ActivityCompat.requestPermissions(this, arrayOf("Manifest.permission.READ_PHONE_STATE"), 1)
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dbHelper = DBHelper(this)
        database = dbHelper!!.writableDatabase

        scrollView = findViewById<ListView>(R.id.scrollView)
        scrollView!!.setOnItemClickListener { parent, view, position, id ->
            this.position = position
            val cursor: Cursor? = dbHelper!!.select(database)
            cursor!!.moveToPosition(position)

            var data = ArrayList<String>()
            try {
                for (i in 0..(cursor.columnCount - 1)) {
                    data!!.add(cursor.getString(i))
                }

                var intent = Intent(applicationContext, ViewActivity::class.java)
                intent.putExtra("data", data)
                startActivity(intent)

            } catch (e: Exception) {
                Toast.makeText(this, "Exception", Toast.LENGTH_LONG).show()
            }
        }

        scrollView!!.setOnItemLongClickListener { parent, view, position, id ->
            //Toast.makeText(this, "long touch", Toast.LENGTH_SHORT).show()
            //임시로 (삭제)
            this.position = position
            val intent = Intent(applicationContext, deleteActivity::class.java)
            startActivityForResult(intent, DELETE_ACTIVITY)
            true
        }


        addButton = findViewById(R.id.insert)
        addButton!!.setOnClickListener { view ->
            var intent = Intent(applicationContext, InsertActivity::class.java)
            startActivityForResult(intent, INSERT_ACTIVITY)
        }

        drow()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode){

            INSERT_ACTIVITY->
                if(resultCode == Activity.RESULT_OK){
                    val name = data!!.getStringExtra("name")
                    dbHelper!!.insert(
                        dbHelper!!.writableDatabase, Column(System.currentTimeMillis().toString(), android.os.Build.SERIAL, name)
                    )
                    Toast.makeText(this, "추가완료", Toast.LENGTH_SHORT).show()
                }

            DELETE_ACTIVITY-> {
                if(resultCode == Activity.RESULT_OK){
                    var cursor: Cursor? = dbHelper!!.select(database)
                    cursor!!.moveToPosition(position!!)
                    dbHelper!!.delete(dbHelper!!.writableDatabase, cursor.getString(0))
                    Toast.makeText(this, "삭제", Toast.LENGTH_SHORT).show()
                }
            }
        }
        drow()
    }

    fun drow(){

        var arrayColumn = ArrayList<Column>()
        var cursor: Cursor? = dbHelper!!.select(database)

        while(cursor!!.moveToNext()){
            arrayColumn.add(Column(cursor.getString(0), cursor.getString(1), cursor.getString(2)))
        }

        adapter = CustomAdapter(arrayColumn)
        scrollView!!.setAdapter(adapter)
    }
}

data class Column(val id:String, val serial:String, val name:String)


