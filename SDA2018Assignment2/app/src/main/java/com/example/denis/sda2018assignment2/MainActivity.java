package com.example.denis.sda2018assignment2;

import android.app.Activity;
import android.net.Uri;         //import uri for email
import android.os.Bundle;
import android.provider.MediaStore; //import utility for camera use
import android.util.Log;    //import Log utility to facilitate logcat messages
import android.content.Intent;  // import Intent to facilitate calling activities
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends Activity {

    private final String TAG = "SDA Assignment 2";      // set a private string called TAG for logcat purposes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "The main application page is created.");  // setting a info tag
        setContentView(R.layout.activity_main);

       // enable or disable the Send button
        // check for content in the to field
        // ive decided to only check for 'to' field to control the send button activation as without the 'to' the mail should not be sent.
        // also a mail can be sent with blank subject and content

        Intent g = getIntent();
        String toAddress = g.getStringExtra(EmailActivity.EXTRA_MESSAGE);// get the to address

        Log.i(TAG, "button check toAddress is .." + toAddress);

        if (toAddress != null ){                                            //check for null
            Button btn = findViewById(R.id.send_button);
            btn.setEnabled(true);                                           // if not null then enable
        }



        // Get the Intent that started this activity and extract the strings as 3 parts
        Intent intent = getIntent();
        String message = intent.getStringExtra(EmailActivity.EXTRA_MESSAGE);
            //check if the message is null for the initial loading of the app.
            // if null then stop the method
            if (message == null) {
                return;             //https://stackoverflow.com/questions/6620949/difference-between-return-and-break-statements
            }
        message = message + "\n" + intent.getStringExtra(EmailActivity.EXTRA_MESSAGE1);
        message = message + "\n" + intent.getStringExtra(EmailActivity.EXTRA_MESSAGE2);

        Log.i(TAG, "get message back is .." + message);

        TextView textView = findViewById(R.id.return_TextView);
        textView.setText(message);


    }


//      https://stackoverflow.com/questions/26224011/camera-is-not-saving-after-taking-picture
//      https://developer.android.com/guide/topics/media/camera
 //     https://www.youtube.com/watch?v=6Z6k7X2vfhk


    // As were used in the Activity Lifecycle example i use onClick attribute for the edit text fields.
    // I used takePictureIntent as described in https://developer.android.com/training/camera/photobasics

    static final int REQUEST_IMAGE_CAPTURE = 1; // code for image capture intent
    public void openCamera(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {// this is runtime check for valid app to run
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);   // tell the camera app available to open
        }
    }


    // I experienced a problem with the image not saving in the gallery.
 //          Turns out that it would not work on Huawai P20 (take picture but not save in gallery).
//          Worked perfect with LG G4 so I could strip out all the File, Filename URI etc..
//          I still dont understand why and how i would get to work with the P20 device, I understand there may be a permissions problem with the more recent APKS on my phone.




// open gallery app intent..
    static final int RESULT_GALLERY = 1;
    public void openGallery(View v) {
        Intent galleryIntent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (galleryIntent.resolveActivity(getPackageManager()) != null) {   // this is runtime check for valid app to run
            startActivityForResult(galleryIntent,RESULT_GALLERY);
    // I notice that while the gallery opens and the picture can be selected, it flicks up momentarily but then app goes back to activityMain screen
        }
    }


    // Open the activity_email page
    public void explicitIntent(View view) {
        Intent intent = new Intent(this,EmailActivity.class);
        startActivity(intent);
    }

    //  https://stackoverflow.com/questions/3312438/how-to-open-email-program-via-intents-but-only-an-email-program

    // Open the Mail application and add the content of the returned values
    public void mailIntent (View view) {    // method for when Send button is pressed
        // get the email information
        Intent g = getIntent();
        String toAddress = g.getStringExtra(EmailActivity.EXTRA_MESSAGE);
        String subject = g.getStringExtra(EmailActivity.EXTRA_MESSAGE1);
        String content = g.getStringExtra(EmailActivity.EXTRA_MESSAGE2);

        Intent intent = new Intent(Intent.ACTION_VIEW);

        Log.i(TAG, "toAddress is." + toAddress);    //check the email information is correct
        Log.i(TAG, "subject is." + subject);
        Log.i(TAG, "content is." + content);

        // set the information required into the package
        Uri data = Uri.parse("mailto:"+ toAddress +"?subject=" + subject + "&body=" + content);
        intent.setData(data);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
