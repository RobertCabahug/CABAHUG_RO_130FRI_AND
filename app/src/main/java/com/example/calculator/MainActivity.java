package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView display;
    String currentInput = "";
    double firstValue = 0;
    String operator = "";
    boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);

        setNumberButtonListener(R.id.button_0, "0");
        setNumberButtonListener(R.id.button_1, "1");
        setNumberButtonListener(R.id.button_2, "2");
        setNumberButtonListener(R.id.button_3, "3");
        setNumberButtonListener(R.id.button_4, "4");
        setNumberButtonListener(R.id.button_5, "5");
        setNumberButtonListener(R.id.button_6, "6");
        setNumberButtonListener(R.id.button_7, "7");
        setNumberButtonListener(R.id.button_8, "8");
        setNumberButtonListener(R.id.button_9, "9");

        setOperatorButtonListener(R.id.button_add, "+");
        setOperatorButtonListener(R.id.button_subtract, "-");
        setOperatorButtonListener(R.id.button_multiply, "*");
        setOperatorButtonListener(R.id.button_divide1, "/");

        Button buttonEqual = findViewById(R.id.button_equal);
        buttonEqual.setOnClickListener(v -> calculateResult());

        Button buttonClear = findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(v -> clearDisplay());
    }

    private void setNumberButtonListener(int id, final String number) {
        Button button = findViewById(id);
        button.setOnClickListener(v -> {
            if (isOperatorPressed) {
                currentInput = "";
                isOperatorPressed = false;
            }
            currentInput += number;
            display.setText(currentInput);
        });
    }

    private void setOperatorButtonListener(int id, final String op) {
        Button button = findViewById(id);
        button.setOnClickListener(v -> {
            firstValue = Double.parseDouble(currentInput);
            operator = op;
            isOperatorPressed = true;
        });
    }

    private void calculateResult() {
        double secondValue = Double.parseDouble(currentInput);
        double result = 0;

        switch (operator) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "*":
                result = firstValue * secondValue;
                break;
            case "/":
                if (secondValue != 0) {
                    result = firstValue / secondValue;
                } else {
                    display.setText("Error");
                    return;
                }
                break;
        }

        display.setText(String.valueOf(result));
        currentInput = String.valueOf(result);
    }

    private void clearDisplay() {
    }
    }