package dev.mahdi0.language_enhancer.db

object DbContract {
    class WordEntity {
        companion object {
            val TABLE_NAME = "Words"
            val KEYWORD_COLUMN_NAME = "Word"
            val MEANING_COLUMN_NAME = "Meaning"
            val STARRED_STATUS_COLUMN_NAME = "Starred_status"
        }
    }
}
