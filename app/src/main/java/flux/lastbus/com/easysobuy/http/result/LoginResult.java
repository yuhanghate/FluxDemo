package flux.lastbus.com.easysobuy.http.result;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 登陆
 * Created by yuhang on 16-6-12.
 */
public class LoginResult implements Parcelable {
    /**
     * code : 200
     * datas : {"username":"test123","userid":"4","key":"4c47111f3236691b0d74d38b31717d61"}
     */

    private int code;
    /**
     * username : test123
     * userid : 4
     * key : 4c47111f3236691b0d74d38b31717d61
     */

    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean implements Parcelable {
        private String username;
        private String userid;
        private String key;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.username);
            dest.writeString(this.userid);
            dest.writeString(this.key);
        }

        public DatasBean() {
        }

        protected DatasBean(Parcel in) {
            this.username = in.readString();
            this.userid = in.readString();
            this.key = in.readString();
        }

        public static final Creator<DatasBean> CREATOR = new Creator<DatasBean>() {
            @Override
            public DatasBean createFromParcel(Parcel source) {
                return new DatasBean(source);
            }

            @Override
            public DatasBean[] newArray(int size) {
                return new DatasBean[size];
            }
        };

        @Override
        public String toString() {
            return "DatasBean{" +
                    "username='" + username + '\'' +
                    ", userid='" + userid + '\'' +
                    ", key='" + key + '\'' +
                    '}';
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeParcelable(this.datas, flags);
    }

    public LoginResult() {
    }

    protected LoginResult(Parcel in) {
        this.code = in.readInt();
        this.datas = in.readParcelable(DatasBean.class.getClassLoader());
    }

    public static final Creator<LoginResult> CREATOR = new Creator<LoginResult>() {
        @Override
        public LoginResult createFromParcel(Parcel source) {
            return new LoginResult(source);
        }

        @Override
        public LoginResult[] newArray(int size) {
            return new LoginResult[size];
        }
    };

    @Override
    public String toString() {
        return "LoginResult{" +
                "code=" + code +
                ", datas=" + datas +
                '}';
    }
}
