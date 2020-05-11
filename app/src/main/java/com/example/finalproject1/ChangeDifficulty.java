package com.example.finalproject1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeDifficulty extends AppCompatActivity {

    private RadioButton eightButton;
    private RadioButton twelveButton;
    private RadioButton sixteenButton;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_difficulty);

        eightButton = findViewById(R.id.eightButton);
        twelveButton = findViewById(R.id.twelveButton);
        sixteenButton = findViewById(R.id.sixteenButton);
        Intent intent = getIntent();
        int diffInt = intent.getIntExtra("DIFF", 1);
        if(diffInt == 1) {
            eightButton.setChecked(true);
        } else if(diffInt == 2) {
            twelveButton.setChecked(true);
        } else {
            sixteenButton.setChecked(true);
        }

    }

    @Override
    public void onBackPressed(){
        int diff;
        if (eightButton.isChecked()){
            diff = 1;
        }
        else if(twelveButton.isChecked()){
            diff = 2;
        }
        else{
            diff = 3;
        }
        Intent i = new Intent();
        i.putExtra("DIFF", diff);
        setResult(RESULT_OK, i);
        finish();
    }

}