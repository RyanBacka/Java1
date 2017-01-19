// Ryan Backa
// Jav1 - 1609
// MainActivity

package com.ce04.backaryan.backaryan_ce04;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText cell1_1;
    EditText cell1_2;
    EditText cell1_3;
    EditText cell1_4;
    EditText cell2_1;
    EditText cell2_2;
    EditText cell2_3;
    EditText cell2_4;
    EditText cell3_1;
    EditText cell3_2;
    EditText cell3_3;
    EditText cell3_4;
    EditText cell4_1;
    EditText cell4_2;
    EditText cell4_3;
    EditText cell4_4;

    Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cell1_1 = (EditText) findViewById(R.id.editText1_1);
        cell1_2 = (EditText) findViewById(R.id.editText1_2);
        cell1_3 = (EditText) findViewById(R.id.editText1_3);
        cell1_4 = (EditText) findViewById(R.id.editText1_4);
        cell2_1 = (EditText) findViewById(R.id.editText2_1);
        cell2_2 = (EditText) findViewById(R.id.editText2_2);
        cell2_3 = (EditText) findViewById(R.id.editText2_3);
        cell2_4 = (EditText) findViewById(R.id.editText2_4);
        cell3_1 = (EditText) findViewById(R.id.editText3_1);
        cell3_2 = (EditText) findViewById(R.id.editText3_2);
        cell3_3 = (EditText) findViewById(R.id.editText3_3);
        cell3_4 = (EditText) findViewById(R.id.editText3_4);
        cell4_1 = (EditText) findViewById(R.id.editText4_1);
        cell4_2 = (EditText) findViewById(R.id.editText4_2);
        cell4_3 = (EditText) findViewById(R.id.editText4_3);
        cell4_4 = (EditText) findViewById(R.id.editText4_4);
        submitButton = (Button) findViewById(R.id.submit);

        submitButton.setOnClickListener(checkListener);

        chooseBoard();
    }

    private void chooseBoard() {
        Random random = new Random();
        int boardCount = random.nextInt(3) + 1;
        if (boardCount == 1) {
            cell1_4.setText("3");
            cell1_4.setFocusable(false);
            cell2_2.setText("4");
            cell2_2.setFocusable(false);
            cell3_3.setText("2");
            cell3_3.setFocusable(false);
            cell4_1.setText("1");
            cell4_1.setFocusable(false);
        } else if (boardCount == 2) {
            cell1_4.setText("3");
            cell1_4.setFocusable(false);
            cell2_2.setText("3");
            cell2_2.setFocusable(false);
            cell3_3.setText("4");
            cell3_3.setFocusable(false);
            cell4_1.setText("1");
            cell4_1.setFocusable(false);
            cell4_4.setText("2");
            cell4_4.setFocusable(false);
        } else if (boardCount == 3) {
            cell1_1.setText("1");
            cell1_1.setFocusable(false);
            cell1_4.setText("3");
            cell1_4.setFocusable(false);
            cell2_2.setText("3");
            cell2_2.setFocusable(false);
            cell3_3.setText("2");
            cell3_3.setFocusable(false);
            cell4_1.setText("2");
            cell4_1.setFocusable(false);
        }
    }

    View.OnClickListener checkListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            EditText[] quadrant1 = {cell1_1, cell1_2, cell2_1, cell2_2};
            EditText[] quadrant2 = {cell1_3, cell1_4, cell2_3, cell2_4};
            EditText[] quadrant3 = {cell3_1, cell3_2, cell4_1, cell4_2};
            EditText[] quadrant4 = {cell3_3, cell3_4, cell4_3, cell4_4};
            EditText[] row1 = {cell1_1, cell1_2, cell1_3, cell1_4};
            EditText[] row2 = {cell2_1, cell2_2, cell2_3, cell2_4};
            EditText[] row3 = {cell3_1, cell3_2, cell3_3, cell3_4};
            EditText[] row4 = {cell4_1, cell4_2, cell4_3, cell4_4};
            EditText[] column1 = {cell1_1, cell2_1, cell3_1, cell4_1};
            EditText[] column2 = {cell1_2, cell2_2, cell3_2, cell4_2};
            EditText[] column3 = {cell1_3, cell2_3, cell3_3, cell4_3};
            EditText[] column4 = {cell1_4, cell2_4, cell3_4, cell4_4};

            EditText[][] quadrants = {quadrant1, quadrant2, quadrant3, quadrant4};
            EditText[][] rows = {row1, row2, row3, row4};
            EditText[][] columns = {column1, column2, column3, column4};
            int quadWrong = 0;
            int rowWrong = 0;
            int colWrong = 0;

            for (EditText[] q : quadrants) {
                int num;
                num = checkCorrect(q);
                if (num!=0){
                    quadWrong++;
                }
            }
            for (EditText[] r : rows) {
                int num;
                num = checkCorrect(r);
                if (num!=0){
                    rowWrong++;
                }
            }
            for (EditText[] c : columns) {
                int num;
                num = checkCorrect(c);
                if (num!=0){
                    colWrong++;
                }
            }

            if (quadWrong == 50 || rowWrong == 50 || colWrong == 50) {
                Context context = getApplicationContext();
                CharSequence message = "The puzzle is incomplete";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, message, duration);
                toast.show();
            } else if ((quadWrong == 0) && (rowWrong == 0) && (colWrong == 0)) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("You won!")
                        .setMessage("You guessed all numbers correctly!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        reset();
                                    }

                                })
                        .show();
            } else {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("You guessed incorrectly!")
                        .setMessage("quadrants wrong:" + quadWrong + ", rows wrong: " + rowWrong + ", columns wrong: " + colWrong)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        }
    };

    public int checkCorrect(EditText[] array) {
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        int num4 = 0;
        int numberWrong = 0;


        ArrayList<Integer> guesses = new ArrayList<>();

        for (EditText value : array) {
            if (value.getText().toString().equals("")) {
                numberWrong = 50;
            } else {
                guesses.add(Integer.parseInt(value.getText().toString()));
            }
        }

        if (numberWrong != 50) {

            if (guesses.contains(1)) {
                num1++;
            }
            if (guesses.contains(2)) {
                num2++;
            }
            if (guesses.contains(3)) {
                num3++;
            }
            if (guesses.contains(4)) {
                num4++;
            }

            System.out.println("1's: " + num1 + ", 2's: " + num2 + ", 3's: " + num3 + ", 4's: " + num4);

            if (num1 == 0) {
                numberWrong++;
            } else if (num2 == 0) {
                numberWrong++;
            } else if (num3 == 0) {
                numberWrong++;
            } else if (num4 == 0) {
                numberWrong++;
            }

        }
        return numberWrong;
    }

    public void reset() {
        cell1_1.setText("");
        cell1_2.setText("");
        cell1_3.setText("");
        cell1_4.setText("");
        cell2_1.setText("");
        cell2_2.setText("");
        cell2_3.setText("");
        cell2_4.setText("");
        cell3_1.setText("");
        cell3_2.setText("");
        cell3_3.setText("");
        cell3_4.setText("");
        cell4_1.setText("");
        cell4_2.setText("");
        cell4_3.setText("");
        cell4_4.setText("");
        chooseBoard();
    }
}
