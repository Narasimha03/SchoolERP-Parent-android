package com.example.harish.proschoolforparentadvanced.suresh;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.harish.proschoolforparentadvanced.Constants;
import com.example.harish.proschoolforparentadvanced.QuickDashboardClass;

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
 * Created by JANI on 26-06-2017.
 */
public class LauncherBackGroundTask extends AsyncTask<String, Void, String> {
    String json_result;
    Context mContext;
    SharedPreferences sharedPreferences;
    public LauncherBackGroundTask(Context mContext){
        this.mContext = mContext;
    }
    @Override
    protected void onPreExecute() {
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
    @Override
    protected void onPostExecute(String result) {
        json_result = result;
        Log.e("Log In", "" + json_result);
        if (json_result != null && !json_result.isEmpty()) {
            try {
                JSONObject jsonObject = new JSONObject(json_result);
                String token = jsonObject.getString("token");
                String schoolId = jsonObject.getString("school_id");
                String role = jsonObject.getString("role");
                String empUniqueId = jsonObject.getString("uniqueId");
            /*    SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Constants.schoolIdPref, schoolId);
                // editor.putString(Email, e);
                editor.commit();
*/
                System.out.println("login token launcher"+token);
                System.out.println("schoolIDLauncher"+schoolId);
                if (token != null && !token.isEmpty()&&role.equals("parent")) {
                    sharedPreferences = mContext.getSharedPreferences("AppInfo", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Constants.schoolIdPref, schoolId);
                    editor.putString(Constants.rolePref, role);
                    editor.putString(Constants.tokenPref, token);
                    //  editor.putString(Constants.empUniqueId, empUniqueId);
                    editor.commit();
                    Intent intent = new Intent(mContext, QuickDashboardClass.class);
                    mContext.startActivity(intent);
                }
               /* else  if (token != null && !token.isEmpty()&&role.equals("teacher")) {
                    String empId = jsonObject.getString("employee_id");
                    sharedPreferences = mContext.getSharedPreferences("AppInfo", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Constants.schoolIdPref, schoolId);
                    editor.putString(Constants.rolePref, role);
                    editor.putString(Constants.tokenPref, token);
                    editor.putString(Constants.empUniqueId, empUniqueId);
                    editor.putString(Constants.empId, empId);
                    editor.commit();
                    Intent intent = new Intent(mContext, TeacherQuickDashboardClass.class);
                    mContext.startActivity(intent);
                }*/
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("The email or password you entered was incorrect. Please try again");
                    builder.setPositiveButton("TRY AGAIN", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            SharedPreferences sharedPreferences = mContext.getSharedPreferences("AppInfo", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.apply();
                            Intent intent = new Intent(mContext, LoginActivity.class);
                            mContext.startActivity(intent);
                        }
                    });
                    builder.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mContext, "Sorry, Connection Timed Out..!", Toast.LENGTH_LONG).show();
        }
    }
}