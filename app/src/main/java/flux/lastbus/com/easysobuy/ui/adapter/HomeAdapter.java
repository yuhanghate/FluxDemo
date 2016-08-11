package flux.lastbus.com.easysobuy.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import flux.lastbus.com.easysobuy.R;

/**
 * Created by yuhang on 16-8-11.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    Context mContext;
    List<String> mList;
    LayoutInflater mLayoutInflater;

    public HomeAdapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setList(List<String> list){
        mList = list;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_home, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList == null? 0 : mList.size();
    }

    static class HomeViewHolder extends RecyclerView.ViewHolder{

        public HomeViewHolder(View itemView) {
            super(itemView);
        }
    }
}
