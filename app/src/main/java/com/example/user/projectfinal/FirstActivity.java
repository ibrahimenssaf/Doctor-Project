package com.example.user.projectfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class FirstActivity extends AppCompatActivity {

    TextView textView3, tv_Doc, tv_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        textView3= (TextView)findViewById(R.id.tv_firstAct);

        YoYo.with(Techniques.Bounce).duration(1000).delay(1).repeat(1).playOn(textView3);

        tv_Doc = (TextView)findViewById(R.id.tv_Doc);
        tv_Doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this,DoctorLoginActivity.class);
                startActivity(i);
            }
        });
        tv_user =(TextView)findViewById(R.id.tv_user);
        tv_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstActivity.this,Register.class);
                startActivity(i);
            }
        });
    }
}
