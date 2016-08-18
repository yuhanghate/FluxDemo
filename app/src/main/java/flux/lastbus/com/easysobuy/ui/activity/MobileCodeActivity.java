package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;

/**
 * Created by yuhang on 16-8-17.
 */
public class MobileCodeActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.codeView1)
    EditText codeView1;
    @BindView(R.id.codeView2)
    EditText codeView2;
    @BindView(R.id.codeView3)
    EditText codeView3;
    @BindView(R.id.codeView4)
    EditText codeView4;

    @Override
    public int onContentView() {
        return R.layout.activity_mobile_code;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MobileCodeActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
        initView();
    }

    private void initView() {
        button.setClickable(false);
        codeView1.setFocusable(true);
        codeView1.addTextChangedListener(new mTextWatcher1());
        codeView2.addTextChangedListener(new mTextWatcher2() );
        codeView3.addTextChangedListener(new mTextWatcher3() );
        codeView4.addTextChangedListener(new mTextWatcher4() );

    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_mobile_code, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.code:
                Snackbar.make(toolbar, "完成", Snackbar.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class mTextWatcher1 implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s.length() >= 1){
                if(codeView1.isFocusable()){
                    codeView1.clearFocus();
                    codeView1.setFocusable(false);
                    codeView2.setFocusable(true);
                    codeView2.setFocusableInTouchMode(true);
                    codeView2.requestFocus();

                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
    class mTextWatcher2 implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s.length() >= 1){
                if(codeView2.isFocusable()){
                    codeView2.clearFocus();
                    codeView2.setFocusable(false);
                    codeView3.setFocusable(true);
                    codeView3.setFocusableInTouchMode(true);
                    codeView3.requestFocus();
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
    class mTextWatcher3 implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s.length() >= 1){
                if(codeView3.isFocusable()){
                    codeView3.clearFocus();
                    codeView3.setFocusable(false);
                    codeView4.setFocusable(true);
                    codeView4.setFocusableInTouchMode(true);
                    codeView4.requestFocus();
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
    class mTextWatcher4 implements TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s.length() >= 1){
                if(codeView4.isFocusable()){
                    codeView4.clearFocus();
                    codeView4.setFocusable(false);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(button.getWindowToken(), 0); //强制隐藏键盘
                    button.setClickable(true);
//                    button.setFocusable(false);
//                    button.setFocusableInTouchMode(true);
//                    button.requestFocus();
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
