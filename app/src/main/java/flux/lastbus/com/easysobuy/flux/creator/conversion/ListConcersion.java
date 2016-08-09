package flux.lastbus.com.easysobuy.flux.creator.conversion;

import android.os.Bundle;

import java.util.ArrayList;

import flux.lastbus.com.easysobuy.utils.BundleUtil;
import flux.lastbus.com.easysobuy.widget.recyclerview.BaseAdapterData;

/**
 * Created by yuhang on 16-8-3.
 */
public class ListConcersion {
    public static Bundle getList2Bundle(ArrayList<BaseAdapterData> list, String aciton){
        return BundleUtil.createBundleList( list, aciton);

    }
}
