package com.example.p11_mydatabook;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Drawer> drawerItems;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    ListViewAdapter aa;
    String currentTitle;
    ActionBar ab;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerList = findViewById(R.id.left_drawer);

        drawerItems = new ArrayList<Drawer>();
        drawerItems.add(new Drawer("Bio","Bio"));
        drawerItems.add(new Drawer("Vaccination","Vaccination"));
        drawerItems.add(new Drawer("Anniversary","Anniversary"));
        drawerItems.add(new Drawer("About","About"));

        ab = getSupportActionBar();

        aa = new ListViewAdapter(this, android.R.layout.browser_link_context_header, drawerItems);
        drawerList.setAdapter(aa);

        // Set the list's click listener
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                  @Override
                  public void onItemClick(AdapterView<?> arg0, View arg1, int
                          position, long arg3) {

                      Fragment fragment = null;
                      Intent i = new Intent(MainActivity.this,AboutActivity.class);
                      if (position == 0)
                          fragment = new BioFragment();
                      else if (position == 1)
                          fragment = new VaccinationFragment();
                      else if (position == 2)
                          fragment = new AnniversaryFragment();
                      else if (position == 3) {
                          startActivity(i);
                          return;
                      }

                      FragmentManager fm = getSupportFragmentManager();
                      FragmentTransaction trans = fm.beginTransaction();
                      trans.replace(R.id.content_frame, fragment);
                      trans.commit();

                      // Highlight the selected item,
                      //  update the title, and close the drawer
                      drawerList.setItemChecked(position, true);
                      currentTitle = drawerItems.get(position).getName();
                      ab.setTitle(currentTitle);
                      drawerLayout.closeDrawer(drawerList);
                  }
              });

        currentTitle = this.getTitle().toString();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerList);
            }
        });


        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, 	  /* DrawerLayout object */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close /* "close drawer" description */
        ) {

            /** Would be called when a drawer has completely closed */
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                ab.setTitle(currentTitle);
            }

            /** Would be called when a drawer has completely open */
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ab.setTitle("P11 - My Data Book");
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.addDrawerListener(drawerToggle);
        ab.setDisplayHomeAsUpEnabled(true);

    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync toggle state so the indicator is shown properly.
        //  Have to call in onPostCreate()
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
