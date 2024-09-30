package dev.mahdi0.language_enhancer

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.mahdi0.language_enhancer.adapters.WordAdapter
import dev.mahdi0.language_enhancer.data.MockData

class MainActivity : AppCompatActivity() {
    private lateinit var mainList: RecyclerView
    private lateinit var addBtn: soup.neumorphism.NeumorphImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        bindViews()
        mainList.layoutManager = LinearLayoutManager(this)
        mainList.adapter = WordAdapter(this, MockData.getDataList())
        addBtn.setOnClickListener {
            Toast.makeText(this, "As soon as possible...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bindViews() {
        mainList = findViewById(R.id.main_list)
        addBtn = findViewById(R.id.add_btn)
    }
}
