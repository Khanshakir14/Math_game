package com.shakir.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
      Button addition;
      Button subtraction;
      Button multiplication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addition = findViewById(R.id.buttonAdd);
        subtraction = findViewById(R.id.buttonSub);
        multiplication = findViewById(R.id.buttonMulti);

        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Game.class);
                startActivity(intent);
                finish();
            }
        });

       subtraction.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent2= new Intent(MainActivity.this,Game_subtraction.class);
               startActivity(intent2);
               finish();

           }
       });

       multiplication.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent3= new Intent(MainActivity.this,game_multiplication.class);
               startActivity(intent3);
               finish();

           }
       });


    }
}