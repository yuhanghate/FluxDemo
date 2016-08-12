package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;

/**
 * Created by yuhang on 16-8-12.
 */
public class Text2Activity extends BaseActivity {
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
        Intent starter = new Intent(context, Text2Activity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbarView);
        setTitle("Settings");
    }
}
