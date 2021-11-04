package com.example.socialite;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dbManager = new DatabaseManager(this);

    }

    public void add (View v){

        EditText emailET = findViewById(R.id.input_email);
        String emailString = emailET.getText().toString();

        EditText lastNameET = findViewById(R.id.input_last_name);
        String lastNameString = lastNameET.getText().toString();

        EditText firstNameET = findViewById(R.id.input_first_name);
        String firstNameString = firstNameET.getText().toString();


        try {
            Friend friend = new Friend(0,firstNameString, lastNameString, emailString);
            dbManager.insertFriend(friend);
            Toast.makeText(this,firstNameString + " is inserted to DB table", Toast.LENGTH_LONG).show();
        } catch (NumberFormatException ne) {
            Toast.makeText(this, "Price error", Toast.LENGTH_LONG).show();
        }

        emailET.setText("");
        firstNameET.setText("");
        lastNameET.setText("");

    }


    public void goBack (View v){
        this.finish();

    }

}
