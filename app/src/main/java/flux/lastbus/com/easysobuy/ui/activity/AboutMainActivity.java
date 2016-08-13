package flux.lastbus.com.easysobuy.ui.activity;

import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;

/**
 * Created by yuhang on 16-8-13.
 */
public class AboutMainActivity extends BaseActivity {
    @Override
    public int onContentView() {
        return R.layout.fragment_about_main;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }
}
