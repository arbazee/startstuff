package vritika.app.startstuf;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

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

public class otp extends AppCompatActivity {
    EditText ET_NAME,ET_PASS,ET_OTP;
    String login_name,login_pass,otp_code,response;
    int flag=0;
    //ProgressBar p,q;
    LinearLayout linearLayout;

    public class BackgroundTask extends AsyncTask<String,Void,String> {


        AlertDialog alertDialog;
        Context ctx;

        BackgroundTask(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(ctx).create();
            alertDialog.setTitle("Login Information....");
        }

        @Override
        protected String doInBackground(String... params) {
            String login_url = "http://startstuffapp.in/startstuff/register.php";
            String get_otp_url = "http://startstuffapp.in/startstuff/genotp.php";
            String method = params[0];

            if (method.equals("login")) {
                String login_name = params[1];
                String login_pass = params[2];
                String token = params[3];
                try {
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(login_name, "UTF-8") + "&" +
                            URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(login_pass, "UTF-8") + "&" +
                            URLEncoder.encode("token", "UTF-8") + "=" + URLEncoder.encode(token, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String response = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return response;


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (method.equals("getotp")) {
                String login_name = params[1];
                String login_pass = params[2];
                try {
                    URL url = new URL(get_otp_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("login_name", "UTF-8") + "=" + URLEncoder.encode(login_name, "UTF-8") + "&" +
                            URLEncoder.encode("login_pass", "UTF-8") + "=" + URLEncoder.encode(login_pass, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String response = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }


                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return response;


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

            if(flag==1)
            {
                if(result!=null)
                    response=result;

                Toast.makeText(getBaseContext(),"OTP Generated You will get it by SMS",Toast.LENGTH_LONG).show();
            }
            else
            {
                if (result.equals("success")) {

                    SharedPreferences sharedpref=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2=sharedpref.edit();
                    editor2.putString("data","set");
                    editor2.apply();
                    alertDialog.setMessage(result);
                    alertDialog.show();
                    login_pass = ET_PASS.getText().toString();
                    login_name = ET_NAME.getText().toString();
                    EditText u=(EditText)findViewById(R.id.user_name);
                    //String login_name=u.getText().toString();
                    final SharedPreferences sharedpreferences = getSharedPreferences("unumber", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("uid",login_pass);
                    editor.putString("uname",login_name);



                    editor.commit();
                    Intent i = new Intent(getBaseContext(),MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                } else {
                    alertDialog.setMessage(result);
                    alertDialog.show();
                }
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        linearLayout = (LinearLayout) findViewById(R.id.linear1);

        SharedPreferences sharedpref=getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String data=sharedpref.getString("data","");


        if(data.equals("set")){
            finish();
            Intent i=new Intent(getBaseContext(),MainActivity.class);
            startActivity(i);

        }
        ET_NAME = (EditText) findViewById(R.id.user_name);
        ET_PASS = (EditText) findViewById(R.id.user_pass);
        //ET_OTP = (EditText) findViewById(R.id.user_otp);




    }


    public void otpgenerate(View view) {

        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(ET_PASS.getWindowToken(), 0);


        login_name = ET_NAME.getText().toString();
        login_pass = ET_PASS.getText().toString();

        if(login_pass.length()==10) {

            linearLayout.setEnabled(false);
            setContentView(R.layout.otp2);

        ET_OTP = (EditText) findViewById(R.id.user_otp);





        String method = "getotp";
        flag=1;
        Boolean test=isNetworkAvailable();
        if(test==true) {
            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(method, login_name, login_pass);
        }

        else
        {
            Toast.makeText(getBaseContext(), "Connect To Internet", Toast.LENGTH_SHORT).show();
        }

        }
        else
        {

            Toast.makeText(getApplication(),"Enter 10 digit mobile number",Toast.LENGTH_LONG).show();

        }
    }


    public void userLogin(View view) {
        //q.setVisibility(View.VISIBLE);
        login_name = ET_NAME.getText().toString();
        login_pass = ET_PASS.getText().toString();


        ET_OTP = (EditText) findViewById(R.id.user_otp);



        otp_code = ET_OTP.getText().toString();


        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(ET_OTP.getWindowToken(), 0);

        if(otp_code.equals(response)&&otp_code.length()==4) {
            String method = "login";
            flag = 0;
            Boolean test = isNetworkAvailable();
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();


            if (test == true) {
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(method, login_name, login_pass, refreshedToken);
            } else {
                Toast.makeText(getBaseContext(), "Connect To Internet", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {

            Toast.makeText(getBaseContext(), "Incorrect OTP", Toast.LENGTH_SHORT).show();
//            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//            alertDialogBuilder.setTitle("Oops");
//            alertDialogBuilder.setMessage("Otp Entered is incorrect");
//            alertDialogBuilder.show();
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


}
