package com.yummy.foodonmytrain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import static com.yummy.foodonmytrain.ProfileFragment.PARAM2;
import static com.yummy.foodonmytrain.ProfileFragment.newInstance;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLocker{


    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView mNavigationView;
    SharedPreferences mSharedPreferences;
    View mNavigationProfileDetails;
    TextView mTxtNavProfileName, mTxtNavProfilePhone,mTxtNavProfileDob,mTxtNavProfileUser ;
    Button mBtnNavEditProfile;
    private static final String PARAM1 = "Edit";


    Personal_details personalDetails;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper= new DatabaseHelper(this);

        initNavigationView();
        //Auth data retriever
        mSharedPreferences=getSharedPreferences("AuthData", MODE_PRIVATE);

        if(mSharedPreferences.getBoolean("isAuth", false)) {

            if(mSharedPreferences.getBoolean("ProfilePresent", false )) {
                personalDetails = databaseHelper.getMemberDetails();
                setNavigationValues();

                String usertype=personalDetails.getUserType();

                if(usertype.equalsIgnoreCase("1")) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.mainFragContent, SellerMainMenuFragment.newInstance(personalDetails.getTrainNo()))
                            .commit();
                }else {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.mainFragContent, new CustomerMainMenuFragment())
                            .commit();
                }
            }else{
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.mainFragContent, ProfileFragment.newInstance(ProfileFragment.PARAM1))
                        .commit();
            }

        }else{
            startActivity(new Intent(MainActivity.this, PhoneAuthActivity.class));
            finish();
        }

        mBtnNavEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    getSupportFragmentManager()
                            .beginTransaction().replace(R.id.mainFragContent, newInstance(PARAM2))
                            .addToBackStack("Profile Editing")
                            .commit();
                    changeDrawerState();
                }
                else{
                    nointernetcaller();
                }
            }
        });
    }

    private void initNavigationView() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                    setNavigationValues();
            }
        };

        drawer.setStatusBarBackground(R.color.colorPrimary);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = findViewById(R.id.nav_view);
        mNavigationProfileDetails = mNavigationView.inflateHeaderView(R.layout.nav_profile_details);

        mTxtNavProfileName = mNavigationView.findViewById(R.id.txtNavProfileName);
        mTxtNavProfilePhone = mNavigationView.findViewById(R.id.txtNavProfilePhone);
        mTxtNavProfileDob = mNavigationView.findViewById(R.id.txtNavProfileDOB);
        mTxtNavProfileUser= mNavigationView.findViewById(R.id.txtNavProfileUser);
        mBtnNavEditProfile = mNavigationView.findViewById(R.id.btnNavEditProfile);
    }

    public boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

    private void nointernetcaller() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("No internet");

        alertDialogBuilder
                .setMessage("Please connect to internet to update you profile details")
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        changeDrawerState();
    }

    public void changeDrawerState(){
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void setDrawerEnabled(boolean enabled,String name) {
        int lockMode = enabled ? DrawerLayout.LOCK_MODE_UNLOCKED :
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
        drawer.setDrawerLockMode(lockMode);
        toggle.setDrawerIndicatorEnabled(enabled);
        toolbar.setTitle(name);
        if (enabled) {
            toolbar.setVisibility(View.VISIBLE);
            setNavigationValues();
        } else
            toolbar.setVisibility(View.GONE);
    }

    private void setNavigationValues() {

        if(personalDetails != null) {
            String tempFullName = personalDetails.getFirstname() +" "+ personalDetails.getLastname();
            mTxtNavProfileName.setText(tempFullName);
            mTxtNavProfilePhone.setText(personalDetails.getPersonalno());
            mTxtNavProfileDob.setText(personalDetails.getPersonalDOB());

            if(personalDetails.getUserType().length() !=0) {
                String tempGender = personalDetails.getUserType();
                switch (tempGender) {
                    case "1":
                        mTxtNavProfileUser.setText(R.string.user_seller);
                        break;
                    case "2":
                        mTxtNavProfileUser.setText(R.string.user_customer);
                        break;
                }
            } else mTxtNavProfileUser.setVisibility(View.GONE);
        }
    }
}
