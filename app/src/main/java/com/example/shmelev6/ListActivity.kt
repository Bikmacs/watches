package com.example.rubtsova_var6

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.room.Room

class ListActivity : AppCompatActivity() {
    private lateinit var listContainer: LinearLayout
    private lateinit var database: AppDatabase
    private val items = ArrayList<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        listContainer = findViewById(R.id.listContainer)
        database = Room.databaseBuilder(this, AppDatabase::class.java, "app_database").allowMainThreadQueries().build()
        loadItemsFromDatabase()
        updateList()
    }

    private fun loadItemsFromDatabase() {
        val itemList = database.itemDao().getAll()
        items.clear()
        items.addAll(itemList.map { it.name })
    }

    private fun updateList() {
        listContainer.removeAllViews()
        for (item in items) {
            val textView = TextView(this)
            textView.text = item
            textView.setTextColor(resources.getColor(android.R.color.white))
            textView.textSize = 18f
            listContainer.addView(textView)
        }
    }
}