package flux.lastbus.com.easysobuy.flux.action;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Action 基类,所有action都需要继承此类
 * author yuhang
 * date 2016-05-09 17:29
 */
public class BaseAction implements Parcelable {
    private final String type;
    private final Bundle data;

    public BaseAction(String type, Bundle data) {
        this.type = type;
        this.data = data;
    }

    public BaseAction(String type) {
        this(type, null);
    }

    public String getType() {
        return type;
    }

    public Bundle getData() {
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeBundle(this.data);
    }

    protected BaseAction(Parcel in) {
        this.type = in.readString();
        this.data = in.readBundle();
    }

    public static final Creator<BaseAction> CREATOR = new Creator<BaseAction>() {
        @Override
        public BaseAction createFromParcel(Parcel source) {
            return new BaseAction(source);
        }

        @Override
        public BaseAction[] newArray(int size) {
            return new BaseAction[size];
        }
    };
}
