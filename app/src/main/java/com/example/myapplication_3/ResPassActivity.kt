package com.example.myapplication_3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class ResPassActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var btnResetPassw: Button
    private lateinit var btnSignIn: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView((R.layout.activity_res_pass))

        etEmail = findViewById((R.id.emailEt))
        btnResetPassw = findViewById(R.id.button)


        auth = FirebaseAuth.getInstance()

        btnResetPassw.setOnClickListener {
            val sEmail = etEmail.text.toString()
            auth.sendPasswordResetEmail((sEmail))
                .addOnSuccessListener {
                    Toast.makeText(this, "Per favore controlla la tua Email", Toast.LENGTH_SHORT)
                        .show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                }


        }
        btnSignIn = findViewById(R.id.button1)
            btnSignIn.setOnClickListener {
                val intent = Intent(this,SignInActivity::class.java)
                startActivity(intent)
    }

    }
}