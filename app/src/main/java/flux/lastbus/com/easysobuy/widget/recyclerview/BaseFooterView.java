package flux.lastbus.com.easysobuy.widget.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * 底部Adapter继承
 * Created by yuhang on 16-7-6.
 */
public abstract class BaseFooterView<T extends BaseAdapterData, VH extends RecyclerView.ViewHolder> extends BaseAdapterDelegate<T,VH> {
    private boolean isLoadAll;
    /**
     * 数据是否是底部View对象
     * @param t 数据源对象
     * @return
     */
//    public abstract boolean isFooterViewType(BaseAdapterData t);

    @Override
    protected void onBindViewHolder(@NonNull T item, @NonNull VH viewHolder, int position, @NonNull List<T> items) {
        onBindViewHolder(item, viewHolder, position, items,isLoadAll);
    }

    /**
     * 获取数据源对象
     * @return
     */
    public abstract BaseAdapterData getFooterView();

    /**
     * 是否加载完成
     * @param isLoadAll
     */
    public void setLoadAll(boolean isLoadAll){
        this.isLoadAll = isLoadAll;
    }

    @Override
    public boolean isFooterView() {
        return true;
    }

    protected abstract void onBindViewHolder(@NonNull T item, @NonNull VH viewHolder, int position, List<T> list, boolean isLoadAll);
}
