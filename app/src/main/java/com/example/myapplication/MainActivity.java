package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView version;
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.fragment_login2);
//
//            Button btn = findViewById(R.id.simpleButton);
//            btn.setOnClickListener(new View.OnClickListener() {
//
//
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(MainActivity.this, login2.class));
//                    Toast.makeText(getApplicationContext(), "TEST", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//            });
//        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        version = findViewById(R.id.version_name);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Saved Successfully", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        setVersionName();




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.tool_search) {
            // Handle the camera action
            Fragment testfragment = new test();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            transaction.replace(R.id.master_container, ((test) testfragment).newInstance());
            transaction.commit();

        } else if (id == R.id.check_in) {
            Fragment checkinfragment = new checkin();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            transaction.replace(R.id.master_container, ((checkin) checkinfragment).newInstance());
            transaction.commit();

        } else if (id == R.id.check_out) {
            Fragment checkoutfragment = new tool_checkout();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            transaction.replace(R.id.master_container, ((tool_checkout)checkoutfragment ).newInstance());
            transaction.commit(); }

           else if (id == R.id.newtool) {
            Fragment newtoolfragment = new register_tool();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            transaction.replace(R.id.master_container, ((register_tool) newtoolfragment).newInstance());
            transaction.commit(); }

        else if (id == R.id.newcategory) {
            Fragment newgroupfragment = new new_group();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            transaction.replace(R.id.master_container, ((new_group) newgroupfragment).newInstance());
            transaction.commit(); }

        else if (id == R.id.newspec) {
            Fragment newspecfragment = new new_spec();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            transaction.replace(R.id.master_container, ((new_spec) newspecfragment).newInstance());
            transaction.commit(); }

           else if (id == R.id.logout) {
            Fragment logoutfragment = new logout();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            transaction.replace(R.id.master_container, ((logout)logoutfragment ).newInstance());
            transaction.commit();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setVersionName(){
        version.setText("Version: " + BuildConfig.VERSION_NAME);
    }


}
