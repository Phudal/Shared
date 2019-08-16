package com.example.recycle

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_view.*
import java.lang.Exception

class ViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val intent: Intent = getIntent()
        var data = ArrayList<String>()
        data = intent.getStringArrayListExtra("data") as ArrayList<String>

        var viewArray = ArrayList<TextView>()
        viewArray!!.run{
            add(findViewById(R.id.id_view))
            add(findViewById(R.id.serial_view))
            add(findViewById(R.id.name_view))
        }

        try {
            for (i in 0..(data!!.size - 1)) {
                viewArray.get(i).setText(data!!.get(i))
            }
        }catch (e:Exception){
            Toast.makeText(this, "Exception", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
