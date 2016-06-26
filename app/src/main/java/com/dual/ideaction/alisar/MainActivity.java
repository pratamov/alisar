package com.dual.ideaction.alisar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dual.ideaction.alisar.dummy.DummyContent;
import com.dual.ideaction.alisar.fragments.BudgetFragment;
import com.dual.ideaction.alisar.fragments.ConsummerFragment;
import com.dual.ideaction.alisar.fragments.DashboardFragment;
import com.dual.ideaction.alisar.fragments.ProfileFragment;
import com.dual.ideaction.alisar.fragments.StatisticsFragment;
import com.dual.ideaction.alisar.fragments.SwitchFragment;
import com.dual.ideaction.alisar.fragments.TerminalFragment;
import com.mikepenz.iconics.context.IconicsContextWrapper;

public class MainActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        BudgetFragment.OnFragmentInteractionListener,
        DashboardFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener,
        StatisticsFragment.OnFragmentInteractionListener,
        SwitchFragment.OnFragmentInteractionListener,
        ConsummerFragment.OnFragmentInteractionListener,
        TerminalFragment.OnListFragmentInteractionListener {
    static final int FRAGMENT_DASHBOARD = 0;
    static final int FRAGMENT_BUDGET = 1;
    static final int FRAGMENT_TERMINAL = 2;
    static final int FRAGMENT_CONSUMMER = 3;
    static final int FRAGMENT_STATISTICS = 4;
    static final int FRAGMENT_PROFILE = 5;
    static final int FRAGMENT_LOGIN = 6;
    Fragment fragment = null;
    String title = "Home";
    int fragment_id = FRAGMENT_DASHBOARD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setFragmentDashboard();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (fragment_id == MainActivity.FRAGMENT_CONSUMMER) {
                setFragmentTerminal();
            } else if (fragment_id == MainActivity.FRAGMENT_DASHBOARD){
                super.onBackPressed();
            }
            else {
                setFragmentDashboard();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            setFragmentDashboard();
        } else if (id == R.id.nav_budget) {
            setFragmentBudget();
        } else if (id == R.id.nav_terminal) {
            setFragmentTerminal();
        } else if (id == R.id.nav_statistics) {
            setFragmentStatistics();
        } else if (id == R.id.nav_profile) {
            setFragmentProfile();
        } else if (id == R.id.nav_logout) {
            setFragmentLogin();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        setFragmentConsummer();
    }

    private void setFragmentDashboard() {
        fragment = new DashboardFragment();
        fragment_id = MainActivity.FRAGMENT_DASHBOARD;
        getSupportActionBar().setTitle("Dashboard");
        FragmentManager fragmentManager =
                getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    private void setFragmentBudget() {
        fragment = new BudgetFragment();
        fragment_id = MainActivity.FRAGMENT_BUDGET;
        getSupportActionBar().setTitle("Manage Budget");
        FragmentManager fragmentManager =
                getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    private void setFragmentTerminal() {
        fragment = new TerminalFragment();
        fragment_id = MainActivity.FRAGMENT_TERMINAL;
        getSupportActionBar().setTitle("Manage Room Electricity");
        FragmentManager fragmentManager =
                getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    private void setFragmentConsummer() {
        fragment = new ConsummerFragment();
        fragment_id = MainActivity.FRAGMENT_CONSUMMER;
        getSupportActionBar().setTitle("Electronic Devices");
        FragmentManager fragmentManager =
                getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    private void setFragmentStatistics() {
        fragment = new StatisticsFragment();
        fragment_id = MainActivity.FRAGMENT_STATISTICS;
        getSupportActionBar().setTitle("View Statistics Usage");
        FragmentManager fragmentManager =
                getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    private void setFragmentProfile() {
        fragment = new ProfileFragment();
        fragment_id = MainActivity.FRAGMENT_PROFILE;
        getSupportActionBar().setTitle("Profile");
        FragmentManager fragmentManager =
                getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    private void setFragmentLogin(){
        Intent mainIntent = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(mainIntent);
        MainActivity.this.finish();
    }
}
