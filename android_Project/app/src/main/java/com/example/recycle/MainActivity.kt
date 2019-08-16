
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
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text


class MainActivity:AppCompatActivity() {

    var dbHelper: DBHelper? = null
    var database: SQLiteDatabase? = null
    var scrollView: ListView? = null
    var addButton: FloatingActionButton? = null
    var adapter: CustomAdapter? = null

        override fun onCreate(savedInstanceState: Bundle?) {
        //DB에 접근하기위해 (내부저장소) 권한설정
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){

            }else{
                ActivityCompat.requestPermissions(this,arrayOf("Manifest.permission.READ_EXTERNAL_STORAGE"),1)
            }
        }

        //get serial number
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_PHONE_STATE)){

            }else{
                ActivityCompat.requestPermissions(this,arrayOf("Manifest.permission.READ_PHONE_STATE"),1)
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dbHelper = DBHelper(this)
        database = dbHelper!!.writableDatabase

        scrollView = findViewById<ListView>(R.id.scrollView)
        scrollView!!.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
        }

        scrollView!!.setOnItemLongClickListener { parent, view, position, id ->
            Toast.makeText(this, "long touch", Toast.LENGTH_SHORT).show()
            true
        }


        addButton = findViewById(R.id.insert)
        addButton!!.setOnClickListener { view->
            var intent = Intent(applicationContext, InsertActivity::class.java)
            startActivityForResult(intent, 0)
        }

        drow()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            drow()
        }
    }

    fun drow(){

        var arrayColumn: ArrayList<Column> = ArrayList<Column>()
        var cursor: Cursor? = dbHelper!!.select(database)

        while(cursor!!.moveToNext()){
            arrayColumn.add(Column(cursor.getString(0), cursor.getString(1), cursor.getString(2)))
        }

        adapter = CustomAdapter(arrayColumn)
        scrollView!!.setAdapter(adapter)
    }
}

data class Column(val id:String, val serial:String, val name:String)


