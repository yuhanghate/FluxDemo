package flux.lastbus.com.easysobuy.flux.creator;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import flux.lastbus.com.easysobuy.flux.action.TestAction;
import flux.lastbus.com.easysobuy.flux.creator.conversion.ListConcersion;
import flux.lastbus.com.easysobuy.flux.dispatcher.Dispatcher;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.bean.Cat;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.bean.Dog;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.bean.Geko;
import flux.lastbus.com.easysobuy.utils.BundleUtil;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;
import rx.Observable;
import rx.Subscription;

/**
 * Created by yuhang on 16-8-3.
 */
public class TestActionCreator extends BaseActionCreator {
    public TestActionCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public void getTestItemList(int count){
        dispatch(new TestAction(TestAction.ACTION_TEST_PROGRESSBAR, BundleUtil.newInstance()));
        Subscription subscribe = Observable.timer(3, TimeUnit.SECONDS)
                .subscribe(aLong -> {
                    Bundle bundle = ListConcersion.getList2Bundle(getList(), TestAction.ACTION_TEST_SHOW);
                    dispatch(new TestAction(TestAction.ACTION_TEST_SHOW, bundle, count));
                });
        addSubscription(subscribe);
    }

    private ArrayList<BaseAdapterData> getList(){
        ArrayList<BaseAdapterData> list = new ArrayList<>();
        list.add(new Cat("American Curl"));
        list.add(new Cat("Baliness"));
        list.add(new Cat("Bengal"));
        list.add(new Cat("Corat"));
        list.add(new Cat("Manx"));
        list.add(new Cat("Nebelung"));
        list.add(new Dog("Aidi"));
        list.add(new Dog("Chinook"));
        list.add(new Dog("Appenzeller"));
        list.add(new Dog("Collie"));
        list.add(new Geko("Mub Adder", "Adder"));
        list.add(new Geko("Texas Blind Snake", "Blind snake"));
        list.add(new Geko("Tree Boa", "Boa"));
        list.add(new Geko("Fat-tailed", "Hemitheconyx"));
        list.add(new Geko("Stenodactylus", "Dune Gecko"));
        list.add(new Geko("Leopard Gecko", "Eublepharis"));
        list.add(new Geko("Madagascar Gecko", "Phelsuma"));

        Collections.shuffle(list);
        return list;
    }
}
