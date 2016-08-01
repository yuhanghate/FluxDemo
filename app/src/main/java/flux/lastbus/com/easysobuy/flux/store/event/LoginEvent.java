package flux.lastbus.com.easysobuy.flux.store.event;

import android.os.Parcel;

/**
 * 登陆状态改变事件
 * Created by yuhang on 16-7-27.
 */
public class LoginEvent implements ChangeEvent{
    private String type;

    public String getType() {
        return type;
    }

    public LoginEvent setType(String type) {
        this.type = type;
        return this;
    }

    public LoginEvent() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
    }

    protected LoginEvent(Parcel in) {
        this.type = in.readString();
    }

    public static final Creator<LoginEvent> CREATOR = new Creator<LoginEvent>() {
        @Override
        public LoginEvent createFromParcel(Parcel source) {
            return new LoginEvent(source);
        }

        @Override
        public LoginEvent[] newArray(int size) {
            return new LoginEvent[size];
        }
    };
}
