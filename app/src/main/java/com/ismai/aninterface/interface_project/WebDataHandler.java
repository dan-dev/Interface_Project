package com.ismai.aninterface.interface_project;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;


public class WebDataHandler extends AsyncTask<String, Void, Bitmap>{

    private final WeakReference<ImageView> imageViewWeakReference;

    public WebDataHandler(ImageView imageView){
        imageViewWeakReference = new WeakReference<ImageView>(imageView);
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        return  getImage(strings[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(isCancelled()){
            bitmap = null;
        }

        if(imageViewWeakReference != null){
            ImageView imageView = imageViewWeakReference.get();
            if(imageView != null){
                if (bitmap != null){
                    imageView.setImageBitmap(bitmap);
                }
                else {
                    imageView.setImageResource(R.drawable.thumbnail);
                }
            }
        }
        //progressDialog.dismiss();
    }

    private  Bitmap getImage(String link){
        HttpURLConnection connection = null;
        try {
            URL url = new URL(link);
            connection = (HttpURLConnection) url.openConnection();
            int code = connection.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK){
                return null;
            }
            InputStream stream = connection.getInputStream();
            if(stream != null){
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                return bitmap;
            }
            //Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(strings[0]).getContent());
            //URL url = new URL(strings[0]);
            //Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            //return bitmap;
        } catch (IOException e) {
            connection.disconnect();
        }
        finally {
            if (connection != null){
                connection.disconnect();
            }
        }
        return null;
    }
}
