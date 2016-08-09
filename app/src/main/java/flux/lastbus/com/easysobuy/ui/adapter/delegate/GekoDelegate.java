package flux.lastbus.com.easysobuy.ui.adapter.delegate;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.bean.Geko;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.holder.GekoViewHolder;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterDelegate;

/**
 * Created by yuhang on 16-8-3.
 */
public class GekoDelegate extends BaseAdapterDelegate<Geko, GekoViewHolder> {
    LayoutInflater mLayoutInflater;

    public GekoDelegate(Activity activity) {
        mLayoutInflater = activity.getLayoutInflater();
    }

    @Override
    public boolean isForViewType(@NonNull BaseAdapterData item) {
        return item instanceof Geko;
    }

    @NonNull
    @Override
    public GekoViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new GekoViewHolder(mLayoutInflater.inflate(R.layout.recycler_view_item_geko, parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull Geko item, @NonNull GekoViewHolder viewHolder, int position, @NonNull List<Geko> items) {
        viewHolder.layout.setBackgroundResource(R.color.material_cyan_500);
    }
}
