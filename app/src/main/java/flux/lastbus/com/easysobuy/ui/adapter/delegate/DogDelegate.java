package flux.lastbus.com.easysobuy.ui.adapter.delegate;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.bean.Dog;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.holder.DogViewHolder;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterDelegate;

/**
 * Created by yuhang on 16-8-3.
 */
public class DogDelegate extends BaseAdapterDelegate<Dog,DogViewHolder> {
    LayoutInflater mLayoutInflater;

    public DogDelegate(Activity activity) {
        mLayoutInflater = activity.getLayoutInflater();
    }

    @Override
    public boolean isForViewType(@NonNull BaseAdapterData item) {
        return item instanceof Dog;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new DogViewHolder(mLayoutInflater.inflate(R.layout.recycler_view_item_dog, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull Dog item, @NonNull DogViewHolder viewHolder, int position, @NonNull List<Dog> items) {

        viewHolder.layout.setBackgroundResource(R.color.material_blue_500);
        viewHolder.name.setText("Dog");
    }
}
