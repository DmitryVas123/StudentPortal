package com.example.studentportal

import com.google.firebase.firestore.DocumentSnapshot

data class StudentProfile(
    val fullName: String = "",
    val group: String = "",
    val specialty: String = "",
    val email: String = "",
    val studentId: String = "",
    val aboutMe: String = "",
    val photoUrl: String? = null,  // URL фото з Firebase Storage (опціонально)
    val phoneNumber: String? = null,
    val courseYear: Int = 1,
    val faculty: String = ""
) {
    companion object {
        /**
         * Статичний метод для створення профілю за замовчуванням
         * Використовується для локальної демонстрації
         */
        fun getDefaultProfile(): StudentProfile {
            return StudentProfile(
                fullName = "Василець Дмитро Ігорович",
                group = "ІТШІ-23-4",
                specialty = "Комп'ютерні науки",
                email = "dmytro.vasylets@nure.ua",
                studentId = "КВ 12345678",
                aboutMe = "Цікавлюся розробкою мобільних додатків та Штучним Інтелектом. " +
                        "У вільний час займаюся спортом, вивчаю англійську мову та люблю подорожувати.",
                phoneNumber = "+380 (95) 123-45-67",
                courseYear = 3,
                faculty = "Комп'ютерних наук"
            )
        }

        fun fromFirestore(document: DocumentSnapshot): StudentProfile? {
            return try {
                StudentProfile(
                    fullName = document.getString("fullName") ?: "",
                    group = document.getString("group") ?: "",
                    specialty = document.getString("specialty") ?: "",
                    email = document.getString("email") ?: "",
                    studentId = document.getString("studentId") ?: "",
                    aboutMe = document.getString("aboutMe") ?: "",
                    photoUrl = document.getString("photoUrl"),
                    phoneNumber = document.getString("phoneNumber"),
                    courseYear = document.getLong("courseYear")?.toInt() ?: 1,
                    faculty = document.getString("faculty") ?: ""
                )
            } catch (e: Exception) {
                null
            }
        }



    }

    fun toMap(): Map<String, Any?> {
        return mapOf(
            "fullName" to fullName,
            "group" to group,
            "specialty" to specialty,
            "email" to email,
            "studentId" to studentId,
            "aboutMe" to aboutMe,
            "photoUrl" to photoUrl,
            "phoneNumber" to phoneNumber,
            "courseYear" to courseYear,
            "faculty" to faculty
        )
    }
}