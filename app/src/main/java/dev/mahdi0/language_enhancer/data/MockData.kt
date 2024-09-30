package dev.mahdi0.language_enhancer.data

class MockData {
    companion object {
        private val dataList = mutableListOf<Word>()
        fun getDataList(): List<Word> {
            return dataList
        }

        init {
            dataList.clear()
            dataList.add(Word("Error is human.", "انسان جایزالخطاست."))
            dataList.add(Word("Get ahead", "پیشرفت‌کردن"))
            dataList.add(Word("No offence", "دور از جون", true))
            dataList.add(Word("I am as I am.", "من همینم که هستم."))
        }
    }
}
