package flux.lastbus.com.easysobuy.flux.store;

import android.os.Parcelable;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

import flux.lastbus.com.easysobuy.bus.RxBus;
import flux.lastbus.com.easysobuy.flux.action.BaseAction;
import flux.lastbus.com.easysobuy.flux.store.event.ChangeEvent;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


/**
 * Store基类,所有Store都需要继承此类,Store主要对View的状态进行管理,以及发送Store改变事件给View
 * author yuhang
 * date 2016-05-09 17:25
 */
public abstract class BaseStore implements Serializable {
    public static final int VIS = View.VISIBLE;
    public static final int GONE = View.GONE;
    public static final int INVIS = View.INVISIBLE;

    public CompositeSubscription mCompositeSubscription;

    public BaseStore() {
    }

    public BaseStore addCompositeSubscription(CompositeSubscription subscription){
        this.mCompositeSubscription = subscription;
        return this;
    }

    public abstract void onAction(BaseAction action);

    /**
     * 发送Store改变事件,View接收到后进行相应的render
     */
    protected void emitStoreChange() {
        RxBus.instance().send(getChangeEvent());
    }

    protected abstract ChangeEvent getChangeEvent();

    /**
     * 获取对象
     * @param action
     * @return
     */
    public <T extends Parcelable> T getParcelable(BaseAction action){
        return action.getData().getParcelable(action.getType());
    }

    /**
     * 获取对象集合
     * @param action
     * @param <T>
     * @return
     */
    public <T extends Parcelable> ArrayList<T> getParcelableArrayList(BaseAction action){
        return action.getData().getParcelableArrayList(action.getType());
    }

    /**
     * 获取对象数组
     * @param action
     * @return
     */
    public Parcelable[] getParcelableArray(BaseAction action){
        return action.getData().getParcelableArray(action.getType());
    }

    /**
     * 释放Rx对象
     * @param subscription
     */
    public void addSubscription(Subscription subscription){
        mCompositeSubscription.add(subscription);
    }
}
