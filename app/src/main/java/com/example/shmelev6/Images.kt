package com.example.rubtsova_var6

import android.app.ListActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room

class Images : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var database: AppDatabase
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)
        editText = findViewById(R.id.editText)
        button = findViewById(R.id.btnAdd)
        database = Room.databaseBuilder(this, AppDatabase::class.java, "app_database").allowMainThreadQueries().build()
        sharedPreferences = getSharedPreferences("smart_watch_prefs", Context.MODE_PRIVATE)
        button.setOnClickListener {
            val text = editText.text.toString().trim()
            if (text.isNotEmpty()) {
                saveToSharedPreferences("ITEM_TEXT", text)
                val item = Item(name = text)
                database.itemDao().insert(item)
                val intent = Intent(this, ListActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun saveToSharedPreferences(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }
}