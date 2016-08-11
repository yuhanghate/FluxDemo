package flux.lastbus.com.easysobuy.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import flux.lastbus.com.easysobuy.ui.fragment.BaseFragment;

/**
 * 注册 Adapter
 * Created by yuhang on 16-8-11.
 */
public class RegisterViewPagerAdapter extends FragmentStatePagerAdapter {
    List<BaseFragment> mFragments;
    String[] mTitls;

    public RegisterViewPagerAdapter(FragmentManager fm, List<BaseFragment> mFragments, String[] mTitls) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitls = mTitls;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

   /* @Override
    public CharSequence getPageTitle(int position) {
        return mTitls[position];
    }*/
}
