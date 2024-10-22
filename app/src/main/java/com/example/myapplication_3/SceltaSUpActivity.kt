package com.example.myapplication_3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth

class SceltaSUpActivity : AppCompatActivity() {
    private lateinit var btnPersonale : CardView
    private lateinit var btnCanile : CardView

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scelta_profilo)

        btnPersonale = findViewById(R.id.materialCardView)
        btnCanile = findViewById(R.id.materialCardView2)

        auth = FirebaseAuth.getInstance()


        btnPersonale.setOnClickListener(){
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        btnCanile.setOnClickListener(){
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }



}