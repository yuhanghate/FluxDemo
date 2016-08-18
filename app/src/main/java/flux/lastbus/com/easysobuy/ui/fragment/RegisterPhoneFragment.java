package flux.lastbus.com.easysobuy.ui.fragment;

import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import flux.lastbus.com.easysobuy.ui.activity.MobileCodeActivity;

/**
 * Created by yuhang on 16-8-11.
 */
public class RegisterPhoneFragment extends BaseFragment {
    @BindView(R.id.registerButton)
    Button registerButton;

    @Override
    public int onContentView() {
        return R.layout.activity_register_phone;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

    public static RegisterPhoneFragment newInstance() {

        Bundle args = new Bundle();

        RegisterPhoneFragment fragment = new RegisterPhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onInitFragment() {

    }


    @OnClick(R.id.registerButton)
    public void onClick() {
        MobileCodeActivity.start(getActivity());
    }
}
