package com.vicomo.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Score extends AppCompatActivity {
TextView scoreTextView;
String score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreTextView = (TextView) findViewById(R.id.scores);
        score = getIntent().getStringExtra("Scores");
        scoreTextView.setText(score);
    }

    public void goBack(View view){
        startActivity(getParentActivityIntent());
    }
}
