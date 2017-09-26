package vritika.app.startstuf;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.SparseIntArray;
import android.view.View;

public abstract class AbsPermissions extends Activity {

    private SparseIntArray mErrorString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mErrorString=new SparseIntArray();
    }

    public abstract void onPermissiongranted(int requestcode);
    public void requestAppPermissions(final String[]requestedPermissions,final int stringId,final int requestCode){
        mErrorString.put(requestCode,stringId);


        int permissionCheck= PackageManager.PERMISSION_GRANTED;
        boolean showRequestPrmissions=false;
        for(String permission:requestedPermissions){
             permissionCheck=permissionCheck+ ContextCompat.checkSelfPermission(this,permission);
            showRequestPrmissions=showRequestPrmissions || ActivityCompat.shouldShowRequestPermissionRationale(this,permission);
            if(permissionCheck!=PackageManager.PERMISSION_GRANTED) {
                if (showRequestPrmissions) {
                    Snackbar.make(findViewById(android.R.id.content), stringId, Snackbar.LENGTH_INDEFINITE).setAction("GRANT", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityCompat.requestPermissions(AbsPermissions.this, requestedPermissions, requestCode);


                        }
                    }).show();
                } else {
                    ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
                }
            }
                else
                {
                    onPermissiongranted(requestCode);
                }
            }
        }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck=PackageManager.PERMISSION_GRANTED;
        for(int permission:grantResults){
            permissionCheck=permissionCheck+permission;
        }

            onPermissiongranted(requestCode);
    }
}
