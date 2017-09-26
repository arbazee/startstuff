package vritika.app.startstuf;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    String name;
    int flag=0;
    String msg,gname;
    String test;
    SQLiteDatabase mydatabase, mydatabase2,mydb1,mydb2;
    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    private NotificationUtils notificationUtils;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());
        /*if(remoteMessage.getData().size()>0){
            String test=remoteMessage.getData().get("tag");
            if(test!=null && test.equals("group"))
                msg=remoteMessage.getData().get("message");
                handledata(msg);
        }*/
        if (remoteMessage==null)
            return;



        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());
            return;
        }

        if(remoteMessage.getData().size()>0)
        {
            String check=remoteMessage.getData().get("tag");

            if(check.equals("group")) {
                String gname=remoteMessage.getData().get("gname");
                String msg=remoteMessage.getData().get("message");
                String sender=remoteMessage.getData().get("title");
                String flag=remoteMessage.getData().get("flag");
                handledata(gname,msg,sender,flag);

            }
            else if(check.equals("single"))
            {
                String sender=remoteMessage.getData().get("title");
                String msg=remoteMessage.getData().get("message");
                handleNotification(msg,sender);
            }

            else
                handleNotification(remoteMessage.getNotification().getBody(),remoteMessage.getNotification().getTitle());
            return;
        }
    }

    public void handledata(String gname,String msg,String sender,String flag){
        Log.e(TAG,"the sender is="+sender);
        if (!NotificationUtils.isAppIsInBackground(getApplicationContext()))
        {
            if(gname.equals(Chat_group.tname))
            {
                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                pushNotification.putExtra("message", msg);
                pushNotification.putExtra("sender",sender);
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
                // play notification sound
                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();
                return;
            }
            else if(gname.equals(FeedsNotification.tname))
            {
                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                pushNotification.putExtra("message", msg);
                pushNotification.putExtra("sender",sender);
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
                // play notification sound
               NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();
                return;
            }
            else
            {
                mydb1 =openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
                mydb1.execSQL("CREATE TABLE IF NOT EXISTS groupmessages(gname VARCHAR,fromnum VARCHAR,msg VARCHAR,side VARCHAR);");
                ContentValues values = new ContentValues();
                values.put("gname", gname);
                values.put("fromnum", sender);
                values.put("msg", msg);
                values.put("side","right");
                Log.d("test1","test");
                mydb1.insert("groupmessages", null, values);
                mydb1.close();
                if(flag.equals("0")) {
                    mydb2 =openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
                    mydb2.execSQL("CREATE TABLE IF NOT EXISTS usergroups(name VARCHAR,number VARCHAR);");
                    String query2="select * from usergroups where name='"+gname+"'";
                    Cursor resultSet2= mydb2.rawQuery(query2,null);
                    resultSet2.moveToFirst();
                    int i=resultSet2.getCount();
                    if(i==0){
                        ContentValues values2 = new ContentValues();
                        values2.put("name",gname);
                        values2.put("number", "group");
                        mydb2.insert("usergroups", null, values2);
                    }
                    mydb2.close();
                }

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
                mBuilder.setSmallIcon(R.drawable.app_icon);
                mBuilder.setContentTitle(gname);
                mBuilder.setContentText(msg);
                mBuilder.setAutoCancel(true);
                Intent notificationIntent = new Intent(this, Chat_group.class);
                notificationIntent.putExtra("name",gname);
                notificationIntent.putExtra("number","group");
                PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(contentIntent);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(0, mBuilder.build());
                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();
                return;
            }
        }
        else
        {
            mydb1 =openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
            mydb1.execSQL("CREATE TABLE IF NOT EXISTS groupmessages(gname VARCHAR,fromnum VARCHAR,msg VARCHAR,side VARCHAR);");
            ContentValues values = new ContentValues();
            values.put("gname", gname);
            values.put("fromnum", sender);
            values.put("msg", msg);
            values.put("side","right");
            Log.d("test1","test");
            mydb1.insert("groupmessages", null, values);
            mydb1.close();
            if(flag.equals("0")) {
                mydb2 =openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
                mydb2.execSQL("CREATE TABLE IF NOT EXISTS usergroups(name VARCHAR,number VARCHAR);");
                String query2="select * from usergroups where name='"+gname+"'";
                Cursor resultSet2= mydb2.rawQuery(query2,null);
                resultSet2.moveToFirst();
                int i=resultSet2.getCount();
                if(i==0){
                    ContentValues values2 = new ContentValues();
                    values2.put("name",gname);
                    values2.put("number", "group");
                    mydb2.insert("usergroups", null, values2);
                }
                mydb2.close();
            }
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setSmallIcon(R.drawable.app_icon);
            mBuilder.setContentTitle(gname);
            mBuilder.setContentText(msg);
            mBuilder.setAutoCancel(true);
            Intent notificationIntent = new Intent(this, Chat_group.class);
            notificationIntent.putExtra("name",gname);
            notificationIntent.putExtra("number","group");
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(contentIntent);
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(0, mBuilder.build());
            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();
        }
    }




    private void handleNotification(String message,String number)
    {
        Log.d("The title","Title="+number);
        Log.d("Chatid","id="+ Chat_room.tid);
        if (!NotificationUtils.isAppIsInBackground(getApplicationContext()))
        {
            if(number.equals(Chat_room.tid))
            {
                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                pushNotification.putExtra("message", message);
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
                // play notification sound
                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();
            }
            else {



                Cursor phones =getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
                        null, null);
                while (phones.moveToNext()) {
                    String name2 = phones
                            .getString(phones
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phoneNumber = phones
                            .getString(phones
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    phoneNumber=phoneNumber.replace("+91","");

                    if(phoneNumber.equals(number))
                    {
                        name=name2;
                        flag=1;
                        break;
                    }

                }
                phones.close();


                mydatabase =openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS messages(tid VARCHAR,msg VARCHAR,side VARCHAR);");
                ContentValues values = new ContentValues();
                values.put("tid", number);
                values.put("msg", message);
                values.put("side","right");
                Log.d("test1","test");
                mydatabase.insert("messages", null, values);
                mydatabase.close();

                mydatabase2 =openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
                mydatabase2.execSQL("CREATE TABLE IF NOT EXISTS friends(name VARCHAR,number VARCHAR);");
                String query2="select * from friends where number='"+number+"'";
                Cursor resultSet2= mydatabase2.rawQuery(query2,null);
                resultSet2.moveToFirst();
                int i=resultSet2.getCount();
                if(i==0){
                    ContentValues values2 = new ContentValues();
                    if(flag==1)
                        values2.put("name",name);
                    else
                        values2.put("name",number);
                    values2.put("number", number);
                    mydatabase2.insert("friends", null, values2);
                    Log.d("test","test");
                    Log.d("number",number);
                }
                mydatabase2.close();
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
                mBuilder.setSmallIcon(R.drawable.app_icon);
                if(flag==1)
                    mBuilder.setContentTitle(name);
                else
                    mBuilder.setContentTitle(number);
                test=name;
                mBuilder.setContentText(message);
                mBuilder.setAutoCancel(true);
                if(name.length()==0)
                    test=number;
                Intent notificationIntent = new Intent(this, Chat_room.class);
                notificationIntent.putExtra("name",test);
                notificationIntent.putExtra("number",number);
                PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(contentIntent);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(0, mBuilder.build());
                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();
            }
        }
        else
        {
            // If the app is in background, firebase itself handles the notification
            Cursor phones =getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
                    null, null);
            while (phones.moveToNext()) {
                String name2 = phones
                        .getString(phones
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones
                        .getString(phones
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phoneNumber=phoneNumber.replace("+91","");

                if(phoneNumber.equals(number))
                {
                    name=name2;
                    flag=1;
                    break;
                }

            }
            phones.close();


            mydatabase =openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS messages(tid VARCHAR,msg VARCHAR,side VARCHAR);");
            ContentValues values = new ContentValues();
            values.put("tid", number);
            values.put("msg", message);
            values.put("side","right");
            Log.d("test1","test");
            mydatabase.insert("messages", null, values);
            mydatabase.close();

            mydatabase2 =openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
            mydatabase2.execSQL("CREATE TABLE IF NOT EXISTS friends(name VARCHAR,number VARCHAR);");
            String query2="select * from friends where number='"+number+"'";
            Cursor resultSet2= mydatabase2.rawQuery(query2,null);
            resultSet2.moveToFirst();
            int i=resultSet2.getCount();
            if(i==0){
                ContentValues values2 = new ContentValues();
                if(flag==1)
                    values2.put("name",name);
                else
                    values2.put("name",number);
                values2.put("number", number);
                mydatabase2.insert("friends", null, values2);
                Log.d("test","test");
                Log.d("number",number);
            }
            mydatabase2.close();
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setSmallIcon(R.drawable.app_icon);
            if(flag==1)
                mBuilder.setContentTitle(name);
            else
                mBuilder.setContentTitle(number);
            test=name;
            mBuilder.setContentText(message);
            mBuilder.setAutoCancel(true);
            if(name.length()==0)
                test=number;
            Intent notificationIntent = new Intent(this, Chat_room.class);
            notificationIntent.putExtra("name",test);
            notificationIntent.putExtra("number",number);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(contentIntent);
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(0, mBuilder.build());
            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();
        }
    }

    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }
}