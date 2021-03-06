package flux.lastbus.com.easysobuy.flux.store;

import flux.lastbus.com.easysobuy.bus.RxBus;
import flux.lastbus.com.easysobuy.flux.action.BaseAction;
import flux.lastbus.com.easysobuy.flux.action.UserAction;
import flux.lastbus.com.easysobuy.flux.bean.UserView;
import flux.lastbus.com.easysobuy.flux.store.event.ChangeEvent;
import flux.lastbus.com.easysobuy.flux.store.event.LoginEvent;

/**
 * 登陆界面逻辑层及数据存储
 * Created by yuhang on 16-7-27.
 */
public class LoginStore extends BaseStore {
    private LoginEvent mLoginEvent;

    private UserView mUserView;
    private boolean isLoginStatus;
    private boolean isProgressLoading;


    public LoginStore() {
        mLoginEvent = new LoginEvent();
    }

    /**
     * 通知View层事件对象
     * @return
     */
    @Override
    protected ChangeEvent getChangeEvent() {
        return mLoginEvent;
    }



    @Override
    public void onAction(BaseAction action) {
        switch (action.getType()){
            case UserAction.ACTION_LOGIN_SUCCESSED:
                onLoginSuccessEvent((UserAction) action);
                break;
            case UserAction.ACTION_LOGIN_FAILED:
                onLoginFaildEvent();
                break;
            case UserAction.ACTION_LOADING:
                isProgressLoading = true;
                break;
            case UserAction.ACTION_LOADED:
                isProgressLoading = false;
                break;
            default:
                break;
        }

        mLoginEvent.setType(action.getType());
        RxBus.instance().send(getChangeEvent());
    }

    /**
     * 登陆成功事件通知
     * @param loginAction
     */
    public void onLoginSuccessEvent(UserAction loginAction){
        isLoginStatus = true;
        mUserView = getParcelable(loginAction);
    }

    /**
     * 登陆失败事件通知
     */
    public void onLoginFaildEvent(){
        isLoginStatus = false;
    }

    /************************************* 提供接口 *****************************************/


    /**
     * 提供登陆帐号信息
     * @return
     */
    public UserView getmUserView(){
        return mUserView;
    }

    /**
     * 登陆状态
     * @return
     */
    public boolean isLoginStatus(){
        return isLoginStatus;
    }

    /**
     * 等待条
     * @return
     */
    public boolean isProgressLoading(){return isProgressLoading;}

}
