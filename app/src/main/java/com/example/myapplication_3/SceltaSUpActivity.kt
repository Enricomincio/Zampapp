package com.example.myapplication_3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SceltaSUpActivity : AppCompatActivity() {
    private lateinit var btnPersonale : Button
    private lateinit var btnCanile : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scelta_profilo)

        btnPersonale = findViewById(R.id.materialCardView)
        btnCanile = findViewById(R.id.materialCardView2)

        btnPersonale.setOnClickListener(){
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }



}