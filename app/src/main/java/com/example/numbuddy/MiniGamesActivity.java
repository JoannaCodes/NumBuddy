package com.example.numbuddy;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class MiniGamesActivity extends AppCompatActivity {
    Button MiniEasy_Btn, MiniMid_Btn, MiniHard_Btn;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_games);

        MiniEasy_Btn = findViewById(R.id.miniEasy_Btn);
        MiniMid_Btn = findViewById(R.id.miniMid_Btn);
        MiniHard_Btn = findViewById(R.id.miniHard_Btn);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        MiniEasy_Btn.setOnClickListener(v -> openEasyGame());
        MiniMid_Btn.setOnClickListener(v -> openMediumGame());
        MiniHard_Btn.setOnClickListener(v -> openHardGame());

        //======Navigation Drawer======
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            int item_selected = item.getItemId();

            if (item_selected == R.id.nav_Home) {
                Intent home = new Intent(this, MainActivity.class);
                startActivity(home);
                drawerLayout.closeDrawers();
            } else if (item_selected == R.id.nav_UnitConverter) {
                Intent unit_converter = new Intent(this, UnitConverterActivity.class);
                startActivity(unit_converter);
                drawerLayout.closeDrawers();
            } else if (item_selected == R.id.nav_Formula) {
                Intent formula = new Intent(this, FormulaActivity.class);
                startActivity(formula);
                drawerLayout.closeDrawers();
            } else if (item_selected == R.id.nav_MiniGame) {
                drawerLayout.closeDrawers();
            } else if (item_selected == R.id.nav_AboutUs) {
                Intent about_us = new Intent(this, AboutUsActivity.class);
                startActivity(about_us);
                drawerLayout.closeDrawers();
            }

            return true;
        });

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    public void openEasyGame() {
        Intent intent;
        intent = new Intent(this, MiniEasyGamesActivity.class);
        startActivity(intent);
    }

    public void openMediumGame() {
        Intent intent = new Intent(this, MiniMedGamesActivity.class);
        startActivity(intent);
    }

    public void openHardGame() {
        Intent intent;
        intent = new Intent(this, MiniHardGamesActivity. class);
        startActivity(intent);
    }
}