package com.example.wli5_countbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addCounterActivity extends AppCompatActivity {

    private View.OnClickListener addConfirmListener = new View.OnClickListener(){

        public void onClick(View v) {

            EditText nametext = (EditText) findViewById(R.id.addName);
            EditText valuetext = (EditText) findViewById(R.id.addValue);
            EditText commenttext = (EditText) findViewById(R.id.addComment);
            String name = nametext.getText().toString();
            String initialvalue = valuetext.getText().toString();
            String comment = commenttext.getText().toString();


            //https://stackoverflow.com/questions/920306/sending-data-back-to-the-main-activity-in-android


            int number = Integer.parseInt(initialvalue);
            Intent intentAdd = getIntent();
            intentAdd.putExtra("name", name);
            intentAdd.putExtra("initial_value", number);
            intentAdd.putExtra("comment", comment);
            setResult(Activity.RESULT_OK, intentAdd);
            finish();
        }
    };

    private View.OnClickListener addCancelListener = new View.OnClickListener(){
        public void onClick(View v){
            Intent cancelIntent = new Intent();
            setResult(RESULT_CANCELED,cancelIntent);
            finish();
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counter);
        Button addConfirmButton = (Button) findViewById(R.id.confirmAdd);
        Button addCancelButton = (Button) findViewById(R.id.confirmEdit);
        addConfirmButton.setOnClickListener(addConfirmListener);
        addCancelButton.setOnClickListener(addCancelListener);



    }
}
