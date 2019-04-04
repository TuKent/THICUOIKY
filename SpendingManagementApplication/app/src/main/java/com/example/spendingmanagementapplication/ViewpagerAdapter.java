package com.example.spendingmanagementapplication;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerAdapter extends FragmentStatePagerAdapter
{
    List<Fragment> Fragment = new ArrayList<>();
    List<String> stringList = new ArrayList<>();


    public void AddFragment(Fragment fragment, String Title)
    {
        Fragment.add(fragment);
        stringList.add(Title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return stringList.get(position);
    }


    public ViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return Fragment.get(i) ;
    }

    @Override
    public int getCount() {
        return Fragment.size();
    }
}
