package com.example.socialite;


import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    DatabaseManager dbManager;

    String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        // instance of DB Manager
        dbManager = new DatabaseManager(this);

        // update screen
        updateView();
    }

    // Build a View dynamically with all the candies
    @SuppressWarnings("deprecation")
    public void updateView( ) {

        // Get all candies form the db table
        ArrayList<Friend> friends = dbManager.selectAll();


        if( friends.size() > 0 ) {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView( this );

            // Use gridLayout
            GridLayout grid = new GridLayout(this);
            // set columns and rows
            grid.setRowCount(friends.size());
            grid.setColumnCount(5);

            // create arrays of components
            TextView[] ids = new TextView[friends.size( )];
            EditText[][] namesAndPrices = new EditText[friends.size( )][3];
            Button[] buttons = new Button[friends.size( )];
            ButtonHandler bh = new ButtonHandler( );

            // retrieve width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;


            int i = 0;

            for ( Friend friend: friends ) {

                // create the TextView for the candy's id
                ids[i] = new TextView( this );
                ids[i].setGravity( Gravity.CENTER );
                ids[i].setText("" + (i + 1));

                // set id here

                // create the two EditTexts for the candy's name and price
                // create EditText for both name and price
                namesAndPrices[i][0] = new EditText(this);
                namesAndPrices[i][0].setMaxLines(1);
                namesAndPrices[i][1] = new EditText(this);
                namesAndPrices[i][1].setMaxLines(1);
                namesAndPrices[i][2] = new EditText(this);
                namesAndPrices[i][2].setMaxLines(1);

                namesAndPrices[i][0].setText( friend.getLastName());
                namesAndPrices[i][1].setText( friend.getFirstName());
                namesAndPrices[i][2].setText( friend.getEmail()); //"" +
                //namesAndPrices[i][2].setInputType( InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);
                namesAndPrices[i][0].setId( 10 * friend.getId( ) );
                namesAndPrices[i][1].setId( 10 * friend.getId( ) + 1 );
                namesAndPrices[i][2].setId( 10 * friend.getId( ) + 2 );
                namesAndPrices[i][0].setMaxLines(1);
                namesAndPrices[i][1].setMaxLines(1);
                namesAndPrices[i][2].setMaxLines(1);

                // create the button
                buttons[i] = new Button( this );
                buttons[i].setText( "Update" );
                buttons[i].setId( friend.getId( ) );

                // set up event handling
                buttons[i].setOnClickListener( bh );

                // add the elements to grid
                grid.addView( ids[i], width / 10, ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndPrices[i][0], ( int ) ( width * .28 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndPrices[i][1], (int) ( width * .18 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndPrices[i][2], (int) ( width * .20 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( buttons[i], ( int ) ( width * .20 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );

                i++;
            }

            // create a back button
            Button backButton = new Button(this);
            backButton.setText("Back");
            backButton.setBackgroundColor(0xFF6200EE);
            backButton.setTextColor(Color.WHITE);

            //

            TextView emptyText = new TextView(this);
            grid.addView(emptyText,( int ) ( width / 10 ), ViewGroup.LayoutParams.WRAP_CONTENT );
            grid.addView( backButton, ( int ) ( width * .15 ),ViewGroup.LayoutParams.WRAP_CONTENT );

            backButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    // finish the current update activity
                    UpdateActivity.this.finish();
                }
            });

            // Add views
            scrollView.addView(grid);
            setContentView(scrollView);
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick( View v ) {
            // retrieve name and price of the candy
            int friendId = v.getId();
            EditText lastNameET = findViewById(10 * friendId);
            EditText firstNameET = findViewById(10 * friendId + 1);
            EditText emailET = findViewById(10 * friendId + 2);

            String lastName = lastNameET.getText().toString();
            String firstName = firstNameET.getText().toString();
            String emailString = emailET.getText().toString();

            // update candy in database
            try {
                dbManager.updateById(friendId, firstName, lastName, emailString);

                Toast.makeText(UpdateActivity.this, lastName+ ", " + firstName + " is updated", Toast.LENGTH_LONG).show();
                // update screen
                updateView();

            } catch( NumberFormatException nfe ) {
                Toast.makeText( UpdateActivity.this, "email error", Toast.LENGTH_LONG ).show( );
            }

        }
    }
}
