package com.example.harish.proschoolforparentadvanced.suresh.timetable;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import com.example.harish.proschoolforparentadvanced.Constants;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by JANI on 14-06-2017.
 */

public class SessionTimingsBackGroundTask extends AsyncTask<String, Void, String>{

    Context mContext;
    private OnTimeResponse onTimeResponse;

    public SessionTimingsBackGroundTask(Context mContext, OnTimeResponse onTimeResponse){
        this.mContext = mContext;
        this.onTimeResponse = onTimeResponse;
    }

    // Callback....
    public interface OnTimeResponse{
        void onTimeResponse(String response) throws JSONException;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String json_string;
        String schoolId = params[0];
        String json_url = Constants.sessionTimingUrl + schoolId;
        try {
            URL url = new URL(json_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));
            StringBuilder stringBuilder = new StringBuilder();
            while ((json_string = bufferedReader.readLine()) != null) {
                stringBuilder.append(json_string + "\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null && !result.isEmpty()){
            if (onTimeResponse != null) {
                try {
                    onTimeResponse.onTimeResponse(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
