package com.example.shockdotjava.selecthelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DataSelectionActivity extends AppCompatActivity {

    public static ArrayAdapter adapter;
    private static ArrayList<String>[] selectionNames;
    private static Intent intent;
    private static int groupIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_selection);

        intent = getIntent();
        groupIndex = intent.getIntExtra("groupIndex", 0);

        TextView tv_Title = findViewById(R.id.tv_DSATitle);
        tv_Title.setText((CharSequence) DataActivity.groups.get(groupIndex).GroupName());

        Button btn_delete = findViewById(R.id.btn_DSAdelete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataActivity.groups.remove(groupIndex);
                DataActivity.updateGroupNames();
                DataActivity.adapter.notifyDataSetChanged();
                finish();
            }
        });

        Button btn_back = findViewById(R.id.btn_DSAback);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btn_add = findViewById(R.id.btn_DSAadd);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DataSelectionActivity.this, AddSelectionActivity.class);
                intent1.putExtra("groupIndex", groupIndex);
                startActivity(intent1);
            }
        });

        final ListView list_Selection = findViewById(R.id.list_Seletion);
        selectionNames = new ArrayList[]{DataActivity.groups.get(groupIndex).getSelectionsNames()};
        adapter = new ArrayAdapter(DataSelectionActivity.this, android.R.layout.simple_list_item_1, selectionNames[0]);
        list_Selection.setAdapter(adapter);
        list_Selection.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position >= DataActivity.groups.get(groupIndex).getSelections().size())
                {
                    updateSelectionNames();
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    Intent intent1 = new Intent(DataSelectionActivity.this, SeletionEditActivity.class);
                    intent1.putExtra("groupIndex", groupIndex);
                    intent1.putExtra("selectionIndex", position);
                    startActivity(intent1);
                }
            }
        });

        Button btn_refresh = findViewById(R.id.btn_DSArefresh);
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSelectionNames();
                adapter.notifyDataSetChanged();
            }
        });
    }

    public static void updateSelectionNames()
    {
        selectionNames[0].clear();
        selectionNames[0].addAll(DataActivity.groups.get(groupIndex).getSelectionsNames());
    }
}
