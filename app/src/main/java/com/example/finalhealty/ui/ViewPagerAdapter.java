package com.example.finalhealty.ui;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> listtFragment = new ArrayList<>();
    private final List<String> listtTitulos = new ArrayList<>();


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return listtFragment.get(position);
    }

    @Override
    public int getCount() {
        return listtFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listtTitulos.get(position);
    }

    public void addFragment (Fragment fragment, String titulo){

        listtFragment.add(fragment);
        listtTitulos.add(titulo);
    }


}
