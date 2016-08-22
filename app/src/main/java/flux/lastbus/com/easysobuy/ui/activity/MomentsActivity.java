package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import flux.lastbus.com.easysobuy.ui.adapter.MomentsPagerAdapter;
import flux.lastbus.com.easysobuy.ui.fragment.BaseFragment;
import flux.lastbus.com.easysobuy.ui.fragment.MomentsFragment;

/**
 * Created by yuhang on 16-8-22.
 */
public class MomentsActivity extends BaseActivity implements ViewPager.OnPageChangeListener{
    List<BaseFragment> fragments = new ArrayList<>();
    String[] titles = {"全部","关注","产品秀"};

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tableLayout)
    TabLayout tableLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    public int onContentView() {
        return R.layout.activity_moments;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MomentsActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        initPagerAdapter();
    }

    private void initPagerAdapter() {
        for (int i = 0; i < 3; i++) {
            MomentsFragment fragment = MomentsFragment.newInstance();
            fragments.add(fragment);
        }

        MomentsPagerAdapter adapter = new MomentsPagerAdapter(getSupportFragmentManager(), fragments,titles);
        viewpager.setAdapter(adapter);
        // 设置ViewPager最大缓存的页面个数
        viewpager.setOffscreenPageLimit(5);
        // 给ViewPager添加页面动态监听器（为了让Toolbar中的Title可以变化相应的Tab的标题）
//        viewpager.addOnPageChangeListener(this);

        tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        // 将TabLayout和ViewPager进行关联，让两者联动起来
        tableLayout.setupWithViewPager(viewpager);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        setTitle("麻豆圈");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
