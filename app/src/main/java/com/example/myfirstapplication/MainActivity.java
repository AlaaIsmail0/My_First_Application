package com.example.myfirstapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView txtResult;
    private Spinner spnHospital;
    private EditText edtNewTitle;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchStatus;
    private DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        Button btnSearch = findViewById(R.id.btnSearch);
        Button btnSave = findViewById(R.id.btnSave);
        txtResult = findViewById(R.id.txtResult);
        spnHospital = findViewById(R.id.spnHospital);
        edtNewTitle = findViewById(R.id.edtNewTitle);
        switchStatus = findViewById(R.id.switchStatus);

        btnSearch.setOnClickListener(v -> {
            String selectedHospital = spnHospital.getSelectedItem().toString();
            displayHospital(selectedHospital);
        });

        btnSave.setOnClickListener(v -> {
            String selectedHospital = spnHospital.getSelectedItem().toString();
            String newTitle = edtNewTitle.getText().toString();

            // Check if new title is provided
            if (!newTitle.isEmpty()) {
                // Save the new book to the SQLite database using the selected category
                boolean isInserted = dbHelper.addData(newTitle, selectedHospital);

                if (isInserted) {
                    // Optionally, clear the input fields after saving
                    edtNewTitle.setText("");

                    // Retrieve and display the updated list of books
                    displayHospital(selectedHospital);
                } else {
                    Toast.makeText(MainActivity.this, "Error saving data", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Please enter a title", Toast.LENGTH_SHORT).show();
            }
        });

        // Set listener for Switch changes
        switchStatus.setOnCheckedChangeListener((buttonView, isChecked) -> updateStatusUI(isChecked));
    }

    private void displayHospital(String selectedHospital) {
        // Retrieve and display the updated list of books
        Cursor result = dbHelper.getData(selectedHospital);
        StringBuilder str = new StringBuilder();
        while (result.moveToNext()) {
            @SuppressLint("Range") String title = result.getString(result.getColumnIndex("Title"));
            str.append(title).append("\n");
        }
        txtResult.setText(str.toString());
    }

    @SuppressLint("SetTextI18n")
    private void updateStatusUI(boolean isSafe) {
        if (isSafe) {
            switchStatus.setTextColor(getResources().getColor(android.R.color.black));
            switchStatus.setText("Safe");
        } else {
            switchStatus.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            switchStatus.setText("Targeted");
        }
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
