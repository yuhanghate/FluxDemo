package flux.lastbus.com.easysobuy.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuhang on 16-6-8.
 */
public class Object2Map {

    public static Map<String, String> object2Map(Object obj){
        Map<String, String> map = new HashMap<>();
        /*
        * 得到类中的所有属性集合
        */
        Field[] fs = obj.getClass().getDeclaredFields();
        for(int i = 0 ; i < fs.length; i++){
            Field f = fs[i];
            f.setAccessible(true); //设置些属性是可以访问的
            try {
            String type = f.getType().toString();//得到此属性的类型
            if (type.endsWith("String")) {
                    if( f.get(obj) == null || "TAG".equals(f.getName()))
                        continue;
                    map.put(f.getName(), (String)f.get(obj));

            }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return map;
    }
}
