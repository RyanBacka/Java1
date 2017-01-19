// Ryan Backa
// Jav1 - 1609
// MainActivity

package com.ce06.backaryan.backaryan_ce06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    GridView myGrid;
    ListView myList;
    ArrayAdapter<String> arrayAdapter;
    List<HashMap<String,String>> nameBirthday;
    String[] from;
    int[] to;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Person[] people = new Person[]{
                new Person("Ryan", "Backa", "03/01/84", R.drawable.ryan_backa), new Person("Brantley", "Gilbert", "01/20/85", R.drawable.brantley_gilbert),
                new Person("Aaron", "Lewis", "04/13/72", R.drawable.aaron_lewis), new Person("David", "Draiman", "03/13/73", R.drawable.david_draiman),
                new Person("Corey", "Taylor", "12/08/73", R.drawable.corey_taylor), new Person("Patrick", "Stump", "04/27/84", R.drawable.patrick_stump),
                new Person("Ivan", "Moody", "01/07/80", R.drawable.ivan_moody), new Person("Anthony", "Kiedis", "11/01/62", R.drawable.anthony_kiedis),
                new Person("Brandon", "Boyd", "02/15/76", R.drawable.brandon_boyd), new Person("John", "Cooper", "04/07/75", R.drawable.john_cooper)
        };

        String[] nameArray = new String[]{
                people[0].firstName + " " + people[0].lastName, people[1].firstName + " " + people[1].lastName, people[2].firstName + " " + people[2].lastName,
                people[3].firstName + " " + people[3].lastName, people[4].firstName + " " + people[4].lastName, people[5].firstName + " " + people[5].lastName,
                people[6].firstName + " " + people[6].lastName, people[7].firstName + " " + people[7].lastName, people[8].firstName + " " + people[8].lastName,
                people[9].firstName + " " + people[9].lastName
        };

        customAdapter = new CustomAdapter(this, people);

        from = new String[] {"Name", "Birthday"};
        to = new int[] {R.id.name, R.id.birthday};

        nameBirthday = new ArrayList<HashMap<String, String>>();
        for (int item = 0; item<nameArray.length; item++){
            HashMap<String,String> map = new HashMap<>();
            map.put("Name", nameArray[item]);
            map.put("Birthday", people[item].birthday);
            nameBirthday.add(map);
        }

        ArrayList<String> nameArrayList = new ArrayList<>();
        nameArrayList.addAll(Arrays.asList(nameArray));

        Spinner viewSpinner = (Spinner) findViewById(R.id.viewSpinner);
        Spinner adapterSpinner = (Spinner) findViewById(R.id.adapterSpinner);
        viewSpinner.setOnItemSelectedListener(this);
        adapterSpinner.setOnItemSelectedListener(this);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameArrayList);
        myGrid = (GridView) findViewById(R.id.gridView);
        myList = (ListView) findViewById(R.id.listView);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){

        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.viewSpinner){
            String selectedView = spinner.getSelectedItem().toString();
            if (selectedView.equalsIgnoreCase("Grid View")){
                myList.setVisibility(View.GONE);
                myGrid.setVisibility(View.VISIBLE);
            } else if (selectedView.equalsIgnoreCase("List View")){
                myList.setVisibility(View.VISIBLE);
                myGrid.setVisibility(View.GONE);
            }
        } else if (spinner.getId() == R.id.adapterSpinner){
            String selectedAdapter = spinner.getSelectedItem().toString();
            if (selectedAdapter.equalsIgnoreCase("Array Adapter")){
                myGrid.setAdapter(arrayAdapter);
                myList.setAdapter(arrayAdapter);
            } else if (selectedAdapter.equalsIgnoreCase("Simple Adapter")){
                SimpleAdapter simpleAdapter = new SimpleAdapter(this, nameBirthday, R.layout.simple_adapter, from,to);
                myList.setAdapter(simpleAdapter);
                myGrid.setAdapter(simpleAdapter);
            } else if (selectedAdapter.equalsIgnoreCase("Base Adapter")){
                myList.setAdapter(customAdapter);
                myGrid.setAdapter(customAdapter);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
