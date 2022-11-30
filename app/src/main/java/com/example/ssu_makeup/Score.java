package com.example.ssu_makeup;

public class Score {
    public static double total_score;
    public static int question_count;
    public static double q1_result;
    public static double q2_result;
    public static double q3_result;
    public static double q4_result;

    public static void addScore(int n, int answer){
        double score=0;
        if(answer==0)//문항을 무시하는 답변은 0을 부여
            return;
        if(n==4)
            switch (answer){
                case 1:
                    score=0;
                case 2:
                    score=0.25;
                case 3:
                    score=0.5;
                case 4:
                    score=1;
            }
        else if(n==5)
            switch (answer) {
                case 1:
                    score = 0.2;
                case 2:
                    score = 0.4;
                case 3:
                    score = 0.6;
                case 4:
                    score = 0.8;
                case 5:
                    score = 1;
            }
        Score.total_score+=score;
        Score.question_count++;
    }
    public static void calculateTotal(int question_num){
        double result=Score.total_score/Score.question_count*100;
        switch (question_num){
            case 1:
                Score.q1_result=result;
            case 2:
                Score.q2_result=result;
            case 3:
                Score.q3_result=result;
            case 4:
                Score.q4_result=result;
        }
        initialize();
    }
    public static void initialize(){
        Score.question_count=0;
        Score.total_score=0;
    }
}