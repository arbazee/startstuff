package vritika.app.startstuf;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

public class Groupinvite extends AppCompatActivity implements
        AdapterView.OnItemClickListener  {
    String query="";
    Boolean net=true;
    Boolean check=false,canAddItem=false,remove=false,cancel=false;
    private static int OBJ = 0;
    private static int OBJ2 = 0;
    Boolean checktest=true;
    ListView listView2;
    SQLiteDatabase mydatabase;
    CheckBox c;
    Cursor res;
    String[] sendnum;
    String gname;
    List<ContactBean> list = new ArrayList<ContactBean>();
    List<String> invitnum = new ArrayList<String>();
    List<sendbean> send=new ArrayList<sendbean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupinvite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i=getIntent();
        gname=i.getStringExtra("gname");
        show("",true);
    }
    public void show(String match,Boolean check)
    {

        listView2 = (ListView) findViewById(R.id.list);
        listView2.setOnItemClickListener(this);
        ContanctAdapter objAdapter = new ContanctAdapter(
                this, R.layout.alluser_row, list,check);
        list.clear();
        objAdapter.notifyDataSetChanged();

        Cursor phones =getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
                null, null);
        while (phones.moveToNext()) {
            String name = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            String phoneNumber = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            if(!match.equals("")) {
                String tmpname=name.toLowerCase();
                if(tmpname.contains(match.toLowerCase())) {
                    ContactBean objContact = new ContactBean();
                    objContact.setName(name);
                    objContact.setPhoneNo(phoneNumber);
                    list.add(objContact);
                }
            }
            else
            {
                ContactBean objContact = new ContactBean();
                objContact.setName(name);
                objContact.setPhoneNo(phoneNumber);
                list.add(objContact);
            }

        }
        phones.close();
    objAdapter = new ContanctAdapter(
                this, R.layout.alluser_row, list,check);
        listView2.setAdapter(objAdapter);


        if (null != list && list.size() != 0) {
            Collections.sort(list, new Comparator<ContactBean>() {

                @Override
                public int compare(ContactBean lhs, ContactBean rhs) {
                    return lhs.getName().compareTo(rhs.getName());
                }
            });

        } else {
            disp("No Contact Found!!!");
        }
    }

    public void disp(String msg)
    {
        Toast.makeText(getBaseContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> listview, View v, int position,
                            long id) {
        if(checktest)
        {
            CheckBox c=(CheckBox)v.findViewById(R.id.icheck);
            if(c.isChecked())
                disp("marked");
            else
                c.setChecked(true);
        }
        else {
            ContactBean bean = (ContactBean) listview.getItemAtPosition(position);
            showCallDialog(bean.getName(), bean.getPhoneNo());
        }
    }

    private void showCallDialog(final String name, final String phoneNo) {
        String num;
        num=phoneNo.replace(" ","");
        num=num.replace("+91","");
        Intent i=new Intent(getBaseContext(),Chat_room.class);
        i.putExtra("name",name);
        i.putExtra("number",num);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_invite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.invitemember:
                Boolean b=isNetworkAvailable();
                if(b==true)
                invite();
                else
                Toast.makeText(getBaseContext(),"Connect to Internet!!!",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void invite(){
        int i=listView2.getCount();
        try {
            for (int j = 0; j < i; j++) {
                View v = listView2.getChildAt(j);
                c = (CheckBox) v.findViewById(R.id.icheck);
                if (c.isChecked()) {
                    ContactBean bean = (ContactBean) listView2.getItemAtPosition(j);
                    String num = bean.getPhoneNo();
                    Log.d("testiffound", num);
                    invitnum.add(num);
                }

            }
        }catch (Exception e){
            Log.d("Exp",""+e);
        }


        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute();
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
            final String tokenurl = "http://startstuffapp.in/startstuff/groupinvite.php";


            try {
                URL url = new URL(tokenurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("gmember", "UTF-8") + "=" + URLEncoder.encode(invitnum.toString(), "UTF-8")+"&"+
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
                Toast.makeText(getBaseContext(),"error here",Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getBaseContext(),"error next",Toast.LENGTH_LONG).show();
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
                Intent i=new Intent(getBaseContext(),Chat_group.class);
                i.putExtra("name",gname);
                i.putExtra("number","group");
                finish();
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
