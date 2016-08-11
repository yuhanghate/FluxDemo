package flux.lastbus.com.easysobuy.ui.fragment;

import android.os.Bundle;

import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;

/**
 * Created by yuhang on 16-8-11.
 */
public class RegisterEmailFragment extends BaseFragment {
    @Override
    public int onContentView() {
        return R.layout.activity_register_email;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

    public static RegisterEmailFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RegisterEmailFragment fragment = new RegisterEmailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onInitFragment() {

    }
}
