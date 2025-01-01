package dev.mahdi0.language_enhancer.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import dev.mahdi0.language_enhancer.adapters.WordAdapter
import dev.mahdi0.language_enhancer.model.Word

class DbHelper(context: Context, name: String = "language_enhancer", version: Int = 1) :
    SQLiteOpenHelper(context, name, null, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE ${DbContract.WordEntity.TABLE_NAME} (${DbContract.WordEntity.KEYWORD_COLUMN_NAME} TEXT PRIMARY KEY, ${DbContract.WordEntity.MEANING_COLUMN_NAME} TEXT, ${DbContract.WordEntity.STARRED_STATUS_COLUMN_NAME} NUMERIC)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${DbContract.WordEntity.TABLE_NAME}")
        onCreate(db)
    }

    fun insert(word: Word): Boolean {
        val db = writableDatabase
        val wordsArray = getAll()

        val words = wordsArray.map { x ->
            x.word
        }

        if (words.indexOf(word.word) == -1) {
            val values = ContentValues()
            values.put(DbContract.WordEntity.KEYWORD_COLUMN_NAME, word.word)
            values.put(DbContract.WordEntity.MEANING_COLUMN_NAME, word.translation)
            values.put(DbContract.WordEntity.STARRED_STATUS_COLUMN_NAME, 0)
            db.insert(DbContract.WordEntity.TABLE_NAME, null, values)
            return true
        } else {
            return false
        }
    }

    @SuppressLint("Range")
    fun getAll(): ArrayList<Word> {
        val allWords = ArrayList<Word>()
        val db = readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery("SELECT * FROM ${DbContract.WordEntity.TABLE_NAME}", null)
        } catch (e: Exception) {
            println(e.message)
            onCreate(db)
            return ArrayList()
        }
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast) {
                    val keyword =
                        cursor.getString(cursor.getColumnIndex(DbContract.WordEntity.KEYWORD_COLUMN_NAME))
                    val meaning =
                        cursor.getString(cursor.getColumnIndex(DbContract.WordEntity.MEANING_COLUMN_NAME))
                    val starred =
                        cursor.getInt(cursor.getColumnIndex(DbContract.WordEntity.STARRED_STATUS_COLUMN_NAME)) == 1
                    allWords.add(Word(keyword, meaning, starred))
                    cursor.moveToNext()
                }
            } else return ArrayList()
            cursor.close()
        } else return ArrayList()
        return allWords
    }

    fun update(word: Word, context: Context): Boolean {
        try {
            val db = writableDatabase
            val values = ContentValues()
            val starredNumeric = if (word.starred) {
                1
            } else {
                0
            }
            values.put(DbContract.WordEntity.STARRED_STATUS_COLUMN_NAME, starredNumeric)
            db.update(
                DbContract.WordEntity.TABLE_NAME,
                values,
                "${DbContract.WordEntity.KEYWORD_COLUMN_NAME} = ?",
                arrayOf(word.word)
            )
            return true
        } catch (e: Exception) {
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            return false
        }
    }

    fun remove(word: Word, context: Context, list: RecyclerView, dbHelper: DbHelper) {
        val db = writableDatabase
        if (word.word.isNotEmpty()) {
            try {
                db.delete(
                    DbContract.WordEntity.TABLE_NAME,
                    "${DbContract.WordEntity.KEYWORD_COLUMN_NAME} = ?",
                    arrayOf(word.word)
                )
                list.adapter = WordAdapter(context, dbHelper.getAll(), dbHelper, list)
                Toast.makeText(context, "Removed.", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
        }
    }
}
