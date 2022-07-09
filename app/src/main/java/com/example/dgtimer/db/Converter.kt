package com.example.dgtimer.db

import androidx.room.TypeConverter
import java.util.ArrayList

class Converter {
    @TypeConverter
    fun convertIntListToString(intList: List<Int>): String {
        var str = ""
        for (num in intList) str += "$num,"
        return str
    }

    @TypeConverter
    fun convertStringToIntList(str: String): List<Int> {
        val intList: MutableList<Int> = ArrayList()
        val arrStr = str.split(",").toTypedArray()
        for (strNum in arrStr) intList.add(strNum.toInt())
        return intList
    }
}