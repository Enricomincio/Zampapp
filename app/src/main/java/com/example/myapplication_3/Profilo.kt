package com.example.myapplication_3

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication_3.databinding.FragmentProfiloBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Profilo : Fragment() {

    private lateinit var binding: FragmentProfiloBinding
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfiloBinding.inflate(inflater, container, false)

        // Recupera e visualizza il nome utente
        mostraNomeUtente()

        // Imposta il logout
        binding.btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            Toast.makeText(requireContext(), "Logout effettuato", Toast.LENGTH_SHORT).show()
            // Potresti anche aggiungere qui la logica per tornare alla schermata di accesso
            val intent = Intent(activity, SignInActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return binding.root
    }

    private fun mostraNomeUtente() {
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            db.collection("utenti").whereEqualTo("email", currentUser.email)
                .get()
                .addOnSuccessListener { documents ->
                    if (!documents.isEmpty) {
                        val user = documents.first()
                        val nome = user.getString("nome") ?: "Utente sconosciuto"
                        binding.tvUserName.text = nome
                    } else {
                        binding.tvUserName.text = "Utente sconosciuto"
                    }
                }
                .addOnFailureListener {
                    binding.tvUserName.text = "Errore nel caricamento"
                }
        } else {
            binding.tvUserName.text = "Utente non autenticato"
        }
    }
}
