package flux.lastbus.com.easysobuy.flux.store.event;

import android.os.Parcel;

import flux.lastbus.com.easysobuy.flux.bean.UserView;

/**
 * 登陆状态改变事件
 * Created by yuhang on 16-7-27.
 */
public class LoginEvent implements ChangeEvent{
    private UserView mUserView;

    public UserView getmUserView() {
        return mUserView;
    }

    public void setmUserView(UserView mUserView) {
        this.mUserView = mUserView;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mUserView, flags);
    }

    public LoginEvent() {
    }

    protected LoginEvent(Parcel in) {
        this.mUserView = in.readParcelable(UserView.class.getClassLoader());
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
