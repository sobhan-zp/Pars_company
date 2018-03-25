package com.pars.company;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pars.company.Admin_fragment.Items_Viewpager_market;
import com.pars.company.Admin_fragment.NonSwipeableViewPager;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    //Button gallery
    LinearLayout home;
    public static TextView tv_home;
    ImageView img_home;

    //Button setting
    LinearLayout setting;
    public static TextView tv_receive;
    public static ImageView img_receive;

    //Button sendFile
    LinearLayout sendFile;
    public static TextView tv_sendFile;
    public static ImageView img_sendFile;


    //Button music
    LinearLayout history;
    public static TextView tv_history;
    ImageView img_history;


    //Button gallery
    LinearLayout user;
    public static TextView tv_user;
    public static ImageView img_user;


    Context conInst;
    public static NonSwipeableViewPager pager;

    // PopUp menu
    Button btnMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(MainActivity.this, btnMenu);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.about_app:
                                Toast.makeText(getApplicationContext(), "about_app", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.about_location:
                                Toast.makeText(getApplicationContext(), "about_location", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.Exit:
                                finish();
                        }
                        return false;
                    }


                });


                popup.show();//showing popup menu

            }
        });


        tv_receive = (TextView) findViewById(R.id.tv_receive);
        img_receive = (ImageView) findViewById(R.id.img_receive);

        img_sendFile = (ImageView) findViewById(R.id.img_sendFile);
        tv_sendFile = (TextView) findViewById(R.id.tv_sendFile);

        img_history = (ImageView) findViewById(R.id.img_history);
        tv_history = (TextView) findViewById(R.id.tv_history);

        img_user = (ImageView) findViewById(R.id.img_user);
        tv_user = (TextView) findViewById(R.id.tv_user);




        pager = (NonSwipeableViewPager) findViewById(R.id.viewpager);
        Items_Viewpager_market adapter_articles = new Items_Viewpager_market(getSupportFragmentManager());
        pager.setAdapter(adapter_articles);
        pager.setCurrentItem(4, false);
        pager.setOffscreenPageLimit(5);

        //defult property View
        tv_user.setVisibility(View.VISIBLE);




    }





    //for Permission
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 12234: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //dastrasi dade shode

                } else {
                    new AlertDialog.Builder(this)
                            .setMessage("برای اجرای برنامه باید حتما دسترسی رو به برنامه بدهید")
                            .setCancelable(false)
                            .setNegativeButton("دادن دسترسی", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    setPermission_Camera();
                                }
                            })
                            .show();
                }
            }
            return;
        }
    }

    //checkPermission
    public void setPermission_Camera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 12234);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 12234);
            }
        }
    }


    //Bottom Navigation

    public void onCLickSetting(View v) {
        setVisibiltyBottomBar(tv_receive);
        Animation animation = new TranslateAnimation(0, 0, 15, 0);
        Animation animation1 = new TranslateAnimation(0, 0, 70, 0);
        animation.setDuration(300);
        animation1.setDuration(200);
        img_receive.startAnimation(animation);
        tv_receive.startAnimation(animation1);

        pager.setCurrentItem(1);


    }

    public void onCLickSendFile(View v) {
        setVisibiltyBottomBar(tv_sendFile);
        Animation animation = new TranslateAnimation(0, 0, 15, 0);
        Animation animation1 = new TranslateAnimation(0, 0, 70, 0);
        animation.setDuration(300);
        animation1.setDuration(200);
        img_sendFile.startAnimation(animation);
        tv_sendFile.startAnimation(animation1);

        pager.setCurrentItem(0);
    }

    public void onCLickHistory(View v) {
        setVisibiltyBottomBar(tv_history);
        Animation animation = new TranslateAnimation(0, 0, 15, 0);
        Animation animation1 = new TranslateAnimation(0, 0, 70, 0);
        animation.setDuration(300);
        animation1.setDuration(200);
        img_history.startAnimation(animation);
        tv_history.startAnimation(animation1);

        pager.setCurrentItem(2);
    }

    public void onCLickUser(View v) {
        setVisibiltyBottomBar(tv_user);
        Animation animation = new TranslateAnimation(0, 0, 15, 0);
        Animation animation1 = new TranslateAnimation(0, 0, 70, 0);
        animation.setDuration(300);
        animation1.setDuration(200);
        img_user.startAnimation(animation);
        tv_user.startAnimation(animation1);

        pager.setCurrentItem(3);
    }



    //method set visible bottom bar
    public static void setVisibiltyBottomBar(TextView tv) {
        tv_receive.setVisibility(View.GONE);
        tv_sendFile.setVisibility(View.GONE);
        tv_history.setVisibility(View.GONE);
        tv_user.setVisibility(View.GONE);
        //tv_home.setVisibility(View.GONE);
        tv.setVisibility(View.VISIBLE);
    }


    // change Font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
