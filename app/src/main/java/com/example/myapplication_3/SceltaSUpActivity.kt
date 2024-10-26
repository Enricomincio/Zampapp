package com.example.myapplication_3

import com.example.myapplication_3.SignUpActivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth

class SceltaSUpActivity : AppCompatActivity() {
    private lateinit var btnPersonale : CardView
    private lateinit var btnCanile : CardView
    private lateinit var accesso : TextView

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scelta_profilo)

        btnPersonale = findViewById(R.id.materialCardView)
        btnCanile = findViewById(R.id.materialCardView2)
        accesso = findViewById((R.id.textView))

        auth = FirebaseAuth.getInstance()

        accesso.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }


        btnPersonale.setOnClickListener(){
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        btnCanile.setOnClickListener(){
            val intent = Intent(this,CanSignUpActivity::class.java)
            startActivity(intent)
        }
    }



}