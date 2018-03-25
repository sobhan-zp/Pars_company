package com.pars.company.user.user_fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by My Hp on 10/20/2016.
 */
public class User_Items_Viewpager_market extends FragmentPagerAdapter {


    public User_Items_Viewpager_market(FragmentManager fm) {
        super(fm);
       
    }

    @Override
    public int getCount() {
        return 5;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return User_FragmentSendFile.newInstance();
            case 1:
                return User_FragmentSetting.newInstance();
            case 2:
                return User_FragmentProfile.newInstance();
            default:
                return User_FragmentProfile.newInstance();
        }
    }

}
