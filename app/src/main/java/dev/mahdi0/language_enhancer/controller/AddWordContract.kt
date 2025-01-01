package dev.mahdi0.language_enhancer.controller

import android.text.Editable

interface AddWordContract {
    fun addWord(wordText: Editable, meaningText: Editable): Boolean
}
