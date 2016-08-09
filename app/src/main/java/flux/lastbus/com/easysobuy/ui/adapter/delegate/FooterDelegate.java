package flux.lastbus.com.easysobuy.ui.adapter.delegate;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.bean.Footer;
import flux.lastbus.com.easysobuy.ui.adapter.delegate.holder.FooterViewHolder;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseFooterView;

/**
 * Created by yuhang on 16-8-3.
 */
public class FooterDelegate extends BaseFooterView<Footer, FooterViewHolder> {
    LayoutInflater mLayoutInflater;

    public FooterDelegate(Activity activity) {
        this.mLayoutInflater = activity.getLayoutInflater();
    }

    @Override
    public BaseAdapterData getFooterView() {
        return new Footer();
    }

    @Override
    protected void onBindViewHolder(@NonNull Footer item, @NonNull FooterViewHolder viewHolder, int position, List<Footer> list, boolean isLoadAll) {

    }

    @Override
    public boolean isForViewType(@NonNull BaseAdapterData item) {
        return item instanceof Footer;
    }

    @NonNull
    @Override
    public FooterViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        return new FooterViewHolder(mLayoutInflater.inflate(R.layout.delegate_footer, parent, false));
    }
}
