package com.example.hwaphon.sign.tool;

import android.os.AsyncTask;
import android.view.View;

import com.example.hwaphon.sign.inter.GetDataListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by hwaphon on 3/18/2016.
 */
public class HttpData extends AsyncTask<String,Void,String>{

    private String url;
    private GetDataListener mListener;
    private URLConnection mConnection;
    private URL mURL;
    private InputStream mInputStream;
    private BufferedReader mReader;
    private StringBuilder mBuilder;

    public HttpData(String url,GetDataListener listenerr){
        this.url = url;
        mListener = listenerr;
    }
    @Override
    protected String doInBackground(String... strings) {

        try {
            mURL = new URL(url);
            mConnection = mURL.openConnection();
            mConnection.setConnectTimeout(8000);
            mConnection.setReadTimeout(8000);
            mInputStream = mConnection.getInputStream();
            mReader = new BufferedReader(new InputStreamReader(mInputStream));
            mBuilder = new StringBuilder();
            String line="";
            while((line = mReader.readLine()) != null){
                mBuilder.append(line);
            }
            return mBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        mListener.getText(s);
        super.onPostExecute(s);

    }
}
