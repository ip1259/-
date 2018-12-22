package com.example.shockdotjava.selecthelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class AddGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);

        final Spinner spinner = findViewById(R.id.sp_Type);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Type, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        final EditText et_Name = findViewById(R.id.et_Name);

        Button btn_Confirm = findViewById(R.id.btn_Confirm);
        btn_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectionGroup temp = new SelectionGroup(et_Name.getText().toString(), spinner.getSelectedItem().toString());
                DataActivity.groups.add(temp);
                DataActivity.updateGroupNames();
                DataActivity.adapter.notifyDataSetChanged();
                finish();
            }
        });

        Button btn_Cancle = findViewById(R.id.btn_Cancle);
        btn_Cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
