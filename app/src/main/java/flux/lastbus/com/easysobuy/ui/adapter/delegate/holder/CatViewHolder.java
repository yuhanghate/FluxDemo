package flux.lastbus.com.easysobuy.ui.adapter.delegate.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseViewHolder;

/**
 * Created by yuhang on 16-8-3.
 */
public class CatViewHolder extends BaseViewHolder {
    @BindView(R.id.itemLayout)
    public View layout;
    @BindView(R.id.name)
    public TextView name;
    @BindView(R.id.image)
    public ImageView image;
    public CatViewHolder(View itemView) {
        super(itemView);
    }
}
