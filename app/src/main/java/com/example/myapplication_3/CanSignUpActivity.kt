package com.example.myapplication_3
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.myapplication_3.databinding.ActivityCanSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class CanSignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCanSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // Modalità notte no

        binding = ActivityCanSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener {
            val nomeCanile = binding.nomeEt.text.toString()
            val telefono = binding.telefonoEt.text.toString()
            val email = binding.emailEt.text.toString()
            val paese = binding.paeseEt.text.toString()
            val provincia = binding.provinciaEt.text.toString()
            val citta = binding.cittaEt.text.toString()
            val cap = binding.capEt.text.toString()
            val via = binding.viaEt.text.toString()
            val password = binding.passwordEt.text.toString()
            val confermaPassword = binding.confermaPasswordEt.text.toString()

            if (nomeCanile.isNotEmpty() && telefono.isNotEmpty() && email.isNotEmpty() && paese.isNotEmpty() &&
                provincia.isNotEmpty() && citta.isNotEmpty() && cap.isNotEmpty() && via.isNotEmpty() &&
                password.isNotEmpty() && confermaPassword.isNotEmpty()) {

                if (password == confermaPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Le Password non corrispondono!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "I campi vuoti non sono ammessi!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
