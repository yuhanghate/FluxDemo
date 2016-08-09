package flux.lastbus.com.easysobuy.ui.adapter.delegate.holder;

import android.view.View;

import butterknife.BindView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseViewHolder;

/**
 * Created by yuhang on 16-8-3.
 */
public class GekoViewHolder extends BaseViewHolder {
    @BindView(R.id.itemLayout)
    public View layout;
    public GekoViewHolder(View itemView) {
        super(itemView);
    }
}
