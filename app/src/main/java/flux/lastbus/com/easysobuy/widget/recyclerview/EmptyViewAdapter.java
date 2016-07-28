package flux.lastbus.com.easysobuy.widget.recyclerview;

import android.support.v7.widget.RecyclerView;

/**
 * 空布局Adapter
 * Created by yuhang on 16-7-6.
 */
public abstract class EmptyViewAdapter<T extends AbsAdatperData, VH extends RecyclerView.ViewHolder> extends AbsListItemAdapterDelegate<T,VH> {

    /**
     * 返回空布局数据对象
     * @return
     */
    public abstract AbsAdatperData getEmptyViewData();
}
