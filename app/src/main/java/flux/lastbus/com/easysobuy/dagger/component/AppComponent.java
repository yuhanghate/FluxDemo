package flux.lastbus.com.easysobuy.dagger.component;

/**
 * Application注入器
 * Created by yuhang on 16-7-27.
 */
/*@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(App application);

    *//********************** Application区域开始 ********************************//*



    *//*********************** Application区域结束 *******************************//*





    *//********************** Database区域开始 ********************************//*

   *//* *//**//**
     * 数据库版本升级
     * @return
     *//**//*
    VersionManageCore getVersionManageCore();

    *//**//**
     * 数据库需要升级的表
     * @return
     *//**//*
    ModuleClassList getModuleClassList();

    *//**//**
     * 获取DAO管理对象
     * @return
     *//**//*
    DaoSession getDaoSession();

    *//**//**
     * 获取帐号管理
     * @return
     *//**//*
    UsersManage getUsersManage();

    *//**//**
     * 用户详细信息管理
     * @return
     *//**//*
    UserInfoManage getUserInfoManage();

    *//**//**
     * 网络关键词管理
     * @return
     *//**//*
    SearchNetKeyManage getSearchNetKeyManage();

    *//**//**
     * 本地搜索关键词管理
     * @return
     *//**//*
    SearchManage getSearchManage();*//*


    *//*********************** Database区域结束 *******************************//*





    *//********************** Http区域开始 ********************************//*

//    Retrofit getRetrofit();

    *//*********************** Http区域结束 *******************************//*


    *//********************** 当前登陆用户区域开始 ********************************//*



    *//********************** 当前登陆用户区域开始 ********************************//*



    *//********************** Flux区域开始 ********************************//*

    Dispatcher getDispatcher();

    *//********************** Flux区域开始 ********************************//*



}*/
