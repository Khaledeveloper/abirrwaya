package com.khaled.aberrwaya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class signup extends AppCompatActivity {
    EditText usernameE,emailE ,passwordE, phoneE;
    TextView signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        usernameE =(EditText)findViewById(R.id.usernameID);
        emailE =(EditText)findViewById(R.id.emailID);
        passwordE =(EditText)findViewById(R.id.passwordID);
        phoneE =(EditText)findViewById(R.id.phoneID);
        signupbtn=(TextView) findViewById(R.id.signupbtnID);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username =usernameE.getText().toString();
                String email =emailE.getText().toString();
                String password =passwordE.getText().toString();
                String phone =phoneE.getText().toString();
                final String url="https://khaledev.000webhostapp.com/getuserss.php?" +
                        "uusername="+username+"&email="+email+"&ppassword="+password+
                        "&phone="+phone+"";

                Runnable runnable=new Runnable() {
                    @Override
                    public void run() {

                        try {
                            URL inserting = new URL(url);
                            HttpURLConnection httpURLConnection=(HttpURLConnection)inserting.openConnection();
                            InputStreamReader inputStream = new InputStreamReader(httpURLConnection.getInputStream());
                            BufferedReader bufferedReader= new BufferedReader(inputStream);
                            final String result = bufferedReader.readLine();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(signup.this, result, Toast.LENGTH_SHORT).show();



                                    Intent intent = new Intent(signup.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Thread thread =new Thread(runnable);
                thread.start();







            }
        });

    }
}
