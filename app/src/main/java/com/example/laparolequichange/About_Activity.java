package com.example.laparolequichange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class About_Activity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(About_Activity.this,Setting_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(intent,0);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // assigning ID of the toolbar to a variable
        Toolbar toolbar = findViewById(R.id.toolbar);

        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("A propos de La Parole Qui Change");

        // using toolbar as ActionBar
        setSupportActionBar(toolbar);



        // Display icon in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_left);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Get a reference to the container view
        LinearLayout container = findViewById(R.id.container);

        // Create a text view and add it to the container
        TextView textView = findViewById(R.id.tvAboutDetail);
        textView.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet turpis auctor, vehicula velit non, convallis risus. Fusce non semper " +
                "arcu. Duis aliquam sem in felis efficitur, sed pharetra lorem tincidunt. " +
                "Integer bibendum, mauris quis fermentum tincidunt, eros quam tristique massa," +
                " eu luctus risus nisi non odio. Etiam sagittis faucibus aliquam. Sed sed dui vel " +
                "libero finibus volutpat eget id diam. Donec pellentesque est sapien, nec vestibulum " +
                "odio commodo a. Vestibulum quis tortor a mi tristique imperdiet. Donec elementum justo " +
                " nulla commodo commodo. Nam sit amet libero non augue lobortis commodo. Morbi bibendum " +
                "gravida sapien, a pellentesque nisl feugiat id. Nunc id bibendum nulla.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet turpis auctor, vehicula velit non, convallis risus. Fusce non semper \" +\n" +
                "arcu. Duis aliquam sem in felis efficitur, sed pharetra lorem tincidunt. " +
                "Integer bibendum, mauris quis fermentum tincidunt, eros quam tristique massa," +
                "eu luctus risus nisi non odio. Etiam sagittis faucibus aliquam. Sed sed dui vel " +
                "libero finibus volutpat eget id diam. Donec pellentesque est sapien, nec vestibulum " +
                "odio commodo a. Vestibulum quis tortor a mi tristique imperdiet. Donec elementum justo " +
                " nulla commodo commodo. Nam sit amet libero non augue lobortis commodo. Morbi bibendum " +
                "gravida sapien, a pellentesque nisl feugiat id. Nunc id bibendum nulla. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet turpis auctor, vehicula velit non, convallis risus. Fusce non semper \" +\n" +
                "arcu. Duis aliquam sem in felis efficitur, sed pharetra lorem tincidunt. " +
                "Integer bibendum, mauris quis fermentum tincidunt, eros quam tristique massa," +
                " eu luctus risus nisi non odio. Etiam sagittis faucibus aliquam. Sed sed dui vel " +
                "libero finibus volutpat eget id diam. Donec pellentesque est sapien, nec vestibulum " +
                "odio commodo a. Vestibulum quis tortor a mi tristique imperdiet. Donec elementum justo " +
                " nulla commodo commodo. Nam sit amet libero non augue lobortis commodo. Morbi bibendum " +
                "gravida sapien, a pellentesque nisl feugiat id. Nunc id bibendum nulla.Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Sed sit amet turpis auctor, vehicula velit non, convallis risus. Fusce non semper " +
                "arcu. Duis aliquam sem in felis efficitur, sed pharetra lorem tincidunt. " +
                "Integer bibendum, mauris quis fermentum tincidunt, eros quam tristique massa," +
                "eu luctus risus nisi non odio. Etiam sagittis faucibus aliquam. Sed sed dui vel " +
                "libero finibus volutpat eget id diam. Donec pellentesque est sapien, nec vestibulum " +
                "odio commodo a. Vestibulum quis tortor a mi tristique imperdiet. Donec elementum justo " +
                " nulla commodo commodo. Nam sit amet libero non augue lobortis commodo. Morbi bibendum " +
                "gravida sapien, a pellentesque nisl feugiat id. Nunc id bibendum nulla.");
        textView.setPadding(20, 20, 20, 20);
        textView.setTextSize(16);
//        container.addView(textView);

    }
}