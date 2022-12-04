package com.example.ssu_makeup;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class Baumann {
    private final static float[] totalScore=new float[5];
    private final static int[] questionCount=new int[5];
    private final static double[] scoreQ1=new double[12];
    private final static double[] scoreQ2=new double[17];
    private final static double[] scoreQ3=new double[15];
    private final static double[] scoreQ4=new double[22];

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
    public static void addScore2Answers(View view, int questionNumber, int subQuestionNumber){
        double score;
        if(view.getId()==R.id.answer1)
            score=0;
        else
            score=1;

        if(questionNumber==1)
            Baumann.scoreQ1[subQuestionNumber]=score;
        else if(questionNumber==2)
            Baumann.scoreQ2[subQuestionNumber]=score;
        else if(questionNumber==3)
            Baumann.scoreQ3[subQuestionNumber]=score;
        else if(questionNumber==4)
            Baumann.scoreQ4[subQuestionNumber]=score;
        Baumann.questionCount[questionNumber]++;
    }
    public static void addScore4Answers(View view, int questionNumber, int subQuestionNumber){
        double score;
        if(view.getId()==R.id.answer1)
            score = 0;
        else if(view.getId()==R.id.answer2)
            score = 0.333333;
        else if(view.getId()==R.id.answer3)
            score = 0.666666;
        else
            score = 1;

        if(questionNumber==1)
            Baumann.scoreQ1[subQuestionNumber]=score;
        else if(questionNumber==2)
            Baumann.scoreQ2[subQuestionNumber]=score;
        else if(questionNumber==3)
            Baumann.scoreQ3[subQuestionNumber]=score;
        else if(questionNumber==4)
            Baumann.scoreQ4[subQuestionNumber]=score;
        Baumann.questionCount[questionNumber]++;
    }

    public static void addScore5Answers(View view, int questionNumber, int subQuestionNumber){
        double score;
        if(view.getId()==R.id.answer1)
            score = 0;
        else if(view.getId()==R.id.answer2)
            score = 0.25;
        else if(view.getId()==R.id.answer3)
            score = 0.5;
        else if(view.getId()==R.id.answer4)
            score = 0.75;
        else
            score = 1;

        if(questionNumber==1)
            Baumann.scoreQ1[subQuestionNumber]=score;
        else if(questionNumber==2)
            Baumann.scoreQ2[subQuestionNumber]=score;
        else if(questionNumber==3)
            Baumann.scoreQ3[subQuestionNumber]=score;
        else if(questionNumber==4)
            Baumann.scoreQ4[subQuestionNumber]=score;
        Baumann.questionCount[questionNumber]++;
    }

    public static void addScore5AnswersIgnore(View view, int questionNumber, int subQuestionNumber){
        double score;
        if(view.getId()==R.id.answer5) {//문항을 무시하는 답변 처리
            if(questionNumber==1){
                if(Baumann.scoreQ1[subQuestionNumber]>0) {
                    Baumann.scoreQ1[subQuestionNumber] = 0;
                    Baumann.questionCount[questionNumber]--;
                }
            }
            else if(questionNumber==2){
                if(Baumann.scoreQ2[subQuestionNumber]>0){
                    Baumann.scoreQ2[subQuestionNumber] = 0;
                    Baumann.questionCount[questionNumber]--;
                }
            }
            else if(questionNumber==3){
                if(Baumann.scoreQ3[subQuestionNumber]>0){
                    Baumann.scoreQ3[subQuestionNumber] = 0;
                    Baumann.questionCount[questionNumber]--;
                }
            }
            else if(questionNumber==4)
                if(Baumann.scoreQ4[subQuestionNumber]>0){
                    Baumann.scoreQ4[subQuestionNumber] = 0;
                    Baumann.questionCount[questionNumber]--;
                }
        }
        if(view.getId()==R.id.answer1)
            score = 0;
        else if(view.getId()==R.id.answer2)
            score = 0.333333;
        else if(view.getId()==R.id.answer3)
            score = 0.666666;
        else
            score = 1;

        if(questionNumber==1)
            Baumann.scoreQ1[subQuestionNumber]=score;
        else if(questionNumber==2)
            Baumann.scoreQ2[subQuestionNumber]=score;
        else if(questionNumber==3)
            Baumann.scoreQ3[subQuestionNumber]=score;
        else if(questionNumber==4)
            Baumann.scoreQ4[subQuestionNumber]=score;
        Baumann.questionCount[questionNumber]++;
    }

    public static String analyzeSurveyResult(){
        String result;
        for(int i=1; i<=11 ;i++)
            Baumann.totalScore[1]+= Baumann.scoreQ1[i];
        for(int i=1; i<=16; i++)
            Baumann.totalScore[2]+= Baumann.scoreQ2[i];
        for(int i=1; i<=14; i++)
            Baumann.totalScore[3]+= Baumann.scoreQ3[i];
        for(int i=1; i<=21; i++)
            Baumann.totalScore[4]+= Baumann.scoreQ4[i];
        for(int i=1; i<=4; i++)
            Baumann.totalScore[i]=totalScore[i]/questionCount[i]*100;
        if(totalScore[1]>=50) result="O";
        else result="D";
        if(totalScore[2]>=50) result+="S";
        else result+="R";
        if(totalScore[3]>=50) result+="P";
        else result+="N";
        if(totalScore[4]>=50) result+="W";
        else result+="T";

        return result;
    }
    public static float getQ1ResultPercent(){ return totalScore[1]; }
    public static float getQ2ResultPercent(){ return totalScore[2]; }
    public static float getQ3ResultPercent(){ return totalScore[3]; }
    public static float getQ4ResultPercent(){ return totalScore[4]; }
    public static void clearResult(){
        totalScore[1]=0;totalScore[2]=0;totalScore[3]=0;totalScore[4]=0;
    }

}