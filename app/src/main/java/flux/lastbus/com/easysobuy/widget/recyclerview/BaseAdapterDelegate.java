package flux.lastbus.com.easysobuy.widget.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 数据绑定模型
 * @param <T> 数据源集合
 * @param <VH> ViewHolder对象
 */
public abstract class BaseAdapterDelegate<T extends BaseAdapterData, VH extends RecyclerView.ViewHolder>
    implements AdapterDelegate<T> {

  /**
   * 数据源集合
   */
  BaseViewHolder.OnItemClickListener item;


  @Override public final void onBindViewHolder(@NonNull final List<T> items, final int position,
                                               @NonNull RecyclerView.ViewHolder holder) {
    final BaseViewHolder.OnItemClickListener listener = getOnItemclickListener();

    if(holder != null && listener != null && item != null && !items.isEmpty()){
      holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          listener.onClick(v,items, position);
        }
      });
    }
    onBindViewHolder( items.get(position), (VH) holder, position, items);
  }

  public  VH onCreateViewHolder(@NonNull ViewGroup parent, final BaseViewHolder.OnItemClickListener listener){
    VH vh = onCreateViewHolder(parent);

    return vh;
  }


  /**
   * 获取Item点击事件
   * @return
     */
  @Override
  public BaseViewHolder.OnItemClickListener getOnItemclickListener(){
    return item;
  }


  /**
   * 设置Item点击事件
   * @param listener
     */
  @NonNull
  public void setOnItemClickListener(@NonNull BaseViewHolder.OnItemClickListener listener){
    this.item = listener;
  }

  /**
   * 是否是头部Adpter
   * @return
     */
  @Override
  public boolean isHeaderView() {
    return false;
  }

  /**
   * 是否是底部Adapter
   * @return
     */
  @Override
  public boolean isFooterView() {
    return false;
  }

  /**
   * View绑定
   * @param parent
   * @return ViewHolder
   */
  @NonNull
  @Override public abstract VH onCreateViewHolder(@NonNull ViewGroup parent);



  /**
   * 数据绑定
   * @param item 数据对象
   * @param viewHolder ViewHolder
   */
  protected abstract void onBindViewHolder(@NonNull T item, @NonNull VH viewHolder, int position, @NonNull List<T> items);


  /**
   * 滑动停止
   */
  @Override
  public void onScrollPause() {

  }

  /**
   * 滑动重启
   */
  @Override
  public void onScrollResume() {

  }
}
