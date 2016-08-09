package flux.lastbus.com.easysobuy.ui.adapter.delegate.bean;

import android.os.Parcel;
import android.os.Parcelable;

import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;

/**
 * Created by yuhang on 16-8-3.
 */
public class Empty implements BaseAdapterData, Parcelable{
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
