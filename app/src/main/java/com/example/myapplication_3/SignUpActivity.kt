package com.example.myapplication_3
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication_3.SignInActivity
import com.example.myapplication_3.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener {
            val nome = binding.nomeEt.text.toString()
            val cognome = binding.cognomeEt.text.toString()
            val username = binding.usernameEt.text.toString()
            val telefono = binding.telefonoEt.text.toString()
            val email = binding.emailEt.text.toString()
            val paese = binding.paeseEt.text.toString()
            val provincia = binding.provinciaEt.text.toString()
            val citta = binding.cittaEt.text.toString()
            val cap = binding.capEt.text.toString()
            val via = binding.viaEt.text.toString()
            val password = binding.passwordEt.text.toString()
            val confermaPassword = binding.confermaPasswordEt.text.toString()

            if (nome.isNotEmpty() && cognome.isNotEmpty() && username.isNotEmpty() && telefono.isNotEmpty() &&
                email.isNotEmpty() && paese.isNotEmpty() && provincia.isNotEmpty() && citta.isNotEmpty() &&
                cap.isNotEmpty() && via.isNotEmpty() && password.isNotEmpty() && confermaPassword.isNotEmpty()) {

                if (password == confermaPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            // Aggiungi i dati dell'utente a Firestore
                            val user = hashMapOf(
                                "nome" to nome,
                                "cognome" to cognome,
                                "username" to username,
                                "telefono" to telefono,
                                "email" to email,
                                "paese" to paese,
                                "provincia" to provincia,
                                "citta" to citta,
                                "cap" to cap,
                                "via" to via
                            )

                            db.collection("utenti")
                                .add(user)
                                .addOnSuccessListener { documentReference ->
                                    Log.d("SignUpActivity_db", "Utente aggiunto con ID: ${documentReference.id}")
                                    val intent = Intent(this, SignInActivity::class.java)
                                    startActivity(intent)
                                }
                                .addOnFailureListener { e ->
                                    Log.w("SignUpActivity_db", "Errore nell'aggiunta dell'utente", e)
                                    Toast.makeText(this, "Errore nella registrazione dei dati", Toast.LENGTH_SHORT).show()
                                }
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
