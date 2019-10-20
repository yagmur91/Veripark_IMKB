package com.veripark.imkb;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.veripark.fragments.HomeFragment;
import com.veripark.fragments.LotsFragment;
import com.veripark.supportclass.MenuModel;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.view.Menu;
import com.veripark.supportclass.ExpandableListAdapter;

import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {





    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();

    public FragmentManager fragmentManager;
    private Fragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        expandableListView = findViewById(R.id.expandableListView);

        prepareMenuData();
        populateExpandableList();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        homeFragment = new HomeFragment(this);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.id_container, homeFragment).commit();


        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
      int id = item.getItemId();
//
//        if (id == R.id.nav_home) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_tools) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.id_container, fragment).commit();
            return true;
        }
        return false;
    }


    private void prepareMenuData() {


        MenuModel menuModel=new MenuModel("Hisse ve Endeksler",true,true);
        headerList.add(menuModel);

        menuModel = new MenuModel("IMKB Yükselenler", true, false); //Menu of Python Tutorials
        headerList.add(menuModel);

        menuModel = new MenuModel("IMKB Düşenler", true, false); //Menu of Python Tutorials
        headerList.add(menuModel);


        if(!menuModel.hasChildren)
        {
            childList.put(menuModel, null);
        }


        menuModel = new MenuModel("IMKB Hacme Göre", true, true); //Menu of Python Tutorials
        headerList.add(menuModel);


        List<MenuModel> childModelsList = new ArrayList<>();


        MenuModel childModel = new MenuModel("IMKB-30", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("IMKB-50", false, false);
        childModelsList.add(childModel);

        childModel = new MenuModel("IMKB-100", false, false);
        childModelsList.add(childModel);


             if (menuModel.hasChildren) {

            childList.put(menuModel, childModelsList);
        }









    }


    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    MenuModel m=headerList.get(groupPosition);

                        switch (m.menuName)

                        {
                            case "Hisse ve Endeksler" :

                                Toast.makeText(getApplicationContext(),"ohisse ",Toast.LENGTH_SHORT).show();
                                homeFragment=new LotsFragment();
                                loadFragment(homeFragment);
                                break;

                        }

                }

                return false;
            }
        });




        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {
                    MenuModel model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    switch(model.menuName)
                    {
                        case "IMKB-30" :

                            Toast.makeText(getApplicationContext(),"ohisse ",Toast.LENGTH_SHORT).show();
                            homeFragment=new LotsFragment();
                            loadFragment(homeFragment);


                            break;




                    }
                }
                //Toast.makeText(getApplicationContext(),model.menuName,Toast.LENGTH_SHORT).show();


                onBackPressed();



                return false;
            }
        });
    }


}
