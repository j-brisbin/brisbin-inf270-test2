package com.example.user.brisbin_test2;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Problem5Activity extends AppCompatActivity {

    private Spinner daySpinner;
    private ArrayAdapter<CharSequence> spinnerAdapter;
    private int spinnerSelection;
    private SimpleCursorAdapter cursorAdapter;
    private ListView queryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        daySpinner = (Spinner)findViewById(R.id.daySpinner);
        queryListView = (ListView)findViewById(R.id.queryListView);
        /*This code is courtesy of the Android Developers documentation:
        * https://developer.android.com/guide/topics/ui/controls/spinner.html */
        spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.daysOfTheWeek,android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(spinnerAdapter);
        spinnerSelection = 0;
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerSelection = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Problem4DBManager problem4DBManager = new Problem4DBManager(Problem5Activity.this);
                Cursor dayCursor = problem4DBManager.getAllInputsByDayOfWeek(spinnerSelection);
                cursorAdapter = new SimpleCursorAdapter(Problem5Activity.this,
                        android.R.layout.simple_list_item_1,
                        dayCursor,
                        new String[]{
                                problem4DBManager.PROB4_USER_INPUT
                        },
                        new int[]{
                                android.R.id.text1
                        },
                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                queryListView.setAdapter(cursorAdapter);
                queryListView.setChoiceMode(ListView.CHOICE_MODE_NONE);
            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Snackbar.make(v, "Press here to query the database for entries made on " +
                        "a certain day.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            }
        });



    }

}
