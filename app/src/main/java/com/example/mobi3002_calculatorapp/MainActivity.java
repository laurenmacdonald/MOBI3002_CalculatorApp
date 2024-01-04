package com.example.mobi3002_calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

/**
 * Class that runs the calculator app.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * Calculator object initialized to be used for calculator functionality.
     */
    Calculator calculator = new Calculator();
    /**
     * TextView result to display user input from button press.
     */
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w("MainActivity", "onCreate:");

        // Accessing the result TextView in activity_main.xml
        result = findViewById(R.id.result);

        // Calling buttonSetup method to create buttons and set onClickListeners
        buttonSetup(R.id.buttonClear);
        buttonSetup(R.id.buttonDelete);
        buttonSetup(R.id.buttonDivide);
        buttonSetup(R.id.buttonMultiply);
        buttonSetup(R.id.buttonSubtract);
        buttonSetup(R.id.buttonAdd);
        buttonSetup(R.id.buttonCalculate);
        buttonSetup(R.id.buttonDecimal);
        buttonSetup(R.id.button0);
        buttonSetup(R.id.button1);
        buttonSetup(R.id.button2);
        buttonSetup(R.id.button3);
        buttonSetup(R.id.button4);
        buttonSetup(R.id.button5);
        buttonSetup(R.id.button6);
        buttonSetup(R.id.button7);
        buttonSetup(R.id.button8);
        buttonSetup(R.id.button9);

    }

    /**
     *
     * @param id Resource id for the button created in activity_main.xml.
     *           <br>
     *           Method to create a new MaterialButton, using the findViewById(id) method to set it up.
     *           <br>
     *           Activates an on click listener after button creation.
     */
    void buttonSetup(int id) {
        MaterialButton btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    /**
     *
     * @param view The view that was clicked.
     *             <br>
     *             When the button is clicked, call the calculator's setDataToCalculate method (takes
     *             the view that was clicked and the TextView result).
     */
    @Override
    public void onClick(View view) {
        calculator.setDataToCalculate(view, result);
    }
}