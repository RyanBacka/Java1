// Ryan Backa
// Jav1 - 1609
// MainActivity

package com.ce02.backaryan.backaryan_ce02;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText selected1;
    EditText selected2;
    EditText selected3;
    EditText selected4;
    Button submit;
    int random1;
    int random2;
    int random3;
    int random4;
    int guesses = 4;
    int correct = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random1 = randomGenrator();
        random2 = randomGenrator();
        random3 = randomGenrator();
        random4 = randomGenrator();

        selected1 = (EditText) findViewById(R.id.firstNum);
        selected2 = (EditText) findViewById(R.id.secondNum);
        selected3 = (EditText) findViewById(R.id.thirdNum);
        selected4 = (EditText) findViewById(R.id.fourthNum);

        submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(myListener);

    }

    private Button.OnClickListener myListener = new Button.OnClickListener() {
        public void onClick(View view) {

            correct = 0;
            checkGuess(random1, selected1);
            checkGuess(random2, selected2);
            checkGuess(random3, selected3);
            checkGuess(random4, selected4);
            if (guesses != 1) {
                guesses--;
                if (correct == 4) {

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("You won!")
                            .setMessage("You guessed correctly in four guesses or less")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    reset();
                                }
                            })
                            .show();
                } else {
                    Context context = getApplicationContext();
                    CharSequence message = "You have " + guesses + " left.";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, message, duration);
                    toast.show();
                }
            } else if (guesses == 1) {
                if (correct == 4) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("You won!")
                            .setMessage("You guessed correctly in four guesses or less")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    reset();
                                }
                            })
                            .show();
                } else {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("You lost!")
                            .setMessage("You have run out of guesses.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    reset();
                                }
                            })
                            .show();
                }
            }
        }
    };

    public int randomGenrator() {
        Random rand = new Random();
        int number = rand.nextInt(10);
        return number;
    }

    public void checkGuess(int random, EditText selected) {
        int guess = Integer.parseInt(selected.getText().toString());
        if (guess > random) {
            selected.setTextColor(Color.RED);
        } else if (guess < random) {
            selected.setTextColor(Color.BLUE);
        } else if (guess == random) {
            correct++;
            selected.setTextColor(Color.GREEN);
        }
    }

    public void reset() {
        selected1.setText("");
        selected1.setTextColor(Color.BLACK);
        selected2.setText("");
        selected2.setTextColor(Color.BLACK);
        selected3.setText("");
        selected3.setTextColor(Color.BLACK);
        selected4.setText("");
        selected4.setTextColor(Color.BLACK);
        random1 = randomGenrator();
        random2 = randomGenrator();
        random3 = randomGenrator();
        random4 = randomGenrator();
        guesses = 4;
    }
}

