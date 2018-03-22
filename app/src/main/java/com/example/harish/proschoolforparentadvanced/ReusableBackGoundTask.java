package com.example.harish.proschoolforparentadvanced;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

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

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by personal on 14-06-2017.
 */

public class ReusableBackGoundTask extends AsyncTask<String, Void, String> {
    Context mContext;
    String json_result;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;

    AllUserDetails allUserDetails;
    public AsyncResponse asyncResponse;


    public ReusableBackGoundTask(Context mContext, AsyncResponse asyncResponse) {
        this.mContext = mContext;
        this.asyncResponse = asyncResponse;
    }


    public interface AllUserDetails {
        void allUserDetails(String result) throws JSONException;
    }

    @Override
    protected void onPreExecute() {
       /* progressDialog = ProgressDialog.show(mContext, "Please wait...", "authenticating....", false);
        progressDialog.show();*/
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String json_string;
        String json_login = params[0];
        String json_url = Constants.loginUrl;
        try {
            URL url = new URL(json_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json");
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(json_login);
            //bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
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

    protected void onPostExecute(String result) {
        //   progressDialog.dismiss();
        json_result = result;
        //   Log.e("Log In", "Welcome subjects" + json_result);
        asyncResponse.allPreferedDetails(json_result);
    }
}