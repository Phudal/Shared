package com.example.recycle

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class deleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        val popup_ok:Button = findViewById(R.id.popup_delete_ok)
        val popup_cancle:Button = findViewById(R.id.popup_delete_cancle)

        popup_ok.setOnClickListener { view->
            setResult(RESULT_OK)
            finish()
        }

        popup_cancle.setOnClickListener { view->
            finish()
        }
    }
}
