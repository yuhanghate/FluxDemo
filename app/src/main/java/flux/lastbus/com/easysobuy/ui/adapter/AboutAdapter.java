package flux.lastbus.com.easysobuy.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import flux.lastbus.com.easysobuy.R;

/**
 * Created by yuhang on 16-8-13.
 */
public class AboutAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragment;
    Context mContext;

    private int[] imageResId = {
            R.drawable.about_main,
            R.drawable.about_recruit,
            R.drawable.about_address
    };
    public AboutAdapter(FragmentManager fm, List<Fragment> mFragment,Context context) {
        super(fm);
        this.mFragment = mFragment;
        mContext = context;
    }

    public View getTabView(int position){
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_tab_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(imageResId[position]);
        return imageView;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

 /*   @Override
    public CharSequence getPageTitle(int position) {
        Drawable image = mContext.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }*/
}
