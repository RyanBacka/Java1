// Ryan Backa
// Jav1 - 1609
// MainActivity

package com.ce09.backaryan.backa_ce09;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
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
    String apiKey = "AIzaSyCjbKAlZs27UV64w5SBjF1oaZ60Rtxnv5U";
    ArrayList<Book> books = new ArrayList<>();
    BookAdapter bookAdapter;
    ProgressDialog progressDialog;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        bookAdapter = new BookAdapter(this, books);
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null){
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info!=null){
                boolean isConnected = info.isConnected();
                if (isConnected){
                    GetData task = new GetData();
                    task.execute("https://www.googleapis.com/books/v1/volumes?q=artificial+intelligence&maxResults=20&key=" + apiKey);
                } else {
                    // TODO: Alert User
                    Context context = getApplicationContext();
                    Toast.makeText(context, "Cannot connect to Google Books", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private String getNetworkData(String urlString) {
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
    private class GetData extends AsyncTask<String, Book, Void>{

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
        protected Void doInBackground(String... strings) {
            String data = getNetworkData(strings[0]);

            //Todo: Parse Data
            try {
                JSONObject outermostObject = new JSONObject(data);
                JSONArray items = outermostObject.getJSONArray("items");
                for (int i = 0; i<items.length(); i++){
                    JSONObject bookObject = items.getJSONObject(i);
                    JSONObject volumeInfo = bookObject.getJSONObject("volumeInfo");
                    String title = volumeInfo.getString("title");
                    JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                    String thumbnail = imageLinks.getString("thumbnail");
                    Book book = new Book(title,thumbnail);
                    publishProgress(book);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

            } catch(JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Book... values) {
            super.onProgressUpdate(values);
            books.add(values[0]);
        }

        @Override
        protected void onPostExecute(Void v) {
            //TODO: update UI
            super.onPostExecute(v);
            if(progressDialog!=null){
                progressDialog.dismiss();
            }
            GridView bookGrid = (GridView) findViewById(R.id.gridView);
            bookGrid.setAdapter(bookAdapter);
        }
    }

}
