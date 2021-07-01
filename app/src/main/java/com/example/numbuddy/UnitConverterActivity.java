package com.example.numbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UnitConverterActivity extends AppCompatActivity {
    Spinner measurement_Spnr, unit1_Spnr, unit2_Spnr;
    EditText input_txt, output_txt;
    TextView formula_info, formula_view;

    ArrayAdapter<String> parent;
    ArrayAdapter<String> child;

    ArrayList<String> measurement, length, mass, temperature;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_converter);

        measurement_Spnr = findViewById(R.id.measurement_Spnr1);
        unit1_Spnr = findViewById(R.id.unit_Spnr2);
        unit2_Spnr = findViewById(R.id.unit_Spnr3);
        input_txt = findViewById(R.id.num_txt1);
        output_txt = findViewById(R.id.num_txt2);
        formula_info = findViewById(R.id.formula_info);
        formula_view = findViewById(R.id.formula_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        measurement = new ArrayList<>();
        measurement.add("Length");
        measurement.add("Mass");
        measurement.add("Temperature");

        length = new ArrayList<>();
        length.add("Kilometer");
        length.add("Meter");
        length.add("Centimeter");

        mass = new ArrayList<>();
        mass.add("Kilogram");
        mass.add("Gram");
        mass.add("Milligram");

        temperature = new ArrayList<>();
        temperature.add("Celsius");
        temperature.add("Fahrenheit");
        temperature.add("Kelvin");

//======Input text & Output text======
        input_txt.setFocusable(true);
        output_txt.setEnabled(false);

        input_txt.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {input_txt.setHint("");}
        });

//======Spinner======
        parent = new ArrayAdapter<>(getApplicationContext(),R.layout.style_spinner, measurement);
        measurement_Spnr.setAdapter(parent);

        measurement_Spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            int default_unit1, default_unit2;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    child = new ArrayAdapter<>(getApplicationContext(),R.layout.style_spinner, length);

                    default_unit1 = 1;
                    default_unit2 = 2;
                }
                if (position == 1) {
                    child = new ArrayAdapter<>(getApplicationContext(),R.layout.style_spinner, mass);

                    default_unit1 = 0;
                    default_unit2 = 1;
                }
                if (position == 2) {
                    child = new ArrayAdapter<>(getApplicationContext(),R.layout.style_spinner, temperature);

                    default_unit1 = 0;
                    default_unit2 = 1;
                }

                unit1_Spnr.setAdapter(child);
                unit2_Spnr.setAdapter(child);

                unit1_Spnr.setSelection(default_unit1);
                unit2_Spnr.setSelection(default_unit2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

//======Computation=====
        input_txt.addTextChangedListener(new TextWatcher() {
            double input, value;
            String result;

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!"".equals(input_txt.getText().toString())) {
                    input = Double.parseDouble(input_txt.getText().toString());

                    value = Computation(input);

                    //checks of double is a whole number
                    result = (value % 1 == 0) ? String.valueOf(Math.round(value)): String.valueOf(value);
                    output_txt.setText(result);
                }
                else { output_txt.getText().clear(); }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s) { }
        });

