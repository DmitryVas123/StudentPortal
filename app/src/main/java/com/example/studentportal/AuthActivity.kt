package com.example.studentportal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvSkipLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        // Ініціалізація Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Перевірка, чи користувач вже увійшов
        if (auth.currentUser != null) {
            // Користувач авторизований - переходимо на головний екран
            startHomeActivity()
            return
        }

        // Ініціалізація UI елементів
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvSkipLogin = findViewById(R.id.tvSkipLogin)

        // Обробник кнопки входу
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty()) {
                etEmail.error = "Введіть email"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                etPassword.error = "Введіть пароль"
                return@setOnClickListener
            }

            loginUser(email, password)
        }

        // Кнопка "Пропустити" - для демонстрації без авторизації
        tvSkipLogin.setOnClickListener {
            Toast.makeText(
                this,
                "Вхід пропущено. Показано демо-дані.",
                Toast.LENGTH_SHORT
            ).show()
            startHomeActivity()
        }
    }

    private fun loginUser(email: String, password: String) {
        // Показуємо повідомлення про завантаження
        btnLogin.isEnabled = false
        btnLogin.text = "Вхід..."

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                Toast.makeText(
                    this,
                    "✓ Успішний вхід!",
                    Toast.LENGTH_SHORT
                ).show()
                startHomeActivity()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    this,
                    "Помилка входу: ${exception.message}",
                    Toast.LENGTH_LONG
                ).show()

                // Відновлюємо кнопку
                btnLogin.isEnabled = true
                btnLogin.text = "Увійти"
            }
    }

    private fun startHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish() // Закриваємо LoginActivity, щоб не можна було повернутися назад
    }
}