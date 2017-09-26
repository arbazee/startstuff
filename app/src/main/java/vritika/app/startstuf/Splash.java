package vritika.app.startstuf;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;


public class Splash extends Activity implements Animation.AnimationListener{
    SQLiteDatabase mydatabase;
    int flag=0;
    String name;

    Animation animFadeIn;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        final ImageView iv = (ImageView) findViewById(R.id.imageView);
//        final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
//        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);
//
//        iv.startAnimation(an);
//        an.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                iv.startAnimation(an2);
//
//                Log.d("the title is       ","test string of android");
//                Intent intent = getIntent();
//                try{
//                    String message=intent.getStringExtra("message");
//                    String test=intent.getStringExtra("title");
//                    Log.d("the title is",test);
//                    Log.d("the message is",message);
//                    mydatabase =openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
//                    mydatabase.execSQL("CREATE TABLE IF NOT EXISTS messages(tid VARCHAR,msg VARCHAR,side VARCHAR);");
//                    ContentValues values = new ContentValues();
//                    values.put("tid", test);
//                    values.put("msg", message);
//                    values.put("side","right");
//                    Log.d("test1","test");
//                    mydatabase.insert("messages", null, values);
//                    mydatabase.close();
//                }
//                catch (Exception e)
//                {
//                    Log.d("Exception is=",e.toString());
//                }
//                finish();
//                Intent i = new Intent(getBaseContext(),WelcomeActivity.class);
//                startActivity(i);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//


        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        } else {
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            // Remember that you should never show the action bar if the
            // status bar is hidden, so hide that too if necessary.
        }
        // load the animation
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_fade_in);
        // set animation listener
        animFadeIn.setAnimationListener(this);
        // animation for image
        linearLayout = (LinearLayout) findViewById(R.id.Linear_layout);
        // start the animation
        linearLayout.setVisibility(View.VISIBLE);
        linearLayout.startAnimation(animFadeIn);

    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {


        Intent intent = getIntent();
        try{
            String message=intent.getStringExtra("message");
            String test=intent.getStringExtra("title");
            //Log.d("the title is",test);
            //Log.d("the message is",message);
            mydatabase =openOrCreateDatabase("Chat", Context.MODE_PRIVATE, null);
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS messages(tid VARCHAR,msg VARCHAR,side VARCHAR);");
            ContentValues values = new ContentValues();
            values.put("tid", test);
            values.put("msg", message);
            values.put("side","right");
            //Log.d("test1","test");
            mydatabase.insert("messages", null, values);
            mydatabase.close();
        }
        catch (Exception e)
        {
            Log.d("Exception is=",e.toString());
        }

        intent = new Intent(Splash.this,WelcomeActivity.class);
        ;
        startActivity(intent);


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}

