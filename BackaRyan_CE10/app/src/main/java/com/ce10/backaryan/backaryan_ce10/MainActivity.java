//Ryan Backa
//Jav1 - 1609
//MainActivity

package com.ce10.backaryan.backaryan_ce10;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Context context;
    ArrayList<Reddit> reddits = new ArrayList<>();
    RedditAdapter redditAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        redditAdapter = new RedditAdapter(this, reddits);
        Button searchButton = (Button) findViewById(R.id.button);
        if (searchButton != null) {
            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(reddits!=null){
                        reddits.clear();
                    }
                    EditText searchText = (EditText) findViewById(R.id.editText);
                    String search = searchText.getText().toString();
                    if (search.equalsIgnoreCase("")){
                        Context context = getApplicationContext();
                        Toast.makeText(context, "Enter a valid String", Toast.LENGTH_SHORT).show();
                    } else {
                        search.replaceAll("\\s", "");
                        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (manager != null) {
                            NetworkInfo info = manager.getActiveNetworkInfo();
                            if (info != null) {
                                boolean isConnected = info.isConnected();
                                if (isConnected) {
                                    GetData task = new GetData();
                                    task.execute("https://www.reddit.com/search.json?q=" + search);
                                } else {
                                    Context context = getApplicationContext();
                                    Toast.makeText(context, "Cannot connect", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
            });
        }

        ListView redditList = (ListView) findViewById(R.id.listView);
        if (redditList!=null) {
            redditList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    TextView title = (TextView) findViewById(R.id.title);
                    TextView author = (TextView) findViewById(R.id.author);
                    TextView subreddit = (TextView) findViewById(R.id.subreddit);
                    TextView content = (TextView) findViewById(R.id.content);
                    title.setText(reddits.get(i).title);
                    author.setText("Author: " + reddits.get(i).author + "\n");
                    subreddit.setText("Subreddit: " + reddits.get(i).subreddit + "\n");
                    content.setText(reddits.get(i).content);
                }
            });

        }
    }

    private String getNetworkData(String urlString){
        try {
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            String data = IOUtils.toString(inputStream);
            inputStream.close();
            connection.disconnect();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private class GetData extends AsyncTask<String,Reddit,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Processing...");
            progressDialog.setMessage("Please wait.");
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String data = getNetworkData(strings[0]);
            try{
                JSONObject outermostObject = new JSONObject(data);
                JSONObject objectData = outermostObject.getJSONObject("data");
                JSONArray children = objectData.getJSONArray("children");
                for (int i=0; i<children.length(); i++){
                    JSONObject postObject = children.getJSONObject(i);
                    JSONObject postData = postObject.getJSONObject("data");
                    String subreddit = postData.getString("subreddit");
                    String content = postData.getString("selftext");
                    String title = postData.getString("title");
                    String author = postData.getString("author");
                    Reddit reddit = new Reddit(title,author,content,subreddit);
                    publishProgress(reddit);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Reddit... values) {
            super.onProgressUpdate(values);
            reddits.add(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(progressDialog!=null){
                progressDialog.dismiss();
            }
            ListView redditList = (ListView) findViewById(R.id.listView);
            redditList.setAdapter(redditAdapter);
        }
    }


}
