package flux.lastbus.com.easysobuy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import flux.lastbus.com.easysobuy.R;
import flux.lastbus.com.easysobuy.flux.store.BaseStore;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by yuhang on 16-8-22.
 */
public class PictureActivity extends BaseActivity {
    public static final String TRANSIT_PIC = "picture";

    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    PhotoViewAttacher mPhotoViewAttacher;
    @Override
    public int onContentView() {
        return R.layout.activity_pricture;
    }

    public static Intent start(Context context,String url) {
        Intent starter = new Intent(context, PictureActivity.class);
        starter.putExtra("url",url);
        return starter;
    }

    @Override
    public BaseStore onCreateStore() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        setTitle("图片");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Picasso.with(this).load(getIntent().getStringExtra("url")).into(mImageView);
//        String url = getIntent().getStringExtra("url");
//        Glide.with(this).load(url).into(mImageView);

        setupPhotoAttacher();

    }

    @Override
    protected void onStart() {
        super.onStart();
        ViewCompat.setTransitionName(mImageView, TRANSIT_PIC);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mPhotoViewAttacher.cleanup();
        super.onDestroy();
    }

    private void setupPhotoAttacher() {
        mPhotoViewAttacher = new PhotoViewAttacher(mImageView);
//        mPhotoViewAttacher.setOnViewTapListener((view, v, v1) -> hideOrShowToolbar());
        // @formatter:off
//        mPhotoViewAttacher.setOnLongClickListener(v -> {
//            new AlertDialog.Builder(PictureActivity.this)
//                    .setMessage(getString(R.string.ask_saving_picture))
//                    .setNegativeButton(android.R.string.cancel,
//                            (dialog, which) -> dialog.dismiss())
//                    .setPositiveButton(android.R.string.ok,
//                            (dialog, which) -> {
//                                saveImageToGallery();
//                                dialog.dismiss();
//                            })
//                    .show();
//            // @formatter:on
//            return true;
//        });

    }
}
