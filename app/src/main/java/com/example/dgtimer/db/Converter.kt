package com.example.dgtimer.db

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun convertIntListToString(intList: List<Int>): String {
        var str = ""
        for (num in intList) str += "$num,"
        return str
    }

    @TypeConverter
    fun convertStringToIntList(str: String): List<Int> {
        return str.split(",").filter { it.isNotEmpty() }.map { it.toInt() }
    }
}