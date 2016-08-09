package flux.lastbus.com.easysobuy.ui.adapter.delegate.bean;

import android.os.Parcel;

import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;

/**
 * Created by yuhang on 16-8-3.
 */
public class Header implements BaseAdapterData {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public Header() {
    }

    protected Header(Parcel in) {
    }

    public static final Creator<Header> CREATOR = new Creator<Header>() {
        @Override
        public Header createFromParcel(Parcel source) {
            return new Header(source);
        }

        @Override
        public Header[] newArray(int size) {
            return new Header[size];
        }
    };
}
