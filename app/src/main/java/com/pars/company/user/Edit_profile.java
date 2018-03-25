package com.pars.company.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.pars.company.Network.AppController;
import com.pars.company.Network.CustomRequest;
import com.pars.company.R;
import com.pars.company.SavePref;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Edit_profile extends AppCompatActivity {

    SavePref save;
    ProgressDialog dialog;
    EditText edt_name, edt_namayanhe, edt_mobile, edt_tel,
            edt_pass, edt_conf_pass, edt_address;

    Button btn_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit_profile);

        save = new SavePref(this);
        dialog = new ProgressDialog(this);

        edt_name = (EditText) findViewById(R.id.txt_name_edit);
        edt_namayanhe = (EditText) findViewById(R.id.txt_namayande_edit);
        edt_mobile = (EditText) findViewById(R.id.txt_tel_edit);
        edt_tel = (EditText) findViewById(R.id.txt_telphon_edit);
        edt_pass = (EditText) findViewById(R.id.txt_password_edit);
        edt_conf_pass = (EditText) findViewById(R.id.txt_comfirm_password_edit);
        edt_address = (EditText) findViewById(R.id.txt_address_edit);

        edt_name.setText(save.load(AppController.SAVE_USER_COMPANY, ""));
        edt_namayanhe.setText(save.load(AppController.SAVE_USER_NAME, ""));
        edt_mobile.setText(save.load(AppController.SAVE_USER_MOBILE, ""));
        edt_tel.setText(save.load(AppController.SAVE_USER_TEL, ""));
        edt_address.setText(save.load(AppController.SAVE_USER_ADDRESS, ""));


        btn_edit = (Button) findViewById(R.id.btn_register_edit);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("در حال ویرایش...");
                dialog.show();

                update(save.load(AppController.SAVE_USER_ID, "0"),
                        edt_name.getText().toString(),
                        edt_namayanhe.getText().toString(),
                        edt_mobile.getText().toString(),
                        edt_tel.getText().toString(),
                        edt_pass.getText().toString(),
                        edt_address.getText().toString()
                );
            }
        });

    }


    private void update(String id_user, final String name, final String namayandeh, final String mobile,
                        final String tel, String pass, final String address) {


        Map<String, String> params = new HashMap<String, String>();
        params.put("id_user", id_user);
        params.put("company_name", name);
        params.put("namayandeh", namayandeh);
        params.put("mobile", mobile);
        params.put("tel", tel);
        params.put("pass", pass);
        params.put("address", address);

        CustomRequest jsonObjReq = new CustomRequest(Request.Method.POST, AppController.URL_EDIT_USER_DATA, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject resp = response;
                //Log.d("TAG--------OK", resp.toString());

                try {
                    if (resp.getString("status").equals("200")) {

                        save.save(AppController.SAVE_USER_NAME, namayandeh);
                        save.save(AppController.SAVE_USER_MOBILE, mobile);
                        save.save(AppController.SAVE_USER_TEL, tel);
                        save.save(AppController.SAVE_USER_COMPANY, name);
                        save.save(AppController.SAVE_USER_ADDRESS, address);

                        Toast.makeText(Edit_profile.this, "اطلاعات با موفقیت ویرایش شد", Toast.LENGTH_SHORT).show();
                        finish();

                    } else if (resp.getString("status").equals("500")) {
                        Toast.makeText(Edit_profile.this, "خطا در سرور لطفا در زمان دیگری دوباره انتحان کنید", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(Edit_profile.this, "لطفا در زمان دیگری اقدام کنید", Toast.LENGTH_SHORT).show();

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        jsonObjReq.setShouldCache(false);
        //myRequestQueue.getCache().clear();
        AppController.getInstance().addToRequestQueue(jsonObjReq, "EDIT_USER_DATA");

    }

    // change Font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
