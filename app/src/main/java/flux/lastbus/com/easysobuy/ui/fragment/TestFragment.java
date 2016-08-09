package flux.lastbus.com.easysobuy.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.action.TestAction;
import flux.lastbus.com.easysobuy.flux.creator.TestActionCreator;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import flux.lastbus.com.easysobuy.flux.store.event.TestEvent;
import flux.lastbus.com.easysobuy.flux.store.fragment.TestStore;
import flux.lastbus.com.easysobuy.ui.adapter.TestAdater;
import flux.lastbus.com.easysobuy.ui.listener.OnRecyclerViewListener;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;
import flux.lastbus.com.easysobuy.widget.recyclerview.RecyclerAutoMoreView;
import rx.Subscription;

/**
 * 测试Fragment
 * Created by yuhang on 16-8-3.
 */
public class TestFragment extends BaseFragment implements
        SwipeRefreshLayout.OnRefreshListener, OnRecyclerViewListener,
        RecyclerAutoMoreView.LoadMoreListener{

    //分页.默认起始页角标
    private static final int LAST_POSITION = 0;

    @Inject
    TestActionCreator mTestActionCreator;
    @BindView(R.id.recyclerView)
    RecyclerAutoMoreView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    TestAdater mTestAdater;
    TestStore mTestStore;

    /**
     * New Fragment
     * @return
     */
    public static TestFragment newInstance() {
        TestFragment fragment = new TestFragment();
        return fragment;
    }
    @Override
    public int onContentView() {
        return R.layout.fragment_test;
    }

    @Override
    public BaseStore onCreateStore() {
        return new TestStore().addCompositeSubscription(mCompositeSubscription);
    }

    @Override
    public void onInitFragment() {
        init();
    }


    @Override
    public void onInitComponent() {
        super.onInitComponent();


        //TestFragment变量进行注入
        getFragmentComponent().inject(this);
        mTestActionCreator.addCompositeSubscription(mCompositeSubscription);
    }

    /**
     * 初始化View配置
     */
    public void init() {

        //纵向滑动
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        //上拉,下拉刷新事件
        refreshLayout.setOnRefreshListener(this);
        recyclerView.setLoadMoreListener(this);

        //绑定Adapter
        mTestAdater = new TestAdater(getActivity());
        recyclerView.setAdapter(mTestAdater);
        mTestStore = getInstanceStore();

        //首次加载刷新
        onRefresh();
    }

    @Override
    public void onLoadMore() {
        if(!mTestStore.isProgressbar()&& !recyclerView.isLoadingMore()){
            mTestActionCreator.getTestItemList(mTestStore.getCount());
        }
    }

    @Override
    public void onRefresh() {
        if(!mTestStore.isProgressbar() && !recyclerView.isLoadingMore()){
            mTestActionCreator.getTestItemList(LAST_POSITION);
        }
    }

    @Override
    public void onStoreChangeEvent() {
        super.onStoreChangeEvent();
        Subscription subscribe = registerEvent(TestEvent.class)
                .subscribe(testEvent -> {
                    switch (testEvent.getType()) {
                        case TestAction.ACTION_TEST_ERROR_BUTTON_CLICK://数据异常重新刷新
                            onProgressbarView();
                            break;
                        default:
                            if (mTestStore.isProgressbar()) { //首次加载进行进度条显示
                                onProgressbarView();
                            } else if (mTestStore.isError()) { //加载异常
                                onErrorView();
                            } else if (mTestStore.isEmpty()) { //空数据
                                onEmptyView();
                            } else {
                                onShowRecyclerView(); //正常显示List
                            }
                            break;
                    }
                });
        addSubscription(subscribe);
    }

    @Override
    public void onProgressbarView() {
        if(mTestStore.getCount() == LAST_POSITION){//首页才会刷新
            refreshLayout.post(() -> {
                recyclerView.setAutoLoadMoreEnable(false);
                refreshLayout.setRefreshing(true);
                onRefresh();
            });
        }
    }

    @Override
    public void onErrorView() {
        refreshLayout.setRefreshing(false);
        recyclerView.setErrorView(true);
    }

    @Override
    public void onEmptyView() {
        refreshLayout.setRefreshing(false);
        recyclerView.setEmptyView(true);
    }

    @Override
    public void onShowRecyclerView() {
        //隐藏加载条
        refreshLayout.setRefreshing(false);
        //自动加载更多
        recyclerView.setAutoLoadMoreEnable(true);

        List<BaseAdapterData> items = mTestAdater.getItems();

        //加载第一次
        if (items == null || items.isEmpty() || mTestStore.getCount() == LAST_POSITION) {
            mTestAdater.setItems(mTestStore.getListItem());
            recyclerView.notifyRefersh();
        }else{//加载更多
            mTestAdater.addItems(mTestStore.getListItem());
            recyclerView.notifyMoreFinish(true);
        }
    }
}
