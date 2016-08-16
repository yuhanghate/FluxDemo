package flux.lastbus.com.easysobuy;

import android.support.v7.widget.AppCompatButton;

import butterknife.BindView;
import butterknife.OnClick;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import flux.lastbus.com.easysobuy.ui.activity.BaseActivity;
import flux.lastbus.com.easysobuy.ui.activity.LoginActivity;

public class MainActivity extends BaseActivity {


    @BindView(R.id.loginButton)
    AppCompatButton loginButton;


    @OnClick(R.id.loginButton)
    public void onClick() {
        LoginActivity.start(this);
    }


    @Override
    public int onContentView() {
        return R.layout.activity_main;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }
}
