package com.example.ssu_makeup;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

//String 입력에 따라 해당 피부 타입의 색을 리턴해주는 함수
//Activity에서 사용 시 context에 this 사용
//Fragment에서 사용시 requireActivity() 사용
public class Baumann {
    public static int getColorByString(Context context, @NonNull final String skinType){
        switch (skinType){
            case "DSPT":
                return ContextCompat.getColor(context, R.color.DSPT);
            case "DSNT":
                return ContextCompat.getColor(context, R.color.DSNT);
            case "DSPW":
                return ContextCompat.getColor(context, R.color.DSPW);
            case "DSNW":
                return ContextCompat.getColor(context, R.color.DSNW);
            case "OSPT":
                return ContextCompat.getColor(context, R.color.OSPT);
            case "OSNT":
                return ContextCompat.getColor(context, R.color.OSNT);
            case "OSPW":
                return ContextCompat.getColor(context, R.color.OSPW);
            case "OSNW":
                return ContextCompat.getColor(context, R.color.OSNW);
            case "ORPT":
                return ContextCompat.getColor(context, R.color.ORPT);
            case "ORNT":
                return ContextCompat.getColor(context, R.color.ORNT);
            case "ORPW":
                return ContextCompat.getColor(context, R.color.ORPW);
            case "ORNW":
                return ContextCompat.getColor(context, R.color.ORNW);
            case "DRPT":
                return ContextCompat.getColor(context, R.color.DRPT);
            case "DRNT":
                return ContextCompat.getColor(context, R.color.DRNT);
            case "DRPW":
                return ContextCompat.getColor(context, R.color.DRPW);
            case "DRNW":
                return ContextCompat.getColor(context, R.color.DRNW);
            default:
                return ContextCompat.getColor(context, R.color.black);
        }
    }
//    public static int getDescriptionByString(Context context, @NonNull final String skinType){
//        switch (skinType){
//            case "DSPT":
//                return ContextCompat.getColor(context, R.string.DSPT);
//            case "DSNT":
//                return ContextCompat.getColor(context, R.string.DSNT);
//            case "DSPW":
//                return ContextCompat.getColor(context, R.string.DSPW);
//            case "DSNW":
//                return ContextCompat.getColor(context, R.string.DSNW);
//            case "OSPT":
//                return ContextCompat.getColor(context, R.string.OSPT);
//            case "OSNT":
//                return ContextCompat.getColor(context, R.string.OSNT);
//            case "OSPW":
//                return ContextCompat.getColor(context, R.string.OSPW);
//            case "OSNW":
//                return ContextCompat.getColor(context, R.string.OSNW);
//            case "ORPT":
//                return ContextCompat.getColor(context, R.string.ORPT);
//            case "ORNT":
//                return ContextCompat.getColor(context, R.string.ORNT);
//            case "ORPW":
//                return ContextCompat.getColor(context, R.string.ORPW);
//            case "ORNW":
//                return ContextCompat.getColor(context, R.string.ORNW);
//            case "DRPT":
//                return ContextCompat.getColor(context, R.string.DRPT);
//            case "DRNT":
//                return ContextCompat.getColor(context, R.string.DRNT);
//            case "DRPW":
//                return ContextCompat.getColor(context, R.string.DRPW);
//            case "DRNW":
//                return ContextCompat.getColor(context, R.string.DRNW);
//            default:
//                return -1;
//        }
//    }
}
