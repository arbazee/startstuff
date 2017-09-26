package vritika.app.startstuf;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;


public class Posts extends AppCompatActivity {
    SQLiteDatabase mydatabase,mydatabase2;
    private ChatArrayAdapter2 chatArrayAdapter;
    private ListView listView;
    private EmojiconEditText chatText;
    TextView trname;
    private ImageView buttonSend;
    private boolean side = false;
    public static String uid,tid,data,tname;
    String method,mess,pos;
    byte[] data2;
    String sendname2;
    String json = null;
    private static final String TAG = MainActivity.class.getSimpleName();
    View rootView;

    private static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddmmss");

    private static final String COLUMN_TEXT = "text";
    private static final String COLUMN_SENDER = "sender";
    Handler h;
    List<String> send = new ArrayList<String>();
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post);

        rootView = findViewById(R.id.root);
        buttonSend = (ImageView) findViewById(R.id.send);
        chatText = (EmojiconEditText) findViewById(R.id.emojicon_edit_text);

        final SharedPreferences sharedpreferences = getSharedPreferences("unumber", Context.MODE_PRIVATE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
       /* if (toolbar != null)
        {

            Intent intent = getIntent();
            tname = intent.getStringExtra("name");
            tid = intent.getStringExtra("number");

            trname=(TextView) findViewById(R.id.tname);
            trname.setText(tname);
            //   setSupportActionBar(toolbar);
            trname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getBaseContext(),Grouplist.class);
                    i.putExtra("gname",tname);
                    startActivity(i);
                }
            });
        }*/


        uid= sharedpreferences.getString("uid","Not");
        sendname2=sharedpreferences.getString("uname","~");
        buttonSend = (ImageView) findViewById(R.id.send);
        listView = (ListView) findViewById(R.id.msgview);
        chatArrayAdapter = new ChatArrayAdapter2(getApplicationContext(), R.layout.forumnotifyleft);
        listView.setAdapter(chatArrayAdapter);
        chatText = (EmojiconEditText) findViewById(R.id.emojicon_edit_text);
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setAdapter(chatArrayAdapter);
        //to scroll the list view to bottom on data change
        chatArrayAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatArrayAdapter.getCount() - 1);
            }
        });




        mydatabase =openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS groupmessages (gname VARCHAR,fromnum VARCHAR,msg VARCHAR,side VARCHAR);");
        String query3="select * from groupmessages where gname='"+tname+"'";
        Cursor resultSet3= mydatabase.rawQuery(query3,null);
        resultSet3.moveToFirst();
        int i=resultSet3.getCount();
        String from;
        while (!resultSet3.isAfterLast()) {
            from = resultSet3.getString(1);
            data = resultSet3.getString(2);
            pos=resultSet3.getString(3);
            sendChatMessage(data,from,pos);
            Log.d("side1",pos);
            resultSet3.moveToNext();
        }
        resultSet3.close();
        mydatabase.close();


        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                // new push notification is received

                String message = intent.getStringExtra("message");
                String title=intent.getStringExtra("sender");
                sendChatMessage(message,title,"right");

                mydatabase =openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS groupmessages(gname VARCHAR,fromnum VARCHAR,msg VARCHAR,side VARCHAR);");

                ContentValues values = new ContentValues();
                values.put("gname", tname);
                values.put("msg",message);
                values.put("fromnum", title);
                values.put("side","right");
                mydatabase.insert("groupmessages", null, values);
                mydatabase.close();
            }
        };
    }

    public static String value()
    {
        return tid;
    }


    public void sendm(View v) {
        Boolean test2 = isNetworkAvailable();
        String n = chatText.getText().toString();
        if (n.equals("")) {
            Toast.makeText(getBaseContext(), "Text written required!!!", Toast.LENGTH_LONG).show();
        } else {
            if (test2 == true) {
                mydatabase = openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS groupmessages(gname VARCHAR,fromnum VARCHAR,msg VARCHAR,side VARCHAR);");
                EmojiconEditText message = (EmojiconEditText) findViewById(R.id.emojicon_edit_text);
                mess = message.getText().toString();
                ContentValues values = new ContentValues();
                values.put("gname", tname);
                values.put("fromnum", sendname2);
                values.put("msg", mess);
                values.put("side", "left");
                mydatabase.insert("groupmessages", null, values);
                mydatabase.close();
                BackgroundTask backgroundTask = new BackgroundTask(this);
                backgroundTask.execute(uid, mess);
                sendChatMessage(mess, sendname2, "left");
            } else
                Toast.makeText(getBaseContext(), "Connect to Internet!!!", Toast.LENGTH_LONG).show();
        }
    }



    private boolean sendChatMessage(String msg,String from,String side) {

        Log.d("msg",msg);
        Log.d("from",from);
        Log.d("side2",side);

        chatArrayAdapter.add(new ChatMessage2(msg,from,side));
        chatText.setText("");
        SimpleDateFormat simpleDateFormat;
        String time;
        Calendar calander;
        calander = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
        time = simpleDateFormat.format(calander.getTime());
        return true;
    }

    public Boolean insertdata(SQLiteDatabase db,Cursor res)
    {
        return true;
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
            final String tokenurl = "http://startstuffapp.in/startstuff/group.php";
            String uid = params[0];
            String msg=params[1];

            try {
                URL url = new URL(tokenurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("sender", "UTF-8") + "=" + URLEncoder.encode(uid, "UTF-8")+"&"+
                        URLEncoder.encode("gname", "UTF-8") + "=" + URLEncoder.encode(tname, "UTF-8")+"&"+
                        URLEncoder.encode("message","UTF-8")+"="+URLEncoder.encode(msg,"UTF-8");
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
            send.clear();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        tname = intent.getStringExtra("name");
        tid = intent.getStringExtra("number");
        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);

        tname = "";
        super.onPause();
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
        getMenuInflater().inflate(R.menu.forum_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.search:
                search();
                return true;
            case R.id.deletechat:
                xcxlistrefresh();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void search() {
        Uri uri = Uri.parse("https://www.google.co.in/?gfe_rd=cr&ei=lY7CWPj3BKnA8ge63oGgCQ");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void xcxlistrefresh(){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Delete");
        alertDialogBuilder.setMessage("Do want to delete chat history?");
        alertDialogBuilder.setPositiveButton("OK", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mydatabase = openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
                        mydatabase.delete("groupmessages","gname"+"="+"'"+tname+"'",null);
                        mydatabase.close();
                        Intent i=new Intent(getBaseContext(),TabChat.class);
                        finish();
                        startActivity(i);
                        Toast.makeText(getBaseContext(),"Chat history deleted!!!",Toast.LENGTH_LONG).show();
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialogBuilder.show();
    }
}
