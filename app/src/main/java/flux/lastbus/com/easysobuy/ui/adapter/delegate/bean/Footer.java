package flux.lastbus.com.easysobuy.ui.adapter.delegate.bean;

import android.os.Parcel;

import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;

/**
 * Created by yuhang on 16-8-3.
 */
public class Footer implements BaseAdapterData {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public Footer() {
    }

    protected Footer(Parcel in) {
    }

    public static final Creator<Footer> CREATOR = new Creator<Footer>() {
        @Override
        public Footer createFromParcel(Parcel source) {
            return new Footer(source);
        }

        @Override
        public Footer[] newArray(int size) {
            return new Footer[size];
        }
    };
}
