package dev.mahdi0.language_enhancer.controller

import android.content.Context
import android.text.Editable
import android.widget.Toast
import dev.mahdi0.language_enhancer.db.DbHelper
import dev.mahdi0.language_enhancer.model.Word

class AddWordController(private val dbHelper: DbHelper, private val context: Context): AddWordContract {
    override fun addWord(wordText: Editable, meaningText: Editable): Boolean {
        if (wordText.isEmpty()) {
            Toast.makeText(context, "Enter the Word!", Toast.LENGTH_SHORT).show()
        } else if (meaningText.isEmpty()) {
            Toast.makeText(context, "Enter the Meaning/Translation!", Toast.LENGTH_SHORT).show()
        } else {
            if (dbHelper.insert(
                    Word(
                        wordText.toString(),
                        meaningText.toString()
                    )
                )
            ) {
                Toast.makeText(context, "Word Added!", Toast.LENGTH_LONG).show()
                return true //finish()
            } else {
                Toast.makeText(context, "This Word already exists!", Toast.LENGTH_SHORT).show()
            }
        }
        return false
    }
}