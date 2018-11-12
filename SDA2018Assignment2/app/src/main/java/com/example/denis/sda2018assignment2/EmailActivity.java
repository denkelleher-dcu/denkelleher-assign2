package com.example.denis.sda2018assignment2;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;    //import Log utility to facilitate logcat messages
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class EmailActivity extends Activity {

    private final String TAG = "SDA Assignment 2";      // set a private string called TAG for logcat purposes
    public static final String EXTRA_MESSAGE = "com.example.denis.SDA2018Assignment2.MESSAGE";  // to be used for key code
    public static final String EXTRA_MESSAGE1 = "com.example.denis.SDA2018Assignment2.MESSAGE1";  // to be used for key code
    public static final String EXTRA_MESSAGE2 = "com.example.denis.SDA2018Assignment2.MESSAGE2";  // to be used for key code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "The email activity page is created.");  // setting a info tag
        setContentView(R.layout.activity_email);

       // add back button aka up button in top toolbar
      ActionBar actionBar = getActionBar();
         actionBar.setDisplayHomeAsUpEnabled(true);  // not sure about this. I wanted to use getSupportActivity but not available to Activiy
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == android.R.id.home){
            this.finish();
        }
        return super .onOptionsItemSelected(item);
    }

// when the send button is clicked the content of the relevant fields are put into the intent
    //and the page is terminated.
    public void returnToIntent (View view) {
        Intent intent = new Intent(this,MainActivity.class);
        // find the relevant field
        EditText editText = (EditText) findViewById(R.id.to_Text);
        // put the content of the relevant field into the message string.
        String message = editText.getText().toString();
        //put the message into the intent with a message key for reference
        intent.putExtra(EXTRA_MESSAGE,message);
        //repeat the above for the two other fields.
        EditText editText1 = (EditText) findViewById(R.id.subject_Text);
        String message1 = editText1.getText().toString();
        intent.putExtra(EXTRA_MESSAGE1,message1);
        EditText editText2 = (EditText) findViewById(R.id.content_Text);
        String message2 = editText2.getText().toString();
        intent.putExtra(EXTRA_MESSAGE2,message2);
        startActivity(intent);
        this.finish();
    }
}
















