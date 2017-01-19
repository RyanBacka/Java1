// Ryan Backa
// Jav1 - 1609
// MainActivity

package com.ce07.backaryan.backaryan_ce07;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView playerList;
    String[] from;
    int[] to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Players[] players = new Players[]{
            new Players("Carson Palmer", "QB", "New England", false, true, 0), new Players("Mark Ingram", "RB", "Oakland", false, true, 7),
                new Players("Jeremy Langford", "RB", "Houston", false, true, 9), new Players("Demaryius Thomas", "WR", "Carolina", true, true, 8),
                new Players("Eric Decker", "WR", "Cincinnati", false, true, 8), new Players("Dwayne Allen", "TE", "Detroit", false, true, 0),
                new Players("DeSean Jackson", "FLEX", "Pittsburgh", false, true, 0), new Players("Packers", "D/ST", "Jacksonville", false, true, 7),
                new Players("Adam Vinatieri", "K", "Detroit", false, true, 0), new Players("Rob Gronkowski", "TE", "Arizona", true, false, 0),
                new Players("Jeremy Hill", "RB", "New York Jets", false, false, 1), new Players("Phillip Rivers", "QB", "Kansas City", false, false, 10),
                new Players("Justin Forsett", "RB", "Buffalo", false, false, 6), new Players("Markus Wheaton", "WR", "Washington", true, false, 0),
                new Players("Sammie Coates", "WR", "Washington", false, false, 0)
        };

        final ArrayList<HashMap<String, String>> nameOpponent = new ArrayList<HashMap<String, String>>();
        final ArrayList<String> playerNameList = new ArrayList<>();
        for (int i=0; i<players.length; i++){
            playerNameList.add(players[i].name);
            HashMap<String, String> map = new HashMap<>();
            map.put("Name", players[i].name.toString());
            map.put("Opponent", players[i].opponent.toString());
            nameOpponent.add(map);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, playerNameList);
        Spinner nameSpinner = (Spinner) findViewById(R.id.nameSpinner);
        if (nameSpinner != null) {
            nameSpinner.setAdapter(arrayAdapter);
            nameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView playerName = (TextView) findViewById(R.id.playerName);
                    TextView playerPoints = (TextView) findViewById(R.id.playerPoints);
                    TextView opponent = (TextView) findViewById(R.id.opponent);
                    TextView injured = (TextView) findViewById(R.id.injured);
                    playerPoints.setText("" + players[i].points);
                    opponent.setText("Opponent: " + players[i].opponent);
                    if (players[i].isInjured && players[i].isStarting) {
                        playerName.setTextColor(Color.GREEN);
                        playerName.setText(players[i].name + ", " + players[i].position);
                        injured.setTextColor(Color.RED);
                        injured.setText("Inj");
                    } else if (!(players[i].isInjured) && players[i].isStarting) {
                        playerName.setTextColor(Color.GREEN);
                        playerName.setText(players[i].name + ", " + players[i].position);
                        injured.setText("");
                    } else if (players[i].isInjured) {
                        playerName.setTextColor(Color.RED);
                        playerName.setText(players[i].name + ", " + players[i].position);
                        injured.setTextColor(Color.RED);
                        injured.setText("Inj");
                    } else {
                        playerName.setTextColor(Color.BLACK);
                        playerName.setText(players[i].name + ", " + players[i].position);
                        injured.setText("");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        playerList = (ListView) findViewById(R.id.playerList);
        from = new String[] {"Name", "Opponent"};
        to = new int[] {R.id.adapterName, R.id.adapterOpponent};
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, nameOpponent, R.layout.simple_adapter, from,to);
        if (playerList != null) {
            playerList.setAdapter(simpleAdapter);

            playerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView playerName = (TextView) findViewById(R.id.playerNameLand);
                    TextView opponent = (TextView) findViewById(R.id.opponentLand);
                    TextView points = (TextView) findViewById(R.id.playerPointsLand);
                    TextView injured = (TextView) findViewById(R.id.injuredLand);
                    opponent.setText("Opponent: " + players[i].opponent);
                    points.setText("" + players[i].points);

                    if (players[i].isStarting) {
                        playerName.setTextColor(Color.GREEN);
                        playerName.setText(players[i].name + ", " + players[i].position);
                    } else {
                        playerName.setTextColor(Color.BLACK);
                        playerName.setText(players[i].name + ", " + players[i].position);
                    }

                    if (players[i].isInjured){
                        injured.setTextColor(Color.RED);
                        injured.setText("Injured");
                    } else {
                        injured.setText("");
                    }



                }
            });
        }


    }
}
