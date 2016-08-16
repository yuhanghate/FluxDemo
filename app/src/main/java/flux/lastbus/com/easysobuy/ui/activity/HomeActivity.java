package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import flux.lastbus.com.easysobuy.ui.adapter.HomeViewPagerAdapter;
import flux.lastbus.com.easysobuy.ui.fragment.BaseFragment;
import flux.lastbus.com.easysobuy.ui.fragment.GoodsListFragment;
import flux.lastbus.com.easysobuy.ui.fragment.HomeFragment;

/**
 * Created by yuhang on 16-8-9.
 */
public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener, ViewPager.OnPageChangeListener, MaterialSearchView.OnQueryTextListener,
        MaterialSearchView.SearchViewListener {

    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.homeLayoutView)
    DrawerLayout homeLayoutView;
    @BindView(R.id.toolbarView)
    Toolbar toolbarView;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    HomeViewPagerAdapter mHomeViewPagerAdapter;
    ActionBarDrawerToggle drawerToggle;
    View headerView;

    List<BaseFragment> mFragments;
    String[] mTitles;
    @BindView(R.id.tableLayout)
    TabLayout tableLayout;
//    @BindView(R.id.search_view)
//    MaterialSearchView searchView;


    @Override
    public int onContentView() {
        return R.layout.activity_home;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }


    private void initView() {
        setSupportActionBar(toolbarView);

        //获取抽屉头部View
        headerView = navigationView.getHeaderView(0);

        //设置头像点击事件
        headerView.findViewById(R.id.profile_image).setOnClickListener(this);

        //初始化左上角抽屉按钮
        drawerToggle = new ActionBarDrawerToggle(this, homeLayoutView, toolbarView, R.string.drawer_open, R.string.drawer_close);
        homeLayoutView.addDrawerListener(drawerToggle);

        //设置抽屉Item选择事件
        navigationView.setNavigationItemSelectedListener(this);

        //浮动按钮点击
        floatingActionButton.setOnClickListener(this);

        //初始化ViewPager的适配器，并设置
        mHomeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        viewpager.setAdapter(mHomeViewPagerAdapter);
        // 设置ViewPager最大缓存的页面个数
        viewpager.setOffscreenPageLimit(5);
        // 给ViewPager添加页面动态监听器（为了让Toolbar中的Title可以变化相应的Tab的标题）
        viewpager.addOnPageChangeListener(this);

        tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        // 将TabLayout和ViewPager进行关联，让两者联动起来
        tableLayout.setupWithViewPager(viewpager);
        // 设置Tablayout的Tab显示ViewPager的适配器中的getPageTitle函数获取到的标题
//        tableLayout.setTabsFromPagerAdapter(mHomeViewPagerAdapter);


//        //搜索栏面设置
//        searchView.setVoiceSearch(false);
//        searchView.setOnQueryTextListener(this);
//        searchView.setOnSearchViewListener(this);

    }

    private void initData() {
        mTitles = getResources().getStringArray(R.array.home_tab_titles);

        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            if(i == 1){
                mFragments.add(GoodsListFragment.newInstance());
            }else{
                Bundle mBundle = new Bundle();
                mBundle.putInt("flag", i);
                HomeFragment fragment = HomeFragment.newInstance();
                mFragments.add(i, fragment);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
//                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.category:
                ClassifyActivity.start(this);
                msg = "打开分类";
                break;
            case R.id.customService:
                msg = "打开客服";
                break;
            case R.id.active:
                msg = "打开活动中心";
                break;
            case R.id.collection:
                msg = "商品收藏";
                break;
            case R.id.footprint:
                msg = "我的足迹";
                ScrollingActivity.start(this);
                break;
            case R.id.settings:
                msg = "设置";
                SettingsActivity.start(this);
                break;
        }

        Snackbar.make(homeLayoutView, msg, Snackbar.LENGTH_LONG).show();
        //设置为选中状态
        item.setCheckable(true);

        //关闭抽屉
        homeLayoutView.closeDrawers();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_main, menu);
//        MenuItem item = menu.findItem(R.id.search);
//        searchView.setMenuItem(item);


        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        SearchView.SearchAutoComplete textView = ( SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        textView.setHintTextColor(getResources().getColor(android.R.color.white));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.collection:
                msg = "商品收藏";
                break;
            case R.id.footprint:
                msg = "我的足迹";
                break;
            case R.id.settings:
                msg = "设置";
                break;
        }
        Snackbar.make(toolbarView, msg, Snackbar.LENGTH_LONG).show();
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        drawerToggle.syncState();
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_image:
                //关闭抽屉
                homeLayoutView.closeDrawers();
                GuidePageActivity.start(this);
                break;
            case R.id.floatingActionButton:
                Snackbar.make(toolbarView, "FloatingButton", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.search:
                onSearchViewShown();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        toolbarView.setTitle(mTitles[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
       /* floatingActionButton.hide();
        if(newText != null && newText.trim().length()>0){
            tableLayout.setVisibility(View.GONE);
        }*/
//        onSearchViewShown();
//        onSearchViewShown();
//        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        return false;
    }

    @Override
    public void onSearchViewShown() {
        floatingActionButton.hide();
        tableLayout.setVisibility(View.GONE);

    }

    @Override
    public void onSearchViewClosed() {
        floatingActionButton.show();
        tableLayout.setVisibility(View.VISIBLE);

    }
}
