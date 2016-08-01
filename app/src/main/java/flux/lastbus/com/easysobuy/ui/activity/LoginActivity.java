package flux.lastbus.com.easysobuy.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.action.UserAction;
import flux.lastbus.com.easysobuy.flux.creator.UserActionCreator;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import flux.lastbus.com.easysobuy.flux.store.LoginStore;
import flux.lastbus.com.easysobuy.flux.store.event.LoginEvent;

/**
 * 登陆界面
 */
public class LoginActivity extends BaseActivity {

    @Inject
    UserActionCreator mUserActionCreator;
/*    @Inject
    @UserName
    String username;
    @Inject
    @UserID
    String uid;
    @Inject
    @UserKey
    String key;*/


    @BindView(R.id.login_progress)
    ProgressBar mProgressView;
    @BindView(R.id.email)
    AutoCompleteTextView mEmailView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.login_form)
    ScrollView mLoginFormView;
    @BindView(R.id.email_sign_in_button)
    Button emailSignInButton;
    @BindView(R.id.email_login_form)
    LinearLayout emailLoginForm;
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    /**
     * Start Login Activity
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int onContentView() {
        return R.layout.activity_login;
    }

    @Override
    public BaseStore onCreateStore() {
        return new LoginStore();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up the login form.
        getActivityComponent().inject(this);
        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == R.id.login || id == EditorInfo.IME_NULL) {
                return true;
            }
            return false;
        });
    }


    @Override
    public void onStoreChangeEvent() {
        super.onStoreChangeEvent();
        registerEvent(LoginEvent.class)
                .subscribe(login -> {
                    switch (login.getType()) {
                        case UserAction.ACTION_LOGIN_SUCCESSED:
                        case UserAction.ACTION_LOGIN_FAILED:
                            refershSanbar();
                            break;
                        case UserAction.ACTION_LOADED:
                            showProgress(false);
                            break;
                        case UserAction.ACTION_LOADING:
                            showProgress(true);
                            break;
                    }
                });
    }

    /**
     * 登陆状态返回
     */
    private void refershSanbar() {
        LoginStore store = getStore();
        String msg;
        if (store.isLoginStatus()) {
//            getApp().getUserComponent(store.getmUserView());
            msg = "登陆成功";
        } else {
            msg = "登陆失败";
        }
        Snackbar.make(mLoginFormView, msg, Snackbar.LENGTH_LONG).show();
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @OnClick(R.id.email_sign_in_button)
    public void onClick() {
        String name = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        mUserActionCreator.login(name, password);
    }

}

