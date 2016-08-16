package flux.lastbus.com.easysobuy.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.ButterKnife;
import flux.lastbus.com.easysobuy.R;

/**
 * Created by yuhang on 16-8-16.
 */
public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.GoodsViewHolder> {
    Context mContext;
    List<String> mList;
    LayoutInflater mInflater;
    List<Integer> heights;

    public GoodsAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
        mInflater = LayoutInflater.from(mContext);
//        getRandomHeight(mList);
    }

    @Override
    public GoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_goods, parent, false);
        view.setOnClickListener(v -> Log.i("",""));
        return new GoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GoodsViewHolder holder, int position) {
       /* ViewGroup.LayoutParams params =  holder.itemView.getLayoutParams();//得到item的LayoutParams布局参数
        params.height = heights.get(position);//把随机的高度赋予item布局
        holder.itemView.setLayoutParams(params);//把params设置给item布局
*/
        Glide.with(mContext).load(mList.get(position)).into(holder.imageView);
    }

  /*  private void getRandomHeight(List<String> lists){//得到随机item的高度
        heights = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            heights.add((int)(500+Math.random()*250));
        }
    }*/
    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class GoodsViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public GoodsViewHolder(View itemView) {
            super(itemView);
            imageView = ButterKnife.findById(itemView,R.id.imageView);
        }
    }
}
