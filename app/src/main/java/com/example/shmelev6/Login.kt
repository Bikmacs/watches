package com.example.rubtsova_var6

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class Login : AppCompatActivity() {
    private lateinit var login: EditText
    private lateinit var pass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login = findViewById(R.id.login)
        pass = findViewById(R.id.password)

    }

    fun next(view: View) {
        if (login.text.toString().isNotEmpty() && pass.text.toString().isNotEmpty()) {
            if (pass.text. toString()== "ects2024" && login.text.toString() == "ects") {
                val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("username", login.text.toString())
                editor.putString("password", pass.text.toString())
                editor.apply()
                val intent = Intent(this, Main::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()            }
        }
        else
        {
            Toast.makeText(this, "Заполните поля", Toast.LENGTH_SHORT).show()
        }

    }
}

