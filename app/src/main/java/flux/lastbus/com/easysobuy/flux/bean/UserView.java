package flux.lastbus.com.easysobuy.flux.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yuhang on 16-7-27.
 */
public class UserView implements Parcelable {
    private String name;
    private String key;
    private String uid;

    public String getName() {
        return name;
    }

    public UserView setName(String name) {
        this.name = name;
        return this;
    }

    public String getKey() {
        return key;
    }

    public UserView setKey(String key) {
        this.key = key;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public UserView setUid(String uid) {
        this.uid = uid;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.key);
        dest.writeString(this.uid);
    }

    public UserView() {
    }

    protected UserView(Parcel in) {
        this.name = in.readString();
        this.key = in.readString();
        this.uid = in.readString();
    }

    public static final Parcelable.Creator<UserView> CREATOR = new Parcelable.Creator<UserView>() {
        @Override
        public UserView createFromParcel(Parcel source) {
            return new UserView(source);
        }

        @Override
        public UserView[] newArray(int size) {
            return new UserView[size];
        }
    };
}
