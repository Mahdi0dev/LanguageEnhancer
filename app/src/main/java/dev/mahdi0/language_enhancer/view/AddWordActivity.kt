package dev.mahdi0.language_enhancer.view

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import dev.mahdi0.language_enhancer.R
import dev.mahdi0.language_enhancer.controller.AddWordController
import dev.mahdi0.language_enhancer.db.DbHelper
import soup.neumorphism.NeumorphButton

class AddWordActivity : AppCompatActivity() {
    private lateinit var wordEditText: EditText
    private lateinit var meaningEditText: EditText
    private lateinit var addButton: NeumorphButton
    private lateinit var dbHelper: DbHelper
    private lateinit var controller: AddWordController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_word)
        init()
    }

    private fun init() {
        bindViews()
        dbHelper = DbHelper(this)
        controller = AddWordController(dbHelper, this)
        addButton.setOnClickListener {
            if (controller.addWord(wordEditText.text, meaningEditText.text)) {
                finish()
            }
        }
    }

    private fun bindViews() {
        wordEditText = findViewById(R.id.keywordEditText)
        meaningEditText = findViewById(R.id.meaningEditText)
        addButton = findViewById(R.id.add_button)
    }
}
