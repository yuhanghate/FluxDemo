package flux.lastbus.com.easysobuy.widget.recyclerview; /**
 * Alipay.com Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 支持上拉加载更多的
 *
 * @author 紫韶
 * @version $$Id: LoadMoreRecyclerView.java, v 0.1 11/17/15 10:07 alicx Exp $$
 */
public class RecyclerAutoMoreView extends RecyclerView {


    private boolean mIsFooterEnable = false;//是否允许加载更多

    /**
     * 自定义实现了头部和底部加载更多的adapter
     */
    private AutoLoadAdapter mAutoLoadAdapter;
    /**
     * 标记是否正在加载更多，防止再次调用加载更多接口
     */
    private boolean mIsLoadingMore;

    /**
     * 加载更多的监听-业务需要实现加载数据
     */
    private LoadMoreListener mListener;


    private boolean isEmpty;
    private boolean isError;

    public RecyclerAutoMoreView(Context context) {
        super(context);
        init();
    }

    public RecyclerAutoMoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecyclerAutoMoreView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * 初始化-添加滚动监听
     * <p/>
     * 回调加载更多方法，前提是
     * <pre>
     *    1、有监听并且支持加载更多：null != mListener && mIsFooterEnable
     *    2、目前没有在加载，正在上拉（dy>0），当前最后一条可见的view是否是当前数据列表的最好一条--及加载更多
     * </pre>
     */
    private void init() {
        super.addOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //每一条数据都是一个Map
                switch (newState){
                    case SCROLL_STATE_DRAGGING:
                        Log.i("Main","用户在手指离开屏幕之前，由于滑了一下，视图仍然依靠惯性继续滑动");
                        if(mAutoLoadAdapter != null){
                            mAutoLoadAdapter.onScrollPause();
                        }
                        //刷新
                        break;
                    case SCROLL_STATE_IDLE:
                        Log.i("Main", "视图已经停止滑动");
                        if(mAutoLoadAdapter != null){
                            mAutoLoadAdapter.onScrollResume();
                        }
                        break;
                    case SCROLL_STATE_SETTLING:
                        Log.i("Main","手指没有离开屏幕，视图正在滑动");
                        if(mAutoLoadAdapter != null){
                            mAutoLoadAdapter.onScrollResume();
                        }
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (null != mListener && mIsFooterEnable && !mIsLoadingMore && dy > 0) {
                    int lastVisiblePosition = getLastVisiblePosition();
                    if (lastVisiblePosition >= mAutoLoadAdapter.getItemCount()-2) {
                        mListener.onLoadMore();
                        setLoadingMore(true);
                    }
                }
            }
        });
    }

    /**
     * 设置加载更多的监听
     *
     * @param listener
     */
    public void setLoadMoreListener(LoadMoreListener listener) {
        mListener = listener;
    }

    /**
     * 设置正在加载更多
     *
     * @param loadingMore
     */
    private void setLoadingMore(boolean loadingMore) {
        this.mIsLoadingMore = loadingMore;
    }

    /**
     * 加载更多监听
     */
    public interface LoadMoreListener {
        /**
         * 加载更多
         */
        void onLoadMore();
    }

    /**
     * 代理Adapter
     */
    public class AutoLoadAdapter extends Adapter<ViewHolder> {


        private BaseRecyclerViewAdapter mInternalAdapter;

        boolean isAddHeader;
        boolean isAddFooter;

        public AutoLoadAdapter(BaseRecyclerViewAdapter adapter) {
            mInternalAdapter = adapter;
        }

        @Override
        public int getItemViewType(int position) {

            AdapterDelegatesManager manager = mInternalAdapter.getAdapterDelegatesManager();
            int headerPosition = 0;
            int footerPosition = getItemCount() -1;
            //加入头部Adapter
            if(position == headerPosition && manager.isHeaderEnable()&& !isAddHeader && !isEmpty){
                isAddHeader = true;
                mInternalAdapter.getItems().add(position, manager.getHeaderView().getHeaderData());
            }
            //加入底部Adapter
            if((position == footerPosition) && manager.isFooterEnable()
                    && !isAddFooter && !isEmpty && mIsFooterEnable){
                isAddFooter = true;
                BaseFooterView footerView = manager.getFooterView();
                mInternalAdapter.getItems().add(footerView.getFooterView());
            }


            //转换成Itemtype
            List items = mInternalAdapter.getItems();
            int viewType = manager.getItemViewType(items,position);
            return viewType;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return mInternalAdapter.onCreateViewHolder(parent,viewType);
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
                mInternalAdapter.onBindViewHolder(holder,position);
        }

        @Override
        public int getItemCount() {

            int count = mInternalAdapter.getItemCount();
            AdapterDelegatesManager manager = mInternalAdapter.getAdapterDelegatesManager();

            //动态增加头部及底部View
            if(manager.isFooterEnable()&& !isAddHeader && !isEmpty) count++;
            if(manager.isHeaderEnable()&& !isAddFooter && !isEmpty && mIsFooterEnable) count++;

            return count;
        }

        /**
         * 下拉刷新.从代理Adapter获取数据
         */
        public void notifyLoadMore(){
            isAddFooter = false;
            int position = mInternalAdapter.getItemDataPosition();
            if(position < mInternalAdapter.getItems().size() && position>=0 ){
                mInternalAdapter.getItems().remove(position);
                removeItemView(position);
            }else{
                getAdapter().notifyDataSetChanged();
            }
        }

        /**
         * 上拉更多。从代理Adapter获取
         */
        public void notifyRefersh(){
            isAddHeader = false;
            isAddFooter = false;
            getAdapter().notifyDataSetChanged();
            getAdapter().notifyItemRangeChanged(0,mInternalAdapter.getItemCount());
        }

        public void onScrollPause(){
            mInternalAdapter.onScrollPause();
        }

        public void onScrollResume(){
            mInternalAdapter.onScrollResume();
        }

        public void setEmptyView(){
            BaseEmptyView emptyView = mInternalAdapter.getEmptyView();
            if(emptyView != null){
                List<BaseAdapterData> items = new ArrayList<>();
                mInternalAdapter.setItems(items);
                if(isEmpty){
                    items.add(emptyView.getEmptyViewData());
                }
            }
        }

        public void setErrorView(){
            BaseErrorView errorView = mInternalAdapter.getErrorView();
            if(errorView != null){
                List<BaseAdapterData> items = new ArrayList<>();
                mInternalAdapter.setItems(items);
                if(isEmpty){
                    items.add(errorView.getErrorViewData());
                }
            }
        }

        /**
         * 是否全部加载
         * @param isLoadAll
         */
        public void setLoadAll(boolean isLoadAll){
            BaseFooterView footerView = mInternalAdapter.getAdapterDelegatesManager().getFooterView();
            if(footerView != null){
                footerView.setLoadAll(isLoadAll);
            }
        }

    }

    /**
     * 进行包装模式
     * @param adapter
     */
    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter != null) {
            mAutoLoadAdapter = new AutoLoadAdapter((BaseRecyclerViewAdapter) adapter);
        }
        super.swapAdapter(mAutoLoadAdapter, true);
    }

    /**
     * 切换layoutManager
     *
     * 为了保证切换之后页面上还是停留在当前展示的位置，记录下切换之前的第一条展示位置，切换完成之后滚动到该位置
     * 另外切换之后必须要重新刷新下当前已经缓存的itemView，否则会出现布局错乱（俩种模式下的item布局不同），
     * RecyclerView提供了swapAdapter来进行切换adapter并清理老的itemView cache
     *
     * @param layoutManager
     */
    public void switchLayoutManager(LayoutManager layoutManager) {
        int firstVisiblePosition = getFirstVisiblePosition();
        setLayoutManager(layoutManager);
        getLayoutManager().scrollToPosition(firstVisiblePosition);
    }

    /**
     * 获取第一条展示的位置
     *
     * @return
     */
    private int getFirstVisiblePosition() {
        int position;
        if (getLayoutManager() instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        } else if (getLayoutManager() instanceof GridLayoutManager) {
            position = ((GridLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
        } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) getLayoutManager();
            int[] lastPositions = layoutManager.findFirstVisibleItemPositions(new int[layoutManager.getSpanCount()]);
            position = getMinPositions(lastPositions);
        } else {
            position = 0;
        }
        return position;
    }

    /**
     * 获得当前展示最小的position
     *
     * @param positions
     * @return
     */
    private int getMinPositions(int[] positions) {
        int size = positions.length;
        int minPosition = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            minPosition = Math.min(minPosition, positions[i]);
        }
        return minPosition;
    }

    /**
     * 获取最后一条展示的位置
     *
     * @return
     */
    private int getLastVisiblePosition() {
        int position;
        if (getLayoutManager() instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else if (getLayoutManager() instanceof GridLayoutManager) {
            position = ((GridLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        } else if (getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) getLayoutManager();
            int[] lastPositions = layoutManager.findLastVisibleItemPositions(new int[layoutManager.getSpanCount()]);
            position = getMaxPosition(lastPositions);
        } else {
            position = getLayoutManager().getItemCount() - 1;
        }
        return position;
    }

    /**
     * 获得最大的位置
     *
     * @param positions
     * @return
     */
    private int getMaxPosition(int[] positions) {
        int size = positions.length;
        int maxPosition = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            maxPosition = Math.max(maxPosition, positions[i]);
        }
        return maxPosition;
    }

    /**
     * 设置是否支持自动加载更多
     *
     * @param autoLoadMore
     */
    public void setAutoLoadMoreEnable(boolean autoLoadMore) {
        mIsFooterEnable = autoLoadMore;
    }

    /**
     * 通知更多的数据已经加载
     *
     * 每次加载完成之后添加了Data数据，用notifyItemRemoved来刷新列表展示，
     * 而不是用notifyDataSetChanged来刷新列表
     *
     * @param hasMore
     */
    public void notifyMoreFinish(boolean hasMore) {
        setAutoLoadMoreEnable(hasMore);
        mAutoLoadAdapter.notifyLoadMore();
        mIsLoadingMore = false;
    }

    /**
     * 上拉刷新
     */
    public void notifyRefersh(){
        mAutoLoadAdapter.notifyRefersh();
    }

    /**
     * 删除指定Item
     * @param position
     */
    public void removeItemView(int position){
        getAdapter().notifyItemRemoved(position);
        int itemCount = getAdapter().getItemCount();
        if(position != itemCount){
            getAdapter().notifyItemRangeChanged(position,itemCount-position);
        }
    }

    /**
     * 是否正在加载更多
     * @return
     */
    public boolean isLoadingMore(){
        return mIsLoadingMore;
    }


    /**
     * 是否显示空页面
     * @param isEmpty
     */
    public void setEmptyView(boolean isEmpty){
        this.isEmpty = isEmpty;
        if(isEmpty){
            mAutoLoadAdapter.setEmptyView();
            getAdapter().notifyDataSetChanged();
        }
    }

    /**
     * 是否显示异常界面
     * @param isError
     */
    public void setErrorView(boolean isError){
        this.isError = isError;
        if(isError){
            mAutoLoadAdapter.setErrorView();
            getAdapter().notifyDataSetChanged();
        }
    }

    public void setLoadAll(boolean isLoadAll){
        mAutoLoadAdapter.setLoadAll(isLoadAll);
        getAdapter().notifyDataSetChanged();
    }

}
