package flux.lastbus.com.easysobuy.widget.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * 缓存对象类
 * Created by yuhang on 16-7-5.
 */
public class AbsViewHolder extends RecyclerView.ViewHolder {
    public AbsViewHolder(View itemView) {
        super(itemView);

    }


    /**
     * Itme 点击事件接口
     */
    public interface OnItemClickListener<T> {

        /**
         * Item点击事件
         * @param v View视图
         * @param items 数据源List
         * @param position 索引
         */
        void onClick(View v, List<T> items, int position);
    }
}
