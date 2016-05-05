package houm.com.cameramine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import houm.com.cameramine.adapter.PhotoAdapter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class PhotoActivity extends BaseActivity {

    @Bind(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    private PhotoAdapter mPhotoAdapter;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        getSupportActionBar().setTitle("相片");
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mPhotoAdapter = new PhotoAdapter(this);
        mRecyclerView.setAdapter(mPhotoAdapter);
        mPhotoAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                BaseApplication baseApplication = (BaseApplication) getApplication();
                if (baseApplication.isJoiningActivity) {
                    Intent itent = new Intent(PhotoActivity.this, UpLoadActivity.class);
                    startActivity(itent);
                    baseApplication.choosedFile = mPhotoAdapter.getItem(position);
                    finish();
                }
            }
        });


        Observable
                .create(new Observable.OnSubscribe<File>() {
                    @Override
                    public void call(Subscriber<? super File> subscriber) {
                        File picDir = (File) getIntent().getSerializableExtra("dirName");
                        subscriber.onNext(picDir);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<File, File[]>() {
                    @Override
                    public File[] call(File picDir) {
                        return picDir.listFiles();
                    }
                }).subscribe(new Action1<File[]>() {
            @Override
            public void call(File[] files) {
                mPhotoAdapter.addAll(files);
            }
        });

/*        new Thread(new Runnable() {
            @Override
            public void run() {
                final File picDir = (File) getIntent().getSerializableExtra("dirName");
                for (File picFile : picDir.listFiles()) {
                    Log.i("picFile", picFile.getAbsolutePath());
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mPhotoAdapter.addAll(picDir.listFiles());
                    }
                });
            }
        }).start();*/
    }
}
