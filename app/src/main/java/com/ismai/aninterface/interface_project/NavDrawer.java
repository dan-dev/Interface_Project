package com.ismai.aninterface.interface_project;

import android.app.Activity;
import android.content.ClipData;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class NavDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Toolbar toolbar;
    Activity ac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ac = this;

        getSupportActionBar().setTitle("Search");
        InitialFragment fragment = new InitialFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, fragment).commit();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //MenuItem item = (MenuItem) findViewById(R.id.action_settings);
        //item.setVisible(false);
        //toolbar.setVisibility(View.INVISIBLE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        invalidateOptionsMenu();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_layout);
            getSupportActionBar().setTitle(fragment.getTag());
        }
        refreshOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
        //return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_layout);
        if (fragment instanceof FavouritesFragment || fragment instanceof ResultsFragment){
            MenuItem itm = (MenuItem) menu.findItem(R.id.action_settings);
            itm.setVisible(true);
            return true;
        }
        else{
            MenuItem itm = (MenuItem) menu.findItem(R.id.action_settings);
            itm.setVisible(false);
            return true;
        }

        /*Toast.makeText(getApplicationContext(), String.valueOf("yo"), Toast.LENGTH_SHORT).show();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_layout);
        if (fragment instanceof ResultsFragment){
            Toast.makeText(getApplicationContext(), String.valueOf("yeah"), Toast.LENGTH_SHORT).show();
            getMenuInflater().inflate(R.menu.nav_drawer, menu);
            return true;
        }
        else{
            Toast.makeText(getApplicationContext(), String.valueOf("no"), Toast.LENGTH_SHORT).show();
            return false;
        }*/
    }

    public void refreshOptionsMenu(){
        invalidateOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment frag = fragmentManager.findFragmentById(R.id.fragment_layout);

            getSupportActionBar().setTitle("Filter");
            Bundle bundle = new Bundle();
            bundle.putString("fragment", frag.getClass().getSimpleName());
            FilterFragment fragment = new FilterFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_layout, fragment).addToBackStack(null).commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            getSupportActionBar().setTitle("Search");
            InitialFragment fragment = new InitialFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_layout, fragment).addToBackStack(null).commit();
            refreshOptionsMenu();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            getSupportActionBar().setTitle("Favourites");
            FavouritesFragment fragment = new FavouritesFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_layout, fragment).addToBackStack(null).commit();
            refreshOptionsMenu();

        } else if (id == R.id.nav_slideshow) {

            getSupportActionBar().setTitle("Recommended");
            RecommendedFragment fragment = new RecommendedFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_layout, fragment).addToBackStack(null).commit();
            invalidateOptionsMenu();

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
