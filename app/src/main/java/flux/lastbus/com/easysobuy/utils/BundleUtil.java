package flux.lastbus.com.easysobuy.utils;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;

import flux.lastbus.com.easysobuy.constant.BundleConstant;

/**
 * author lsxiao
 * date 2016-05-09 21:30
 */
public class BundleUtil {
    private BundleUtil() {
    }

    public static Bundle newInstance() {
        return new Bundle();
    }

    public static Bundle withThrowable(Throwable throwable) {
        Bundle bundle = newInstance();
        bundle.putSerializable(BundleConstant.THROWABLE, throwable);
        return bundle;
    }

    public static Throwable getThrowable(Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            throw new IllegalArgumentException("the bundle is null or empty");
        }
        return (Throwable) bundle.getSerializable(BundleConstant.THROWABLE);
    }

    public static <T extends Parcelable> Bundle createBundle(T data, String action){
        Bundle bundle = BundleUtil.newInstance();
        bundle.putParcelable(action, data);
        return bundle;
    }

    public static <T extends Parcelable> Bundle createBundleList(ArrayList<T> list, String action){
        Bundle bundle = BundleUtil.newInstance();
        bundle.putParcelableArrayList(action, list);
        return bundle;
    }
}
