package com.example.socialite;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // dbManager object and update view
        dbManager = new DatabaseManager(this);
        //updateView();

    }

/*
    public void updateView() {

        //select all candies from the dbtable abd display with a radio button
        ArrayList<Friend> friends = dbManager.selectAll();

        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);

        ViewGroup group = new ViewGroup(this);
        for (Friend friend: friends){
            RadioButton rb = new RadioButton(this);
            rb.setId(friend.getId());
            rb.setText(friend.toString());
            group.addView(rb);
        }


        // set up event handling
        ///DeleteActivity.RadioButtonHandler rbh = new DeleteActivity.RadioButtonHandler();
        //group.setOnCheckedChangeListener(rbh);

        // create a back button
        Button backButton = new Button(this);
        backButton.setText(R.string.button_back);
        backButton.setBackgroundColor(0xFF6200EE);
        backButton.setTextColor(Color.WHITE);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DeleteActivity.this.finish();
            }
        });


        // add view here
        scrollView.addView(group);
        layout.addView(scrollView);


        // add back button at bottom
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 50);
        layout.addView(backButton, params);


        //   set content view here
        setContentView(layout);

    }
*/
}
