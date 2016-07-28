package flux.lastbus.com.easysobuy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import flux.lastbus.com.easysobuy.ui.activity.LoginActivity;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.loginButton)
    AppCompatButton loginButton;

    Unbinder mUnbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnbinder= ButterKnife.bind(this);
    }



    @OnClick(R.id.loginButton)
    public void onClick() {
        LoginActivity.start(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
