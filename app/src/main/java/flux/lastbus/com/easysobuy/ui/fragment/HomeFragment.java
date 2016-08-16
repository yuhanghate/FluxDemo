package flux.lastbus.com.easysobuy.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import flux.lastbus.com.easysobuy.ui.adapter.HomeAdapter;
import flux.lastbus.com.easysobuy.widget.recyclerview.DividerItemDecoration;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by yuhang on 16-8-11.
 */
public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    HomeAdapter mHomeAdapter;

    @Override
    public int onContentView() {
        return R.layout.fragment_home;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

    public static HomeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onInitFragment() {
        initView();
    }

    public void initView(){
        mHomeAdapter = new HomeAdapter(getActivity());
        recyclerView.setAdapter(mHomeAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark);
//        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light);
//        onRefresh();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(() -> {
            swipeRefreshLayout.setRefreshing(true);
            onRefresh();
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onRefresh() {
        Observable.just("")
                .delay(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    swipeRefreshLayout.setRefreshing(false);
                    mHomeAdapter.setList(getList());
                    mHomeAdapter.notifyDataSetChanged();
                });
    }

    public List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("http://www.51pla.com/UploadPic/2013/03/28/20130328140922814788.jpg");
        list.add("http://i1.ymfile.com/uploads/product/10/05/x1_1.1349404532_800_600_164514.jpg");
        list.add("http://file.youboy.com/d/21/31/80/8/825798.JPG");
        list.add("http://static.panoramio.com/photos/large/35006301.jpg");
        list.add("http://www.etaji.cn/uploadpic/news/201512100955562713.jpg");
        list.add("http://img3.redocn.com/20120220/Redocn_2012022005260591.jpg");
        list.add("http://img.sccnn.com/bimg/323/006.jpg");
        list.add("http://www.pvc123.com/file/upload/201408/20/16-44-58-24-332387.jpg");
        list.add("http://file2.youboy.com/d/124/75/59/0/437900.jpg");
        list.add("http://file2.youboy.com/e/2015/4/27/45/451418.jpg");
        list.add("http://img09.hc360.cn/09/busin/110/291/b/09-110291199.jpg");
        return list;
    }
}
