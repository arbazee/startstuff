package vritika.app.startstuf;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;


public class EnquiryDetails extends AppCompatActivity {
    private String encoded_string, image_name,datetime;
    EditText name,mobile,mail,compony,remark;
    Button btnSubmit;
    ImageView imageToUpload;
    private File file;
    private Uri file_uri;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_details);
        registerViews();
        compony = (EditText) findViewById(R.id.etcompony);
        name = (EditText) findViewById(R.id.etName);
        mobile = (EditText) findViewById(R.id.etmobile);
        mail = (EditText) findViewById(R.id.etmail);
        remark = (EditText) findViewById(R.id.etremark);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    private void registerViews() {
        name = (EditText) findViewById(R.id.etName);
        // TextWatcher would let us check validation error on the fly
       name.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(name);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        mobile = (EditText) findViewById(R.id.etmobile);
        mobile.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        mail = (EditText) findViewById(R.id.etmail);
        mail.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }
    private boolean checkValidation(){
        boolean ret = true;
        if (!Validation.hasText(name)) ret = false;
        if (!Validation.hasText(mail)) ret = false;
        if (!Validation.hasText(mobile)) ret = false;
        return ret;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void submit(View view){
        if (checkValidation()){
            Toast.makeText(this, "Submitting form...", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ItemListActivity.class);
            startActivity(intent);
            String str_compony=compony.getText().toString();
            String str_name=name.getText().toString();
            String str_mobile=mobile.getText().toString();
            String str_mail=mail.getText().toString();
            String str_remark=remark.getText().toString();
            String type="Enquiry";
            BackgroundWorker backgroundWorker=new BackgroundWorker(this);
            backgroundWorker.execute(type,str_compony,str_name,str_mobile,str_mail,str_remark);
        }

        else {
            Toast.makeText(EnquiryDetails.this, "please enter valid credentials", Toast.LENGTH_LONG).show();
        }

    }
}
