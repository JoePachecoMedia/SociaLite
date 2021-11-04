package com.example.socialite;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.socialite.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Button btnFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //binding.recyclerView.LayoutManager = LinearLayoutManager(this);

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btnFriends = (Button) findViewById(R.id.button_friends);
        btnFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignIn = new Intent(MainActivity.this, FriendsActivity.class);
                startActivity(intentSignIn);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent addIntent = new Intent(this, AddActivity.class);
            this.startActivity(addIntent);
            return true;
        }

        if (id == R.id.action_delete) {
            Intent deleteIntent = new Intent(this, DeleteActivity.class);
            this.startActivity(deleteIntent);
            return true;
        }

        //update menu
        if (id == R.id.action_update) {
            Intent deleteIntent = new Intent(this, UpdateActivity.class);
            this.startActivity(deleteIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
/*
    public void updateView(){
        ArrayList<Friend> friends = dbManager.selectAll();

        if( friends.size() > 0 ) {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView(this);

            // Use gridLayout
            GridLayout grid = new GridLayout(this);
            // set columns and rows
            grid.setRowCount(friends.size());
            grid.setColumnCount(4);

            // create arrays of components
            TextView[] ids = new TextView[friends.size()];
            TextView[][] namesAndPrices = new TextView[friends.size()][3];
            //Button[] buttons = new Button[friends.size( )];
            //UpdateActivity.ButtonHandler bh = new UpdateActivity.ButtonHandler( );

            // retrieve width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;


            int i = 0;

            for (Friend friend : friends) {

                // create the TextView for the candy's id
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + (i + 1));

                // set id here

                // create the two EditTexts for the candy's name and price
                // create EditText for both name and price
                namesAndPrices[i][0] = new TextView(this);
                namesAndPrices[i][1] = new TextView(this);
                namesAndPrices[i][2] = new TextView(this);
                namesAndPrices[i][0].setText(friend.getLastName());
                namesAndPrices[i][1].setText(friend.getFirstName());
                namesAndPrices[i][2].setText(friend.getEmail()); //"" +
                //namesAndPrices[i][2].setInputType( InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT);
                namesAndPrices[i][0].setId(10 * friend.getId());
                namesAndPrices[i][1].setId(10 * friend.getId() + 1);
                namesAndPrices[i][2].setId(10 * friend.getId() + 2);
                namesAndPrices[i][0].setMaxLines(1);
                namesAndPrices[i][1].setMaxLines(1);
                namesAndPrices[i][2].setMaxLines(1);

                // create the button
                //buttons[i] = new Button( this );
                //buttons[i].setText( "Update" );
                //buttons[i].setId( friend.getId( ) );

                // set up event handling
                //buttons[i].setOnClickListener( bh );

                // add the elements to grid
                grid.addView(ids[i], width / 10, ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndPrices[i][0], (int) (width * .25),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndPrices[i][1], (int) (width * .15),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndPrices[i][2], (int) (width * .25),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                //grid.addView( buttons[i], ( int ) ( width * .20 ),
                //ViewGroup.LayoutParams.WRAP_CONTENT );

                i++;
            }

            TextView emptyText = new TextView(this);
            grid.addView(emptyText,( int ) ( width / 10 ), ViewGroup.LayoutParams.WRAP_CONTENT );
            //grid.addView( backButton, ( int ) ( width * .15 ),ViewGroup.LayoutParams.WRAP_CONTENT );


            // Add views
            scrollView.addView(grid);
            //setContentView(scrollView);
        }
    }*/
}