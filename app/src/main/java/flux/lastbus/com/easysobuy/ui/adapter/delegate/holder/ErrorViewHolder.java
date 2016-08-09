package flux.lastbus.com.easysobuy.ui.adapter.delegate.holder;

import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseViewHolder;

/**
 * Created by yuhang on 16-8-4.
 */
public class ErrorViewHolder extends BaseViewHolder {
    @BindView(R.id.errorBtn)
    public Button button;
    public ErrorViewHolder(View itemView) {
        super(itemView);
    }
}
