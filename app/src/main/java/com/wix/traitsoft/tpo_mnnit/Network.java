package com.wix.traitsoft.tpo_mnnit;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedInputStream;
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
import java.net.URLEncoder;

import static android.media.tv.TvContract.Programs.Genres.encode;

/**
 * Created by Asus on 03-09-2017.
 */

public class Network extends AsyncTask<String,Void,String> {

    private String php_url0,php_url1;
    private Context context;
    private Dialog loadingDialog;
    private String status="";
    private String username = "";
    private String password = "";

    Network(Context context)
    {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        php_url0 = "https://one-eyed-rewards.000webhostapp.com/conn.php";
        php_url1 = "https://one-eyed-rewards.000webhostapp.com/login.php";
        loadingDialog = ProgressDialog.show(context, "Please wait", "Loading...");
    }

    @Override
    protected String doInBackground(String... args) {
        username = args[0];
        password = args[1];
        status = args[2];
        URL url;
        try {
            if(status.equals("signup"))
                url = new URL(php_url0);
            else
                url = new URL(php_url1);
            HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream is = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));


            String response = br.readLine();

            is.close();
            httpURLConnection.disconnect();
            if(status.equals("signup"))
            {
                if(response.equals("success"))
                    return "Success";
                else
                    return "User Already Exists";
            }
            else
            {
                if(response.equals("success"))
                    return "success";
                else
                    return "Invalid Credentials";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        loadingDialog.dismiss();
        Toast.makeText(context,result, Toast.LENGTH_LONG).show();
        if(status.equals("login"))
        {
            if(result.equals("success"))
            {
                Session session = new Session(context);
                session.store(username,password);
                Intent i = new Intent(context, Home.class);

                i.putExtra("username",username);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
            }
        }
    }
}
