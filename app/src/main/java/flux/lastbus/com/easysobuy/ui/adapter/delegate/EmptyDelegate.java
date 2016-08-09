package flux.lastbus.com.easysobuy.ui.adapter.delegate;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.bean.Empty;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.holder.EmptyViewHolder;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseEmptyView;

/**
 * Created by yuhang on 16-8-4.
 */
public class EmptyDelegate extends BaseEmptyView<Empty, EmptyViewHolder> {
    LayoutInflater mInflater;
    public EmptyDelegate(Activity activity) {
        mInflater = activity.getLayoutInflater();
    }

    @Override
    public BaseAdapterData getEmptyViewData() {
        return new Empty();
    }

    @Override
    public boolean isForViewType(@NonNull BaseAdapterData item) {
        return item instanceof Empty;
    }

    @NonNull
    @Override
    public EmptyViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new EmptyViewHolder(mInflater.inflate(R.layout.recycler_view_item_empty, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull Empty item, @NonNull EmptyViewHolder viewHolder, int position, @NonNull List<Empty> items) {

    }
}
