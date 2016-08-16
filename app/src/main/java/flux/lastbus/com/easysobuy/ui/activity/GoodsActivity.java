package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import flux.lastbus.com.easysobuy.ui.adapter.GoodsAdapter;

/**
 * 商品列表界
 * Created by yuhang on 16-8-16.
 */
public class GoodsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;

    @Override
    public int onContentView() {
        return R.layout.activity_goods;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

public static void start(Context context,String title) {
    Intent starter = new Intent(context, GoodsActivity.class);
    starter.putExtra("title",title);
    context.startActivity(starter);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        setTitle(getIntent().getStringExtra("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));//设置RecyclerView布局管理器为2列垂直排布

        GoodsAdapter adapter = new GoodsAdapter(this, getList());
        recyclerView.setAdapter(adapter);
    }

    private List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("http://img1.imgtn.bdimg.com/it/u=4114387076,2373356283&fm=21&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=2612016176,1507418585&fm=21&gp=0.jpg");
        list.add("http://img5.imgtn.bdimg.com/it/u=2307241750,4111493921&fm=23&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=965425830,562257874&fm=21&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=1755148271,589576565&fm=21&gp=0.jpg");
        list.add("http://img5.imgtn.bdimg.com/it/u=3816950372,3033535057&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=1994322345,4082006221&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=2085089256,2612929678&fm=21&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=2919570024,3794407380&fm=21&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=1294179439,1308266161&fm=21&gp=0.jpg");
        list.add("http://img5.imgtn.bdimg.com/it/u=3660658123,3063414092&fm=21&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=2863850510,83886359&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=1259361375,4281815431&fm=23&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=3233324191,2738945901&fm=21&gp=0.jpg");
        list.add("http://img5.imgtn.bdimg.com/it/u=2157718066,800719735&fm=11&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=2339248553,1787846308&fm=21&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=411047841,1441779106&fm=23&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=113018455,2728659224&fm=23&gp=0.jpg");
        list.add("http://img5.imgtn.bdimg.com/it/u=3234033299,3081972484&fm=23&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=865943159,1813406089&fm=23&gp=0.jpg");
        list.add("http://img5.imgtn.bdimg.com/it/u=3478102351,2537163962&fm=23&gp=0.jpg");
        list.add("http://img5.imgtn.bdimg.com/it/u=301162848,3550073963&fm=21&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=3714644428,1901090386&fm=21&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=3081875685,2614790272&fm=21&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=3475176033,3104049290&fm=21&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=96316196,1674967944&fm=21&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=3105356096,3078272854&fm=21&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=1710304944,2372330959&fm=21&gp=0.jpg");
        list.add("http://img5.imgtn.bdimg.com/it/u=4111242388,3681447749&fm=21&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=826953800,2674980830&fm=21&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=2929490024,2000771814&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=1510630957,1314606299&fm=21&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=2502449660,1049038283&fm=21&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=996276226,1427926133&fm=21&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=1368447750,2455823962&fm=21&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=3996516648,111317899&fm=21&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=4062783570,3214991676&fm=21&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=3300104809,1523134140&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=1482602730,1397290368&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=1055774559,3283919509&fm=21&gp=0.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=2502449660,1049038283&fm=21&gp=0.jpg");
        list.add("http://img2.imgtn.bdimg.com/it/u=2053130824,1345301368&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=2686850590,1907547486&fm=21&gp=0.jpg");


        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_goods, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        SearchView.SearchAutoComplete textView = ( SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        textView.setHintTextColor(getResources().getColor(android.R.color.white));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.floatingActionButton)
    public void onClick() {
    }
}
