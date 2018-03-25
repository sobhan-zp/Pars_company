package com.pars.company;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pars.company.Network.AppController;
import com.pars.company.Network.CustomRequest;
import com.pars.company.user.User_mainActivity;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NextActivityFile extends AppCompatActivity {

    SavePref save;
    ProgressDialog dialog;
    int category_id = -1;
    String[] spin_id;
    String[] chk_id;
    Context context;
    ListView list_cate;
    LinearLayout li_malyat, li_hoghoghi, li_hesabdari,
            li_amozeshi, li_hesabrasi, li_moshavereh, li_refahi;


    Button cate_one, cate_tow, cate_three;

    MaterialDialog dialog_Cate;


    MaterialBetterSpinner sp_arzeshafzode, sp_mashaghel, sp_hoghogh, sp_fasli, sp_tanzim,
            sp_mali, sp_shora, sp_daftar, sp_ekhtelaf, sp_category;


    CheckBox a1, a2, a3, a4, a5, h1, h2, h3, m1, m2, m3, m4, m5, m6, m7, m8, m9, r1, r2, r3, r4, r5, r6;


    Button btn_next_next;

    String[] category_item = {"مالیاتی", "حقوقی", "حسابداری", "آموزشی", "حسابرسی", "مشاوره", "رفاهی"};

    String[] arzesh_item = {"90", "91", "92", "93", "94", "95", "96"};
    String[] mashaghel_item = {"90", "91", "92", "93", "94", "95", "96"};
    String[] hoghogh_item = {"90", "91", "92", "93", "94", "95", "96"};
    String[] fasli_item = {"90", "91", "92", "93", "94", "95", "96"};
    String[] tanzim_item = {"اظهار نامه مشاغل", "اظهار نامه ارزش افزوده", "گزارش فصلي"};

    String[] mali_item = {"چك", "سفته", "قراردادهاي مالي"};
    String[] shora_item = {"كار و كارگري", "تامين اجتماعي", "ديوان عدالت اداري", "بيمه بيكاري"};

    String[] daftar_item = {"دفاتر ويژه مشاغل", "تنظيم اسناد مالي"};
    String[] ekhtelaf_item = {"آری", "خیر"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_file);

        spin_id =new String[] {"-1","-1","-1","-1","-1","-1","-1","-1","-1"};
        chk_id = new String[] {"0","0","0","0","0","0","0","0","0","0","0",
                "0","0","0","0","0","0","0","0","0","0","0"};

        save = new SavePref(this);
        dialog = new ProgressDialog(this);

        findview();
        Spinner_all();


        btn_next_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NextActivityFile.this, MainActivity.class);
                startActivity(i);
            }
        });


        hideall();

        sp_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                category_id = position;
                proccess(position);

                //Toast.makeText(NextActivityFile.this, ""+category_id, Toast.LENGTH_SHORT).show();

            }
        });

        btn_next_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.setMessage("در حال ذخیره اطلاعات...");
                dialog.setCancelable(false);
                dialog.show();

                sendData(
                        save.load(AppController.SAVE_USER_ID, "0"),
                        String.valueOf(category_id),
                        spin_id[0]+","+spin_id[1]+","+spin_id[2]+","+spin_id[3]+","+spin_id[4],
                        spin_id[5]+","+spin_id[6],
                        spin_id[7]+","+spin_id[8],
                        chk_id[0]+","+chk_id[1]+","+chk_id[2]+","+chk_id[3]+","+chk_id[4],
                        chk_id[5]+","+chk_id[6]+","+chk_id[7],
                        chk_id[8]+","+chk_id[9]+","+chk_id[10]+","+chk_id[11]
                                +","+chk_id[12]+","+chk_id[13]+","+chk_id[14]+","+chk_id[15],
                        chk_id[16]+","+chk_id[17]+","+chk_id[18]+","+chk_id[19]+","+chk_id[20]+","+chk_id[21]
                );
            }
        });


    }




    private void sendData(String id_user, String id_group, String maliat, String hoghoghi,
                          String hesabdari, String amozeshi, String hesabrasi, String moshavere, String refahi) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("id_user", save.load(AppController.SAVE_USER_ID_FOR_SENDFILE, "0"));
        params.put("img", AppController.URL_IMAGE + save.load(AppController.SAVE_IMAGE_NAME, "null"));
        params.put("id_group", id_group);
        params.put("is_admin", save.load(AppController.SAVE_USER_IS_ADMIN, "0"));

        params.put("maliat", maliat);
        params.put("hoghoghi", hoghoghi);
        params.put("hesabdari", hesabdari);
        params.put("amozeshi", amozeshi);
        params.put("hesabrasi", hesabrasi);
        params.put("moshavere", moshavere);
        params.put("refahi", refahi);

        CustomRequest jsonObjReq = new CustomRequest(Request.Method.POST, AppController.URL_SAVE_DATA, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONObject resp = response;
                //Log.d("TAG--------OK", resp.toString());

                try {
                    if (resp.getString("status").equals("200")) {

                        Toast.makeText(NextActivityFile.this, "اطلاعات با موفقیت ارسال شد", Toast.LENGTH_SHORT).show();

                        finish();

                    } else if (resp.getString("status").equals("500")) {
                        Toast.makeText(NextActivityFile.this, "خطا در ذخیره سازی لطفا مجدد سعی کنید", Toast.LENGTH_SHORT).show();
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

                Toast.makeText(NextActivityFile.this, "لطفا در زمان دیگری اقدام کنید", Toast.LENGTH_SHORT).show();

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        jsonObjReq.setShouldCache(false);
        //myRequestQueue.getCache().clear();
        AppController.getInstance().addToRequestQueue(jsonObjReq, "SAVEDATA");

    }



    private void Spinner_all() {
        ArrayAdapter<String> arzesh_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spinner, arzesh_item);
        sp_arzeshafzode.setAdapter(arzesh_adapter);

        ArrayAdapter<String> category_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spinner, category_item);
        sp_category.setAdapter(category_adapter);

        ArrayAdapter<String> mashaghel_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spinner, mashaghel_item);
        sp_mashaghel.setAdapter(mashaghel_adapter);

        ArrayAdapter<String> hoghogh_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spinner, hoghogh_item);
        sp_hoghogh.setAdapter(hoghogh_adapter);

        ArrayAdapter<String> fasli_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spinner, fasli_item);
        sp_fasli.setAdapter(fasli_adapter);

        ArrayAdapter<String> tnzim_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spinner, tanzim_item);
        sp_tanzim.setAdapter(tnzim_adapter);


        ArrayAdapter<String> mali_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spinner, mali_item);
        sp_mali.setAdapter(mali_adapter);

        ArrayAdapter<String> shora_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spinner, shora_item);
        sp_shora.setAdapter(shora_adapter);

        ArrayAdapter<String> daftar_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spinner, daftar_item);
        sp_daftar.setAdapter(daftar_adapter);

        ArrayAdapter<String> ekhtelaf_adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row_spinner, ekhtelaf_item);
        sp_ekhtelaf.setAdapter(ekhtelaf_adapter);


    }

    private void proccess(int pos) {
        switch (pos) {
            case 0:
                hideall();
                li_malyat.setVisibility(View.VISIBLE);
                btn_next_next.setVisibility(View.VISIBLE);
                break;
            case 1:
                hideall();
                li_hoghoghi.setVisibility(View.VISIBLE);
                btn_next_next.setVisibility(View.VISIBLE);
                break;
            case 2:
                hideall();
                li_hesabdari.setVisibility(View.VISIBLE);
                btn_next_next.setVisibility(View.VISIBLE);
                break;
            case 3:
                hideall();
                li_amozeshi.setVisibility(View.VISIBLE);
                btn_next_next.setVisibility(View.VISIBLE);
                break;
            case 4:
                hideall();
                li_hesabrasi.setVisibility(View.VISIBLE);
                btn_next_next.setVisibility(View.VISIBLE);
                break;
            case 5:
                hideall();
                li_moshavereh.setVisibility(View.VISIBLE);
                btn_next_next.setVisibility(View.VISIBLE);
                break;
            case 6:
                hideall();
                li_refahi.setVisibility(View.VISIBLE);
                btn_next_next.setVisibility(View.VISIBLE);
                break;
            default:

        }
    }

    private void hideall() {
        li_malyat.setVisibility(View.GONE);
        li_hoghoghi.setVisibility(View.GONE);
        li_hesabdari.setVisibility(View.GONE);
        li_amozeshi.setVisibility(View.GONE);
        li_hesabrasi.setVisibility(View.GONE);
        li_moshavereh.setVisibility(View.GONE);
        li_refahi.setVisibility(View.GONE);
        btn_next_next.setVisibility(View.GONE);
    }


    private void findview() {
        sp_category = (MaterialBetterSpinner) findViewById(R.id.sp_category);

        btn_next_next = (Button) findViewById(R.id.btn_next_next);
        sp_arzeshafzode = (MaterialBetterSpinner) findViewById(R.id.sp_arzeshafzode);
        sp_mashaghel = (MaterialBetterSpinner) findViewById(R.id.sp_mashaghel);
        sp_hoghogh = (MaterialBetterSpinner) findViewById(R.id.sp_hoghogh);
        sp_fasli = (MaterialBetterSpinner) findViewById(R.id.sp_fasli);
        sp_tanzim = (MaterialBetterSpinner) findViewById(R.id.sp_tanzim);
        sp_mali = (MaterialBetterSpinner) findViewById(R.id.sp_mali);
        sp_shora = (MaterialBetterSpinner) findViewById(R.id.sp_shora);
        sp_daftar = (MaterialBetterSpinner) findViewById(R.id.sp_daftar);
        sp_ekhtelaf = (MaterialBetterSpinner) findViewById(R.id.sp_ekhtelaf);

        a1 = (CheckBox) findViewById(R.id.cb_a1);
        a2 = (CheckBox) findViewById(R.id.cb_a2);
        a3 = (CheckBox) findViewById(R.id.cb_a3);
        a4 = (CheckBox) findViewById(R.id.cb_a4);
        a5 = (CheckBox) findViewById(R.id.cb_a5);

        h1 = (CheckBox) findViewById(R.id.cb_h1);
        h2 = (CheckBox) findViewById(R.id.cb_h2);
        h3 = (CheckBox) findViewById(R.id.cb_h3);

        m1 = (CheckBox) findViewById(R.id.cb_m1);
        m2 = (CheckBox) findViewById(R.id.cb_m2);
        m3 = (CheckBox) findViewById(R.id.cb_m3);
        m4 = (CheckBox) findViewById(R.id.cb_m4);
        m5 = (CheckBox) findViewById(R.id.cb_m5);
        m6 = (CheckBox) findViewById(R.id.cb_m6);
        m7 = (CheckBox) findViewById(R.id.cb_m7);
        m8 = (CheckBox) findViewById(R.id.cb_m8);

        r1 = (CheckBox) findViewById(R.id.cb_r1);
        r2 = (CheckBox) findViewById(R.id.cb_r2);
        r3 = (CheckBox) findViewById(R.id.cb_r3);
        r4 = (CheckBox) findViewById(R.id.cb_r4);
        r5 = (CheckBox) findViewById(R.id.cb_r5);
        r6 = (CheckBox) findViewById(R.id.cb_r6);


        //LinearLayout li_malyat, li_hoghoghi, li_hesabdari, li_amozeshi, li_hesabrasi, li_moshavereh, li_refahi;

        li_malyat = (LinearLayout) findViewById(R.id.ll_maliyat);
        li_hoghoghi = (LinearLayout) findViewById(R.id.ll_hoghoghi);
        li_hesabdari = (LinearLayout) findViewById(R.id.ll_hesabdari);
        li_amozeshi = (LinearLayout) findViewById(R.id.ll_amoozeshi);
        li_hesabrasi = (LinearLayout) findViewById(R.id.ll_hesabresi);
        li_moshavereh = (LinearLayout) findViewById(R.id.ll_moshavere);
        li_refahi = (LinearLayout) findViewById(R.id.ll_refahi);


        sp_arzeshafzode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spin_id[0] = arzesh_item[position];
            }
        });

        sp_mashaghel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spin_id[1] = mashaghel_item[position];
            }
        });

        sp_hoghogh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spin_id[2] = hoghogh_item[position];
            }
        });

        sp_fasli.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spin_id[3] = fasli_item[position];
            }
        });

        sp_tanzim.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spin_id[4] = String.valueOf(position);
            }
        });

        sp_mali.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spin_id[5] = String.valueOf(position);
            }
        });

        sp_shora.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spin_id[6] = String.valueOf(position);
            }
        });

        sp_daftar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spin_id[7] = String.valueOf(position);
            }
        });

        sp_ekhtelaf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                spin_id[8] = String.valueOf(position);
            }
        });




        a1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[0] = "1";
                }else {
                    chk_id[0] = "0";
                }
            }
        });
        a2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[1] = "1";
                }else {
                    chk_id[1] = "0";
                }
            }
        });
        a3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[2] = "1";
                }else {
                    chk_id[2] = "0";
                }
            }
        });
        a4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[3] = "1";
                }else {
                    chk_id[3] = "0";
                }
            }
        });
        a5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[4] = "1";
                }else {
                    chk_id[4] = "0";
                }
            }
        });


        h1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[5] = "1";
                }else {
                    chk_id[5] = "0";
                }
            }
        });
        h2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[6] = "1";
                }else {
                    chk_id[6] = "0";
                }
            }
        });
        h3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[7] = "1";
                }else {
                    chk_id[7] = "0";
                }
            }
        });



        m1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[8] = "1";
                }else {
                    chk_id[8] = "0";
                }
            }
        });
        m2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[9] = "1";
                }else {
                    chk_id[9] = "0";
                }
            }
        });
        m3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[10] = "1";
                }else {
                    chk_id[10] = "0";
                }
            }
        });
        m4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[11] = "1";
                }else {
                    chk_id[11] = "0";
                }
            }
        });
        m5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[12] = "1";
                }else {
                    chk_id[12] = "0";
                }
            }
        });
        m6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[13] = "1";
                }else {
                    chk_id[13] = "0";
                }
            }
        });
        m7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[14] = "1";
                }else {
                    chk_id[14] = "0";
                }
            }
        });
        m8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[15] = "1";
                }else {
                    chk_id[15] = "0";
                }
            }
        });



        r1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[16] = "1";
                }else {
                    chk_id[16] = "0";
                }
            }
        });
        r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[17] = "1";
                }else {
                    chk_id[17] = "0";
                }
            }
        });
        r3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[18] = "1";
                }else {
                    chk_id[18] = "0";
                }
            }
        });
        r4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[19] = "1";
                }else {
                    chk_id[19] = "0";
                }
            }
        });
        r5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[20] = "1";
                }else {
                    chk_id[20] = "0";
                }
            }
        });
        r6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    chk_id[21] = "1";
                }else {
                    chk_id[21] = "0";
                }
            }
        });

    }

    // change Font
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
