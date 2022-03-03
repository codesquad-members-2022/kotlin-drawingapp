package com.example.kotlindrawingapp.square

data class ID(val id: String) {

    init {
        check(checkID(id)) { NOT_FORMAT_MESSAGE }
    }

    private fun checkID(id: String): Boolean {
        val splitID = id.split("-")
        return splitID.size >= 3
    }

    companion object {
        private const val NOT_FORMAT_MESSAGE = "형식에 올바르지 않습니다."

        fun generateID(): String {
            var list = listOf<String>()
            val alphabet = ('a'..'z').toList()
            val number = ('0'..'9').toList()
            val randomList = (alphabet + number)
            repeat(3) {
                list = list + getRandomChar(3, randomList)
            }
            return list.joinToString("-")
        }

        private fun getRandomChar(count: Int, randomList: List<Char>): String {
            var character: String = ""
            repeat(count) {
                character += randomList.random().toString()
            }
            return character
        }
    }
}
