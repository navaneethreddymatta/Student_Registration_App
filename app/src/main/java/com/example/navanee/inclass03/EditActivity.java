package com.example.navanee.inclass03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nameEditor = null;
    EditText emailEditor = null;
    SeekBar moodEditor = null;
    RadioGroup sRGroup = null;
    RadioButton sRButton = null;
    //Student currentStudent;
    int moodValue = 0;
    int deptIndex = 0;
    String editingKey = "";
    String editingValue = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        findViewById(R.id.sSubmit).setOnClickListener(this);
        if(getIntent().getExtras() != null) {
            editingKey = getIntent().getExtras().get(DisplayActivity.Edit_Key).toString();
            System.out.println(editingKey);
            if(editingKey.equals("editName")) {
                System.out.println("1:1");
                findViewById(R.id.sName).setVisibility(View.VISIBLE);
                nameEditor = (EditText) findViewById(R.id.sName);
                nameEditor.setText(getIntent().getExtras().get(DisplayActivity.Edit_Value).toString());
            } else if(editingKey.equals("editEmail")) {
                System.out.println("2:2");
                findViewById(R.id.sEmail).setVisibility(View.VISIBLE);
                emailEditor = (EditText) findViewById(R.id.sEmail);
                emailEditor.setText(getIntent().getExtras().get(DisplayActivity.Edit_Value).toString());
            } else if(editingKey.equals("editDept")) {
                System.out.println("3:3");
                findViewById(R.id.deptRG).setVisibility(View.VISIBLE);
                findViewById(R.id.deptLbl).setVisibility(View.VISIBLE);
                deptIndex = Integer.parseInt(getIntent().getExtras().get(DisplayActivity.Edit_Value).toString());
                sRGroup = (RadioGroup) findViewById(R.id.deptRG);
                sRButton = (RadioButton) sRGroup.getChildAt(deptIndex);
                sRButton.setChecked(true);

                sRGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        View radioButton = sRGroup.findViewById(checkedId);
                        deptIndex = sRGroup.indexOfChild(radioButton);
                    }
                });
            } else {
                System.out.println("4:4");
                findViewById(R.id.sMood).setVisibility(View.VISIBLE);
                findViewById(R.id.moodLbl).setVisibility(View.VISIBLE);
                moodEditor = (SeekBar) findViewById(R.id.sMood);
                moodValue = Integer.parseInt(getIntent().getExtras().get(DisplayActivity.Edit_Value).toString());
                moodEditor.setProgress(moodValue);
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
        }




    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra(DisplayActivity.Edit_Key,editingKey);
        if(editingKey.equals("editName")) {
            intent.putExtra(DisplayActivity.Edit_Value,nameEditor.getText().toString());
        } else if(editingKey.equals("editEmail")) {
            intent.putExtra(DisplayActivity.Edit_Value,emailEditor.getText().toString());
        } else if(editingKey.equals("editDept")) {
            intent.putExtra(DisplayActivity.Edit_Value,"" + deptIndex);
        } else {
            intent.putExtra(DisplayActivity.Edit_Value,"" + moodValue);
        }
        setResult(RESULT_OK,intent);
        finish();
    }
}
