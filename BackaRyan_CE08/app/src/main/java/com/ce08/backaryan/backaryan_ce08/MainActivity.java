package com.ce08.backaryan.backaryan_ce08;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CounterTask countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button) findViewById(R.id.startButton);
        Button stopButton = (Button) findViewById(R.id.stopButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText minutesEdit = (EditText) findViewById(R.id.minutesEdit);
                EditText secondsEdit = (EditText) findViewById(R.id.secondsEdit);
                if (secondsEdit.getText().toString().equalsIgnoreCase("") && minutesEdit.getText().toString().equalsIgnoreCase("")
                        || secondsEdit.getText().toString().equalsIgnoreCase("0") && minutesEdit.getText().toString().equalsIgnoreCase("0")
                || secondsEdit.getText().toString().equalsIgnoreCase("00") && minutesEdit.getText().toString().equalsIgnoreCase("00")) {
                    Context context = getApplicationContext();
                    Toast.makeText(context, "Enter a valid number", Toast.LENGTH_SHORT).show();
                } else if (minutesEdit.getText().toString().equalsIgnoreCase("")
                        || minutesEdit.getText().toString().equalsIgnoreCase("0")
                        || minutesEdit.getText().toString().equalsIgnoreCase("00")) {
                    long seconds = Long.parseLong(secondsEdit.getText().toString());
                    long milliseconds = (seconds * 1000) + 1000;
                    countdown = new CounterTask();
                    countdown.execute(milliseconds);
                    ((Button) findViewById(R.id.startButton)).setEnabled(false);
                    ((Button) findViewById(R.id.stopButton)).setEnabled(true);
                } else if (secondsEdit.getText().toString().equalsIgnoreCase("")
                        || secondsEdit.getText().toString().equalsIgnoreCase("0")
                        || secondsEdit.getText().toString().equalsIgnoreCase("00")) {
                    long minutes = Long.parseLong(minutesEdit.getText().toString());
                    long seconds = minutes * 60;
                    long milliseconds = (seconds * 1000) + 1000;
                    countdown = new CounterTask();
                    countdown.execute(milliseconds);
                    ((Button) findViewById(R.id.startButton)).setEnabled(false);
                    ((Button) findViewById(R.id.stopButton)).setEnabled(true);
                } else {
                    long minutes = Long.parseLong(minutesEdit.getText().toString());
                    long seconds = Long.parseLong(secondsEdit.getText().toString());
                    seconds = seconds + (minutes * 60);
                    long miliseconds = (seconds * 1000) + 1000;
                    countdown = new CounterTask();
                    countdown.execute(miliseconds);
                    ((Button) findViewById(R.id.startButton)).setEnabled(false);
                    ((Button) findViewById(R.id.stopButton)).setEnabled(true);
                }
            }
        });


        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countdown.onCancelled();
                ((Button) findViewById(R.id.startButton)).setEnabled(true);
                ((Button) findViewById(R.id.stopButton)).setEnabled(false);
            }
        });
    }

    private class CounterTask extends AsyncTask<Long, Long, Long> {
        private boolean running = true;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Context context = getApplicationContext();
            Toast.makeText(context, "Timer Started!", Toast.LENGTH_SHORT).show();

        }

        @Override
        protected Long doInBackground(final Long... longs) {
            long startTime = System.currentTimeMillis();
            long totalTime = longs[0];
            long timeElapsed = 0;

            while (timeElapsed < totalTime) {
                if (running) {
                    try {
                        long currentTime = System.currentTimeMillis();
                        timeElapsed = currentTime - startTime;
                        if ((timeElapsed >= 1000) ) {
                            publishProgress(longs[0] - timeElapsed);
                        }
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    running = false;
                    publishProgress(timeElapsed);
                    break;
                }
            }
            return (longs[0]);
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);
            long seconds = values[0] / 1000 % 60;
            long minutes = values[0] / 1000 / 60;
            if (running) {
                if (minutes < 10) {
                    ((TextView) findViewById(R.id.minutesEdit)).setText("0" + minutes);
                } else {
                    ((TextView) findViewById(R.id.minutesEdit)).setText("" + minutes);
                }
                if (seconds < 10) {
                    ((TextView) findViewById(R.id.secondsEdit)).setText("0" + seconds);
                } else {
                    ((TextView) findViewById(R.id.secondsEdit)).setText("" + seconds);
                }
            } else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("Timer Cancelled")
                        .setMessage("Timer ran for " + minutes + " minutes and " + seconds + " seconds.")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ((TextView) findViewById(R.id.minutesEdit)).setText("");
                                ((TextView) findViewById(R.id.secondsEdit)).setText("");
                                dialogInterface.dismiss();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }

        @Override
        protected void onPostExecute(Long s) {
            super.onPostExecute(s);
            ((TextView) findViewById(R.id.stopButton)).setEnabled(false);
            ((TextView) findViewById(R.id.startButton)).setEnabled(true);
            ((TextView) findViewById(R.id.minutesEdit)).setText("00");
            ((TextView) findViewById(R.id.secondsEdit)).setText("00");

            if (running) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("Finished")
                        .setMessage("Timer has completed")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ((TextView) findViewById(R.id.minutesEdit)).setText("");
                                ((TextView) findViewById(R.id.secondsEdit)).setText("");
                                dialogInterface.dismiss();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            running = false;
            ((TextView) findViewById(R.id.minutesEdit)).setText("00");
            ((TextView) findViewById(R.id.secondsEdit)).setText("00");
        }
    }
}
