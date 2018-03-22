package com.example.harish.proschoolforparentadvanced.suresh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.harish.proschoolforparentadvanced.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        Button student = (Button)findViewById(R.id.student);
        Button teacher = (Button)findViewById(R.id.teacher);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(it);

            }
        });





        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent teacherLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(teacherLogin);


            }
        });
    }
}
