package com.example.dgtimer.db;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    @TypeConverter
    public String ListConverter(List<Integer> intList){
        String str = "";

        for (int num : intList)
            str += (num + ",");

        return str;
    }

    @TypeConverter
    public List<Integer> StringConverter(String str){
        List<Integer> intList = new ArrayList<>();
        String[] arrStr = str.split(",");

        for (String strNum : arrStr)
            intList.add(Integer.parseInt(strNum));

        return intList;
    }
}
