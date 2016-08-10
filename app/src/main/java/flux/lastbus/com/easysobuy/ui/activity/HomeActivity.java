package flux.lastbus.com.easysobuy.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;

/**
 * Created by yuhang on 16-8-9.
 */
public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,
View.OnClickListener{
    @BindView(R.id.toolbarView)
    Toolbar toolbarView;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.homeLayoutView)
    DrawerLayout homeLayoutView;

    ActionBarDrawerToggle drawerToggle;
    View headerView;

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
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbarView);

        int headerCount = navigationView.getHeaderCount();
        headerView = navigationView.getHeaderView(0);

        //设置头像点击事件
        headerView.findViewById(R.id.profile_image).setOnClickListener(this);

        drawerToggle = new ActionBarDrawerToggle(this, homeLayoutView, toolbarView, R.string.drawer_open, R.string.drawer_close);
        homeLayoutView.addDrawerListener(drawerToggle);

        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.home:
                msg = "打开主页";
                break;
            case R.id.message:
                msg = "打开消息";
                break;
            case R.id.friends:
                msg = "打开朋友";
                break;
            case R.id.dashboard:
                msg = "打开仪表板";
                break;
            case R.id.forum:
                msg = "打开论坛";
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
        getMenuInflater().inflate(R.menu.home_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    public void onClick(View view){
        switch (view.getId()){
            case R.id.profile_image:
                //关闭抽屉
                homeLayoutView.closeDrawers();
                GuidePageActivity.start(this);
                break;
        }
    }
}
