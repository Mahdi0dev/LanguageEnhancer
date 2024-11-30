package dev.mahdi0.language_enhancer

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import dev.mahdi0.language_enhancer.data.Word
import dev.mahdi0.language_enhancer.db.DbHelper
import soup.neumorphism.NeumorphButton

class AddWordActivity : AppCompatActivity() {
    private lateinit var wordEditText: EditText
    private lateinit var meaningEditText: EditText
    private lateinit var addButton: NeumorphButton
    private lateinit var dbHelper: DbHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_word)
        init()
    }

    private fun init() {
        bindViews()
        dbHelper = DbHelper(this)
        addButton.setOnClickListener {
            if (wordEditText.text.isEmpty()) {
                Toast.makeText(this, "Enter the Word!", Toast.LENGTH_SHORT).show()
            } else if (meaningEditText.text.isEmpty()) {
                Toast.makeText(this, "Enter the Meaning/Translation!", Toast.LENGTH_SHORT).show()
            } else {
                if (dbHelper.insert(
                        Word(
                            wordEditText.text.toString(),
                            meaningEditText.text.toString()
                        )
                    )
                ) {
                    Toast.makeText(this, "Word Added!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "This Word already exists!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun bindViews() {
        wordEditText = findViewById(R.id.keywordEditText)
        meaningEditText = findViewById(R.id.meaningEditText)
        addButton = findViewById(R.id.add_button)
    }
}
