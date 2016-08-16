package flux.lastbus.com.easysobuy.ui.adapter;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import flux.lastbus.com.easysobuy.R;

/**
 * Created by yuhang on 16-8-16.
 */
public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodsListViewHolder> {
    Fragment mFragment;
    LayoutInflater mInflater;
    List<String> mList;

    public GoodsListAdapter(Fragment mFragment, List<String> list) {
        this.mFragment = mFragment;
        this.mList = list;
        mInflater = LayoutInflater.from(mFragment.getActivity());
    }

    @Override
    public GoodsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_goods_list, parent, false);
        view.setOnClickListener(v -> Snackbar.make(v,"点击",Snackbar.LENGTH_LONG).show());
        return new GoodsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GoodsListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class GoodsListViewHolder extends RecyclerView.ViewHolder{

        public GoodsListViewHolder(View itemView) {
            super(itemView);
        }
    }
}
