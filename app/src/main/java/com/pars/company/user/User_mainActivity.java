package com.pars.company.user;

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

import com.pars.company.R;
import com.pars.company.user.user_fragment.User_Items_Viewpager_market;
import com.pars.company.user.user_fragment.User_NonSwipeableViewPager;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class User_mainActivity extends AppCompatActivity {

    //Button gallery
    LinearLayout home;
    TextView tv_home;
    ImageView img_home;

    //Button setting
    LinearLayout user_setting;
    TextView user_tv_setting;
    ImageView user_img_setting;

    //Button sendFile
    LinearLayout user_sendFile;
    TextView user_tv_sendFile;
    ImageView user_img_sendFile;



    //Button gallery
    LinearLayout user_user;
    TextView user_tv_user;
    ImageView user_img_user;


    Context conInst;
    User_NonSwipeableViewPager user_pager;

    // PopUp menu
    Button btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_main);



        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(User_mainActivity.this, btnMenu);
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




        //user_tv_setting = (TextView) findViewById(R.id.user_tv_setting);
        //user_img_setting = (ImageView) findViewById(R.id.user_img_setting);

        user_img_sendFile = (ImageView) findViewById(R.id.user_img_sendFile);
        user_tv_sendFile = (TextView) findViewById(R.id.user_tv_sendFile);

        user_img_user = (ImageView) findViewById(R.id.user_img_user);
        user_tv_user = (TextView) findViewById(R.id.user_tv_user);




        user_pager = (User_NonSwipeableViewPager) findViewById(R.id.user_viewpager);
        User_Items_Viewpager_market adapter_articles = new User_Items_Viewpager_market(getSupportFragmentManager());
        user_pager.setAdapter(adapter_articles);
        user_pager.setCurrentItem(4, false);
        user_pager.setOffscreenPageLimit(5);

        //defult property View
        user_tv_user.setVisibility(View.VISIBLE);




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

   /* public void UseronCLickSetting(View v) {
        setVisibiltyBottomBar(user_tv_setting);
        Animation animation = new TranslateAnimation(0, 0, 15, 0);
        Animation animation1 = new TranslateAnimation(0, 0, 70, 0);
        animation.setDuration(300);
        animation1.setDuration(200);
        user_img_setting.startAnimation(animation);
        user_tv_setting.startAnimation(animation1);

        user_pager.setCurrentItem(1);


    }*/

    public void UseronCLickSendFile(View v) {
        setVisibiltyBottomBar(user_tv_sendFile);
        Animation animation = new TranslateAnimation(0, 0, 15, 0);
        Animation animation1 = new TranslateAnimation(0, 0, 70, 0);
        animation.setDuration(300);
        animation1.setDuration(200);
        user_img_sendFile.startAnimation(animation);
        user_tv_sendFile.startAnimation(animation1);

        user_pager.setCurrentItem(0);
    }



    public void UseronCLickUser(View v) {
        setVisibiltyBottomBar(user_tv_user);
        Animation animation = new TranslateAnimation(0, 0, 15, 0);
        Animation animation1 = new TranslateAnimation(0, 0, 70, 0);
        animation.setDuration(300);
        animation1.setDuration(200);
        user_img_user.startAnimation(animation);
        user_tv_user.startAnimation(animation1);

        user_pager.setCurrentItem(2);
    }



    //method set visible bottom bar
    public void setVisibiltyBottomBar(TextView tv) {
//        user_tv_setting.setVisibility(View.GONE);
        user_tv_sendFile.setVisibility(View.GONE);
        user_tv_user.setVisibility(View.GONE);
        //tv_home.setVisibility(View.GONE);
        tv.setVisibility(View.VISIBLE);
    }


    // change Font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
