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

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {

    public static ArrayList<SelectionGroup> groups = new ArrayList<SelectionGroup>();
    public static ArrayList<String> groupNames = getGroupsNames();
    public static ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Button btn_Add = findViewById(R.id.btn_Add);
        Button btn_Refresh = findViewById(R.id.btn_Refresh);
        Button btn_Back = findViewById(R.id.btn_back);

        final ListView list_Group = findViewById(R.id.list_Group);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, groupNames);
        list_Group.setAdapter(adapter);
        list_Group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if(position >= groups.size())
                {
                    //groupNames = getGroupsNames();
                    //ArrayAdapter adapter1 = new ArrayAdapter(DataActivity.this, android.R.layout.simple_list_item_1, groupNames);
                    //list_Group.setAdapter(adapter1);
                    updateGroupNames();
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    Intent intentDAtoDSA = new Intent(DataActivity.this, DataSelectionActivity.class);
                    intentDAtoDSA.putExtra("groupIndex", position);
                    startActivity(intentDAtoDSA);
                }

            }
        });


        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataActivity.this, AddGroupActivity.class);
                startActivity(intent);
            }
        });

        btn_Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //groupNames = getGroupsNames();
                //ArrayAdapter adapter1 = new ArrayAdapter(DataActivity.this, android.R.layout.simple_list_item_1, groupNames);
                //list_Group.setAdapter(adapter1);
                updateGroupNames();
                adapter.notifyDataSetChanged();
            }
        });

        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public static ArrayList<String> getGroupsNames()
    {
        ArrayList<String> names = new ArrayList<String>();
        for(int i = 0; i < groups.size(); i++)
        {
            names.add(groups.get(i).GroupName());
        }

        return names;
    }

    public static void updateGroupNames()
    {
        groupNames.clear();
        groupNames.addAll(getGroupsNames());
    }
}
