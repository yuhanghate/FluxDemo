package flux.lastbus.com.easysobuy.ui.adapter.delegate.bean;

import android.os.Parcel;
import android.os.Parcelable;

import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;

/**
 * Created by yuhang on 16-8-3.
 */
public class Cat implements BaseAdapterData{
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Cat setName(String name) {
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

    protected Cat(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Cat> CREATOR = new Parcelable.Creator<Cat>() {
        @Override
        public Cat createFromParcel(Parcel source) {
            return new Cat(source);
        }

        @Override
        public Cat[] newArray(int size) {
            return new Cat[size];
        }
    };
}
