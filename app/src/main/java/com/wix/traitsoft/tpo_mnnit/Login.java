package com.wix.traitsoft.tpo_mnnit;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.attr.name;
import static android.os.Build.VERSION_CODES.N;
import static com.wix.traitsoft.tpo_mnnit.R.id.login;

public class Login extends AppCompatActivity implements Intern_Placement {

    String registration;
    String password;
    EditText reg;
    EditText passwd;
    Button signin;
    Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Session session = new Session(this);
        boolean isnull = session.isNull();
        if(!isnull)
        {
            Pair<String,String> hm;
            hm = session.getDetails();
            String key = hm.first;
            Intent i = new Intent(getApplicationContext(), Home.class);
            i.putExtra("username",key);
            startActivity(i);
        }

        reg = (EditText)findViewById(R.id.registration);
        passwd = (EditText)findViewById(R.id.password);
        signin = (Button)findViewById(R.id.signin);
        signup = (Button)findViewById(R.id.signup);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signup();

            }
        });
    }

    private boolean checkreg()
    {
        if(registration == null)
            return false;
        return registration.length() == 8;
    }

    private boolean checkpasswd()
    {
        if(password == null)
            return false;
        return registration.length() >= 4;
    }

    @Override
    public void login() {
        registration = reg.getText().toString();
        registration = registration.trim();
        password = passwd.getText().toString();

        Connection connection = new Connection(Login.this);
        if(connection.checkConnection()==0)
            Toast.makeText(getApplicationContext(), "No Internet Access", Toast.LENGTH_LONG).show();

        else
        {
            if(checkreg())
            {
                if(checkpasswd())
                {
                    Network network = new Network(Login.this);
                    network.execute(registration,password,"login");
                }
                else
                {
                    passwd.setError("Password too Short!");
                }
            }
            else
            {
                reg.setError("Invalid Registration No.!");
            }
        }
    }

    @Override
    public void signup() {

        registration = reg.getText().toString();
        registration = registration.trim();
        password = passwd.getText().toString();

        Connection connection = new Connection(Login.this);
        if(connection.checkConnection()==0)
            Toast.makeText(getApplicationContext(), "No Internet Access", Toast.LENGTH_LONG).show();

        else
        {
            if(checkreg())
            {
                if(checkpasswd())
                {
                    Network network = new Network(Login.this);
                    network.execute(registration,password,"signup");
                }
                else
                {
                    passwd.setError("Password too Short!");
                }
            }
            else
            {
                reg.setError("Invalid Registration No.!");
            }
        }

    }
}
