package flux.lastbus.com.easysobuy.ui.adapter;

import android.app.Activity;

import flux.lastbus.com.easysobuy.ui.adapter.delegate.CatDelegate;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.DogDelegate;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.EmptyDelegate;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.ErrorDelegate;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.FooterDelegate;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.GekoDelegate;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.HeaderDelegate;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseRecyclerViewAdapter;

/**
 * Created by yuhang on 16-8-3.
 */
public class TestAdater extends BaseRecyclerViewAdapter{
    public TestAdater(Activity activity) {
        CatDelegate catDelegate = new CatDelegate(activity);
        DogDelegate dogDelegate = new DogDelegate(activity);
        GekoDelegate gekoDelegate = new GekoDelegate(activity);

        HeaderDelegate headerDelegate = new HeaderDelegate(activity);
        FooterDelegate footerDelegate = new FooterDelegate(activity);
        EmptyDelegate emptyDelegate = new EmptyDelegate(activity);
        ErrorDelegate errorDelegate = new ErrorDelegate(activity);

        addDelegate(catDelegate);
        addDelegate(dogDelegate);
        addDelegate(gekoDelegate);
        addHeaderView(headerDelegate);
        addFooterView(footerDelegate);
        addEmptyView(emptyDelegate);
        addErrorView(errorDelegate);
    }
}
