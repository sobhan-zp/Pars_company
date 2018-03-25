package com.pars.company;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.pars.company.Network.AppController;
import com.pars.company.Network.CustomRequest;
import com.pars.company.user.User_mainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Activity_Login extends AppCompatActivity {

    SavePref save;
    ProgressDialog dialog;
    Button btn_login_main, btn_loginuser_main;
    EditText edt_email, edt_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        save = new SavePref(this);
        dialog = new ProgressDialog(this);

        btn_login_main = (Button) findViewById(R.id.btn_login_main);
        btn_loginuser_main = (Button) findViewById(R.id.btn_loginuser_main);

        edt_email = (EditText) findViewById(R.id.edt_login_email);
        edt_pass = (EditText) findViewById(R.id.edt_login_pass);

        btn_loginuser_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Login.this, User_mainActivity.class));
            }
        });

        btn_login_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("درحال احراز هویت...");
                dialog.show();

                Login(edt_email.getText().toString(), edt_pass.getText().toString());
            }
        });
    }

    private void Login(String email, String pass) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        params.put("pass", pass);

        CustomRequest jsonObjReq = new CustomRequest(Request.Method.POST, AppController.URL_LOGIN, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject resp = response;
                //Log.d("TAG--------OK", resp.toString());

                try {
                    if (resp.getString("status").equals("200")) {

                        save.save(AppController.SAVE_USER_ID, resp.getString("id"));
                        save.save(AppController.SAVE_USER_NAME, resp.getString("full_name"));
                        save.save(AppController.SAVE_USER_MOBILE, resp.getString("mobile"));
                        save.save(AppController.SAVE_USER_TEL, resp.getString("tel"));
                        save.save(AppController.SAVE_USER_COMPANY, resp.getString("company_name"));
                        save.save(AppController.SAVE_USER_ADDRESS, resp.getString("address"));
                        save.save(AppController.SAVE_LOGIN, "1");

                        if (resp.getString("is_admin").equals("0")) {
                            save.save(AppController.SAVE_USER_IS_ADMIN, "0");
                            startActivity(new Intent(Activity_Login.this, User_mainActivity.class));
                        } else {
                            save.save(AppController.SAVE_USER_IS_ADMIN, "1");
                            startActivity(new Intent(Activity_Login.this, MainActivity.class));
                        }
                        finish();

                    } else if (resp.getString("status").equals("204")) {
                        Toast.makeText(Activity_Login.this, "کاربری با این ایمیل یافت نشد", Toast.LENGTH_SHORT).show();
                    } else if (resp.getString("status").equals("205")) {
                        Toast.makeText(Activity_Login.this, "کلمه عبور اشتباه است", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("TAG--------Error", "Error: " + error.getMessage());

                Toast.makeText(Activity_Login.this, "لطفا در زمان دیگری اقدام کنید", Toast.LENGTH_SHORT).show();

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        jsonObjReq.setShouldCache(false);
        //myRequestQueue.getCache().clear();
        AppController.getInstance().addToRequestQueue(jsonObjReq, "LOGIN");

    }


    public void loginOnClicks(View v) {
        switch (v.getId()) {
            case R.id.tv_login_forget: {
                forgot(edt_email.getText().toString());
                break;
            }
            case R.id.tv_login_register: {
                startActivity(new Intent(Activity_Login.this, Activity_Register.class));
                break;
            }
            default:
                break;
        }
    }


    private void forgot(String email) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);

        CustomRequest jsonObjReq = new CustomRequest(Request.Method.POST, AppController.URL_FORGETPASS, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject resp = response;
                Log.d("TAG--------OK", resp.toString());

                try {
                    if (resp.getString("status").equals("210")) {

                        Toast.makeText(Activity_Login.this, "درخواست تغییر کلمه عبور برای ایمیل شما ارسال شد", Toast.LENGTH_SHORT).show();

                    } else if (resp.getString("status").equals("204")) {
                        Toast.makeText(Activity_Login.this, "کاربری با این ایمیل یافت نشد", Toast.LENGTH_SHORT).show();
                    } else if (resp.getString("status").equals("206")) {
                        Toast.makeText(Activity_Login.this, "خطا در سرور لطفا بعدا سعی کنید", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("TAG--------Error", "Error: " + error.getMessage());

                Toast.makeText(Activity_Login.this, "لطفا در زمان دیگری اقدام کنید", Toast.LENGTH_SHORT).show();

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        jsonObjReq.setShouldCache(false);
        //myRequestQueue.getCache().clear();
        AppController.getInstance().addToRequestQueue(jsonObjReq, "FORGOT");

    }


    // change Font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
