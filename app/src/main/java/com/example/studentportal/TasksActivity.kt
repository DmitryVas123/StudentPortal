package com.example.studentportal

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TasksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        // Налаштування заголовка у верхній панелі (ActionBar)
        supportActionBar?.apply {
            title = "Завдання та дедлайни"
            setDisplayHomeAsUpEnabled(true) // Показати кнопку "Назад"
        }
    }

    // Обробка натискання кнопки "Назад" у ActionBar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}