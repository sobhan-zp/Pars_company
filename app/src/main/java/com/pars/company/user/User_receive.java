package com.pars.company.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.pars.company.Admin_fragment.HistoryRecyclerAdapter;
import com.pars.company.Model.History;
import com.pars.company.Network.AppController;
import com.pars.company.R;
import com.pars.company.SavePref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class User_receive extends AppCompatActivity {

    private SavePref save;
    private List<History> itemList_user = new ArrayList<>();
    private RecyclerView recyclerView_user;
    private HistoryRecyclerAdapter adapter_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_receive);

        save = new SavePref(this);

        recyclerView_user =(RecyclerView)findViewById(R.id.recyclerview_receive_user);
        adapter_user = new HistoryRecyclerAdapter(User_receive.this, itemList_user);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(User_receive.this);
        recyclerView_user.setLayoutManager(mLayoutManager);
        recyclerView_user.setItemAnimator(new DefaultItemAnimator());
        recyclerView_user.setAdapter(adapter_user);

        fill();
    }

    private void fill() {



        JsonArrayRequest req = new JsonArrayRequest(AppController.URL_ALL_DATA,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);

                                History item = new History();
                                item.setId(object.getString("id"));
                                item.setId_user(object.getString("id_user"));
                                item.setNamayandeh(object.getString("fullname"));
                                item.setId_group(object.getString("id_group"));
                                item.setDate(object.getString("tarikh"));
                                item.setImg(object.getString("img"));
                                item.setIs_admin(object.getString("is_admin"));

                                item.setAm_1(object.getString("am_1"));
                                item.setAm_2(object.getString("am_2"));
                                item.setAm_3(object.getString("am_3"));
                                item.setAm_4(object.getString("am_4"));
                                item.setAm_5(object.getString("am_5"));
                                item.setHe_1(object.getString("he_1"));
                                item.setHe_2(object.getString("he_2"));
                                item.setHesab_1(object.getString("hesab_1"));
                                item.setHesab_2(object.getString("hesab_2"));
                                item.setHesab_3(object.getString("hesab_3"));
                                item.setHo_1(object.getString("ho_1"));
                                item.setHo_2(object.getString("ho_2"));
                                item.setMa_1(object.getString("ma_1"));
                                item.setMa_2(object.getString("ma_2"));
                                item.setMa_3(object.getString("ma_3"));
                                item.setMa_4(object.getString("ma_4"));
                                item.setMa_5(object.getString("ma_5"));
                                item.setMo_1(object.getString("mo_1"));
                                item.setMo_2(object.getString("mo_2"));
                                item.setMo_3(object.getString("mo_3"));
                                item.setMo_4(object.getString("mo_4"));
                                item.setMo_5(object.getString("mo_5"));
                                item.setMo_6(object.getString("mo_6"));
                                item.setMo_7(object.getString("mo_7"));
                                item.setMo_8(object.getString("mo_8"));
                                item.setRe_1(object.getString("re_1"));
                                item.setRe_2(object.getString("re_2"));
                                item.setRe_3(object.getString("re_3"));
                                item.setRe_4(object.getString("re_4"));
                                item.setRe_5(object.getString("re_5"));
                                item.setRe_6(object.getString("re_6"));

                                Log.d("TAG---------", "item.getId_user():  "+item.getId_user());
                                Log.d("TAG---------", "item.getIs_admin():  "+item.getIs_admin());

                                if (save.load(AppController.SAVE_USER_ID, "0").equals(item.getId_user())) {
                                    if (item.getIs_admin().equals("0")) {
                                        itemList_user.add(item);
                                    }
                                }
                                item = null;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter_user.notifyDataSetChanged();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG------------Error", "Error: " + error.getMessage());

            }
        });
        req.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(req, "ALL_HISTORY");
    }

}
