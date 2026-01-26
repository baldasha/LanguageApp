package com.example.languageapp.core.auth

import android.content.SharedPreferences
import androidx.core.content.edit

interface AuthStorage {
    fun isDataThere(): Boolean
    fun getData(): Profile
    fun saveData(email: String, password: String, name: String, imageUri: String? = null)
    fun checkData(email: String, password: String): Boolean
    fun clearData()
    fun saveImageUri(imageUri: String?)
}

class AuthStorageImpl(
    private val sharedPreferences: SharedPreferences
) : AuthStorage {

    override fun isDataThere(): Boolean {
        return sharedPreferences.getString("email", null).isNullOrBlank().not() &&
                sharedPreferences.getString("password", null).isNullOrBlank().not() &&
                sharedPreferences.getString("name", null).isNullOrBlank().not()
    }

    override fun getData(): Profile {
        return Profile(
            email = sharedPreferences.getString("email", null),
            name = sharedPreferences.getString("name", null),
            password = sharedPreferences.getString("password", null),
            imageUri = sharedPreferences.getString("image_uri", null)
        )
    }

    override fun saveData(email: String, password: String, name: String, imageUri: String?) {
        sharedPreferences.edit {
            putString("email", email)
            putString("name", name)
            putString("password", password)
        }
    }

    override fun checkData(email: String, password: String): Boolean {
        val sharedEmail = sharedPreferences.getString("email", null)
        val sharedPassword = sharedPreferences.getString("password", null)
        return sharedEmail == email && sharedPassword == password
    }

    override fun clearData() {
        sharedPreferences.edit {
            remove("email")
            remove("name")
            remove("password")
            remove("image_uri")
        }
    }

    override fun saveImageUri(imageUri: String?) {
        sharedPreferences.edit {
            putString("image_uri", imageUri)
        }
    }
}