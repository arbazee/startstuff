package vritika.app.startstuf;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity2 extends AbsPermissions {

    private static final int REQUEST_PERMISSION=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestAppPermissions(new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.WRITE_CONTACTS}
                ,R.string.msg, REQUEST_PERMISSION);
        //do anything after permitted
        Toast.makeText(getApplicationContext(),"Permission Granted",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissiongranted(int requestcode) {

        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);


    }
}
