package vritika.app.startstuf;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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


public class TabFragment2 extends Fragment implements
        AdapterView.OnItemClickListener {
    String query="";
    Boolean net=true;
    Boolean check,canAddItem=false,remove=false,cancel=false;
    private static int OBJ = 0;
    private static int OBJ2 = 0;
    Boolean checktest=false;
    ListView listView2;
    Cursor res;
    String[] sendnum;
    List<ContactBean> list = new ArrayList<ContactBean>();
    List<String> invitnum = new ArrayList<String>();
    List<String> allnum=new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View v= inflater.inflate(R.layout.fragment_tab_fragment2, container, false);
        setHasOptionsMenu(true);

        return v;
    }





    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        allnum.clear();
        show("",false);
    }

    public void show(String match,Boolean check)
    {
        allnum.clear();

        listView2 = (ListView)getActivity(). findViewById(R.id.list);
        listView2.setOnItemClickListener(this);
        ContanctAdapter objAdapter = new ContanctAdapter(
                getActivity(), R.layout.alluser_row, list,check);
        list.clear();
        objAdapter.notifyDataSetChanged();

        Cursor phones = getContext().getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
                null, null);
        while (phones.moveToNext()) {
            String name = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            String phoneNumber = phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            phoneNumber=phoneNumber.replace("+91","");
            if(!match.equals("")) {
                String tmpname=name.toLowerCase();
                if(tmpname.contains(match.toLowerCase())) {
                    if(!allnum.contains(phoneNumber)) {
                        ContactBean objContact = new ContactBean();
                        objContact.setName(name);
                        objContact.setPhoneNo(phoneNumber);
                        list.add(objContact);
                        allnum.add(phoneNumber);
                    }
                }
            }
            else
            {
                if(!allnum.contains(phoneNumber)) {
                    ContactBean objContact = new ContactBean();
                    objContact.setName(name);
                    objContact.setPhoneNo(phoneNumber);
                    list.add(objContact);
                    allnum.add(phoneNumber);
                }
            }

        }
        phones.close();


        objAdapter = new ContanctAdapter(
                getActivity(), R.layout.alluser_row, list,check);
        listView2.setAdapter(objAdapter);


        if (null != list && list.size() != 0) {
            Collections.sort(list, new Comparator<ContactBean>() {

                @Override
                public int compare(ContactBean lhs, ContactBean rhs) {
                    return lhs.getName().compareTo(rhs.getName());
                }
            });

        } else {
            showToast("No Contact Found!!!");
        }
    }

    private void showToast(String msg) {

        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
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
            Intent i=new Intent(getContext(),Chat_room.class);
            i.putExtra("name",name);
            i.putExtra("number",num);
            startActivity(i);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }





    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchViewItem.getActionView();
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                show(newText,false);
                return true;
            }

            public boolean onQueryTextSubmit(String query)
            {
                // **Here you can get the value "query" which is entered in the search box.**

                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);

    }



    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        OBJ=menu.FIRST;
        OBJ2=OBJ+1;
        if(canAddItem){

            MenuItem mi = menu.add(0, OBJ, Menu.NONE, "new item");
            mi.setIcon(R.drawable.cancel);
            mi.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
            MenuItem mi2=menu.add(1,OBJ2, Menu.NONE, "send");
            mi2.setIcon(R.drawable.sendicon);
            mi2.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
            canAddItem = false;
        }

        if(remove){
            MenuItem btn=menu.getItem(2);
            btn.setVisible(false);
            remove=false;
            MenuItem si=menu.findItem(R.id.action_search);
            si.setVisible(true);
            show("",false);

        }

        if(cancel){
            MenuItem btn2=menu.getItem(1);
            btn2.setVisible(false);
            cancel=false;
            MenuItem si=menu.findItem(R.id.action_search);
            si.setVisible(true);
            show("",false);
        }

    }


    public void disp(String msg)
    {
        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_search:

                return true;
            case R.id.sharemenu:
                share();
                return true;
                //checktest=true;
                //check=true;
                //updatelist();
                //canAddItem=true;
                //getActivity().invalidateOptionsMenu();

            case 1:show("",false);
                checktest=false;
                    item.setVisible(false);
                    remove=true;
                    getActivity().invalidateOptionsMenu();
                return true;

            case 2:item.setVisible(false);
                    cancel=true;
                    checktest=false;
                    sendm();
                    show("",false);
                    getActivity().invalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void sendm(){

        Intent i=new Intent(getContext(),Inviteall.class);
        i.putExtra("url","http://startstuffapp.in/startstuff/testjson.php");
        i.putExtra("act","tabfrag");
        i.putExtra("gname","");
        i.putExtra("pname","json");
        startActivity(i);
       // BackgroundTask backgroundTask = new BackgroundTask(getActivity());
       // backgroundTask.execute();
    }



    public void updatelist()
    {
        show("",true);
    }



    public void share()
    {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=vritika.app.startstuf");
        startActivity(Intent.createChooser(share, "share link"));
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
            final String tokenurl = "http://pegham.co.in/Beliza_chat/testjson.php";


            try {
                URL url = new URL(tokenurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("json", "UTF-8") + "=" + URLEncoder.encode(invitnum.toString(), "UTF-8");
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
            invitnum.clear();
            Toast.makeText(getContext(),result,Toast.LENGTH_LONG).show();

        }
    }


}
