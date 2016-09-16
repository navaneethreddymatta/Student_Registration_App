package com.example.navanee.inclass03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nameEditor = null;
    EditText emailEditor = null;
    SeekBar moodEditor = null;
    RadioGroup sRGroup = null;
    RadioButton sRButton = null;
    Student currentStudent;
    int moodValue = 0;
    int deptIndex = 0;
    final static String USER_KEY = "user";
    public static String departments[] = {"SIS", "CS", "BIO", "Others"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditor = (EditText) findViewById(R.id.sName);
        emailEditor = (EditText) findViewById(R.id.sEmail);
        moodEditor = (SeekBar) findViewById(R.id.sMood);
        sRGroup = (RadioGroup) findViewById(R.id.deptRG);

        findViewById(R.id.sSubmit).setOnClickListener(this);

        sRGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = sRGroup.findViewById(checkedId);
                deptIndex = sRGroup.indexOfChild(radioButton);
            }
        });

        moodEditor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                moodValue = seekBar.getProgress();
            }
        });
    }

    @Override
    public void onClick(View v) {
        String stdName = nameEditor.getText().toString();
        String stdEmail = emailEditor.getText().toString();
        if(stdName == null || stdName.length() == 0 || stdEmail == null || stdEmail.length() == 0 || moodValue == 0) {
            Toast.makeText(this,"Enter All the Details",Toast.LENGTH_LONG).show();
        } else {
            System.out.println("creating student");
            currentStudent = new Student(stdName, stdEmail, deptIndex, moodValue);
            Intent intent = new Intent(this, DisplayActivity.class);
            intent.putExtra(USER_KEY, currentStudent);
            startActivity(intent);
        }
    }
}
