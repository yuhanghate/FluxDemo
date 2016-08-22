package flux.lastbus.com.easysobuy.ui.adapter;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.ui.activity.BaseActivity;

/**
 * Created by yuhang on 16-8-22.
 */
public class MomentsAdapter extends RecyclerView.Adapter<MomentsAdapter.MomentsViewHolder> {

    BaseActivity mContext;
    List<String> mList;
    List<String> mContent = new ArrayList<>();
    LayoutInflater mInflater;

    public MomentsAdapter(BaseActivity mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
        mInflater = LayoutInflater.from(mContext);
        initContentView();
    }

    public void initContentView(){
        mContent.add("http://a.hiphotos.baidu.com/zhidao/pic/item/3bf33a87e950352a96751c0c5043fbf2b2118b99.jpg");
        mContent.add("http://img3.imgtn.bdimg.com/it/u=2644180387,2973502488&fm=23&gp=0.jpg");
        mContent.add("http://img3.imgtn.bdimg.com/it/u=1645438483,1368322184&fm=23&gp=0.jpg");
        mContent.add("http://img3.imgtn.bdimg.com/it/u=487326788,4110232043&fm=23&gp=0.jpg");
        mContent.add("http://img3.imgtn.bdimg.com/it/u=3002927304,2082353461&fm=23&gp=0.jpg");
        mContent.add("http://img4.imgtn.bdimg.com/it/u=3217870219,1935641899&fm=23&gp=0.jpg");
        mContent.add("http://img5.imgtn.bdimg.com/it/u=96367878,1890819017&fm=23&gp=0.jpg");
        mContent.add("http://img3.imgtn.bdimg.com/it/u=673573285,3732925464&fm=23&gp=0.jpg");
    }

    @Override
    public MomentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MomentsViewHolder(mInflater.inflate(R.layout.adapter_memoments,parent,false));
    }

    @Override
    public void onBindViewHolder(MomentsViewHolder holder, int position) {
        Glide.with(mContext).load(mList.get(position)).into(holder.imageview);
        initRecyclerView(holder.recyclerView);

    }

    private void initRecyclerView(RecyclerView recyclerView){
//        recyclerView.setHasFixedSize(true);

        int width = recyclerView.getLayoutParams().width;
        MomentsContentImageAdapter adapter = new MomentsContentImageAdapter(mContent, mContext,width/3);
        GridLayoutManager manager = new GridLayoutManager(mContext,3);
//        recyclerView.addItemDecoration(new SpaceItemDecoration(3));
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class MomentsViewHolder extends RecyclerView.ViewHolder{
        LinearLayout payView;
        RecyclerView recyclerView;
        CircleImageView imageview;
        public MomentsViewHolder(View itemView) {
            super(itemView);
            payView = ButterKnife.findById(itemView, R.id.payView);
            recyclerView = ButterKnife.findById(itemView, R.id.recyclerView);
            imageview = ButterKnife.findById(itemView, R.id.imageview);
        }
    }


}


