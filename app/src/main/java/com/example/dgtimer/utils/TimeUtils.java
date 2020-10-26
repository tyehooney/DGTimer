package com.example.dgtimer.utils;

public class TimeUtils {
    public static int stageToSecond(int stage){
        int second = 0;

        switch (stage){
            case 1:
                second = 8;
                break;
            case 2:
                second = 10;
                break;
            case 3:
                second = 15;
                break;
            case 4:
                second = 21;
                break;
            case 5:
                second = 24;
                break;
            case 6:
                second = 28;
                break;
            case 7:
                second = 38;
                break;
        }

        return second;
    }
}
