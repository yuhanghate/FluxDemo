package flux.lastbus.com.easysobuy.flux.action;

import android.os.Bundle;
import android.os.Parcel;

/**
 * Created by yuhang on 16-8-3.
 */
public class TestAction extends BaseAction {
    public static final String ACTION_TEST_SHOW = "action_test_show";
    public static final String ACTION_TEST_ERROR = "action_test_error";
    public static final String ACTION_TEST_EMPTY = "action_test_empty";
    public static final String ACTION_TEST_PROGRESSBAR = "action_test_progressbar";
    public static final String ACTION_TEST_ERROR_BUTTON_CLICK = "action_test_error_button_click";

    private int count;
    public TestAction(String type, Bundle data) {
        super(type, data);
    }

    public TestAction(String type, Bundle data, int count) {
        super(type, data);
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public TestAction setCount(int count) {
        this.count = count;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.count);
    }

    protected TestAction(Parcel in) {
        super(in);
        this.count = in.readInt();
    }

    public static final Creator<TestAction> CREATOR = new Creator<TestAction>() {
        @Override
        public TestAction createFromParcel(Parcel source) {
            return new TestAction(source);
        }

        @Override
        public TestAction[] newArray(int size) {
            return new TestAction[size];
        }
    };
}
