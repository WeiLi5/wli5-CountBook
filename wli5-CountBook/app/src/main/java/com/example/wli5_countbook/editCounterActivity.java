package com.example.wli5_countbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editCounterActivity extends AppCompatActivity {

    private View.OnClickListener editConfirmListener = new View.OnClickListener(){

        public void onClick(View v){

            EditText nametext = (EditText)findViewById(R.id.editName);
            EditText currenttext = (EditText)findViewById(R.id.editCurrentValue);
            EditText initialtext = (EditText)findViewById(R.id.editInitialValue);
            EditText commenttext = (EditText)findViewById(R.id.editComment);

            String newName = nametext.getText().toString();
            String newCurrentValueText = currenttext.getText().toString();
            String newInitialValueText = initialtext.getText().toString();
            String newComment = commenttext.getText().toString();

            int newCurrentValue = Integer.parseInt(newCurrentValueText);
            int newInitialValue = Integer.parseInt( newInitialValueText);
            Intent editIntent = getIntent();
            editIntent.putExtra("name",newName);
            editIntent.putExtra("current_value", newCurrentValue);
            editIntent.putExtra("initial_value", newInitialValue);
            editIntent.putExtra("comment", newComment);
            setResult(Activity.RESULT_OK,editIntent);
            finish();


        }
    };

    private View.OnClickListener editCancelListener = new View.OnClickListener(){
        public void onClick(View v){
            Intent cancelIntent = new Intent();
            setResult(RESULT_CANCELED,cancelIntent);
            finish();

        }
    };

    //reset


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);

        Button editCounterButton = (Button) findViewById(R.id.confirmEdit);
        editCounterButton.setOnClickListener(editConfirmListener);

        Button cancelButton = (Button) findViewById(R.id.cancelEdit);
        cancelButton.setOnClickListener(editCancelListener);

        //xxx

        EditText txtname = (EditText)findViewById(R.id.editName);
        EditText txtcomment = (EditText)findViewById(R.id.editComment);
        EditText txtcurrentnum = (EditText)findViewById(R.id.editCurrentValue);
        EditText txtinitialnum = (EditText)findViewById(R.id.editInitialValue);

        //prepopulate text fields with current values
        String name = getIntent().getExtras().getString("s_name");
        String comment = getIntent().getExtras().getString("s_comment");
        int current = getIntent().getExtras().getInt("s_curr_value");
        int initial = getIntent().getExtras().getInt("s_init_value");
        txtname.setText(name);
        txtcomment.setText(comment);
        txtcurrentnum.setText(String.valueOf(current));
        txtinitialnum.setText(String.valueOf(initial));

    }
}
