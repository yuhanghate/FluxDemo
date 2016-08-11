package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import flux.lastbus.com.easysobuy.ui.adapter.RegisterViewPagerAdapter;
import flux.lastbus.com.easysobuy.ui.fragment.BaseFragment;
import flux.lastbus.com.easysobuy.ui.fragment.RegisterEmailFragment;
import flux.lastbus.com.easysobuy.ui.fragment.RegisterPhoneFragment;

import static android.support.design.widget.TabLayout.MODE_SCROLLABLE;

public class RegisterActivity extends BaseActivity implements ViewPager.OnPageChangeListener {


    RegisterViewPagerAdapter mRegisterViewPagerAdapter;
    List<BaseFragment> mFragments;
    String[] mTitls = {"Tel Register", "Email Register"};


    @BindView(R.id.toolbarHeadView)
    ImageView toolbarHeadView;
    @BindView(R.id.toolbarView)
    Toolbar toolbarView;
    @BindView(R.id.tableLayout)
    TabLayout tableLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    public int onContentView() {
        return R.layout.activity_register;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

    /**
     * Start Activity
     *
     * @param context
     */
    public static void start(Context context) {
        Intent starter = new Intent(context, RegisterActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    public void initView() {
        toolbarView.setTitle("Tel & Email");
        setSupportActionBar(toolbarView);
        toolbarView.setNavigationOnClickListener(v -> finish());

        mRegisterViewPagerAdapter = new RegisterViewPagerAdapter(getSupportFragmentManager(),mFragments,mTitls);
        viewPager.setAdapter(mRegisterViewPagerAdapter);
        // 设置ViewPager最大缓存的页面个数
        viewPager.setOffscreenPageLimit(5);
        // 给ViewPager添加页面动态监听器（为了让Toolbar中的Title可以变化相应的Tab的标题）
        viewPager.addOnPageChangeListener(this);

        tableLayout.setTabMode(MODE_SCROLLABLE);
        // 将TabLayout和ViewPager进行关联，让两者联动起来
        tableLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

    public void initData() {
        mTitls = getResources().getStringArray(R.array.home_tab_titles);
        mFragments = new ArrayList<>();

        mFragments.add(RegisterPhoneFragment.newInstance());
        mFragments.add(RegisterEmailFragment.newInstance());
    }


    public String getEditText(EditText et) {
        return et.getText().toString();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        toolbarView.setTitle(mTitls[position]);
        setTitle(mTitls[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
