package com.richard.developer.main;



import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //
        TabHost tabHost = getTabHost();
        
        // Tab for matches
        TabSpec matches = tabHost.newTabSpec("Matches");
        matches.setIndicator("Matches");
        Intent photosIntent = new Intent(this, AllMatchPublic.class);
        matches.setContent(photosIntent);
        
        // Tab for admin
        TabSpec adminspec = tabHost.newTabSpec("Admin");
        // setting Title and Icon for the Tab
        adminspec.setIndicator("Admin");
        Intent songsIntent = new Intent(this, AdminPanel.class);
        adminspec.setContent(songsIntent);
     
        tabHost.addTab(matches); // Adding all matches tab
        tabHost.addTab(adminspec); // Adding admin tab
     
    }
}