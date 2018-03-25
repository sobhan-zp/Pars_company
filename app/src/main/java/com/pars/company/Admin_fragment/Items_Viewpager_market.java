package com.pars.company.Admin_fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by My Hp on 10/20/2016.
 */
public class Items_Viewpager_market extends FragmentPagerAdapter {


    public Items_Viewpager_market(FragmentManager fm) {
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
                return FragmentSendFile.newInstance();
            case 1:
                return FragmentSetting.newInstance();
            case 2:
                return FragmenHistory.newInstance();
            case 3:
                return FragmentUser.newInstance();
            default:
                return FragmentUser.newInstance();
        }
    }

}
