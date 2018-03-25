package com.pars.company.Admin_fragment;

import android.app.ProgressDialog;
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
import com.pars.company.Model.Users;
import com.pars.company.Network.AppController;
import com.pars.company.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentUser extends Fragment {

    ProgressDialog dialog;

    private List<Users> itemList_user = new ArrayList<>();
    private RecyclerView recyclerView_user;
    private UserRecyclerAdapter adapter_user;


    private FragmentActivity contInst;
    private View view;
    private LinearLayoutManager mLayoutManager;

    public static FragmentUser newInstance() {

        Bundle args = new Bundle();
        FragmentUser fragment = new FragmentUser();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);
        contInst = getActivity();
        //java co0de

        dialog = new ProgressDialog(getContext());


        recyclerView_user = (RecyclerView) view.findViewById(R.id.recyclerview_calculator);
        adapter_user = new UserRecyclerAdapter(getActivity(), itemList_user);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView_user.setLayoutManager(mLayoutManager);
        recyclerView_user.setItemAnimator(new DefaultItemAnimator());
        recyclerView_user.setAdapter(adapter_user);


        fill();

        ///java code
        return view;
    }


    private void fill() {


        dialog.setMessage("Loading...");
        dialog.show();

        JsonArrayRequest req = new JsonArrayRequest(AppController.URL_ALL_USERS,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.d("TAG---------OK", response.toString());

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject object = response.getJSONObject(i);

                                Users item = new Users();
                                item.setId(object.getString("id"));
                                item.setCompany_name(object.getString("company_name"));
                                item.setFullname(object.getString("fullname"));
                                item.setEmail(object.getString("email"));
                                item.setMobile(object.getString("mobile"));
                                item.setTel(object.getString("tel"));
                                item.setAddress(object.getString("address"));

                                itemList_user.add(item);
                                item = null;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter_user.notifyDataSetChanged();

                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG------------Error", "Error: " + error.getMessage());
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
        req.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(req, "ALL_USER");
    }


}
