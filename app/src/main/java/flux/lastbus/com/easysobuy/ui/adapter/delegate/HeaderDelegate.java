package flux.lastbus.com.easysobuy.ui.adapter.delegate;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.bean.Header;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.holder.HeaderViewHolder;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseHeaderView;

/**
 * Created by yuhang on 16-8-3.
 */
public class HeaderDelegate extends BaseHeaderView<Header, HeaderViewHolder> {
    LayoutInflater mLayoutInflater;

    public HeaderDelegate(Activity activity) {
        mLayoutInflater = activity.getLayoutInflater();
    }

    @Override
    public Header getHeaderData() {
        return new Header();
    }

    @Override
    public boolean isForViewType(@NonNull BaseAdapterData item) {
        return item instanceof Header;
    }

    @NonNull
    @Override
    public HeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.delegate_header, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull Header item, @NonNull HeaderViewHolder viewHolder, int position, @NonNull List<Header> items) {

    }
}
