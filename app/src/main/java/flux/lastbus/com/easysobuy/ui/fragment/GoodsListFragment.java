package flux.lastbus.com.easysobuy.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import flux.lastbus.com.easysobuy.ui.adapter.GoodsListAdapter;
import flux.lastbus.com.easysobuy.widget.recyclerview.DividerItemDecoration;

/**
 * Created by yuhang on 16-8-16.
 */
public class GoodsListFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Override
    public int onContentView() {
        return R.layout.fragment_goods_list;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

    public static GoodsListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        GoodsListFragment fragment = new GoodsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onInitFragment() {
        initRecyclerView();
    }

    private void initRecyclerView(){
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));

        GoodsListAdapter adapter = new GoodsListAdapter(this, getList());
        recyclerView.setAdapter(adapter);
    }

    private List<String> getList(){
        List<String> list = new ArrayList<>();
        for(int i=0; i<20; i++){
            list.add("");
        }
        return list;
    }
}
