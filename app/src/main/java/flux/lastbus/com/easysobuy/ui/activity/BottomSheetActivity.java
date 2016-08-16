package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewParent;

import flux.lastbus.com.easysobuy.R;

/**
 * Created by yuhang on 16-8-15.
 */
public class BottomSheetActivity extends AppCompatActivity {
    BottomSheetBehavior behavior;


    public static void start(Context context) {
        Intent starter = new Intent(context, BottomSheetActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        View bottom = findViewById(R.id.bottom_sheet);
        behavior  = BottomSheetBehavior.from(bottom);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            public boolean hasRequest;
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                //这里是bottomSheet 状态的改变，根据slideOffset可以做一些动画
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

        View bg = findViewById(R.id.bottomSheetBg);
        bg.setOnClickListener(v -> {
            showBottom();
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            finish();
        }
    }

    public void showBottom(){
        if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            finish();
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
}
