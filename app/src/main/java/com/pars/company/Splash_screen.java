package com.pars.company;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pars.company.Network.AppController;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Splash_screen extends Activity {

    SavePref save;

    public void onAttchedToWindow(){

        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    Thread splashTread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        save = new SavePref(this);
        save.save(AppController.SAVE_USER_ID_FOR_SENDFILE, "0");
        save.save(AppController.SAVE_USER_IS_ADMIN, "0");
        StartAnimations();

    }



    private void StartAnimations() {
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim1.reset();

        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.alpha2);
        anim2.reset();

        LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim1);

        l.startAnimation(anim2);



        anim1 = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim1.reset();

        anim2 = AnimationUtils.loadAnimation(this, R.anim.translate2);
        anim2.reset();


        TextView iv1 = (TextView) findViewById(R.id.splash1);
        iv1.clearAnimation();
        iv1.startAnimation(anim1);


        splashTread = new Thread() {

            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 350) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(Splash_screen.this,
                            Activity_Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    Splash_screen.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Splash_screen.this.finish();
                }

            }
        };
        splashTread.start();


    }


    // change Font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



}