package com.pars.company.user.user_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pars.company.R;
import com.pars.company.user.Edit_profile;
import com.pars.company.user.User_receive;
import com.pars.company.user.User_sendFile;


public class User_FragmentProfile extends Fragment {


    TextView tv_edit_profile;
    private ImageView img_sendfile_userprofile , img_recfile_userprofile;

    private FragmentActivity contInst;
    private View view;
    private LinearLayoutManager mLayoutManager;

    public static User_FragmentProfile newInstance() {

        Bundle args = new Bundle();
        User_FragmentProfile fragment = new User_FragmentProfile();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_fragment_profile, container, false);
        contInst = getActivity();
        //java code


        img_sendfile_userprofile = (ImageView)view.findViewById(R.id.img_sendfile_userprofile);
        img_sendfile_userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(contInst,User_sendFile.class));
            }
        });


        img_recfile_userprofile = (ImageView)view.findViewById(R.id.img_recfile_userprofile);
        img_recfile_userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(contInst,User_receive.class));
            }
        });



        tv_edit_profile = (TextView)view.findViewById(R.id.tv_edit_profile);
        tv_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(contInst,Edit_profile.class));
            }
        });





        ///java code
        return view;
    }




}
