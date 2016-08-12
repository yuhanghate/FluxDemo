package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;

/**
 * Created by yuhang on 16-8-10.
 */
public class GuidePageActivity extends BaseActivity{
    @BindView(R.id.guideIcon)
    ImageView guideIcon;
    @BindView(R.id.guideTitle)
    TextView guideTitle;
    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.registerButton)
    Button registerButton;
    @BindView(R.id.toolbarView)
    Toolbar toolbarView;

    @Override
    public int onContentView() {
        return R.layout.activity_guide_page;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

    /**
     * Start Activity
     *
     * @param context
     */
    public static void start(Context context) {
        Intent starter = new Intent(context, GuidePageActivity.class);
        context.startActivity(starter);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbarView);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarView.setNavigationOnClickListener(v -> finish());
        setTitle("");
    }

    @OnClick({R.id.loginButton, R.id.registerButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginButton:
                LoginActivity.start(this);
                break;
            case R.id.registerButton:
                RegisterActivity.start(this);
                break;

        }
    }
}
