package flux.lastbus.com.easysobuy.ui.adapter.delegate.bean;

import android.os.Parcel;
import android.os.Parcelable;

import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;

/**
 * Created by yuhang on 16-8-3.
 */
public class Geko implements BaseAdapterData, Parcelable {
    private String name;
    private String name1;

    public Geko(String name) {
        this.name = name;
    }

    public Geko(String name, String name1) {
        this.name = name;
        this.name1 = name1;
    }

    public String getName1() {
        return name1;
    }

    public Geko setName1(String name1) {
        this.name1 = name1;
        return this;
    }

    public String getName() {
        return name;
    }

    public Geko setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    protected Geko(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Geko> CREATOR = new Parcelable.Creator<Geko>() {
        @Override
        public Geko createFromParcel(Parcel source) {
            return new Geko(source);
        }

        @Override
        public Geko[] newArray(int size) {
            return new Geko[size];
        }
    };
}
