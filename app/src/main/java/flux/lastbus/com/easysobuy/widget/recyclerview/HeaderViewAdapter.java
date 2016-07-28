package flux.lastbus.com.easysobuy.widget.recyclerview;

import android.support.v7.widget.RecyclerView;

/**
 * 头部View
 * Created by yuhang on 16-7-6.
 */
public abstract class HeaderViewAdapter<T extends AbsAdatperData, VH extends RecyclerView.ViewHolder> extends AbsListItemAdapterDelegate<T,VH> {

    @Override
    public boolean isHeaderView() {
        return true;
    }


    /**
     * 获取数据源对象
     * @return
     */
    public abstract T getHeaderData();





}
