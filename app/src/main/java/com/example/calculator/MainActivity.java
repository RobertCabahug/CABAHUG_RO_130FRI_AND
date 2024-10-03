package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        setNumberButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtonIds = {
                R.id.button_0, R.id.button_1, R.id.button_2,
                R.id.button_3, R.id.button_4, R.id.button_5,
                R.id.button_6, R.id.button_7, R.id.button_8,
                R.id.button_9
        };

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (isOperatorPressed) {
                    currentInput = "";
                    isOperatorPressed = false;
                }
                currentInput += button.getText().toString();
                display.setText(currentInput);
            }
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorButtonListeners() {
        findViewById(R.id.button_add).setOnClickListener(new OperatorClickListener("+"));
        findViewById(R.id.button_subtract).setOnClickListener(new OperatorClickListener("-"));
        findViewById(R.id.button_multiply).setOnClickListener(new OperatorClickListener("*"));
        findViewById(R.id.button_divide1).setOnClickListener(new OperatorClickListener("/"));
        findViewById(R.id.button_equal).setOnClickListener(new EqualClickListener());
        findViewById(R.id.button_clear).setOnClickListener(new ClearClickListener());
    }

    private class OperatorClickListener implements View.OnClickListener {
        private final String selectedOperator;

        OperatorClickListener(String operator) {
            this.selectedOperator = operator;
        }

        @Override
        public void onClick(View v) {
            if (!currentInput.isEmpty()) {
                firstNumber = Double.parseDouble(currentInput);
                operator = selectedOperator;
                isOperatorPressed = true;
            }
        }
    }

    private class EqualClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (!currentInput.isEmpty() && !operator.isEmpty()) {
                double secondNumber = Double.parseDouble(currentInput);
                double result = 0;

                switch (operator) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        if (secondNumber != 0) {
                            result = firstNumber / secondNumber;
                        } else {
                            display.setText("Error");
                            return;
                        }
                        break;
                }

                display.setText(String.valueOf(result));
                currentInput = String.valueOf(result);
                operator = "";
            }
        }
    }

    private class ClearClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            currentInput = "";
            operator = "";
            firstNumber = 0;
            display.setText("0");
        }
    }
}