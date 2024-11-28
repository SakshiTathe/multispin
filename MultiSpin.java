package com.example.practiceactivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiSpin extends AppCompatActivity {
    TextView selectedItemsTextView;
    Spinner multiSelectSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_multi_spin);

        selectedItemsTextView =findViewById(R.id.spintxt);
        multiSelectSpinner=findViewById(R.id.spinnermulti);

        List<String> itemList = Arrays.asList("Java", "C++", "Kotlin", "C", "Python", "Javascript");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Select Items"});
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        multiSelectSpinner.setAdapter(spinnerAdapter);

        MultiSelectSpinnerAdapter adapter = new MultiSelectSpinnerAdapter(this, itemList, selectedItems -> {
            selectedItemsTextView.setText("Selected Items: " + String.join(", ", selectedItems));
        });

        multiSelectSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                adapter.showMultiSelectDialog();
                return true;
            }
        });
    }
}