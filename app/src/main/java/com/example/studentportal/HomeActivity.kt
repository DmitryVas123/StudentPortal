package com.example.studentportal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Налаштування кнопок навігації
        val btnTasks = findViewById<Button>(R.id.btnTasks)
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        val btnHealth = findViewById<Button>(R.id.btnHealth)

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
    }
}