//======Formula======
        formula_info.setOnClickListener(new View.OnClickListener() {
            boolean clicked;

            //visibility of formula_view
            @Override
            public void onClick(View v) {
                clicked = !clicked;
                formula_view.setVisibility(clicked ? View.VISIBLE : View.INVISIBLE);
            }
        });

        unit1_Spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //set formulas
                formula_view.setText(Formula());

                //clears text when another item is selected
                input_txt.getText().clear();
                output_txt.getText().clear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        unit2_Spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                formula_view.setText(Formula());
                input_txt.getText().clear();
                output_txt.getText().clear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

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
                    drawerLayout.closeDrawers();
            } else if (item_selected == R.id.nav_Formula) {
                Intent formula = new Intent(this, FormulaActivity.class);
                startActivity(formula);
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

    //======Methods============================================================================================
    //Computation
    private double Computation(double input) {
        double value = 0;
        if (unit1_Spnr.getSelectedItem().toString().equals(unit2_Spnr.getSelectedItem().toString())) { value = input; }

        //Length
        if(unit1_Spnr.getSelectedItem().toString().equals("Kilometer") && unit2_Spnr.getSelectedItem().toString().equals("Meter")) {
            value = input * 1000;
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Kilometer") && unit2_Spnr.getSelectedItem().toString().equals("Centimeter")) {
            value = input * 100000;
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Meter") && unit2_Spnr.getSelectedItem().toString().equals("Kilometer")) {
            value = input / 1000;
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Meter") && unit2_Spnr.getSelectedItem().toString().equals("Centimeter")) {
            value = input * 100;
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Centimeter") && unit2_Spnr.getSelectedItem().toString().equals("Kilometer")) {
            value = input / 100000;
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Centimeter") && unit2_Spnr.getSelectedItem().toString().equals("Meter")) {
            value = input / 100;
        }

        //Mass
        if(unit1_Spnr.getSelectedItem().toString().equals("Kilogram") && unit2_Spnr.getSelectedItem().toString().equals("Gram")) {
            value = input * 1000;
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Kilogram") && unit2_Spnr.getSelectedItem().toString().equals("Milligram")) {
            value = input * 1e+6;
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Gram") && unit2_Spnr.getSelectedItem().toString().equals("Kilogram")) {
            value = input / 1000;
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Gram") && unit2_Spnr.getSelectedItem().toString().equals("Milligram")) {
            value = input * 1000;
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Milligram") && unit2_Spnr.getSelectedItem().toString().equals("Kilogram")) {
            value = input / 1e+6;
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Milligram") && unit2_Spnr.getSelectedItem().toString().equals("Gram")) {
            value = input / 1000;
        }

        //Temperature
        if(unit1_Spnr.getSelectedItem().toString().equals("Celsius") && unit2_Spnr.getSelectedItem().toString().equals("Fahrenheit")) {
            value = (input * 9/5) + 32;
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Celsius") && unit2_Spnr.getSelectedItem().toString().equals("Kelvin")) {
            value = input + 273.15;
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Fahrenheit") && unit2_Spnr.getSelectedItem().toString().equals("Celsius")) {
            value = (input - 32) * 5/9;
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Fahrenheit") && unit2_Spnr.getSelectedItem().toString().equals("Kelvin")) {
            value = (input - 32) * 5/9 + 273.15;
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Kelvin") && unit2_Spnr.getSelectedItem().toString().equals("Celsius")) {
            value = input - 273.15;
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Kelvin") && unit2_Spnr.getSelectedItem().toString().equals("Fahrenheit")) {
            value = (input - 273.15) * 9/5 + 32;
        }

        return value;
    }

    //Formulas
    private String Formula() {
        String value = null;

        if (unit1_Spnr.getSelectedItem().toString().equals(unit2_Spnr.getSelectedItem().toString())) { value = "Multiply Value by 1"; }

        //Length
        if(unit1_Spnr.getSelectedItem().toString().equals("Kilometer") && unit2_Spnr.getSelectedItem().toString().equals("Meter")) {
            value = getString(R.string.Km_M);
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Kilometer") && unit2_Spnr.getSelectedItem().toString().equals("Centimeter")) {
            value = getString(R.string.Km_Cm);
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Meter") && unit2_Spnr.getSelectedItem().toString().equals("Kilometer")) {
            value = getString(R.string.M_Km);
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Meter") && unit2_Spnr.getSelectedItem().toString().equals("Centimeter")) {
            value = getString(R.string.M_Cm);
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Centimeter") && unit2_Spnr.getSelectedItem().toString().equals("Kilometer")) {
            value = getString(R.string.Cm_Km);
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Centimeter") && unit2_Spnr.getSelectedItem().toString().equals("Meter")) {
            value = getString(R.string.Cm_M);
        }

        //Mass
        if(unit1_Spnr.getSelectedItem().toString().equals("Kilogram") && unit2_Spnr.getSelectedItem().toString().equals("Gram")) {
            value = getString(R.string.Kg_G);
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Kilogram") && unit2_Spnr.getSelectedItem().toString().equals("Centimeter")) {
            value = getString(R.string.Kg_Mg);
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Gram") && unit2_Spnr.getSelectedItem().toString().equals("Kilogram")) {
            value = getString(R.string.G_Kg);
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Gram") && unit2_Spnr.getSelectedItem().toString().equals("Milligram")) {
            value = getString(R.string.G_Mg);
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Milligram") && unit2_Spnr.getSelectedItem().toString().equals("Kilogram")) {
            value = getString(R.string.Mg_Kg);
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Milligram") && unit2_Spnr.getSelectedItem().toString().equals("Gram")) {
            value = getString(R.string.Mg_G);
        }

        //Temperature
        if(unit1_Spnr.getSelectedItem().toString().equals("Celsius") && unit2_Spnr.getSelectedItem().toString().equals("Fahrenheit")) {
            value = getString(R.string.C_F);
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Celsius") && unit2_Spnr.getSelectedItem().toString().equals("Kelvin")) {
            value = getString(R.string.C_K);
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Fahrenheit") && unit2_Spnr.getSelectedItem().toString().equals("Celsius")) {
            value = getString(R.string.F_C);
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Fahrenheit") && unit2_Spnr.getSelectedItem().toString().equals("Kelvin")) {
            value = getString(R.string.F_K);
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Kelvin") && unit2_Spnr.getSelectedItem().toString().equals("Celsius")) {
            value = getString(R.string.K_C);
        }
        if(unit1_Spnr.getSelectedItem().toString().equals("Kelvin") && unit2_Spnr.getSelectedItem().toString().equals("Fahrenheit")) {
            value = getString(R.string.K_F);
        }

        return value;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}