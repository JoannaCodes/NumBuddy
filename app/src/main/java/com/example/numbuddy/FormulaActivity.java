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

public class FormulaActivity extends AppCompatActivity {
    Button geometry_Btn, trigonometry_Btn, algebra_Btn, arithmetic_Btn;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formula);



        geometry_Btn = findViewById(R.id.Geometry_Btn);
        trigonometry_Btn = findViewById(R.id.Trigonometry_Btn);
        algebra_Btn = findViewById(R.id.Algebra_Btn);
        arithmetic_Btn = findViewById(R.id.Arithmetic_Btn);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        geometry_Btn.setOnClickListener(v -> openGeometry());
        trigonometry_Btn.setOnClickListener(v -> openTrigonometry());
        algebra_Btn.setOnClickListener(v -> openAlgebra());
        arithmetic_Btn.setOnClickListener(v -> openArithmetic());


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
                drawerLayout.closeDrawers();
            } else if (item_selected == R.id.nav_MiniGame) {
                Intent mini_game = new Intent(this, MiniGamesActivity.class);
                startActivity(mini_game);
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

    public void openGeometry(){
        Intent intent = new Intent(this, GeometryActivity.class);
        startActivity(intent);
    }

    public void openTrigonometry(){
        Intent intent = new Intent(this, TrigoActivity.class);
        startActivity(intent);
    }
    public void openAlgebra(){
        Intent intent = new Intent(this, AlgebraActivity.class);
        startActivity(intent);
    }

    public void openArithmetic(){
        Intent intent = new Intent(this, ArithmeticActivity.class);
        startActivity(intent);
    }



}

