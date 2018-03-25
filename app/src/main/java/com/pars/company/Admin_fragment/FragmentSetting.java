package com.pars.company.Admin_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.pars.company.Model.History;
import com.pars.company.Network.AppController;
import com.pars.company.R;
import com.pars.company.SavePref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentSetting extends Fragment {


    private FragmentActivity contInst;
    private View view;
    private LinearLayoutManager mLayoutManager;
    private SavePref save;


    private List<History> itemList_user = new ArrayList<>();
    private RecyclerView recyclerView_user;
    private ReceiveRecyclerAdapter adapter_user;



    public static FragmentSetting newInstance() {

        Bundle args = new Bundle();
        FragmentSetting fragment = new FragmentSetting();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        contInst = getActivity();
        //java code


        save = new SavePref(getContext());

        recyclerView_user = (RecyclerView) view.findViewById(R.id.recyclerview_onlineClass);
        adapter_user = new ReceiveRecyclerAdapter(getActivity(), itemList_user);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView_user.setLayoutManager(mLayoutManager);
        recyclerView_user.setItemAnimator(new DefaultItemAnimator());
        recyclerView_user.setAdapter(adapter_user);

        fill();



        ///java code
        return view;
    }



    private void fill() {



        JsonArrayRequest req = new JsonArrayRequest(AppController.URL_ALL_DATA,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.d("TAG---------OK", response.toString());

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);

                                History item = new History();
                                item.setId(object.getString("id"));
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

                                if (save.load(AppController.SAVE_USER_IS_ADMIN, "1").equals("1")) {
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
