package com.example.wli5_countbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class MainActivity extends AppCompatActivity implements ItemDeleteListener{

    public static final int ADD_NEW_COUNTER_CODE = 1;
    public static final int EDIT_EXIST_COUNTER_CODE=2;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TextView numOfCountersText;
    private CounterArrayAdapter adapter;

    ArrayList<Counter> counterArrayList = new ArrayList<Counter>();
    int numOfCounters=counterArrayList.size();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterArrayList=loadFromFile();

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewCounter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter =new CounterArrayAdapter(counterArrayList,this,this);
        recyclerView.setAdapter(adapter);

        numOfCountersText =(TextView) findViewById(R.id.numOfCountersText);
        numOfCountersText.setText(String.valueOf(numOfCounters));

        Button addNewCounterButton = (Button) findViewById(R.id.addNewCounter);
        addNewCounterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            //https://developer.android.com/training/basics/intents/result.html
            public void onClick(View v){
                Intent  mainCounterPage = new Intent(MainActivity.this,addCounterActivity.class);
                startActivityForResult(mainCounterPage,ADD_NEW_COUNTER_CODE);
            }
        });
    }

    protected void onStart(){
        super.onStart();

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewCounter);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter =new CounterArrayAdapter(counterArrayList,this,this);
        recyclerView.setAdapter(adapter);
        numOfCounters=counterArrayList.size();
        numOfCountersText.setText(String.valueOf(numOfCounters));
        adapter.notifyDataSetChanged();
        saveInFile();

    }

    @Override
    protected void onPause(){
        super.onPause();
        saveInFile();
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent counterData){

        if (resultCode == RESULT_CANCELED){}

        if (requestCode == ADD_NEW_COUNTER_CODE && resultCode == RESULT_OK){
            String name = counterData.getStringExtra("name");
            int initialValue= counterData.getIntExtra("initial_value",0);
            String comment = counterData.getStringExtra("comment");

            Counter newCounter = new Counter(name,initialValue,comment);
            counterArrayList.add(newCounter);
            numOfCounters=counterArrayList.size();
            numOfCountersText.setText(String.valueOf(numOfCounters));
            adapter.notifyDataSetChanged();
            saveInFile();

        }

        if (requestCode == EDIT_EXIST_COUNTER_CODE && resultCode == RESULT_OK){
            //unfinish
            int position = counterData.getIntExtra("position",0);
            Counter counter = counterArrayList.get(position);
            String name = counterData.getStringExtra("name");
            int initialValue= counterData.getIntExtra("initial_value",0);
            int currentValue= counterData.getIntExtra("current_value",0);
            String comment = counterData.getStringExtra("comment");

            counter.setName(name);
            counter.setInitialValue(initialValue);
            counter.setCurrentValue(currentValue);
            counter.setComment(comment);
            counter.setDate();

            adapter.onDateReady(counter,position);
        }


    }

    //edit from LonelyTweet

    public ArrayList<Counter> loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-09-19
            Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
            counterArrayList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            counterArrayList = new ArrayList<Counter>();
        }
        return counterArrayList;

    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(counterArrayList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }



    public void updateCountersNumber(){

        numOfCounters = counterArrayList.size();
        numOfCountersText.setText(String.valueOf(numOfCounters));
        adapter = new CounterArrayAdapter(counterArrayList,this,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        saveInFile();
    }

    public void onItemDeleted(){
        updateCountersNumber();
    }
}
