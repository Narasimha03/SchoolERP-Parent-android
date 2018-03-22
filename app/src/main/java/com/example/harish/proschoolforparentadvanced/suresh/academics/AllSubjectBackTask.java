package com.example.harish.proschoolforparentadvanced.suresh.academics;

import android.content.Context;
import android.os.AsyncTask;


import com.example.harish.proschoolforparentadvanced.Constants;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by JANI on 16-06-2017.
 */

public class AllSubjectBackTask extends AsyncTask<String, Void, String>{
    Context mContext;
    OnSubjectResponse onSubjectResponse;

    public AllSubjectBackTask(Context mContext, OnSubjectResponse onSubjectResponse){
        this.mContext = mContext;
        this.onSubjectResponse = onSubjectResponse;
    }

    public interface OnSubjectResponse{
        void OnSubjectResponse(String response) throws JSONException;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String json_string;
        String sectionId = params[0];
        String json_url = Constants.subjectUrl + sectionId;
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
            if (onSubjectResponse != null){
                try {
                    onSubjectResponse.OnSubjectResponse(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
