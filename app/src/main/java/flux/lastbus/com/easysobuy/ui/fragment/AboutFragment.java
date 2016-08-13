package flux.lastbus.com.easysobuy.ui.fragment;

import android.os.Bundle;

import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;

/**
 * Created by yuhang on 16-8-13.
 */
public class AboutFragment extends BaseFragment {
    @Override
    public int onContentView() {
        return R.layout.fragment_about_main;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

    public static AboutFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onInitFragment() {

    }
}
