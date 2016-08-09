package flux.lastbus.com.easysobuy.ui.adapter.delegate;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.bus.RxBus;
import flux.lastbus.com.easysobuy.flux.action.TestAction;
import flux.lastbus.com.easysobuy.flux.store.event.TestEvent;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.bean.Error;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.holder.ErrorViewHolder;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseErrorView;

/**
 * Created by yuhang on 16-8-4.
 */
public class ErrorDelegate extends BaseErrorView<Error, ErrorViewHolder> {
    LayoutInflater mInflater;

    public ErrorDelegate(Activity activity) {
        this.mInflater = activity.getLayoutInflater();
    }

    @Override
    public BaseAdapterData getErrorViewData() {
        return new Error();
    }

    @Override
    public boolean isForViewType(@NonNull BaseAdapterData item) {
        return item instanceof Error;
    }

    @NonNull
    @Override
    public ErrorViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new ErrorViewHolder(mInflater.inflate(R.layout.recycler_view_item_error, parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull Error item, @NonNull ErrorViewHolder viewHolder, int position, @NonNull List<Error> items) {
        viewHolder.button.setOnClickListener(v -> {
            TestEvent event = new TestEvent();
            event.setType(TestAction.ACTION_TEST_ERROR_BUTTON_CLICK);
            RxBus.instance().send(event);});
    }
}
