package flux.lastbus.com.easysobuy.ui.adapter;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.ui.activity.BaseActivity;
import flux.lastbus.com.easysobuy.ui.activity.PictureActivity;

/**
 * Created by yuhang on 16-8-22.
 */
public class MomentsContentImageAdapter extends RecyclerView.Adapter<MomentsContentImageAdapter.ContentViewHolder> {

    List<String> urls;
    BaseActivity mContext;
    LayoutInflater mInflater;
    int width;

    public MomentsContentImageAdapter(List<String> urls, BaseActivity context, int width) {
        this.urls = urls;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.width = width;
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentViewHolder(mInflater.inflate(R.layout.adapter_moments_content,parent,false));
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, final int position) {
        /*ViewGroup.LayoutParams lp = holder.imageView.getLayoutParams();
        lp.height = width;
        lp.width = width;
        holder.imageView.setLayoutParams(lp);*/
        Glide.with(mContext).load(urls.get(position)).into(holder.imageView);
        holder.imageView.setOnClickListener(v -> {
//            startPictureActivity(urls.get(position), holder.imageView);
            Picasso.with(mContext).load(urls.get(position)).fetch(new Callback() {

                @Override
                public void onSuccess() {
                    startPictureActivity(urls.get(position), holder.imageView);
                }


                @Override
                public void onError() {

                }
            });
        });
    }
    private void startPictureActivity(String url, View transitView) {
        Intent intent = PictureActivity.start(mContext, url);
        ActivityOptionsCompat optionsCompat
                = ActivityOptionsCompat.makeSceneTransitionAnimation(
                mContext, transitView, PictureActivity.TRANSIT_PIC);
        ActivityCompat.startActivity(mContext, intent,
                optionsCompat.toBundle());

    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    static class ContentViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ContentViewHolder(View itemView) {
            super(itemView);
            imageView = ButterKnife.findById(itemView, R.id.imageView);
        }
    }
}
