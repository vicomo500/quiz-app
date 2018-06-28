package com.vicomo.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ListView quizListView;
List<Question> questions;
QuizAdapter quizAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quizListView = (ListView) findViewById(R.id.quizListView);
        questions = new ArrayList<Question>();
        questions.add(new Question("1. Which ocean surrounds the Maldives ?", "A. Pacific Ocean", "B. Atlantic Ocean", "C. Indian Ocean", "C. Indian Ocean"));
        questions.add(new Question("2. What is the name of Europe's most northern town ?", "A. Longyearbyen, Norway", "B. Hammerfest, Norway", "C. Reykjavik, Iceland", "B. Hammerfest, Norway"));
        questions.add(new Question("3. Which Russian town suffered an infamous nuclear disaster in 1986 ?", "A. Chernobyl", "B. Moscow", "C. Makhachkala", "A. Chernobyl"));
        questions.add(new Question("4. Name the American actress famous for her violet eyes and voluptuous figure, who found fame as an Egyptian.", "A. Marilyn Monroe", "B. Grace Kelly", "C. Elizabeth Taylor", "C. Elizabeth Taylor"));
        
        quizAdapter = new QuizAdapter(questions);
        quizListView.setAdapter(quizAdapter);

    }

    public void calculate(View view){
        int counter;
        int score = 0;
        String finalScore = "";
        for(counter = 0; counter < quizListView.getCount(); counter ++){
            View rel = (View) quizListView.getChildAt(counter);
            RadioGroup rGroup = rel.findViewById(R.id.optionsGroup);
            if(rGroup.getCheckedRadioButtonId() == -1){
                Toast.makeText(getApplicationContext(),"All questions are compulsory",Toast.LENGTH_LONG ).show();
                break;
            }else{
                RadioButton checkedRadioButton = (RadioButton)rGroup.findViewById(rGroup.getCheckedRadioButtonId());
                QuizAdapter qAdapter = (QuizAdapter)quizListView.getAdapter();
                Question q = (Question) qAdapter.getItem(counter);
                if(checkedRadioButton.getText().toString().equalsIgnoreCase(q.getAnswer())){
                    score++;
                }
            }
        }
        //Toast.makeText(getApplicationContext(),"Total Score = "+score+" / "+quizListView.getCount(),Toast.LENGTH_LONG ).show();

        if(counter == quizListView.getCount()){
            finalScore = "Total Score = "+score+" / "+quizListView.getCount();
            Intent intent = new Intent("com.vicomo.quiz.SCORE");
            intent.putExtra("Scores", finalScore);
            startActivity(intent);
        }

    }
    class QuizAdapter extends BaseAdapter{
        List<Question> questions;
        public QuizAdapter(List<Question> list){
            questions = list;
        }
        @Override
        public int getCount() {
            return questions.size();
        }

        @Override
        public Object getItem(int i) {
            return questions.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.question_layout, null);
            TextView question = view.findViewById(R.id.question);
            RadioButton optionA = view.findViewById(R.id.optionA);
            RadioButton optionB = view.findViewById(R.id.optionB);
            RadioButton optionC = view.findViewById(R.id.optionC);
            question.setText(questions.get(i).getDesc());
            optionA.setText(questions.get(i).getOptionA());
            optionB.setText(questions.get(i).getOptionB());
            optionC.setText(questions.get(i).getOptionC());
            return view;
        }
    }
}
