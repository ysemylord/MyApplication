package houm.com.cameramine;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import houm.com.cameramine.adapter.AlbumAdapter;
import houm.com.cameramine.bean.AlbumBean;

public class AppAlbumActivity extends BaseActivity {

    @Bind(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    private AlbumAdapter mAlbumAdapter;
    private Map<String, List<String>> mGruopMap = new HashMap<>();
    private List<AlbumBean> mAlbumBeans = new ArrayList<>();
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_album);
        getSupportActionBar().setTitle("相册");
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mAlbumAdapter = new AlbumAdapter(this);
        mRecyclerView.setAdapter(mAlbumAdapter);
        mAlbumAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                AlbumBean albumBean = mAlbumAdapter.getItem(position);
                File dir = new File(albumBean.getDirName());
                Intent intent = new Intent(AppAlbumActivity.this, PhotoActivity.class);
                intent.putExtra("dirName", dir);
                startActivity(intent);
                //
                BaseApplication baseApplication = (BaseApplication) getApplication();
                if (baseApplication.isJoiningActivity) {
                    finish();
                }
                //
            }
        });

        notRX();
    }




    private void notRX() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                String picDirStr = BaseApplication.getInstance().getPicDir().getAbsolutePath();
                List<String> chileListmy = new ArrayList<String>();
                for (File file : BaseApplication.getInstance().getPicDir().listFiles()) {
                    chileListmy.add(file.getAbsolutePath());
                }
                mGruopMap.put(picDirStr, chileListmy);


                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = getContentResolver();

                //只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);

                if (mCursor == null) {
                    return;
                }

                while (mCursor.moveToNext()) {
                    //获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));

                    //获取该图片的父路径名
                    String parentName = new File(path).getParentFile().getAbsolutePath();


                    //根据父路径名将图片放入到mGruopMap中
                    if (!mGruopMap.containsKey(parentName)) {
                        List<String> chileList = new ArrayList<String>();
                        chileList.add(path);
                        mGruopMap.put(parentName, chileList);
                        Log.i("file", parentName + "--" + path);
                    } else {
                        mGruopMap.get(parentName).add(path);
                        Log.i("file", parentName + "--" + path);
                    }
                }

                for (Map.Entry<String, List<String>> entry : mGruopMap.entrySet()) {
                    String dirName = entry.getKey();
                    int num = entry.getValue().size();
                    String firlUrl = entry.getValue().get(0);
                    mAlbumBeans.add(new AlbumBean(dirName, num, firlUrl));
                }

                //通知Handler扫描图片完成
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mAlbumAdapter.addAll(mAlbumBeans);
                    }
                });
                mCursor.close();
            }
        }).start();
    }
}
