package vritika.app.startstuf;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.List;

public class Grouplist extends AppCompatActivity implements AdapterView.OnItemClickListener{
    SQLiteDatabase mydatabase;
    private ListView listView;
    String gname;
    SwipeRefreshLayout swipeContainer;
    ProgressBar p;
    int check=0;
    String data;

    private List<ContactBean> list = new ArrayList<ContactBean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grouplist);
        Intent i=getIntent();
        gname=i.getStringExtra("gname");
        TextView txt=(TextView)findViewById(R.id.groupname);
        txt.setText(gname);
        listView = (ListView)findViewById(R.id.grouplist);
        listView.setOnItemClickListener(this);
        p=(ProgressBar)findViewById(R.id.listpro);
        getlist();

    }
    @Override
    public void onItemClick(AdapterView<?> listview, View v, int position,
                            long id) {
        ContactBean bean = (ContactBean) listview.getItemAtPosition(position);
        showCallDialog(bean.getName(), bean.getPhoneNo());
    }

    private void showCallDialog(final String name, final String phoneNo)
    {
        finish();
        Intent i=new Intent(getBaseContext(),Chat_room.class);
        i.putExtra("name",name);
        i.putExtra("number",phoneNo);
        startActivity(i);
    }

    public void getlist(){
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(gname);
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
            final String tokenurl = "http://startstuffapp.in/startstuff/gmember.php";
            String gname = params[0];

            try {
                URL url = new URL(tokenurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("gname", "UTF-8") + "=" + URLEncoder.encode(gname, "UTF-8");
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
            Boolean test = isNetworkAvailable();
            if (test == true) {
                BackgroundTask backgroundTask = new BackgroundTask(Grouplist.this);
                backgroundTask.execute(gname);
            } else {
                Toast.makeText(getBaseContext(), "Connect To Internet", Toast.LENGTH_SHORT).show();
            }
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            p.setVisibility(View.GONE);
            if(!result.equals("")) {
                data = result;
                String[] members = data.split("#");
                int i = members.length;

                for (int j = 0; j < i; j++) {
                    String[] mix = members[j].split("@");
                    String name2 = mix[0];
                    String num = mix[1];
                    ContactBean objContact = new ContactBean();
                    objContact.setName(name2);
                    objContact.setPhoneNo(num);
                    list.add(objContact);
                }
                ContanctAdapter objAdapter = new ContanctAdapter(Grouplist.this, R.layout.alluser_row2, list, false);
                listView.setAdapter(objAdapter);
                objAdapter.notifyDataSetChanged();
            }
            else
                Toast.makeText(getBaseContext(),"No members found!!!",Toast.LENGTH_LONG).show();



        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

}
