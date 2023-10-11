package com.example.unit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText valueInput;
    private Spinner unitSelector;
    private Button convertButton;
    private TextView resultOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        valueInput = findViewById(R.id.value_input);
        unitSelector = findViewById(R.id.unit_selector);
        convertButton = findViewById(R.id.convert_button);
        resultOutput = findViewById(R.id.result_output);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSelector.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
            }
        });
    }

    private void convert() {
        double inputValue = Double.parseDouble(valueInput.getText().toString());
        String selectedUnit = unitSelector.getSelectedItem().toString();
        double result = 0;

        switch (selectedUnit) {
            case "Centimeters to Meters":
                result = UnitConverter.convertCentimetersToMeters(inputValue);
                break;
            case "Meters to Centimeters":
                result = UnitConverter.convertMetersToCentimeters(inputValue);
                break;
            case "Grams to Kilograms":
                result = UnitConverter.convertGramsToKilograms(inputValue);
                break;
            case "Kilograms to Grams":
                result = UnitConverter.convertKilogramsToGrams(inputValue);
                break;
        }

        resultOutput.setText(String.format("%.2f", result));
    }
}