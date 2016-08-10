package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;

public class RegisterActivity extends BaseActivity {


    @BindView(R.id.toolbarView)
    Toolbar toolbarView;
    @BindView(R.id.usernameET)
    EditText usernameET;
    @BindView(R.id.passwordET)
    EditText passwordET;
    @BindView(R.id.passwordAgainET)
    EditText passwordAgainET;
    @BindView(R.id.registerButton)
    Button registerButton;

    @Override
    public int onContentView() {
        return R.layout.activity_register;
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
        Intent starter = new Intent(context, RegisterActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView() {
        setSupportActionBar(toolbarView);
        toolbarView.setNavigationOnClickListener(v -> finish());
    }

    @OnClick(R.id.registerButton)
    public void onClick() {
        if(TextUtils.isEmpty(getEditText(usernameET))){
            usernameET.setError("请输入正确用户名!");
        }else if(TextUtils.isEmpty(getEditText(passwordET))){
            passwordET.setError("请输入正确密码！");
        }else if(TextUtils.isEmpty(getEditText(passwordAgainET))){
            passwordAgainET.setError("两次密码不一致！");
        }else{
            Snackbar.make(usernameET,"登陆成功",Snackbar.LENGTH_LONG).show();
        }
    }

    public String getEditText(EditText et){
        return et.getText().toString();
    }
}
