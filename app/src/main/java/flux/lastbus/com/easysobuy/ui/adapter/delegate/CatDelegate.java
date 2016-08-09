package flux.lastbus.com.easysobuy.ui.adapter.delegate;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.bean.Cat;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.holder.CatViewHolder;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterDelegate;

/**
 * Created by yuhang on 16-8-3.
 */
public class CatDelegate extends BaseAdapterDelegate<Cat, CatViewHolder> {

    LayoutInflater mInflater;
    Activity mActivity;

    public CatDelegate(Activity activity) {
        mInflater = activity.getLayoutInflater();
        mActivity = activity;
    }

    @Override
    public boolean isForViewType(@NonNull BaseAdapterData item) {
        return item instanceof Cat;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new CatViewHolder(mInflater.inflate(R.layout.recycler_view_item_cat,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull Cat item, @NonNull CatViewHolder viewHolder, int position, @NonNull List<Cat> items) {
        viewHolder.layout.setBackgroundResource(R.color.color_200);
        viewHolder.name.setText("CAT");
    }
}
