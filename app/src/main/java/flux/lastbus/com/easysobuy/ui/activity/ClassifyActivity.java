package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import flux.lastbus.com.easysobuy.ui.adapter.ClassifyAdapter;
import flux.lastbus.com.easysobuy.widget.recyclerview.DividerItemDecoration;

/**
 * 分类
 * Created by yuhang on 16-8-16.
 */
public class ClassifyActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    public int onContentView() {
        return R.layout.activity_classify;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ClassifyActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        setTitle("类别");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        ClassifyAdapter adapter = new ClassifyAdapter(this, getList());
        recyclerView.setAdapter(adapter);
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

    private List<String> getList(){
        List<String> list = new ArrayList<>();
        list.add("ABS");
        list.add("AES");
        list.add("AS");
        list.add("CA");
        list.add("ASA");
        list.add("BOPP");
        list.add("BR");
        list.add("CPE");
        list.add("CPVC");
        list.add("EAA");
        list.add("EEA");
        list.add("PP");
        list.add("POE");
        list.add("APT/ABS");
        list.add("TPE");
        list.add("PEX");
        list.add("PPC");
        list.add("PPE");
        list.add("PEI");
        list.add("TPV");
        list.add("UF");
        list.add("TSC");
        list.add("ULDPE");
        list.add("MPPO");
        list.add("GPPS");
        list.add("HIPS");

        return list;
    }
}
