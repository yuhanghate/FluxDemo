package flux.lastbus.com.easysobuy.ui.adapter.delegate.bean;

import android.os.Parcel;
import android.os.Parcelable;

import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;

/**
 * Created by yuhang on 16-8-3.
 */
public class Dog implements BaseAdapterData, Parcelable {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Dog setName(String name) {
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

    protected Dog(Parcel in) {
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Dog> CREATOR = new Parcelable.Creator<Dog>() {
        @Override
        public Dog createFromParcel(Parcel source) {
            return new Dog(source);
        }

        @Override
        public Dog[] newArray(int size) {
            return new Dog[size];
        }
    };
}
