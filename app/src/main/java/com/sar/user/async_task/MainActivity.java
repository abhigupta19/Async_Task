package com.sar.user.async_task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
      URL url;
      ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.button);
         imageView=findViewById(R.id.imageView);

        try {
            url = new URL("http://www.freeimageslive.com/galleries/objects/general/pics/woodenbox0482.jpg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task=new Task();
                task.execute(url);

            }
        });



    }
    class Task extends AsyncTask<URL,Void,Bitmap>{



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Bitmap doInBackground(URL... urls) {
            URL urlk=urls[0];
            HttpURLConnection httpURLConnection=null;
            try {
                 httpURLConnection= (HttpURLConnection) urlk.openConnection();
                httpURLConnection.connect();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
                Bitmap bitmap=BitmapFactory.decodeStream(bufferedInputStream);
                return  bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                httpURLConnection.disconnect();
            }
            return null;
        }
    }
}
