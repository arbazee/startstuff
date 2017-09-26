package vritika.app.startstuf;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class TabFragment1 extends Fragment implements AdapterView.OnItemClickListener {
    SQLiteDatabase mydatabase;
    ArrayAdapter<String> adapter ;
    private ListView listView;
   SearchView searchView;
    SwipeRefreshLayout swipeContainer;
    private List<ContactBean> list = new ArrayList<ContactBean>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_fragment1, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        listView = (ListView) getActivity().findViewById(R.id.list1);
        listView.setOnItemClickListener(this);
        mydatabase = getContext().openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS friends(name VARCHAR,number VARCHAR);");
        Cursor resultSet = mydatabase.rawQuery("Select * from friends", null);
        String name, num;
        resultSet.moveToFirst();
        while (!resultSet.isAfterLast()) {
            name = resultSet.getString(0);
            num = resultSet.getString(1);
            ContactBean objContact = new ContactBean();
            objContact.setName(name);
            objContact.setPhoneNo(num);
            list.add(objContact);
            resultSet.moveToNext();
        }
        ContanctAdapter objAdapter = new ContanctAdapter(
                getActivity(), R.layout.alluser_row2, list, false);
        listView.setAdapter(objAdapter);
        resultSet.close();
        mydatabase.close();


        if (null != list && list.size() != 0) {
            Collections.sort(list, new Comparator<ContactBean>() {

                @Override
                public int compare(ContactBean lhs, ContactBean rhs) {
                    return lhs.getName().compareTo(rhs.getName());
                }
            });

        } else {
            showToast("No Friends Found");
        }

        swipeContainer = (SwipeRefreshLayout) getActivity().findViewById(R.id.swiperefresh);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listrefresh();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub
                ContactBean bean = (ContactBean) listView.getItemAtPosition(pos);
                String testname = bean.getName();
                final String testnum = bean.getPhoneNo();
                Log.v("long clicked", "pos: " + pos);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
                alertDialogBuilder.setTitle("Delete");
                alertDialogBuilder.setMessage("Do you want to delete?");
                alertDialogBuilder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Log.d("testnumber=", testnum);
                                mydatabase = getContext().openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
                                mydatabase.delete("friends", "number" + "=" + testnum, null);
                                mydatabase.delete("messages", "tid" + "=" + testnum, null);
                                mydatabase.close();
                                listrefresh();


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
    }

   /* public void show(String match) {
        if (!match.equals("")) {
            String name;
            listView = (ListView) getActivity().findViewById(R.id.list1);
            listView.setOnItemClickListener(this);
            mydatabase = getContext().openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS friends(name VARCHAR,number VARCHAR);");
            Cursor resultSet = mydatabase.rawQuery("Select * from friends", null);
            name = resultSet.getString(0);
            String tmpname = name.toLowerCase();
            if (tmpname.contains(match.toLowerCase())) {
                if (null != list && list.size() != 0) {
                    Collections.sort(list, new Comparator<ContactBean>() {

                        @Override
                        public int compare(ContactBean lhs, ContactBean rhs) {
                            return lhs.getName().compareTo(rhs.getName());
                        }
                    });

                } else {
                    showToast("No Friends Found");
                }
            }
        }
    }
*/


    public void listrefresh(){
        listView = (ListView) getActivity().findViewById(R.id.list1);
        listView.setOnItemClickListener(this);
        ContanctAdapter objAdapter = new ContanctAdapter(
                getActivity(), R.layout.alluser_row2, list,false);
        list.clear();
        objAdapter.notifyDataSetChanged();
        mydatabase = getContext().openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS friends(name VARCHAR,number VARCHAR);");
        Cursor resultSet = mydatabase.rawQuery("Select * from friends", null);
        String name, num;
        resultSet.moveToFirst();
        while (!resultSet.isAfterLast()) {
            name = resultSet.getString(0);
            num = resultSet.getString(1);
            ContactBean objContact = new ContactBean();
            objContact.setName(name);
            objContact.setPhoneNo(num);
            list.add(objContact);
            resultSet.moveToNext();
        }

        listView.setAdapter(objAdapter);

        if (null != list && list.size() != 0) {
            Collections.sort(list, new Comparator<ContactBean>() {

                @Override
                public int compare(ContactBean lhs, ContactBean rhs) {
                    return lhs.getName().compareTo(rhs.getName());
                }
            });

        } else {
            showToast("No Friends Found");
        }
        swipeContainer.setRefreshing(false);
        resultSet.close();
        mydatabase.close();
    }

    private void showToast(String msg) {

        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> listview, View v, int position,
                            long id) {
        ContactBean bean = (ContactBean) listview.getItemAtPosition(position);
        showCallDialog(bean.getName(), bean.getPhoneNo());
    }

    private void showCallDialog(final String name, final String phoneNo)
    {
        Intent i=new Intent(getContext(),Chat_room.class);
        i.putExtra("name",name);
        i.putExtra("number",phoneNo);
        startActivity(i);
    }


    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.main_menu, menu);
    /*    MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        if (searchMenuItem == null) {
            return;
        }

        searchView = (SearchView) searchMenuItem.getActionView();
        if (searchView != null) {
           // searchView.setQueryHint(getString(R.string.));
            searchView.setMaxWidth(2129960); // http://stackoverflow.com/questions/18063103/searchview-in-optionsmenu-not-full-width
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    TabFragment1.this.onQueryTextChange(s);
                    return false;
                }
            });
            }*/

    }
    void onQueryTextChange(String query) {
       // TabFragment1.getFilter().filter(query);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {


            case R.id.sharemenu:
                share();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void share()
    {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=vritika.app.startstuf");
        startActivity(Intent.createChooser(share, "share link"));
    }
}

