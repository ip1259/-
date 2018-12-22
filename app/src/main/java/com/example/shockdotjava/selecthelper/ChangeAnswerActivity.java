package com.example.shockdotjava.selecthelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ChangeAnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_answer);

        Intent intent = getIntent();
        int groupIndex = intent.getIntExtra("groupIndex", 0);
        int selectionIndex = intent.getIntExtra("selectionIndex", 0);
        final int answerIndex = intent.getIntExtra("answerIndex", 0);
        String question = intent.getStringExtra("question");
        int resource = intent.getIntExtra("resource", 0);
        final Selection cur = DataActivity.groups.get(groupIndex).getSelectionOf(selectionIndex);
        String[] curAnswer;
        curAnswer = new String[cur.getAnswers().size()];
        switch (resource)
        {
            case R.array.QFood :
                if(answerIndex == 0)
                    curAnswer = getResources().getStringArray(R.array.AFood_1);
                if(answerIndex == 1)
                    curAnswer = getResources().getStringArray(R.array.AFood_2);
                if(answerIndex == 2)
                    curAnswer = getResources().getStringArray(R.array.AFood_3);
                if(answerIndex == 3)
                    curAnswer = getResources().getStringArray(R.array.AFood_4);
                if(answerIndex == 4)
                    curAnswer = getResources().getStringArray(R.array.AFood_5);
                break;
            case R.array.QDrink :
                if(answerIndex == 0)
                    curAnswer = getResources().getStringArray(R.array.ADrink_1);
                if(answerIndex == 1)
                    curAnswer = getResources().getStringArray(R.array.ADrink_2);
                if(answerIndex == 2)
                    curAnswer = getResources().getStringArray(R.array.ADrink_3);
                if(answerIndex == 3)
                    curAnswer = getResources().getStringArray(R.array.ADrink_4);
                if(answerIndex == 4)
                    curAnswer = getResources().getStringArray(R.array.ADrink_5);
                break;
            default :
                if(answerIndex == 0)
                    curAnswer = getResources().getStringArray(R.array.AOther_1);
                if(answerIndex == 1)
                    curAnswer = getResources().getStringArray(R.array.AOther_2);

        }


        TextView tv_title = findViewById(R.id.tv_CAATitle);
        final Spinner sp_answer = findViewById(R.id.sp_CAAanswer);
        Button btn_save = findViewById(R.id.btn_CAAsave);
        Button btn_back = findViewById(R.id.btn_CAAback);

        tv_title.setText(question);
        ArrayAdapter adapter = new ArrayAdapter(ChangeAnswerActivity.this, android.R.layout.simple_spinner_item, curAnswer);
        sp_answer.setAdapter(adapter);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cur.setAnswerOf(answerIndex,sp_answer.getSelectedItem().toString());
                SeletionEditActivity.adapter.clear();
                int i = 0;
                for(String s:cur.getAnswers())
                {
                    SeletionEditActivity.adapter.add(SeletionEditActivity.questions[i] + " : " + s);
                    i++;
                }
                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
