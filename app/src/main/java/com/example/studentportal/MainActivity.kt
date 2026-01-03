package com.example.studentportal

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            // Користувач авторизований - переходимо на Home
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        } else {
            // Користувач не авторизований - переходимо на Login
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
        finish()
    }
}