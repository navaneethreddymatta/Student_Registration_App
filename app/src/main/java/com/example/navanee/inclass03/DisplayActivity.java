package com.example.navanee.inclass03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity implements View.OnClickListener {
    TextView nameField = null;
    TextView emailField = null;
    TextView deptField = null;
    TextView moodField = null;
    int reqCode = 1112;
    Student student = null;
    final static String Edit_Key = "editField";
    final static String Edit_Value = "editFieldValue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        nameField = (TextView) findViewById(R.id.stdName);
        emailField = (TextView) findViewById(R.id.stdEmail);
        deptField = (TextView) findViewById(R.id.stdDept);
        moodField = (TextView) findViewById(R.id.stdMood);

        if (getIntent().getExtras() != null) {
            student = (Student) getIntent().getExtras().get(MainActivity.USER_KEY);
            nameField.setText(student.getName());
            emailField.setText(student.getEmailID());
            deptField.setText(MainActivity.departments[student.getDepartment()]);
            moodField.setText(student.getMood() + "% Positive");
        }

        findViewById(R.id.editName).setOnClickListener(this);
        findViewById(R.id.editEmail).setOnClickListener(this);
        findViewById(R.id.editDept).setOnClickListener(this);
        findViewById(R.id.editMood).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent thisIntent = new Intent("android.intent.action.View");
        //thisIntent.putExtra(MainActivity.USER_KEY,student);
        System.out.println("1:" + v.getId());
        System.out.println("2:" + R.id.editName);
        if(v.getId() == R.id.editName) {
            System.out.println("1:");
            thisIntent.putExtra(Edit_Key,"editName");
            thisIntent.putExtra(Edit_Value,student.getName());
        } else if (v.getId() == R.id.editEmail) {
            System.out.println("2:");
            thisIntent.putExtra(Edit_Key,"editEmail");
            thisIntent.putExtra(Edit_Value,student.getEmailID());
        } else if (v.getId() == R.id.editDept) {
            System.out.println("3:");
            thisIntent.putExtra(Edit_Key,"editDept");
            thisIntent.putExtra(Edit_Value,"" + student.getDepartment());
        } else {
            System.out.println("4:");
            thisIntent.putExtra(Edit_Key,"editMood");
            thisIntent.putExtra(Edit_Value,"" + student.getMood());
        }
        startActivityForResult(thisIntent,reqCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1112 && resultCode == RESULT_OK) {
            String eKey = data.getExtras().get(Edit_Key).toString();
            String newVal = newVal = data.getExtras().get(Edit_Value).toString();
            if(eKey.equals("editName")){
                nameField.setText(newVal);
                student.setName(newVal);
            } else if(eKey.equals("editEmail")){
                emailField.setText(newVal);
                student.setEmailID(newVal);
            } else if(eKey.equals("editDept")){
                deptField.setText(MainActivity.departments[Integer.parseInt(newVal)]);
                student.setDepartment(Integer.parseInt(newVal));
            } else {
                moodField.setText(newVal + "% Positive");
                student.setMood(Integer.parseInt(newVal));
            }
        }
    }
}
