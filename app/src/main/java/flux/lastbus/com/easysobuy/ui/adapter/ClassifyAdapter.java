package flux.lastbus.com.easysobuy.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.ui.activity.GoodsActivity;

/**
 * Created by yuhang on 16-8-16.
 */
public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.ClassifyViewHolder> {
    Context mContext;
    List<String> mList;
    LayoutInflater mInflater;

    public ClassifyAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ClassifyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_classify, parent, false);
        return new ClassifyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassifyViewHolder holder, int position) {
        holder.iconView.setText(mList.get(position).substring(0,1));
        holder.nameView.setText(mList.get(position));
        holder.cardView.setOnClickListener(v -> GoodsActivity.start(mContext,mList.get(position)));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ClassifyViewHolder extends RecyclerView.ViewHolder{
        TextView nameView;
        TextView iconView;
        View cardView;
        public ClassifyViewHolder(View itemView) {
            super(itemView);
            nameView = ButterKnife.findById(itemView, R.id.nameView);
            iconView = ButterKnife.findById(itemView, R.id.iconView);
            cardView = ButterKnife.findById(itemView, R.id.cardView);
        }
    }
}
