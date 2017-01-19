// Ryan Backa
// Jav1 - 1609
// MainActivity

package com.ce05.backaryan.backaryan_ce05;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> stringArray = new ArrayList<>();
    TextView averageText;
    TextView medianText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.addButton).setOnClickListener(buttonListener);
        findViewById(R.id.viewButton).setOnClickListener(buttonListener);
        averageText = (TextView) findViewById(R.id.averageNumber);
        medianText = (TextView) findViewById(R.id.medianNumber);
    }

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final NumberPicker indexPicker = (NumberPicker) findViewById(R.id.indexPicker);
            EditText stringEntry = (EditText) findViewById(R.id.stringEntry);
            int id = view.getId();

            if (id == R.id.addButton) {
                boolean stringExists = false;
                String enteredString = stringEntry.getText().toString();
                if (!enteredString.equals("") && enteredString.trim().length() > 0) {
                    for (int i = 0; i < stringArray.size(); i++) {
                        String arrayString = stringArray.get(i).toString();
                        if (enteredString.equals(arrayString)) {
                            stringExists = true;
                        }
                    }
                    if (!stringExists) {
                        stringArray.add(stringEntry.getText().toString());
                        indexPicker.setMaxValue(stringArray.size() - 1);
                        indexPicker.setMinValue(stringArray.size() - stringArray.size());
                        indexPicker.setWrapSelectorWheel(true);
                        stringEntry.setText("");
                        setMath();
                    } else {
                        Context context = getApplicationContext();
                        CharSequence message = "String was already entered!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, message, duration);
                        toast.show();
                    }
                } else {
                    Context context = getApplicationContext();
                    CharSequence message = "You entered an impropper string!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, message, duration);
                    toast.show();
                    stringEntry.setText("");
                }
            } else {
                if (stringArray.isEmpty()) {
                    Context context = getApplicationContext();
                    CharSequence message = "No strings have been entered!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, message, duration);
                    toast.show();
                    stringEntry.setText("");
                } else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setTitle("Selected String")
                            .setMessage(stringArray.get(indexPicker.getValue()))
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .setNegativeButton("Remove", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    stringArray.remove(indexPicker.getValue());
                                    indexPicker.setMaxValue(stringArray.size() - 1);
                                    setMath();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        }
    };

    public void setMath() {
        double sumOfLength = 0;
        for (int z = 0; z < stringArray.size(); z++) {
            sumOfLength = sumOfLength + stringArray.get(z).length();
        }
        double average = sumOfLength / (double) stringArray.size();
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        averageText.setText("" + decimalFormat.format(average));
        if (stringArray.size() % 2 == 0) {
            int[] stringLength = new int[stringArray.size()];
            for (int num = 0; num < stringArray.size(); num++) {
                stringLength[num] = stringArray.get(num).length();
            }
            java.util.Arrays.sort(stringLength);
            int arrayLength;
            double length1;
            double length2;
            if (stringArray.size() <= 2) {
                arrayLength = 0;
                length1 = stringLength[arrayLength];
                length2 = stringLength[arrayLength + 1];
            } else {
                arrayLength = stringLength.length / 2;
                length1 = stringLength[arrayLength - 1];
                length2 = stringLength[arrayLength];
            }
            medianText.setText("" + decimalFormat.format((length1 + length2) / 2));
        } else {
            int[] stringLength = new int[stringArray.size()];
            for (int num = 0; num < stringArray.size(); num++) {
                stringLength[num] = stringArray.get(num).length();
            }
            java.util.Arrays.sort(stringLength);
            int arrayLength = stringLength.length / 2;
            double length = stringLength[arrayLength];
            medianText.setText("" + decimalFormat.format(length));
        }
    }
}
