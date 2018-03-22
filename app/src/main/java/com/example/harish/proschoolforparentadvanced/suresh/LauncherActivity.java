package com.example.harish.proschoolforparentadvanced.suresh;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.harish.proschoolforparentadvanced.Constants;
import com.example.harish.proschoolforparentadvanced.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LauncherActivity extends AppCompatActivity {
    Context mContext = this;
    String loggedEmail, loggedPwd,tokenPref;
    boolean network_availability;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // To provide full screen....
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launcher);
        //  PrefManager prefManager = new PrefManager(getApplicationContext());
        // make first time launch TRUE
        //  prefManager.setFirstTimeLaunch(true);
        //  startActivity(new Intent(LauncherActivity.this, WelcomeActivity.class));
        //  finish();
        /**
         * Checking loigin_user is logged in....
         */
        sharedPreferences = this.getSharedPreferences(getString(R.string.appinfo), MODE_PRIVATE);
        if(isNetworkStatusAvialable (getApplicationContext())) {
            // To check active network....
            NetworkBackGroundTask networkBackGroundTask = new NetworkBackGroundTask(mContext);
            networkBackGroundTask.execute();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("No Internet Connection");
            builder.setMessage("Do you want to try again?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(LauncherActivity.this, LauncherActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        // To display launcher activity for some time.....
      /*  Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start(); */
    }
    // To check internet connection....
    public static boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected() && netInfos.isAvailable())
                    return true;
        }
        return false;
    }
    // To check network connection....
    public class NetworkBackGroundTask extends AsyncTask<String, Void, Boolean> {
        Context ctx;
        boolean json_result;
        NetworkBackGroundTask(Context ctx){
            this.ctx = ctx;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected Boolean doInBackground(String... params) {
            try {
                HttpURLConnection urlc = (HttpURLConnection)
                        (new URL("http://clients3.google.com/generate_204")
                                .openConnection());
                urlc.setRequestProperty("User-Agent", "Android");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1000);
                urlc.connect();
                return (urlc.getResponseCode() == 204 &&
                        urlc.getContentLength() == 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        @Override
        protected void onPostExecute(Boolean result) {
            json_result = result;
            if (json_result){
                loggedEmail = sharedPreferences.getString(getString(R.string.useridInfo), "");
                loggedPwd = sharedPreferences.getString(getString(R.string.pwdInfo), "");
                sharedPreferences1 = mContext.getSharedPreferences("AppInfo", MODE_PRIVATE);
                tokenPref = sharedPreferences1.getString(Constants.tokenPref, "");
                if (!tokenPref.isEmpty() && !tokenPref.equals("")) {
                    // Making JSON
                    //   if (!loggedEmail.isEmpty() && !loggedEmail.equals("") && !loggedPwd.isEmpty() && !loggedPwd.equals("")) {
                    JSONObject loginObject = new JSONObject();
                    try {
                        loginObject.put("email", loggedEmail);
                        loginObject.put("password", loggedPwd);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (loginObject.length() > 0){
                        // Validating userid, pwd in server....
                        LauncherBackGroundTask launcherBackGroundTask = new LauncherBackGroundTask(mContext);
                        launcherBackGroundTask.execute(String.valueOf(loginObject));
                    }
                } else {
                    Intent intent = new Intent(ctx, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("No Internet Connection");
                builder.setMessage("Do you want to try again?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(LauncherActivity.this, LauncherActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
    }
}