package dev.mahdi0.language_enhancer.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.mahdi0.language_enhancer.R
import dev.mahdi0.language_enhancer.adapters.WordAdapter
import dev.mahdi0.language_enhancer.db.DbHelper

class MainActivity : AppCompatActivity() {
    private lateinit var mainList: RecyclerView
    private lateinit var addBtn: soup.neumorphism.NeumorphImageButton
    private lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onResume() {
        super.onResume()
        init()
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        bindViews()
        val context = this
        dbHelper = DbHelper(context)
        mainList.layoutManager = LinearLayoutManager(context)
        refreshList(context)
        addBtn.setOnClickListener {
            startActivity(Intent(context, AddWordActivity::class.java))
            refreshList(context)
        }
        refreshList(context)
    }


    private fun refreshList(context: Context) {
        mainList.adapter = WordAdapter(context, dbHelper.getAll(), dbHelper, mainList)
    }

    private fun bindViews() {
        mainList = findViewById(R.id.main_list)
        addBtn = findViewById(R.id.add_btn)
    }
}
