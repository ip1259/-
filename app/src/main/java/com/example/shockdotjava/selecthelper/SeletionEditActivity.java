package com.example.shockdotjava.selecthelper;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SeletionEditActivity extends AppCompatActivity {

    private Intent intent;
    private static int groupIndex;
    private static int selectionIndex;
    public static Selection cur;
    public static String[] questions;
    public static ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seletion_edit);

        intent = getIntent();
        groupIndex = intent.getIntExtra("groupIndex", 0);
        selectionIndex = intent.getIntExtra("selectionIndex", 0);
        cur = DataActivity.groups.get(groupIndex).getSelectionOf(selectionIndex);
        TextView tv_title = findViewById(R.id.tv_SEAtitle);
        final ListView list_qa = findViewById(R.id.list_QA);
        Button btn_back = findViewById(R.id.btn_SEAback);
        Button btn_delete = findViewById(R.id.btn_SEAdelete);
        Button btn_refresh = findViewById(R.id.btn_SEArefresh);
        int resource = 0;
        switch(DataActivity.groups.get(groupIndex).GroupType())
        {
            case "Food" :
                resource = R.array.QFood;
                break;
            case "Drink" :
                resource = R.array.QDrink;
                break;
            default:
                resource = R.array.QOther;
        }
        questions = getResources().getStringArray(resource);

        tv_title.setText(cur.SelectionName());
        adapter = new ArrayAdapter(SeletionEditActivity.this,android.R.layout.simple_list_item_1);
        int i = 0;
        for(String s : cur.getAnswers())
        {
            adapter.add(questions[i] + " : " + s);
            i++;
        }
        list_qa.setAdapter(adapter);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataActivity.groups.get(groupIndex).getSelections().remove(selectionIndex);
                DataSelectionActivity.updateSelectionNames();
                DataSelectionActivity.adapter.notifyDataSetChanged();
                finish();
            }
        });

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.clear();
                int i = 0;
                for(String s : cur.getAnswers())
                {
                    adapter.add(questions[i] + " : " + s);
                    i++;
                }
                adapter.notifyDataSetChanged();
            }
        });

        final int finalResource = resource;
        list_qa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(SeletionEditActivity.this, ChangeAnswerActivity.class);
                intent1.putExtra("question", questions[position]);
                intent1.putExtra("groupIndex", groupIndex);
                intent1.putExtra("selectionIndex", selectionIndex);
                intent1.putExtra("answerIndex", position);
                intent1.putExtra("resource", finalResource);
                //startActivity(intent1);
                startActivityForResult(intent1,0);
            }
        });
    }
}
