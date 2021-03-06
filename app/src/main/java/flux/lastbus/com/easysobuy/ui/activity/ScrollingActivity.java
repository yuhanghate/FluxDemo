package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewParent;

import flux.lastbus.com.easysobuy.R;

public class ScrollingActivity extends AppCompatActivity {

    View sheetButton;
    View bottomSheetBg;
    BottomSheetBehavior behavior;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(v -> finish());
        setTitle("资讯详情");

        sheetButton = findViewById(R.id.bottom_sheet);
        bottomSheetBg = findViewById(R.id.bottomSheetBg);
        behavior = BottomSheetBehavior.from(sheetButton);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            public boolean hasRequest;
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                //这里是bottomSheet 状态的改变，根据slideOffset可以做一些动画
                if(newState == BottomSheetBehavior.STATE_COLLAPSED){
                    bottomSheetBg.setVisibility(View.GONE);
                    fab.show();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //这里是拖拽中的回调，根据slideOffset可以做一些动画
                if (!hasRequest && behavior.getPeekHeight() == 0 && slideOffset > 0) {
                    hasRequest = true;
                    updateOffsets(bottomSheet);

                }

            }
        });



        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
//            BottomSheetActivity.start(ScrollingActivity.this);

            showBottom();
        });
        bottomSheetBg.setOnClickListener(v -> showBottom());

    }

    public void showBottom(){
        if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            fab.setVisibility(View.GONE);
            bottomSheetBg.setVisibility(View.VISIBLE);
        } else {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            bottomSheetBg.setVisibility(View.GONE);
            fab.show();
        }
    }

    private void updateOffsets(View view) {

        // Manually invalidate the view and parent to make sure we get drawn pre-M
        if (Build.VERSION.SDK_INT < 23) {
            tickleInvalidationFlag(view);
            final ViewParent vp = view.getParent();
            if (vp instanceof View) {
                tickleInvalidationFlag((View) vp);
            }
        }
    }

    private static void tickleInvalidationFlag(View view) {
        final float y = ViewCompat.getTranslationY(view);
        ViewCompat.setTranslationY(view, y + 1);
        ViewCompat.setTranslationY(view, y);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ScrollingActivity.class);
        context.startActivity(starter);
    }
}
