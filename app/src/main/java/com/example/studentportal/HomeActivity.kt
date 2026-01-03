package com.example.studentportal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Налаштування кнопок навігації
        val btnTasks = findViewById<Button>(R.id.btnTasks)
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        val btnHealth = findViewById<Button>(R.id.btnHealth)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // Перехід на екран завдань
        btnTasks.setOnClickListener {
            val intent = Intent(this, TasksActivity::class.java)
            startActivity(intent)
        }

        // Перехід на екран профілю
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Перехід на екран здоров'я
        btnHealth.setOnClickListener {
            val intent = Intent(this, HealthActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            auth.signOut()

            Toast.makeText(this, "Ви вийшли з системи", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, AuthActivity::class.java)
            // Очищуємо стек активностей, щоб користувач не міг повернутися назад кнопкою "Back"
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}