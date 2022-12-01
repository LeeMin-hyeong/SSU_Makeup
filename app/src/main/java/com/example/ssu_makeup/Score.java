package com.example.ssu_makeup;

import android.view.View;

public class Score {
    public static double[] total_score=new double[5];
    public static int[] question_count=new int[5];
    public static double[] score_Q1=new double[12];
    public static double[] score_Q2=new double[17];
    public static double[] score_Q3=new double[15];
    public static double[] score_Q4=new double[22];

    public static void addScore_2(View view, int Q_num, int question_num){
        double score=0;
        if(view.getId()==R.id.answer1)
            score=0;
        else
            score=1;

        if(Q_num==1)
            Score.score_Q1[question_num]=score;
        else if(Q_num==2)
            Score.score_Q2[question_num]=score;
        else if(Q_num==3)
            Score.score_Q3[question_num]=score;
        else if(Q_num==4)
            Score.score_Q4[question_num]=score;
        Score.question_count[Q_num]++;
    }
    public static void addScore_4(View view, int Q_num, int question_num){
        double score=0;
        if(view.getId()==R.id.answer1)
            score = 0;
        else if(view.getId()==R.id.answer2)
            score = 0.25;
        else if(view.getId()==R.id.answer3)
            score = 0.5;
        else if(view.getId()==R.id.answer4)
            score = 1;

        if(Q_num==1)
            Score.score_Q1[question_num]=score;
        else if(Q_num==2)
            Score.score_Q2[question_num]=score;
        else if(Q_num==3)
            Score.score_Q3[question_num]=score;
        else if(Q_num==4)
            Score.score_Q4[question_num]=score;
        Score.question_count[Q_num]++;
    }

    public static void addScore_5(View view, int Q_num, int question_num){
        double score=0;
        if(view.getId()==R.id.answer1)
            score = 0.2;
        else if(view.getId()==R.id.answer2)
            score = 0.4;
        else if(view.getId()==R.id.answer3)
            score = 0.6;
        else if(view.getId()==R.id.answer4)
            score = 0.8;
        else if(view.getId()==R.id.answer5)
            score = 1;

        if(Q_num==1)
            Score.score_Q1[question_num]=score;
        else if(Q_num==2)
            Score.score_Q2[question_num]=score;
        else if(Q_num==3)
            Score.score_Q3[question_num]=score;
        else if(Q_num==4)
            Score.score_Q4[question_num]=score;
        Score.question_count[Q_num]++;
    }

    public static void addScore_5_ignore(View view, int Q_num, int question_num){
        double score=0;
        if(view.getId()==R.id.answer5) {//문항을 무시하는 답변 처리
            if(Q_num==1)
                if(Score.score_Q1[question_num]>0) {
                    Score.score_Q1[question_num] = 0;
                    Score.question_count[Q_num]--;
                }
            else if(Q_num==2)
                if(Score.score_Q2[question_num]>0){
                    Score.score_Q2[question_num] = 0;
                    Score.question_count[Q_num]--;
                }
            else if(Q_num==3)
                if(Score.score_Q3[question_num]>0){
                    Score.score_Q3[question_num] = 0;
                    Score.question_count[Q_num]--;
                }
            else if(Q_num==4)
                if(Score.score_Q4[question_num]>0){
                    Score.score_Q4[question_num] = 0;
                    Score.question_count[Q_num]--;
                }
                return;
        }
        if(view.getId()==R.id.answer1)
            score = 0;
        else if(view.getId()==R.id.answer2)
            score = 0.25;
        else if(view.getId()==R.id.answer3)
            score = 0.5;
        else if(view.getId()==R.id.answer4)
            score = 1;

        if(Q_num==1)
            Score.score_Q1[question_num]=score;
        else if(Q_num==2)
            Score.score_Q2[question_num]=score;
        else if(Q_num==3)
            Score.score_Q3[question_num]=score;
        else if(Q_num==4)
            Score.score_Q4[question_num]=score;
        Score.question_count[Q_num]++;
    }

    public static void calculateTotal(int Q_num){
        double sum=0, result=0;
        if(Q_num==1)
            for(int i=1;i<=11;i++)
                sum+=Score.score_Q1[i];
        else if(Q_num==2)
            for(int i=1;i<=16;i++)
                sum+=Score.score_Q2[i];
        else if(Q_num==3)
            for(int i=1;i<=14;i++)
                sum+=Score.score_Q3[i];
        else if(Q_num==4)
            for(int i=1;i<=21;i++)
                sum+=Score.score_Q4[i];

        result=sum/Score.question_count[Q_num]*100;
        Score.total_score[Q_num]=result;
    }
}