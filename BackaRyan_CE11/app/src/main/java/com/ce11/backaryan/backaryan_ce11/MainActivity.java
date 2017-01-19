package com.ce11.backaryan.backaryan_ce11;

import android.content.Context;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> strings = new ArrayList<>();
    ArrayAdapter<String> listAdapter;
    public static final String ENTERED_STRING = "string";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submit = (Button) findViewById(R.id.submitButton);
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);

        if (savedInstanceState != null) {
            ArrayList<String> values = savedInstanceState.getStringArrayList("myList");
            if (values!=null){
                strings=values;
                listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
                ListView lv = (ListView) findViewById(R.id.listView);
                if (lv!=null) {
                    lv.setAdapter(listAdapter);
                }
            }
        }

        if (submit!=null) {
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText editText = (EditText) findViewById(R.id.stringField);
                    if (editText != null) {
                        String submitedString = editText.getText().toString();
                        if (submitedString.equalsIgnoreCase("") || submitedString.trim().equalsIgnoreCase("")) {
                            Context context = getApplicationContext();
                            Toast.makeText(context, "Enter a valid string", Toast.LENGTH_SHORT).show();
                        } else {
                            strings.add(submitedString);
                            ListView lv = (ListView) findViewById(R.id.listView);
                            if (lv!=null) {
                                lv.setAdapter(listAdapter);
                            }
                            editText.setText("");
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText et = (EditText) findViewById(R.id.stringField);
        if (et!=null) {
            String entered = et.getText().toString();
            outState.putString(ENTERED_STRING,entered);
        }
        ArrayList<String> values = strings;
        outState.putStringArrayList("myList",values);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String entered = savedInstanceState.getString(ENTERED_STRING, null);
        EditText et = (EditText) findViewById(R.id.stringField);
        if (et!=null) {
            et.setText(entered);
        }
    }
}
