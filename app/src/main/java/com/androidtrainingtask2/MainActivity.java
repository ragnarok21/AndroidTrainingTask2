package com.androidtrainingtask2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                doInBackground();
            }
        });
    }

    private void doInBackground() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                backToMainThreadWithResponse(fetchNewsReport());
            }
        });
        thread.start();
    }

    private void backToMainThreadWithResponse(final NewsResponse response){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                getListDataResponse(response);
            }
        });
    }
    private void getListDataResponse(NewsResponse newsResponse) {
        ListView listview = (ListView)findViewById(R.id.listView);
        MyListViewAdapter adapter = new MyListViewAdapter(this);

        listview.setAdapter(adapter);
        adapter.setData(newsResponse.items);
    }

    private NewsResponse fetchNewsReport() {
        try{
            URL url = new URL("http://rss2json.com/api.json?rss_url=http://www.feedforall.com/sample.xml");
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection connection = null;
            if (urlConnection instanceof HttpURLConnection) {
                connection = (HttpURLConnection) urlConnection;
            } else {
                System.out.println("Please enter an HTTP URL.");
                return null;
            }
            String urlString = "";
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String current;
            while ((current = in.readLine()) != null) {
                urlString += current;
            }
            System.out.println(urlString);
            //convert string to json using jackson

            return (NewsResponse) Utils.fromJson(urlString,NewsResponse.class);


        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
