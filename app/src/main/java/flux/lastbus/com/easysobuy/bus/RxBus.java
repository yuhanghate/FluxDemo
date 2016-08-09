package flux.lastbus.com.easysobuy.bus;

import android.support.annotation.NonNull;

import java.util.Vector;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * 全应用通知。事件总线
 */
public class RxBus {
    private Subject subject;
    private Vector<Subject> subjectList;
    private static RxBus sInstance;

    private RxBus() {
        subjectList = new Vector<>();
        //SerializedSubject是线程安全的
        //PublishSubject 会发送订阅者从订阅之后的事件序列,这意味着没订阅前的事件序列不会被发送到当前订阅者
        this.subject = new SerializedSubject<>(PublishSubject.create());
    }

    /**
     * 返回一个RxBus的单例对象
     *
     * @return RxBus
     */
    public synchronized static RxBus instance() {
        if (null == sInstance) {
            sInstance = new RxBus();
        }
        return sInstance;
    }

    public synchronized <T> Observable<T> register(@NonNull Object obj){
        Subject<T, T> subject = PublishSubject.create();
        subjectList.add(subject);
        return subject;
    }

    public synchronized void unregister(Object object) {
        subjectList.remove(object);
    }

    public void post(@NonNull Object content) {
        synchronized (this) {
            for (Subject subject : subjectList) {
                if (subject != null) {
                    subject.onNext(content);
                }
            }
        }
    }

    /**
     * 发送事件
     *
     * @param o Object
     */
    public void send(Object o) {
        subject.onNext(o);
    }

    /**
     * @param eventType 只接受eventType类型的响应,ofType = filter + cast
     * @return Observable
     */
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return subject.ofType(eventType);
    }
}
