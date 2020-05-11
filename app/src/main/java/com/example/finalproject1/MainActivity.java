package com.example.finalproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.gridlayout.widget.GridLayout;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridLayout grid;
    private Drawable colorImage;
    private ImageView[] imageViews;
    private int moleLocation;
    private int moleInt;

    public Button start;
    public Button settings;
    public Button help;

    public EditText triesBox;
    public int count;
    public UpdateMole update;
    public UpdateTime update2;

    public Random rand;
    public Handler handler;
    public boolean on;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.gridLayout);

        colorImage = getDrawable(R.color.red);

        start = findViewById(R.id.button);
        settings = findViewById(R.id.button3);
        help = findViewById(R.id.button2);

        handler = new Handler();
        imageViews = new ImageView[16];

        triesBox = findViewById(R.id.triesBox);
        count = 5;
        update = new UpdateMole();
        update2 = new UpdateTime();

        rand = new Random();
        on = false;
        moleLocation = rand.nextInt(16);
        moleInt = 1;

        imageViews[0] = (ImageView) getLayoutInflater().inflate(R.layout.activity_cardback, null);
        imageViews[0].setMinimumWidth(250);
        imageViews[0].setMinimumHeight(350);
        imageViews[0].setImageDrawable(colorImage);


        grid.addView(imageViews[0]);



    }

    public void startPressed (View v){
        if(on) {
            start.setText("START");
            on = false;
            handler.removeCallbacks(update);
            handler.removeCallbacks(update2);
        }
        else {
            on = true;
            start.setText("STOP");
            count = 30;
            double scoreValue = 0;
            scoreBox.setText(String.format("%.0f", scoreValue));
            handler.postDelayed(update, 1000);
            handler.postDelayed(update2, 1000);
        }

    }

    public void moleCheck(View v) {
        if(on) {
            if (v == imageViews[moleLocation]) {
                whack();
            } else {
                miss();
            }
        }

    }

    public void whack() {
        imageViews[moleLocation].setImageDrawable(null);
        String score = scoreBox.getText().toString();
        double scoreValue = Double.parseDouble(score);
        scoreValue++;
        scoreBox.setText(String.format("%.0f", scoreValue));
    }

    public void miss() {
        String score = scoreBox.getText().toString();
        double scoreValue = Double.parseDouble(score);
        scoreValue--;
        scoreBox.setText(String.format("%.0f", scoreValue));
    }


    public void newDiff (View v){
        Intent i = new Intent(this, ChangeDifficulty.class);
        i.putExtra("MOLE", moleInt);
        startActivityForResult(i, 1);
    }
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        moleInt = data.getIntExtra("MOLE", 1);
        if(moleInt == 1){
            this.colorImage = getDrawable(R.drawable.mole);
            imageViews[moleLocation].setImageDrawable(colorImage);
            imageViews[moleLocation].setMaxWidth(270);
            imageViews[moleLocation].setMaxHeight(270);
        }
        else if(moleInt == 2){
            this.colorImage = getDrawable(R.drawable.fish3);
            imageViews[moleLocation].setImageDrawable(colorImage);
            imageViews[moleLocation].setMaxWidth(270);
            imageViews[moleLocation].setMaxHeight(270);
        }
        else{
            this.colorImage = getDrawable(R.drawable.crocodile2);
            imageViews[moleLocation].setImageDrawable(colorImage);
            imageViews[moleLocation].setMaxWidth(270);
            imageViews[moleLocation].setMaxHeight(270);
        }
    }
*/
    public void helpPressed(View v){
        Intent i = new Intent(this, HelpActivity.class);
        startActivity(i);
    }


    private class UpdateMole implements Runnable{

        public void run(){
            imageViews[moleLocation].setImageDrawable(null);
            moleLocation = rand.nextInt(16);
            imageViews[moleLocation].setImageDrawable(mcolormage);
            handler.postDelayed(update, 1000);
        }

    }

    private class UpdateTime implements Runnable{
        public void run() {
            count--;
            timeBox.setText(count + "");
            String input = timeBox.getText().toString();
            double timeValue = Double.parseDouble(input);
            if (timeValue <= 0) {
                start.setText("RESTART");
                on = false;
                count = 0;
                handler.removeCallbacks(update);
                handler.removeCallbacks(update2);
            } else {
                handler.postDelayed(update2, 1000);
            }
        }
    }

}
