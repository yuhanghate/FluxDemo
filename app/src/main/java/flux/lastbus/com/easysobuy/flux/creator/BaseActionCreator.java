package flux.lastbus.com.easysobuy.flux.creator;

import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;

/**
 * ActionCreator基类，所有ActionCreator都需要继承此类
 * author lsxiao
 * date 2016-05-09 17:21
 */
public abstract class BaseActionCreator {
    Dispatcher mDispatcher;

    public BaseActionCreator(Dispatcher dispatcher) {
        mDispatcher = dispatcher;
    }

    public Dispatcher getDispatcher() {
        return mDispatcher;
    }

}
