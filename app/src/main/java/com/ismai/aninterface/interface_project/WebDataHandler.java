package com.ismai.aninterface.interface_project;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class WebDataHandler extends AsyncTask<String, String, Bitmap>{

    ProgressDialog progressDialog;
    Context context;

    @Override
    protected void onPreExecute(){
        super.onPreExecute();

    }

    public void setContext(Context c){
        context = c;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("yosss");
        progressDialog.show();
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(strings[0]).getContent());
            //URL url = new URL(strings[0]);
            //Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void onPostExecute(){
        progressDialog.dismiss();
    }
}
