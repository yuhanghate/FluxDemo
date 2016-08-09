package flux.lastbus.com.easysobuy.flux.creator;

import flux.lastbus.com.easysobuy.flux.action.BaseAction;
import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * ActionCreator基类，所有ActionCreator都需要继承此类
 * author lsxiao
 * date 2016-05-09 17:21
 */
public abstract class BaseActionCreator {
    Dispatcher mDispatcher;
    CompositeSubscription mCompositeSubscription;

    public BaseActionCreator(Dispatcher dispatcher) {
        mDispatcher = dispatcher;
    }

    public void addCompositeSubscription(CompositeSubscription subscription){
        this.mCompositeSubscription = subscription;
    }

    public Dispatcher getDispatcher() {
        return mDispatcher;
    }

    /**
     * 分发数据
     * @param action
     */
    public void dispatch(BaseAction action){
        getDispatcher().dispatch(action);
    }

    /**
     * 释放Rx对象
     * @param subscription
     */
    public void addSubscription(Subscription subscription){
        mCompositeSubscription.add(subscription);
    }
}
