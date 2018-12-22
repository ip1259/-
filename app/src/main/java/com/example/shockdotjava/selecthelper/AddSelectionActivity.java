package com.example.shockdotjava.selecthelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AddSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_selection);
        final Selection[] temp = new Selection[1];

        Intent intent = getIntent();
        final int groupIndex = intent.getIntExtra("groupIndex", 0);

        final TextView tv_name = findViewById(R.id.tv_addSelectionName);
        final EditText et_name = findViewById(R.id.et_addSelectionName);
        final Button btn_ASAbtn2 = findViewById(R.id.btn_ASAbtn2);
        final TextView tv_question = findViewById(R.id.tv_Question);
        final Spinner sp_answer = findViewById(R.id.sp_answer);
        final Button btn_next = findViewById(R.id.btn_next);
        btn_ASAbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Button btn_ASAbtn1 = findViewById(R.id.btn_ASAbtn1);
        btn_ASAbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp[0] = new Selection(et_name.getText().toString());
                tv_name.setVisibility(View.GONE);
                et_name.setVisibility(View.GONE);
                btn_ASAbtn1.setVisibility(View.GONE);
                btn_ASAbtn2.setVisibility(View.GONE);
                final int[] index = {0};
                int resource = 0;

                switch(DataActivity.groups.get(groupIndex).GroupType())
                {
                    case "Food" :
                        resource = R.array.QFood;
                        break;
                    case "Drink" :
                        resource = R.array.QDrink;
                        break;
                    case "Other" :
                        resource = R.array.QOther;
                        break;
                    default:
                        resource = R.array.QOther;
                }
                final int ansResource[] = new int[5];
                switch(resource)
                {
                    case R.array.QFood :
                        ansResource[0] = R.array.AFood_1;
                        ansResource[1] = R.array.AFood_2;
                        ansResource[2] = R.array.AFood_3;
                        ansResource[3] = R.array.AFood_4;
                        ansResource[4] = R.array.AFood_5;
                        break;
                    case R.array.QDrink :
                        ansResource[0] = R.array.ADrink_1;
                        ansResource[1] = R.array.ADrink_2;
                        ansResource[2] = R.array.ADrink_3;
                        ansResource[3] = R.array.ADrink_4;
                        ansResource[4] = R.array.ADrink_5;
                        break;
                    default :
                        ansResource[0] = R.array.AOther_1;
                        ansResource[1] = R.array.AOther_2;
                        ansResource[2] = -1;
                        ansResource[3] = -1;
                        ansResource[4] = -1;
                        break;
                }

                final ArrayAdapter questions = ArrayAdapter.createFromResource(AddSelectionActivity.this, resource, android.R.layout.simple_spinner_item);
                tv_question.setText(questions.getItem(index[0]).toString());
                final ArrayAdapter[] answers = {ArrayAdapter.createFromResource(AddSelectionActivity.this, ansResource[index[0]], android.R.layout.simple_spinner_item)};
                sp_answer.setAdapter(answers[0]);
                tv_question.setVisibility(View.VISIBLE);
                sp_answer.setVisibility(View.VISIBLE);
                btn_next.setVisibility(View.VISIBLE);


                btn_next.setText("Next");
                btn_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(btn_next.getText().toString().equals("Next"))
                        {
                            temp[0].addAnswerToSelection(sp_answer.getSelectedItem().toString());
                            index[0]++;
                            tv_question.setText(questions.getItem(index[0]).toString());
                            answers[0] = ArrayAdapter.createFromResource(AddSelectionActivity.this, ansResource[index[0]], android.R.layout.simple_spinner_item);
                            sp_answer.setAdapter(answers[0]);
                            if(index[0]+1 >= 5 || ansResource[index[0]+1] == -1 )
                            {
                                btn_next.setText("Save");
                            }
                        }
                        else
                        {
                            temp[0].addAnswerToSelection(sp_answer.getSelectedItem().toString());
                            DataActivity.groups.get(groupIndex).addSelectionToGroup(temp[0]);
                            DataSelectionActivity.updateSelectionNames();
                            DataSelectionActivity.adapter.notifyDataSetChanged();
                            finish();
                        }

                    }
                });
            }
        });
    }
}
