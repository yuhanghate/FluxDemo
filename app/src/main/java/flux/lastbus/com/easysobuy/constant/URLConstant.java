package flux.lastbus.com.easysobuy.constant;

import java.io.File;

/**
 * Created by yuhang on 16-7-27.
 */
public class URLConstant {
    /**
     * 与服务器端连接的协议名
     */
    public static final String PROTOCOL = "http://";

    /**
     * 服务器域名
     */
    public static final String HOST = "test.easysobuy.com/";

    /**
     * 应用上下文名
     */
    public static final String APP = "mobile";///mobile

    /**
     * 服务器基本路径
     */
    public static final String URL = PROTOCOL+HOST+APP+ File.separator;
}

