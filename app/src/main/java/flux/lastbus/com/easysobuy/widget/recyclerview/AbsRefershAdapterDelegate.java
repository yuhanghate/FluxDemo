package flux.lastbus.com.easysobuy.widget.recyclerview;

import android.support.annotation.NonNull;

import java.util.List;

/**
 *  下拉刷新，上拉更多Adapter
 * Created by yuhang on 16-7-6.
 */
public abstract class AbsRefershAdapterDelegate extends ListDelegationAdapter {

    /**
     * 获取底部索引
     * @return
     */
    public int getItemDataPosition(){
        if(items != null){
            FooterViewAdapter footerView = delegatesManager.getFooterView();
            if(footerView != null){
                for(int i=items.size()-1; i>=0; i--){
                    if(footerView.isFooterViewType(items.get(i))){
                        return i;
                    }
                }
            }
        }
        return -1;
    }


    /**
     * 设置头部View
     * @param items The items / data source
     */
    @Override
    public void setItems(List<AbsAdatperData> items) {


        super.setItems(items);
    }

    /**
     * 增加数据源。分页使用
     * @param items
     */
    public void addItems(List<AbsAdatperData> items){
        if(items == null || items.isEmpty()){
            throw new NullPointerException("Params items is Null");
        }

        if(this.items == null){
            throw new NullPointerException("Add Items Itmes is Null");
        }

        this.items.addAll((List)items);
    }

    public AdapterDelegatesManager<AbsAdatperData> getAdapterDelegatesManager(){return delegatesManager;}

    /**
     * 增加自定义Adapter
     * @param delegate
     */
    public void addDelegate(@NonNull AdapterDelegate delegate){
        delegatesManager.addDelegate(delegate);
    }

    /**
     * 增加底部View
     */
    public void addFooterView(@NonNull AdapterDelegate delegate){
        if(delegate == null){
            throw new NullPointerException("Params delegate is Null");
        }

        if(delegatesManager.isFooterEnable()){
            throw new IndexOutOfBoundsException("Add repeat Footer View!");
        }

        /**
         * 增加Adapter类型与底部数据源
         */
        if(delegate instanceof  FooterViewAdapter){
            delegatesManager.addFooterView(delegate);
        }else{
            throw new NullPointerException("Params delegate Type is "+delegate);
        }
    }

    public void addHeaderView(@NonNull AdapterDelegate delegate){
        if(delegate == null){
            throw new NullPointerException("Params delegate is Null");
        }

        if(delegatesManager.isHeaderEnable()){
            throw new IndexOutOfBoundsException("Add repeat Footer View!");
        }

        /**
         * 增加Adapter类型与底部数据源
         */
        if(delegate instanceof HeaderViewAdapter){
            delegatesManager.addHeaderView(delegate);
        }else{
            throw new NullPointerException("Params delegate Type is "+delegate);
        }
    }


    /**
     * 重新滑动
     */
    public void onScrollResume(){
        if(items == null || items.isEmpty()) return;

        delegatesManager.onScroollResume();
    }

    /**
     * 停止滑动
     */
    public void onScrollPause(){
        delegatesManager.onScrollPause();
    }

    /**
     * 增加空View
     * @param delegate
     */
    public void addEmptyView(@NonNull EmptyViewAdapter delegate){
        delegatesManager.addEmptyView(delegate);
    }

    /**
     * 获取空View
     */
    public EmptyViewAdapter getEmptyView(){
        return delegatesManager.getEmptyView();
    }
}
