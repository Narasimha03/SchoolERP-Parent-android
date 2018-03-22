package com.example.harish.proschoolforparentadvanced.suresh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.harish.proschoolforparentadvanced.R;

import org.json.JSONException;
import org.json.JSONObject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {
    Context mContext = this;
    TextInputLayout textInputLayoutUsername, textInputLayoutPwd;
    EditText editUsername, editPwd;
    Button loginButton;
    // Session....
    SharedPreferences sharedPreferences;
    String loggedUserid, loggedPwd,schoolId;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Session....
        sharedPreferences = mContext.getSharedPreferences(getString(R.string.appinfo), MODE_PRIVATE);
        // User login details....
        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
        textInputLayoutPwd = (TextInputLayout) findViewById(R.id.textInputLayoutPwd);
        textInputLayoutPwd.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/Handlee-Regular.ttf"));
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPwd = (EditText) findViewById(R.id.editPwd);
        loginButton = (Button) findViewById(R.id.loginButton);
        // On Clicking Login button....
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(mContext, DashboardActivity.class);
                //startActivity(intent);

                String userId = editUsername.getText().toString();
                String password = editPwd.getText().toString();

                //editPwd.setTransformationMethod(new PasswordTransformationMethod());
                if (userId.isEmpty()) {
                    // Validating userId....
                    textInputLayoutUsername.setError("Please enter username");
                } else if (password.isEmpty()) {
                    // Validating password....
                    textInputLayoutPwd.setError("Please enter password");
                } else {
                    // Saving userId, pwd in session....
                    SharedPreferences.Editor editor = sharedPreferences.edit();


                   // SharedPreferences sharedPreferences = mContext.getSharedPreferences(Constants.schoolIdInfo, MODE_PRIVATE);
                    editor.putString(getString(R.string.useridInfo), userId);
                    editor.putString(getString(R.string.pwdInfo), password);
                    /*schoolId = sharedPreferences.getString(Constants.schoolIdPref, "");
                    System.out.println("schoolId login"+schoolId);*/
                    editor.apply();
                    // Making JSON....
                    JSONObject loginObject = new JSONObject();
                    try {
                        loginObject.put("email", userId);
                        loginObject.put("password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                   if (loginObject.length() > 0){

                       LoginBackGroundTask loginBackGroundTask = new LoginBackGroundTask(mContext);
                       loginBackGroundTask.execute(String.valueOf(loginObject));

                    /*
                        try {
                         String email=   loginObject.getString("email");
                            String passwordCheck=   loginObject.getString("password");

                            Log.e("email",email);
                        } catch (JSONException e) {
                            e.printStackTrace();*/
                        }
                        // Validating userid, pwd in server....

                   // }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
