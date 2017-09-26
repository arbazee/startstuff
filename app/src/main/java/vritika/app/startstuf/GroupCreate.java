package vritika.app.startstuf;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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

public class GroupCreate extends AppCompatActivity {
    SQLiteDatabase mydatabase,mydb2;
    String gname;
    ProgressBar p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
      //  getSupportActionBar().setTitle("Startstuff");
        p=(ProgressBar)findViewById(R.id.gcreate);
        p.setVisibility(View.GONE);
        Button b=(Button)findViewById(R.id.cbutton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean b=isNetworkAvailable();
                if(b==true) {
                    p.setVisibility(View.VISIBLE);
                    EditText e = (EditText) findViewById(R.id.gname);
                    gname = e.getText().toString();

                    SharedPreferences sharedpreferences = getSharedPreferences("unumber", Context.MODE_PRIVATE);
                    String unum = sharedpreferences.getString("uid", "Not");
                    String uname=sharedpreferences.getString("uname", "Not");
                    BackgroundTask backgroundTask = new BackgroundTask(getBaseContext());
                    backgroundTask.execute(unum, gname,uname);
                }
                else
                    Toast.makeText(getBaseContext(),"Connect to internet!!!",Toast.LENGTH_LONG).show();

            }
        });
    }

    public class BackgroundTask extends AsyncTask<String,Void,String> {
        Context ctx;
        BackgroundTask(Context ctx)
        {
            this.ctx =ctx;
        }
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            final String tokenurl = "http://startstuffapp.in/startstuff/groupcreate.php";
            String unumber = params[0];
            String gname2=params[1];
            String uname=params[2];

            try {
                URL url = new URL(tokenurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("gname", "UTF-8") + "=" + URLEncoder.encode(gname2, "UTF-8")+"&"+
                        URLEncoder.encode("number","UTF-8")+"="+URLEncoder.encode(unumber,"UTF-8")+"&"+
                        URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(uname,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line  = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
                //httpURLConnection.connect();
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
            p.setVisibility(View.GONE);
            Toast.makeText(getBaseContext(),result,Toast.LENGTH_LONG).show();
            if(result.equals("Group created"))
            {
                SharedPreferences sharedpreferences = getSharedPreferences("unumber", Context.MODE_PRIVATE);
                String unum= sharedpreferences.getString("uid","Not");
                String user=sharedpreferences.getString("uname","Not");
                mydatabase = openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS usergroups(name VARCHAR,number VARCHAR);");
                ContentValues values = new ContentValues();
                values.put("name", gname);
                values.put("number", "group");
                mydatabase.insert("usergroups", null, values);
                mydatabase.close();
                finish();
                Intent i=new Intent(getBaseContext(),Group.class);
                startActivity(i);
            }

        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

}
