package vritika.app.startstuf;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
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
import java.util.List;

public class Group extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView listView,listView2;
    SQLiteDatabase mydatabase,mydatabase2;
    private List<ContactBean> list = new ArrayList<ContactBean>();
    private List<ContactBean> list2 = new ArrayList<ContactBean>();
    List<RowItem> rowItems = new ArrayList<RowItem>();
    /*public static final Integer[] img = { R.drawable.startup,
            R.drawable.forum_icon,
            R.drawable.one_chat_icon,
            R.drawable.chat_back,
            R.drawable.app_icon};*/
    int img6=R.drawable.usergroup;
    CustomListViewAdapter objAdapter2;
    Boolean additem=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Forums");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        View view = View.inflate(this, R.layout.listhead, null);
        listView = (ListView)findViewById(R.id.grouplist);

        listView.setOnItemClickListener(this);
        listView.addHeaderView(view);


        mydatabase =openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS usergroups(name VARCHAR,number VARCHAR);");
        Cursor resultSet2 = mydatabase.rawQuery("Select * from usergroups", null);
        String name2, num2;
        resultSet2.moveToFirst();
        int count=resultSet2.getCount();

        while (!resultSet2.isAfterLast()) {
            name2 = resultSet2.getString(0);
            num2 = resultSet2.getString(1);
            RowItem objContact = new RowItem(img6, name2, num2);
            rowItems.add(objContact);
            resultSet2.moveToNext();
        }

        objAdapter2 = new CustomListViewAdapter(
                this, R.layout.groupview, rowItems);
        listView.setAdapter(objAdapter2);
        resultSet2.close();
        mydatabase.close();
        createdb();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub
                RowItem bean = (RowItem) listView.getItemAtPosition(pos);
                final String testname=bean.getTitle();
                Log.v("long clicked","pos: " + pos);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Group.this,R.style.AppCompatAlertDialogStyle);
                alertDialogBuilder.setTitle("Delete");
                alertDialogBuilder.setMessage("Do you want to delete?");
                alertDialogBuilder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                try{
                                    mydatabase = openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
                                    mydatabase.delete("usergroups","name='"+testname+"'",null);
                                    mydatabase.close();
                                    listrefreshnow();
                                    delfromserver(testname);
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getBaseContext(),"Only user created groups can be deleted",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                );
                alertDialogBuilder.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                );
                alertDialogBuilder.show();
                return true;
            }
        });

        ImageButton g1=(ImageButton)view.findViewById(R.id.img1);
        ImageButton g2=(ImageButton)view.findViewById(R.id.img2);
        ImageButton g3=(ImageButton)view.findViewById(R.id.img3);
        ImageButton g4=(ImageButton)view.findViewById(R.id.img4);
        ImageButton g5=(ImageButton)view.findViewById(R.id.img5);
        ImageButton g6=(ImageButton)view.findViewById(R.id.img6);
        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(),Forum_group1.class);
                i.putExtra("name","Startup");
                i.putExtra("number","group");
                startActivity(i);
            }
        });

        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(),Forum_group1.class);
                i.putExtra("name","Enterpreneurs");
                i.putExtra("number","group");
                startActivity(i);
            }
        });

        g3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(),Forum_group1.class);
                i.putExtra("name","Lawyers");
                i.putExtra("number","group");
                startActivity(i);
            }
        });

        g4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(),Forum_group1.class);
                i.putExtra("name","Teachers");
                i.putExtra("number","group");
                startActivity(i);
            }
        });

        g5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(),Forum_group1.class);
                i.putExtra("name","Human Resources");
                i.putExtra("number","group");
                startActivity(i);
            }
        });
        g6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(),Forum_group1.class);
                i.putExtra("name","Chartered Accountant");
                i.putExtra("number","group");
                startActivity(i);
            }
        });


    }

    public void listrefreshnow(){
        listView.setOnItemClickListener(this);
        CustomListViewAdapter objAdapter = new CustomListViewAdapter(
                this, R.layout.groupview, rowItems);
        rowItems.clear();
        objAdapter.notifyDataSetChanged();


        //createdb();
        mydatabase =openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS usergroups(name VARCHAR,number VARCHAR);");
        Cursor resultSet2 = mydatabase.rawQuery("Select * from usergroups", null);
        String name2, num2;
        resultSet2.moveToFirst();
        int count=resultSet2.getCount();

        while (!resultSet2.isAfterLast()) {
            name2 = resultSet2.getString(0);
            num2 = resultSet2.getString(1);
            RowItem objContact = new RowItem(img6, name2, num2);
            rowItems.add(objContact);
            resultSet2.moveToNext();
        }

        objAdapter2 = new CustomListViewAdapter(
                this, R.layout.groupview, rowItems);
        listView.setAdapter(objAdapter2);
        resultSet2.close();
        mydatabase.close();
    }

    public void delfromserver(String gname){
        final SharedPreferences sharedpreferences = getSharedPreferences("unumber", Context.MODE_PRIVATE);
        String uid= sharedpreferences.getString("uid","Not");
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(gname,uid);
    }

    @Override
    public void onItemClick(AdapterView<?> listview, View v, int position,
                            long id) {


        // Toast.makeText(getBaseContext(),"header Clicked",Toast.LENGTH_LONG).show();

        RowItem bean = (RowItem) listview.getItemAtPosition(position);
        if(!bean.getTitle().equals(""))
            showCallDialog(bean.getTitle(), bean.getDesc());

    }

    private void showCallDialog(final String name, final String phoneNo)
    {
        Intent i=new Intent(getBaseContext(),Chat_group.class);
        i.putExtra("name",name);
        i.putExtra("number",phoneNo);
        startActivity(i);
    }

    public void createdb(){
        SharedPreferences sharedpref=getSharedPreferences("group", Context.MODE_PRIVATE);
        String data=sharedpref.getString("check","");
        if(!data.equals("set")) {
            mydatabase = openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS groups(name VARCHAR,number VARCHAR);");
            ContentValues values = new ContentValues();
            values.put("name", "Startup");
            values.put("number", "group");
            mydatabase.insert("groups", null, values);
            values.put("name","Enterpreneurs");
            values.put("number","group");
            mydatabase.insert("groups", null, values);
            values.put("name","Lawyers");
            values.put("number","group");
            mydatabase.insert("groups", null, values);
            values.put("name","Chartered Accountant");
            values.put("number","group");
            mydatabase.insert("groups", null, values);
            values.put("name","Human Resource Management");
            values.put("number","group");
            mydatabase.insert("groups", null, values);
            values.put("name","Teachers");
            values.put("number","group");
            mydatabase.insert("groups", null, values);
            mydatabase.close();
            SharedPreferences.Editor editor2=sharedpref.edit();
            editor2.putString("check","set");
            editor2.apply();


        }

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.creategroup:
                finish();
                Boolean test = isNetworkAvailable();
                if (test == true) {
                    Intent i1 = new Intent(Group.this, GroupCreate.class);
                    startActivity(i1);
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Connect To Internet", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

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
            final String tokenurl = "http://startstuffapp.in/startstuff/delmember.php";
            String gname = params[0];
            String uid=params[1];


            try {
                URL url = new URL(tokenurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("number", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8")+"&"+
                        URLEncoder.encode("gname", "UTF-8") + "=" + URLEncoder.encode(gname, "UTF-8");
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

        }
    }
}
