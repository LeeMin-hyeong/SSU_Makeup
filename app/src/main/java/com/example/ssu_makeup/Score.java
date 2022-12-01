package com.example.ssu_makeup;

import android.view.View;

public class Score {
    static double[] totalScore=new double[5];
    static int[] questionCount=new int[5];
    static double[] scoreQ1=new double[12];
    static double[] scoreQ2=new double[17];
    static double[] scoreQ3=new double[15];
    static double[] scoreQ4=new double[22];

    public static void addScore_2(View view, int questionNumber, int subQuestionNumber){
        double score;
        if(view.getId()==R.id.answer1)
            score=0;
        else
            score=1;

        if(questionNumber==1)
            Score.scoreQ1[subQuestionNumber]=score;
        else if(questionNumber==2)
            Score.scoreQ2[subQuestionNumber]=score;
        else if(questionNumber==3)
            Score.scoreQ3[subQuestionNumber]=score;
        else if(questionNumber==4)
            Score.scoreQ4[subQuestionNumber]=score;
        Score.questionCount[questionNumber]++;
    }
    public static void addScore_4(View view, int questionNumber, int subQuestionNumber){
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
            Score.scoreQ1[subQuestionNumber]=score;
        else if(questionNumber==2)
            Score.scoreQ2[subQuestionNumber]=score;
        else if(questionNumber==3)
            Score.scoreQ3[subQuestionNumber]=score;
        else if(questionNumber==4)
            Score.scoreQ4[subQuestionNumber]=score;
        Score.questionCount[questionNumber]++;
    }

    public static void addScore_5(View view, int questionNumber, int subQuestionNumber){
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
            Score.scoreQ1[subQuestionNumber]=score;
        else if(questionNumber==2)
            Score.scoreQ2[subQuestionNumber]=score;
        else if(questionNumber==3)
            Score.scoreQ3[subQuestionNumber]=score;
        else if(questionNumber==4)
            Score.scoreQ4[subQuestionNumber]=score;
        Score.questionCount[questionNumber]++;
    }

    public static void addScore_5_ignore(View view, int questionNumber, int subQuestionNumber){
        double score;
        if(view.getId()==R.id.answer5) {//문항을 무시하는 답변 처리
            if(questionNumber==1){
                if(Score.scoreQ1[subQuestionNumber]>0) {
                    Score.scoreQ1[subQuestionNumber] = 0;
                    Score.questionCount[questionNumber]--;
                }
            }
            else if(questionNumber==2){
                if(Score.scoreQ2[subQuestionNumber]>0){
                    Score.scoreQ2[subQuestionNumber] = 0;
                    Score.questionCount[questionNumber]--;
                }
            }
            else if(questionNumber==3){
                if(Score.scoreQ3[subQuestionNumber]>0){
                    Score.scoreQ3[subQuestionNumber] = 0;
                    Score.questionCount[questionNumber]--;
                }
            }
            else if(questionNumber==4)
                if(Score.scoreQ4[subQuestionNumber]>0){
                    Score.scoreQ4[subQuestionNumber] = 0;
                    Score.questionCount[questionNumber]--;
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
            Score.scoreQ1[subQuestionNumber]=score;
        else if(questionNumber==2)
            Score.scoreQ2[subQuestionNumber]=score;
        else if(questionNumber==3)
            Score.scoreQ3[subQuestionNumber]=score;
        else if(questionNumber==4)
            Score.scoreQ4[subQuestionNumber]=score;
        Score.questionCount[questionNumber]++;
    }

    public static String analyzeSurveyResult(){
        String result;
        for(int i=1; i<=11 ;i++)
            Score.totalScore[1]+=Score.scoreQ1[i];
        for(int i=1; i<=16; i++)
            Score.totalScore[2]+=Score.scoreQ2[i];
        for(int i=1; i<=14; i++)
            Score.totalScore[3]+=Score.scoreQ3[i];
        for(int i=1; i<=21; i++)
            Score.totalScore[4]+=Score.scoreQ4[i];
        for(int i=1; i<=4; i++)
            Score.totalScore[i]=totalScore[i]/questionCount[i]*100;
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
}