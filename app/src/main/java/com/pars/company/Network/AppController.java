package com.pars.company.Network;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.pars.company.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Maziar on 11/16/2017.
 */

public class AppController  extends Application {

    public static final String TAG = AppController.class
            .getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static AppController mInstance;


    public final static String URL = "http://iava.in/parsadmin/app/index.php/api/";

    public final static String URL_IMAGE = "http://iava.in/parsadmin/app/assets/image/";

    public final static String URL_LOGIN = URL + "auth/login";
    public final static String URL_SIGNUP = URL + "auth/signup";
    public final static String URL_FORGETPASS = URL + "auth/forgotpass";
    public final static String URL_EDIT_USER_DATA = URL + "config/edituser";

    public final static String URL_ALL_USERS= URL + "data/users";
    public final static String URL_ALL_DATA= URL + "data/load";
    public final static String URL_SAVE_DATA= URL + "data/save";
    public final static String URL_UPLOAD_IMAGE_DATA= URL + "data/uploadimage";



    public final static String SAVE_LOGIN = "LOGIN";
    public final static String SAVE_IMAGE_NAME = "SAVE_IMAGE_NAME";

    public final static String SAVE_USER_ID = "SAVE_USER_ID";
    public final static String SAVE_USER_NAME = "SAVE_USER_NAME";
    public final static String SAVE_USER_MOBILE = "SAVE_USER_MOBILE";
    public final static String SAVE_USER_TEL = "SAVE_USER_TEL";
    public final static String SAVE_USER_COMPANY = "SAVE_USER_COMPANY";
    public final static String SAVE_USER_ADDRESS = "SAVE_USER_ADDRESS";
    public final static String SAVE_USER_IS_ADMIN = "SAVE_USER_IS_ADMIN";

    public final static String SAVE_USER_ID_FOR_SENDFILE = "SAVE_USER_ID_FOR_SENDFILE";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("IRANSansWeb_Medium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}