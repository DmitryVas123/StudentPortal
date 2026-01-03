package com.example.studentportal

import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HealthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health)

        // Підтримка кнопки "Назад" в ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Здоров'я та фокус"

        // Отримання посилань на чекбокси
        val checkWater = findViewById<CheckBox>(R.id.checkWater)
        val checkBreak = findViewById<CheckBox>(R.id.checkBreak)
        val checkEyes = findViewById<CheckBox>(R.id.checkEyes)
        val checkExercise = findViewById<CheckBox>(R.id.checkExercise)

        // Можна додати логіку збереження станів чекбоксів
        // наприклад, через SharedPreferences
    }

    // Обробка натискання кнопки "Назад"
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}