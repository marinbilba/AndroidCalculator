package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd) {
        String oldString = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldString.substring(0, cursorPos);
        String rightStr = oldString.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }

    }

    public void zeroButton(View view) {
        updateText("0");
    }

    public void oneButton(View view) {
        updateText("1");
    }

    public void twoButton(View view) {
        updateText("2");
    }

    public void threeButton(View view) {
        updateText("3");
    }

    public void fourButton(View view) {
        updateText("4");
    }

    public void fiveButton(View view) {
        updateText("5");
    }

    public void sixButton(View view) {
        updateText("6");
    }

    public void sevenButton(View view) {
        updateText("7");
    }

    public void eightButton(View view) {
        updateText("8");
    }

    public void nineButton(View view) {
        updateText("9");
    }

    public void exponentButton(View view) {
        updateText("^");
    }

    public void subtractButton(View view) {
        updateText("-");
    }

    public void addButton(View view) {
        updateText("+");
    }

    public void parenthesesButton(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLength = display.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                openPar += 1;
            }
            if (display.getText().toString().substring(i, i + 1).equals(")")) {
                closedPar += 1;
            }
        }
        if (openPar == closedPar || display.getText().toString().substring(textLength - 1, textLength).equals("(")) {
            updateText("(");

        } else if (closedPar < openPar && !display.getText().toString().substring(textLength - 1, textLength).equals("(")) {
            updateText(")");

        }
        display.setSelection(cursorPos + 1);

    }

    public void equalsButton(View view) {
        String userExpression = display.getText().toString();
        Expression expression = new Expression(userExpression);
        String result = String.valueOf(expression.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }

    public void pointButton(View view) {
        updateText(".");
    }

    public void plusMinusButton(View view) {

    }

    public void multiplyButton(View view) {
        updateText("*");
    }

    public void divisionButton(View view) {
        updateText("/");
    }

    public void clearButton(View view) {
        display.setText("");
    }

    public void backspaceButton(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }

}