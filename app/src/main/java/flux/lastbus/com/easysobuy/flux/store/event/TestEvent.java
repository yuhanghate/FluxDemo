package flux.lastbus.com.easysobuy.flux.store.event;

import android.os.Parcel;

/**
 * Created by yuhang on 16-8-3.
 */
public class TestEvent implements ChangeEvent {
    private String type;

    public String getType() {
        return type;
    }

    public TestEvent setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
    }

    public TestEvent() {
    }

    protected TestEvent(Parcel in) {
        this.type = in.readString();
    }

    public static final Creator<TestEvent> CREATOR = new Creator<TestEvent>() {
        @Override
        public TestEvent createFromParcel(Parcel source) {
            return new TestEvent(source);
        }

        @Override
        public TestEvent[] newArray(int size) {
            return new TestEvent[size];
        }
    };
}
