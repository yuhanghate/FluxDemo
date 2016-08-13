package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import flux.lastbus.com.easysobuy.ui.adapter.AboutAdapter;
import flux.lastbus.com.easysobuy.ui.fragment.AboutFragment;

/**
 * Created by yuhang on 16-8-13.
 */
public class AboutActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.tableLayout)
    TabLayout tableLayout;


    @Override
    public int onContentView() {
        return R.layout.activity_abount;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AboutActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initToolbar();

        initviewPager();

        initCollapsingToolbarLayout();
    }

    private void initCollapsingToolbarLayout() {
        collapsingToolbarLayout.setTitleEnabled(false);
    }

    private void initviewPager() {
        List<Fragment> list = new ArrayList<>();
        list.add(AboutFragment.newInstance());
        list.add(AboutFragment.newInstance());
        list.add(AboutFragment.newInstance());
        AboutAdapter adapter = new AboutAdapter(getSupportFragmentManager(), list,this);
        viewpager.setAdapter(adapter);

        tableLayout.setupWithViewPager(viewpager);
        for (int i = 0; i < tableLayout.getTabCount(); i++) {
            TabLayout.Tab tabAt = tableLayout.getTabAt(i);
            tabAt.setCustomView(adapter.getTabView(i));
        }
    }

    /**
     * 初始化Toolbar
     */
    private void initToolbar() {
        setSupportActionBar(toolbar);
        setTitle("About");
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
}
