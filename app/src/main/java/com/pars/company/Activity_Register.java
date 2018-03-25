package com.pars.company;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
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

public class Activity_Register extends AppCompatActivity {

    SavePref save;
    ProgressDialog dialog;
    EditText edt_name, edt_namayanhe, edt_mobile, edt_tel,
            edt_pass, edt_conf_pass, edt_email, edt_address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        save = new SavePref(this);
        dialog = new ProgressDialog(this);

        edt_name = (EditText) findViewById(R.id.txt_name);
        edt_namayanhe = (EditText) findViewById(R.id.txt_namayande);
        edt_mobile = (EditText) findViewById(R.id.txt_tel);
        edt_tel = (EditText) findViewById(R.id.txt_telphon);
        edt_pass = (EditText) findViewById(R.id.txt_password);
        edt_conf_pass = (EditText) findViewById(R.id.txt_comfirm_password);
        edt_email = (EditText) findViewById(R.id.txt_email);
        edt_address = (EditText) findViewById(R.id.txt_address);

    }


    public void registerOnClicks(View v) {
        switch (v.getId()) {
            case R.id.tv_register_login: {
                startActivity(new Intent(Activity_Register.this, Activity_Login.class));
                break;
            }
            case R.id.btn_register_main: {


                dialog.setMessage("درحال ثبت نام...");
                dialog.show();

                register(edt_name.getText().toString(),
                        edt_namayanhe.getText().toString(),
                        edt_mobile.getText().toString(),
                        edt_tel.getText().toString(),
                        edt_pass.getText().toString(),
                        edt_email.getText().toString(),
                        edt_address.getText().toString()
                );

                break;
            }
            default:
                break;
        }
    }

    private void register(String name, String namayandeh, String mobile,
                          String tel, String pass, String email, String address) {


        Map<String, String> params = new HashMap<String, String>();
        params.put("company_name", name);
        params.put("fullname", namayandeh);
        params.put("mobile", mobile);
        params.put("tel", tel);
        params.put("pass", pass);
        params.put("email", email);
        params.put("address", address);

        CustomRequest jsonObjReq = new CustomRequest(Request.Method.POST, AppController.URL_SIGNUP, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject resp = response;
                //Log.d("TAG--------OK", resp.toString());

                try {
                    if (resp.getString("status").equals("200")) {

                        save.save(AppController.SAVE_LOGIN, "1");
                        startActivity(new Intent(Activity_Register.this, User_mainActivity.class));
                        finish();

                    } else if (resp.getString("status").equals("204")) {
                        Toast.makeText(Activity_Register.this, "این ایمیل قبلا ثبت شده است", Toast.LENGTH_SHORT).show();
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

                //Log.d("TAG--------Error", "Error: " + error.getMessage());

                Toast.makeText(Activity_Register.this, "لطفا در زمان دیگری اقدام کنید", Toast.LENGTH_SHORT).show();

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        jsonObjReq.setShouldCache(false);
        //myRequestQueue.getCache().clear();
        AppController.getInstance().addToRequestQueue(jsonObjReq, "SIGNUP");

    }

    // change Font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
