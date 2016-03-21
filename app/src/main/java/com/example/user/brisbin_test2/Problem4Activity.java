package com.example.user.brisbin_test2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class Problem4Activity extends AppCompatActivity {

    private EditText userTextInput;
    private Calendar current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userTextInput = (EditText)findViewById(R.id.userTextInput);
        current = Calendar.getInstance();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Problem4DBManager problem4DBManager = new Problem4DBManager(Problem4Activity.this);
                String inputText = userTextInput.getText().toString();
                long currentDate = current.get(Calendar.DAY_OF_WEEK);
                UserInput userInput = new UserInput(inputText,currentDate);
                userInput = problem4DBManager.addUserInput(userInput);
                Log.i("userInputID", userInput.getId() + "");
            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Snackbar.make(v, "Press here to add your entry to the database. "
                        ,Snackbar.LENGTH_LONG).setAction("Action", null).show();
                return true;
            }
        });
    }

}
