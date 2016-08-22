package flux.lastbus.com.easysobuy.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import flux.lastbus.com.easysobuy.ui.fragment.BaseFragment;

/**
 * Created by yuhang on 16-8-22.
 */
public class MomentsPagerAdapter extends FragmentStatePagerAdapter {
    List<BaseFragment> fragments;
    String[] titles;

    public MomentsPagerAdapter(FragmentManager fm, List<BaseFragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
