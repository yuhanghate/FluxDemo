package flux.lastbus.com.easysobuy.flux.store.fragment;

import java.util.List;

import flux.lastbus.com.easysobuy.flux.action.BaseAction;
import flux.lastbus.com.easysobuy.flux.action.TestAction;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import flux.lastbus.com.easysobuy.flux.store.event.ChangeEvent;
import flux.lastbus.com.easysobuy.flux.store.event.TestEvent;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;

/**
 * Created by yuhang on 16-8-3.
 */
public class TestStore extends BaseStore {
    TestEvent mTestEvent;
    List<BaseAdapterData> mList;

    boolean isProgressbar;
    boolean isError;
    boolean isEmpty;
    int count;

    public TestStore() {
        mTestEvent = new TestEvent();
    }

    @Override
    public void onAction(BaseAction action) {
        initStutus();
        switch (action.getType()){
            case TestAction.ACTION_TEST_SHOW:
                mList = getParcelableArrayList(action);
                isEmpty = mList == null || mList.isEmpty();
                count((TestAction) action);
                break;
            case TestAction.ACTION_TEST_ERROR:
                isError = true;
                break;
            case TestAction.ACTION_TEST_PROGRESSBAR:
                isProgressbar = true;
                break;
        }
        mTestEvent.setType(action.getType());
        emitStoreChange();
    }

    @Override
    protected ChangeEvent getChangeEvent() {
        return mTestEvent;
    }

    /**
     * 初始化状态
     */
    private void initStutus(){
        isProgressbar = false;
        isEmpty = false;
        isError = false;
    }

    /**
     * 统计分页的页数
     * @param action
     */
    private void count(TestAction action){
        count = action.getCount();
        if(!isEmpty()){
            count++;
        }
    }

    /**
     * 获取测试数据
     * @return
     */
    public List<BaseAdapterData> getListItem(){
        return mList;
    }

    /**
     * 是否显示加载条
     * @return
     */
    public boolean isProgressbar(){return isProgressbar;}

    /**
     * 是否加载异常
     * @return
     */
    public boolean isError(){return isError;}

    /**
     * 是否没有数据
     * @return
     */
    public boolean isEmpty(){return isEmpty;}

    /**
     * 获取当前分页的页数
     * @return
     */
    public int getCount(){
        return count;
    }

}
