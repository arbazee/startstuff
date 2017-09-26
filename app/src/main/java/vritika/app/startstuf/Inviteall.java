package vritika.app.startstuf;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Inviteall extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    ArrayAdapter<Model> adapter;
    List<String> allnum=new ArrayList<>();
    List<Model> list = new ArrayList<Model>();
    List<String> numtosend=new ArrayList<>();
    String gname="";
    String url,act,pname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inviteall);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        Intent i=getIntent();
        url=i.getStringExtra("url");
        act=i.getStringExtra("act");
        pname=i.getStringExtra("pname");
        gname=i.getStringExtra("gname");
        listView = (ListView) findViewById(R.id.inlist);
        adapter = new MyAdapter(this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        Cursor phones = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
                null, null);
        while (phones.moveToNext()) {
            String name = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            String phoneNumber = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            if (!allnum.contains(phoneNumber)) {
                Model objContact = new Model(name,phoneNumber);
                list.add(objContact);
                allnum.add(phoneNumber);
            }

        }

        if (null != list && list.size() != 0) {
            Collections.sort(list, new Comparator<Model>() {

                @Override
                public int compare(Model lhs, Model rhs) {
                    return lhs.getName().compareTo(rhs.getName());
                }
            });

        } else {
            Toast.makeText(getBaseContext(),"No contact found!!!",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
       // TextView label = (TextView) v.getTag(R.id.tvname);
        CheckBox checkbox = (CheckBox) v.getTag(R.id.icheck);
        if(!checkbox.isChecked())
        {
            checkbox.setChecked(true);
        }
       // Toast.makeText(v.getContext(), label.getText().toString()+" "+position+""+isCheckedOrNot(checkbox), Toast.LENGTH_SHORT).show();

    }

    private String isCheckedOrNot(CheckBox checkbox) {
        if(checkbox.isChecked())
            return "is checked";
        else
            return "is not checked";
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.invite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.inviteall:
                Boolean b=isNetworkAvailable();
                if(b)
                    invite();
                else
                    Toast.makeText(getBaseContext(),"Connect to Internet!!!",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void invite(){
        numtosend.clear();
        int i=listView.getCount();
        for(int j=0;j<i;j++){

            String num=list.get(j).getphone();
            Boolean check=list.get(j).isSelected();
            if(check){
                numtosend.add(num);
            }

        }
         BackgroundTask backgroundTask = new BackgroundTask(this);
         backgroundTask.execute();

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public class BackgroundTask extends AsyncTask<ArrayList,Void,String> {
        Context ctx;
        BackgroundTask(Context ctx)
        {
            this.ctx =ctx;
        }
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(ArrayList... params) {
            final String tokenurl =url;


            try {
                URL url = new URL(tokenurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode(pname, "UTF-8") + "=" + URLEncoder.encode(numtosend.toString(), "UTF-8")+"&"+
                        URLEncoder.encode("gname","UTF-8")+"="+URLEncoder.encode(gname,"UTF-8");
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
            Toast.makeText(getBaseContext(),result,Toast.LENGTH_LONG).show();
            if(result.equals("success")){
                if(act.equals("tabfrag"))
                {
                    Intent i = new Intent(getBaseContext(), TabChat.class);
                    finish();
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(getBaseContext(), Chat_group.class);
                    i.putExtra("name", gname);
                    i.putExtra("number", "group");
                    finish();
                    startActivity(i);
                }
            }

        }
    }
}
