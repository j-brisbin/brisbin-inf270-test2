package com.example.user.brisbin_test2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class Problem3Activity extends AppCompatActivity {

    private ScrollView scrollTextContainer;
    private TextView contentTextView;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        scrollTextContainer = (ScrollView)findViewById(R.id.scrollTextContainer);
        contentTextView = (TextView)findViewById(R.id.contentTextView);
        contentTextView.setText("");
        File file = this.getFilesDir();
        contentTextView.append("\n" + file.getAbsolutePath() + "\n");
        String[] filenames = this.fileList();
        try{
            String contents = "";
            for(int i = 0;i<filenames.length;i++){
                contentTextView.append("\n" + filenames[i]);
                contents = InternalStorage.read(this.getApplicationContext(),filenames[i]);
                contentTextView.append("\n" + contents);
            }
        }
        catch(IOException ioe){
            new AlertDialog.Builder(this.getApplicationContext()).setTitle("File Read Error")
                    .setMessage(
                            "An error occurred when attempting to read a file.")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                    .create()
                    .show();
        }

    }

}
