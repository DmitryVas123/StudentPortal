package com.example.studentportal

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class ProfileActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private lateinit var imgStudent: ImageView
    private lateinit var tvFullName: TextView
    private lateinit var tvGroup: TextView
    private lateinit var tvSpecialty: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvStudentId: TextView
    private lateinit var tvAboutMe: TextView
    private lateinit var tvPhoneNumber: TextView
    private lateinit var tvFaculty: TextView

    private var studentProfile: StudentProfile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Налаштування заголовка
        supportActionBar?.apply {
            title = "Профіль студента"
            setDisplayHomeAsUpEnabled(true)
        }

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Ініціалізація UI компонентів
        initializeViews()

        // Завантаження даних профілю
        loadProfileData()
    }


    // Повернення на головний екран при натисканні стрілки
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun initializeViews() {
        imgStudent = findViewById(R.id.imgStudent)
        tvFullName = findViewById(R.id.tvFullName)
        tvGroup = findViewById(R.id.tvGroup)
        tvSpecialty = findViewById(R.id.tvSpecialty)
        tvEmail = findViewById(R.id.tvEmail)
        tvStudentId = findViewById(R.id.tvStudentId)
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber)
        tvFaculty = findViewById(R.id.tvFaculty)
        tvAboutMe = findViewById(R.id.tvAboutMe)
    }

    private fun loadProfileData() {
        Toast.makeText(this, "Завантаження профілю...", Toast.LENGTH_SHORT).show()

        // Для Firebase інтеграції розкоментуйте:
        loadProfileFromFirebase()
    }

    private fun displayProfile(profile: StudentProfile) {
        tvFullName.text = profile.fullName
        tvGroup.text = "Група: ${profile.group}"
        tvSpecialty.text = profile.specialty
        tvEmail.text = profile.email
        tvStudentId.text = "№ ${profile.studentId}"
        tvAboutMe.text = profile.aboutMe

        // Відображення додаткових полів, якщо вони є
        profile.phoneNumber?.let {
            tvPhoneNumber.text = it
        }

        tvFaculty.text = profile.faculty

        // Завантаження фото (якщо є URL з Firebase Storage)
        // loadProfileImage(profile.photoUrl)
    }

    private fun loadProfileFromFirebase() {
        // Отримання поточного користувача
        val currentUser = auth.currentUser

        if (currentUser == null) {
            Toast.makeText(
                this,
                "Користувач не авторизований. Показано демо-дані.",
                Toast.LENGTH_LONG
            ).show()
            // Якщо користувач не авторизований - використовуємо дані за замовчуванням
            studentProfile = StudentProfile.getDefaultProfile()
            displayProfile(studentProfile!!)
            return
        }

        Toast.makeText(this, "User UID: ${currentUser.uid}", Toast.LENGTH_SHORT).show()

        // Запит до Firestore
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .document(currentUser.uid)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Конвертація даних з Firestore у модель
                    studentProfile = StudentProfile.fromFirestore(document)

                    if (studentProfile != null) {
                        displayProfile(studentProfile!!)

                        // Завантаження фото з Firebase Storage
                        studentProfile?.photoUrl?.let { url ->
                            loadProfileImage(url)
                        }
                    }
                } else {
                    // Документ не знайдено - використовуємо дані за замовчуванням
                    studentProfile = StudentProfile.getDefaultProfile()
                    displayProfile(studentProfile!!)
                }
            }
            .addOnFailureListener { exception ->
                // Обробка помилки
                Toast.makeText(
                    this,
                    "Помилка завантаження профілю: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()

                // Використовуємо дані за замовчуванням
                studentProfile = StudentProfile.getDefaultProfile()
                displayProfile(studentProfile!!)
            }
    }

    private fun loadProfileImage(imageUrl: String?) {
        if (imageUrl.isNullOrEmpty()) {
            // Використовуємо локальне зображення за замовчуванням
            imgStudent.setImageResource(R.drawable.student_photo)
            return
        }
        imgStudent.setImageResource(R.drawable.student_photo)
        return

        // Завантаження зображення через Glide
//        Glide.with(this)
//            .load(imageUrl)
//            .placeholder(R.drawable.student_photo)  // Placeholder під час завантаження
//            .error(R.drawable.student_photo)         // Зображення при помилці
//            .circleCrop()                            // Кругла форма
//            .into(imgStudent)
    }
}