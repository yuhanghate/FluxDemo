package flux.lastbus.com.easysobuy.ui.fragment;

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
import flux.lastbus.com.easysobuy.ui.activity.BaseActivity;
import flux.lastbus.com.easysobuy.ui.adapter.MomentsAdapter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by yuhang on 16-8-22.
 */
public class MomentsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    List<String> urls;

    @Override
    public int onContentView() {
        return R.layout.fragment_moments;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

    public static MomentsFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MomentsFragment fragment = new MomentsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onInitFragment() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark);



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


    public List<String> getUrls(){
        urls = new ArrayList<>();

        urls.add("http://img0.imgtn.bdimg.com/it/u=2824772955,1962163183&fm=23&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=2445977049,1933348358&fm=23&gp=0.jpg");
        urls.add("http://img2.imgtn.bdimg.com/it/u=688732682,3726551686&fm=11&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=3155857685,166194937&fm=23&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=2602029786,2698055896&fm=23&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=2007293974,2893395526&fm=11&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=3261017432,641182037&fm=23&gp=0.jpg");
        urls.add("http://img2.imgtn.bdimg.com/it/u=3940891529,3331851055&fm=11&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=3770651437,3387187747&fm=23&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=4246876353,1577963083&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=1188342161,3047134986&fm=11&gp=0.jpg");
        urls.add("http://img2.imgtn.bdimg.com/it/u=1136525921,2469272247&fm=23&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=2097184208,139019956&fm=23&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=943173400,2345765601&fm=23&gp=0.jpg");
        urls.add("http://img3.imgtn.bdimg.com/it/u=2825168445,126186606&fm=23&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=3311814974,1419765567&fm=23&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=3060423689,564850820&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=3780305222,2178920197&fm=23&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=18103931,408259535&fm=23&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=1819655436,1406018065&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=3813888391,1593239882&fm=23&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=3770651437,3387187747&fm=23&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=4246876353,1577963083&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=1188342161,3047134986&fm=11&gp=0.jpg");
        urls.add("http://img2.imgtn.bdimg.com/it/u=1136525921,2469272247&fm=23&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=2097184208,139019956&fm=23&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=943173400,2345765601&fm=23&gp=0.jpg");
        urls.add("http://img3.imgtn.bdimg.com/it/u=2825168445,126186606&fm=23&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=2097184208,139019956&fm=23&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=943173400,2345765601&fm=23&gp=0.jpg");
        urls.add("http://img3.imgtn.bdimg.com/it/u=2825168445,126186606&fm=23&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=3311814974,1419765567&fm=23&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=3060423689,564850820&fm=23&gp=0.jpg");
        urls.add("http://img2.imgtn.bdimg.com/it/u=688732682,3726551686&fm=11&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=3155857685,166194937&fm=23&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=2602029786,2698055896&fm=23&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=943173400,2345765601&fm=23&gp=0.jpg");
        urls.add("http://img3.imgtn.bdimg.com/it/u=2825168445,126186606&fm=23&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=3311814974,1419765567&fm=23&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=3060423689,564850820&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=3780305222,2178920197&fm=23&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=18103931,408259535&fm=23&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=1819655436,1406018065&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=3813888391,1593239882&fm=23&gp=0.jpg");
        urls.add("http://img4.imgtn.bdimg.com/it/u=3770651437,3387187747&fm=23&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=4246876353,1577963083&fm=23&gp=0.jpg");
        urls.add("http://img5.imgtn.bdimg.com/it/u=1188342161,3047134986&fm=11&gp=0.jpg");
        urls.add("http://img2.imgtn.bdimg.com/it/u=1136525921,2469272247&fm=23&gp=0.jpg");
        urls.add("http://img0.imgtn.bdimg.com/it/u=2097184208,139019956&fm=23&gp=0.jpg");
        urls.add("http://img1.imgtn.bdimg.com/it/u=943173400,2345765601&fm=23&gp=0.jpg");
        return urls;
    }

    @Override
    public void onRefresh() {
        Observable.just("")
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    swipeRefreshLayout.setRefreshing(false);
                    getUrls();
                    LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));

                    MomentsAdapter adapter = new MomentsAdapter((BaseActivity) getActivity(),urls);
                    recyclerView.setAdapter(adapter);
                });
    }
}
