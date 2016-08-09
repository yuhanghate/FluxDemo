package flux.lastbus.com.easysobuy.ui.listener;

/**
 * RecyclerView刷新界面View常用状态回调接口
 * Created by yuhang on 16-8-4.
 */
public interface OnRecyclerViewListener {

    /**
     * 首次加载加载条显示
     */
    void onProgressbarView();

    /**
     * 数据异常View
     */
    void onErrorView();

    /**
     * 数据不存在View
     */
    void onEmptyView();

    /**
     * 正常显示View列表
     */
    void onShowRecyclerView();
}
