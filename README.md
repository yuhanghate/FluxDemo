# FluxDemo

<br>

  aboutgif
<br>
![image](https://github.com/yuhanghate/FluxDemo/blob/master/art/about.gif )

<br>

  商品列表
<br>
![image](https://github.com/yuhanghate/FluxDemo/blob/master/art/GoodsList.gif )

<br>

  登陆
<br>
![image](https://github.com/yuhanghate/FluxDemo/blob/master/art/login.gif )

##使用Flux架构
View -> Creator -> Http/Database/SharePreferce -> Dispatcher -> Store
  
  说明：单向数据流。

<br>

  View -> Creator: 使用Dagger2进行注入调用
<br>


  Creator -> Store :使用Dispatcher管理Store，对所有在View层注册的Store进行通知。根据Type区别事件类型
<br>


  Store -> View :使用Rxbus进行数据传递。

<br>
<br>
<br>
<br>

##应用功能介绍



###Application功能：
  用Dagger2进行依次进行Http/Database/Flux注入，以达到单例效果。可以全局使用<br>



###Activity层：
  常用功能注入：使用Application层全所功能对象。
  View对象及事件注入：使用ButterKnife进行快速初始化View及点击事件<br>



###Fragment层：
  常用功能注入：使用Activity层注入器，共享Application级对象及Activity级对象<br>
  View注入：使用ButterKnife进行快速注入<br>



###Adapter层：
  代表：进行细化View。使用代表概念，每种布局都是一个代表。每个Adapter由一个或者多个代表组成。<br>
  Adapter:对代表进行管理。具体事件由每个代表自己完成<br>
  RecyclerView：增加上拉刷新，下拉更多，空白页，错误页，等待界面<br>


<br>
<br>
<br>

##数据读写方案
  按优先级从高到低排列<br>

  1.内存 -> 数据库 -> 网络 <br>
  2.SD -> 网络 <br>
  3.数据库 -> 网络 <br>

<br>
<br>
<br>


##View展现方案：
  1.读取本地缓存 -> View显示 -> 进度条显示 -> 读取网络数据 -> 更新本地缓存 -> View显示 ->进度条隐藏 <br>

###空白页
  本地缓存及网络都没有数据<br>

###异常页
  数据库/网络异常。可以点击刷新重新加载<br>

###数据加载页
  本地缓存无数据显示<br>

<br>
<br>
<br>

##离线View
可以分页加载本地数据<br>

  1.读取本地缓存 -> View显示 ->提示查看网络设置<br>

  
