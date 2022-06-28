package com.shakir.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class game_multiplication extends AppCompatActivity {
    TextView score;
    TextView life;
    TextView time;

    TextView question;
    EditText answer;

    Button ok;
    Button next;

    Random random = new Random(); //to genenrate numbers continously
    int number1;
    int number2;

    int userAnswer;
    int realAnswer;

    int userScore=0;
    int userLife =3;


    CountDownTimer timer;
    private static final long START_TIMER_IN_MILIS=60000;
    Boolean timer_running;
    long time_left_in_milis = START_TIMER_IN_MILIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        score = findViewById(R.id.textViewScore_m);
        life = findViewById(R.id.textViewLife_m);
        time = findViewById(R.id.textViewTime_m);

        question = findViewById(R.id.textViewQuestion_m);
        answer = findViewById(R.id.editTextAnswer_m);

        ok = findViewById(R.id.buttonOk_m);
        next = findViewById(R.id.buttonQuestion_m);

        next.setEnabled(false);

        gameContinue();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userAnswer = Integer.valueOf(answer.getText().toString());  //on clicking the ok button , the answer in the edittext is taken converted into string and is finally taken into userAnswer int block, appropriate typecasting has been done for that


                pauseTimer();//when user clicks on ok button, the timer should stop, wether the answer is true or false


                if(userAnswer==realAnswer){
                    userScore = userScore + 10;
                    score.setText(""+userScore);
                    question.setText("Congratulations!!, your answer is true");
                }

                else{

                    userLife = userLife-1;
                    life.setText(""+userLife);    //this is to display, user remaining lifes
                    question.setText("sorry!, your answer is wrong");
                }

                next.setEnabled(true);
                ok.setEnabled(false);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer.setText(""); //as we click on next button, text in  edittext is cleared

                gameContinue();
                resetTimer();//as soon as player hits the next question button, the timer resets

                if(userLife <= 0){
                    Toast.makeText(getApplicationContext(),"Game Over!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(game_multiplication.this,Result.class);
                    intent.putExtra("score",userScore); //this will pass the key Value pair to the next activity
                    startActivity(intent);
                    finish();
                }
                else
                {
                    gameContinue();
                }


            }
        });



    }

    public void gameContinue(){

        ok.setEnabled(true);
        next.setEnabled(false);

        number1 = random.nextInt(100);          //number will be a number b/w 0 & 100
        number2 = random.nextInt(100);          //number will be a number b/w 0 & 100

        realAnswer = number1-number2;
        question.setText(number1+ " - " +number2);

        startTimer();

    }

    //code to handle timer

    public void startTimer(){
        timer = new CountDownTimer(time_left_in_milis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_milis = millisUntilFinished;
                updateText();

            }

            @Override
            public void onFinish() {
                timer_running =false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife = userLife -1;
                life.setText(""+userLife);
                question.setText("Sorry,Time is up!");


            }
        }.start();

        timer_running =true; //if it does not go to the onFinish() method, timer should remain ticking
    }

    public void updateText(){

        int second = (int)(time_left_in_milis/1000)%60;
        String time_left = String.format(Locale.getDefault(), "%02d",second);
        time.setText(time_left);

    }


    public void pauseTimer(){
        timer.cancel();
        timer_running=false;

    }

    public void resetTimer(){
        time_left_in_milis = START_TIMER_IN_MILIS;
        updateText();

    }
}