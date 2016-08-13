package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;

/**
 * Created by yuhang on 16-8-12.
 */
public class SettingsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbarView;

    @Override
    public int onContentView() {
        return R.layout.activity_settings;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SettingsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbarView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @OnClick(R.id.about)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.about:
                AboutActivity.start(this);
                break;
        }
    }

}
