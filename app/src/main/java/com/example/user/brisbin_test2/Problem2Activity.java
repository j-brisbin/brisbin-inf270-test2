package com.example.user.brisbin_test2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class Problem2Activity extends AppCompatActivity {

    private EditText userInputTextField;
    private Calendar current;
    private int currentDay;
    private int currentMonth;
    private int currentYear;
    private int currentHour;
    private int currentMinute;
    private File file;
    private Context currentContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userInputTextField = (EditText)findViewById(R.id.userInputTextField);
        /*Calendar code is courtesy of Android Developers documentation:
        * https://developer.android.com/reference/java/util/Calendar.html
        * https://developer.android.com/reference/java/util/Date.html */
        current = Calendar.getInstance();
        currentDay = current.get(Calendar.DATE);
        currentMonth = current.get(Calendar.MONTH);
        currentYear = current.get(Calendar.YEAR);
        currentHour = current.get(Calendar.HOUR);
        currentMinute = current.get(Calendar.MINUTE);
        file = this.getFilesDir();
        currentContext = this.getApplicationContext();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userInputText = userInputTextField.getText().toString();
                try{
                    InternalStorage.write(currentContext,"day_" + currentMonth + "_" +
                            currentDay + "_" + currentYear + "_time_" +
                            currentHour + "_" + currentMinute + ".txt", userInputText);
                    new AlertDialog.Builder(Problem2Activity.this).setTitle("File Write Success")
                            .setMessage(
                                    "File was successfully written to internal storage.")
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Problem2Activity.this,
                                                    MainActivity.class);
                                            //below code line is courtesy of tips.androidhive.info
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            dialog.dismiss();
                                        }
                                    })
                            .create()
                            .show();
                }
                catch(IOException ioe){
                    new AlertDialog.Builder(Problem2Activity.this).setTitle("File Write Error")
                            .setMessage(
                                    "An error occurred when writing the file.")
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Problem2Activity.this,
                                                    MainActivity.class);
                                            //below code line is courtesy of tips.androidhive.info
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            dialog.dismiss();
                                        }
                                    })
                            .create()
                            .show();
                }
            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Snackbar.make(v, "Press here to add your entry to your internal storage. "
                        , Snackbar.LENGTH_LONG).setAction("Action", null).show();
                return true;
            }
        });

    }

}
