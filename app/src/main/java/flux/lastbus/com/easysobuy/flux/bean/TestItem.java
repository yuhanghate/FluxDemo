package flux.lastbus.com.easysobuy.flux.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 测试Item
 * Created by yuhang on 16-8-3.
 */
public class TestItem implements Parcelable {
    private static final String TAG = "TestItem";
    private int image;
    private String name;
    private int bg;

    public TestItem(int image) {
        this.image = image;

    }

    public TestItem() {
    }
    public int getImage() {
        return image;
    }

    public TestItem setImage(int image) {
        this.image = image;
        return this;
    }

    public String getName() {
        return name;
    }

    public TestItem setName(String name) {
        this.name = name;
        return this;
    }

    public int getBg() {
        return bg;
    }

    public TestItem setBg(int bg) {
        this.bg = bg;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.image);
        dest.writeString(this.name);
        dest.writeInt(this.bg);
    }

    protected TestItem(Parcel in) {
        this.image = in.readInt();
        this.name = in.readString();
        this.bg = in.readInt();
    }

    public static final Parcelable.Creator<TestItem> CREATOR = new Parcelable.Creator<TestItem>() {
        @Override
        public TestItem createFromParcel(Parcel source) {
            return new TestItem(source);
        }

        @Override
        public TestItem[] newArray(int size) {
            return new TestItem[size];
        }
    };

    @Override
    public String toString() {
        return "TestItem{" +
                "image=" + image +
                ", name='" + name + '\'' +
                ", bg=" + bg +
                '}';
    }
}
