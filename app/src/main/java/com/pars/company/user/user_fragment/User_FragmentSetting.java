package com.pars.company.user.user_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pars.company.R;


public class User_FragmentSetting extends Fragment {


    private FragmentActivity contInst;
    private View view;
    private LinearLayoutManager mLayoutManager;

    public static User_FragmentSetting newInstance() {

        Bundle args = new Bundle();
        User_FragmentSetting fragment = new User_FragmentSetting();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_fragment_setting, container, false);
        contInst = getActivity();
        //java code





        ///java code
        return view;
    }



}
