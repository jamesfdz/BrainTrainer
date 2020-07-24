package com.codingwithjames.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView mTimerView, mScoreView, mSumView, mAnswerView;
    Button mStartBtn, mNum, mNum1, mNum2, mNum3, mPlayAgain;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //casting the textview variables

        mTimerView = findViewById(R.id.timerTV);
        mScoreView = findViewById(R.id.scoreTV);
        mSumView = findViewById(R.id.sumTV);
        mAnswerView = findViewById(R.id.answerTV);
//        mStartBtn = findViewById(R.id.startBtn);
        mNum = findViewById(R.id.numBtn);
        mNum1 = findViewById(R.id.num1Btn);
        mNum2 = findViewById(R.id.num2Btn);
        mNum3 = findViewById(R.id.num3Btn);
        mPlayAgain = findViewById(R.id.playAgain);

        playAgain(mPlayAgain);

    }

    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            mAnswerView.setText("Correct !!!");
        }else{
            mAnswerView.setText("Incorrect !!!");
        }

        numberOfQuestions++;
        mScoreView.setText(Integer.toString(score)+ "/" +Integer.toString(numberOfQuestions));
        generateQuestion();

    }

    public void start(View view){
//        mStartBtn.setVisibility(View.INVISIBLE);
    }

    public void generateQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(51);
        int b = rand.nextInt(51);

        mSumView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for (int i = 0; i < 4; i++){
            if (i == locationOfCorrectAnswer){
                answers.add(a + b);
            }else {

                incorrectAnswer = rand.nextInt(101);

                while(incorrectAnswer == a + b){
                    incorrectAnswer = rand.nextInt(101);
                }

                answers.add(incorrectAnswer);
            }
        }

        mNum.setText(Integer.toString(answers.get(0)));
        mNum1.setText(Integer.toString(answers.get(1)));
        mNum2.setText(Integer.toString(answers.get(2)));
        mNum3.setText(Integer.toString(answers.get(3)));
    }

    public void playAgain(View view) {

        score = 0;
        numberOfQuestions = 0;

        mTimerView.setText("30s");
        mScoreView.setText("0/0");
        mAnswerView.setText("");
        mPlayAgain.setVisibility(View.INVISIBLE);

        generateQuestion();


        new CountDownTimer(30000, 1000){
            @Override
            public void onTick(long l) {
                mTimerView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {

                mPlayAgain.setVisibility(View.VISIBLE);

                mTimerView.setText("0s");

                mAnswerView.setText("Your Score: "+Integer.toString(score)+ "/" +Integer.toString(numberOfQuestions));
            }
        }.start();

    }
}
