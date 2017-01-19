//Ryan Backa
//JAV1 - 1609
//MainActivity

package com.ce01.ryankbacka.backaryan_ce01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button equalsButton;
    Button addButton;
    Button subtractButton;
    Button divideButton;
    Button multiplyButton;
    Button clearButton;
    TextView output;
    String firstNum = "0";
    String secondNum = "0";
    String seleceted = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView) findViewById(R.id.calculatoOutput);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        equalsButton = (Button) findViewById(R.id.equalsButton);
        addButton = (Button) findViewById(R.id.addButton);
        subtractButton = (Button) findViewById(R.id.subtractButton);
        multiplyButton = (Button) findViewById(R.id.multiplyButton);
        divideButton = (Button) findViewById(R.id.divideButton);
        clearButton = (Button) findViewById(R.id.clearButton);

        button0.setOnClickListener(numberClickListener);
        button1.setOnClickListener(numberClickListener);
        button2.setOnClickListener(numberClickListener);
        button3.setOnClickListener(numberClickListener);
        button4.setOnClickListener(numberClickListener);
        button5.setOnClickListener(numberClickListener);
        button6.setOnClickListener(numberClickListener);
        button7.setOnClickListener(numberClickListener);
        button8.setOnClickListener(numberClickListener);
        button9.setOnClickListener(numberClickListener);

        equalsButton.setOnClickListener(functionClickListener);
        addButton.setOnClickListener(functionClickListener);
        subtractButton.setOnClickListener(functionClickListener);
        multiplyButton.setOnClickListener(functionClickListener);
        divideButton.setOnClickListener(functionClickListener);
        clearButton.setOnClickListener(functionClickListener);

    }


    private View.OnClickListener functionClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.addButton:
                    if (!firstNum.equals("0") && !secondNum.equals("0")) {
                        System.out.println("second:" + secondNum);
                        secondNum = doMath(firstNum, secondNum, seleceted);
                        firstNum = "0";
                        seleceted = "+";
                        output.setText(secondNum);
                    } else if (firstNum.equals("0") && !secondNum.equals("0") && !seleceted.equals("")) {
                        seleceted = "+";
                    } else {
                        System.out.println("second:" + secondNum);
                        secondNum = firstNum;
                        firstNum = "0";
                        seleceted = "+";
                    }
                    break;
                case R.id.subtractButton:
                    if (!firstNum.equals("0") && !secondNum.equals("0")) {
                        System.out.println("second:" + secondNum);
                        secondNum = doMath(firstNum, secondNum, seleceted);
                        firstNum = "0";
                        seleceted = "-";
                        output.setText(secondNum);
                    } else if (firstNum.equals("0") && !secondNum.equals("0") && !seleceted.equals("")) {
                        seleceted = "-";
                    } else {
                        System.out.println("second:" + secondNum);
                        secondNum = firstNum;
                        firstNum = "0";
                        seleceted = "-";
                    }
                    break;
                case R.id.multiplyButton:
                    if (!firstNum.equals("0") && !secondNum.equals("0")) {
                        System.out.println("second:" + secondNum);
                        secondNum = doMath(firstNum, secondNum, seleceted);
                        firstNum = "0";
                        seleceted = "*";
                        output.setText(secondNum);
                    } else if (firstNum.equals("0") && !secondNum.equals("0") && !seleceted.equals("")) {
                        seleceted = "*";
                    } else {
                        System.out.println("second:" + secondNum);
                        secondNum = firstNum;
                        firstNum = "0";
                        seleceted = "*";
                    }
                    break;
                case R.id.divideButton:
                    if (!firstNum.equals("0") && !secondNum.equals("0")) {
                        System.out.println("second:" + secondNum);
                        secondNum = doMath(firstNum, secondNum, seleceted);
                        firstNum = "0";
                        seleceted = "/";
                        output.setText(secondNum);
                    } else if (firstNum.equals("0") && !secondNum.equals("0") && !seleceted.equals("")) {
                        seleceted = "/";
                    } else {
                        System.out.println("second:" + secondNum);
                        secondNum = firstNum;
                        firstNum = "0";
                        seleceted = "/";
                    }
                    break;
                case R.id.equalsButton:
                    System.out.println("second:" + secondNum);
                    secondNum = doMath(firstNum, secondNum, seleceted);
                    output.setText(secondNum);
                    firstNum = "0";
                    break;
                case R.id.clearButton:
                    firstNum = "0";
                    secondNum = "0";
                    output.setText("");
                    break;
                default:
                    break;
            }
        }
    };

    private View.OnClickListener numberClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button0:
                    if (firstNum.equals("0")) {
                        break;
                    } else {
                        firstNum = firstNum + "0";
                        output.setText(firstNum);
                    }
                    output.setText(firstNum);
                    break;
                case R.id.button1:
                    if (firstNum.equals("0")) {
                        firstNum = "1";
                    } else {
                        firstNum = firstNum + "1";
                    }
                    output.setText(firstNum);
                    break;
                case R.id.button2:
                    if (firstNum.equals("0")) {
                        firstNum = "2";
                        System.out.println(firstNum);
                        System.out.println(secondNum);
                    } else {
                        firstNum = firstNum + "2";
                    }
                    output.setText(firstNum);
                    break;
                case R.id.button3:
                    if (firstNum.equals("0")) {
                        firstNum = "3";
                    } else {
                        firstNum = firstNum + "3";
                    }
                    output.setText(firstNum);
                    break;
                case R.id.button4:
                    if (firstNum.equals("0")) {
                        firstNum = "4";
                    } else {
                        firstNum = firstNum + "4";
                    }
                    output.setText(firstNum);
                    break;
                case R.id.button5:
                    if (firstNum.equals("0")) {
                        firstNum = "5";
                    } else {
                        firstNum = firstNum + "5";
                    }
                    output.setText(firstNum);
                    break;
                case R.id.button6:
                    if (firstNum.equals("0")) {
                        firstNum = "6";
                    } else {
                        firstNum = firstNum + "6";
                    }
                    output.setText(firstNum);
                    break;
                case R.id.button7:
                    if (firstNum.equals("0")) {
                        firstNum = "7";
                    } else {
                        firstNum = firstNum + "7";
                    }
                    output.setText(firstNum);
                    break;
                case R.id.button8:
                    if (firstNum.equals("0")) {
                        firstNum = "8";
                    } else {
                        firstNum = firstNum + "8";
                    }
                    output.setText(firstNum);
                    break;
                case R.id.button9:
                    if (firstNum.equals("0")) {
                        firstNum = "9";
                    } else {
                        firstNum = firstNum + "9";
                    }
                    output.setText(firstNum);
                    break;
                default:
                    break;

            }
        }
    };

    public String add(String num1, String num2) {
        double solution = 0;
        solution = Double.parseDouble(num1) + Double.parseDouble(num2);
        return Double.toString(solution);
    }

    public String subtract(String firstNum, String secondNum) {
        double solution = 0;
        solution = Double.parseDouble(secondNum) - Double.parseDouble(firstNum);
        return Double.toString(solution);
    }

    public String multiply(String firstNum, String secondNum) {
        double solution = 0;
        solution = Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
        return Double.toString(solution);
    }

    public String divide(String firstNum, String secondNum) {
        double solution = 0;
        solution = Double.parseDouble(secondNum) / Double.parseDouble(firstNum);
        return Double.toString(solution);
    }

    public String doMath(String firstNum, String secondNum, String seleceted) {
        if (seleceted.equals("+")) {
            secondNum = add(firstNum, secondNum);
        } else if (seleceted.equals("-")) {
            secondNum = subtract(firstNum, secondNum);
        } else if (seleceted.equals("*")) {
            secondNum = multiply(firstNum, secondNum);
        } else if (seleceted.equals("/")) {
            secondNum = divide(firstNum, secondNum);
        }
        return secondNum;
    }
}