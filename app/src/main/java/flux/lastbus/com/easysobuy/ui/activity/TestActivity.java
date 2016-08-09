package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import butterknife.BindView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import flux.lastbus.com.easysobuy.ui.fragment.TestFragment;

/**
 * Created by yuhang on 16-8-3.
 */
public class TestActivity extends BaseActivity {


    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    @Override
    public int onContentView() {
        return R.layout.activity_test;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, TestActivity.class);
        context.startActivity(starter);
    }

    public void initFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout, TestFragment.newInstance());
        transaction.commit();

    }
}
