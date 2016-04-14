package houm.com.cameramine;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.ImageView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import houm.com.cameramine.Fragment.InspireFragment;
import houm.com.cameramine.adapter.ItemDecor.SpacesItemDecoration;
import houm.com.cameramine.adapter.JoinerAdapter;

public class ActivityDetailActivity extends AppCompatActivity {

    @Bind(R.id.backdrop)
    ImageView mBackdrop;
    @Bind(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    private AVObject mActiAvObject;
    private String mActivityId;
    private JoinerAdapter mJoinerAdapter;
    private SwipeRefreshLayout.OnRefreshListener mOnrefrsh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_detail);
        ButterKnife.bind(this);

        LinearLayoutManager mLinearLayoutManager;
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mJoinerAdapter = new JoinerAdapter(this);
        mRecyclerView.setAdapter(mJoinerAdapter);
        String strObj = getIntent().getStringExtra(InspireFragment.activity_av_object);
        try {
            mActiAvObject = (AVObject) AVObject.parseAVObject(strObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageLoader.getInstance().displayImage(mActiAvObject.getAVFile("cover_img_1").getUrl(), mBackdrop);

        mActivityId = mActiAvObject.getObjectId();

        //设置item之间的间隔
        SpacesItemDecoration decoration = new SpacesItemDecoration(8);
        mRecyclerView.addItemDecoration(decoration);


        mRecyclerView.setRefreshListener( mOnrefrsh =new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                QUERY();


            }
        });
        mOnrefrsh.onRefresh();

    }

    private void QUERY() {
        AVQuery<AVObject> query = new AVQuery<AVObject>("act_joiner");
        // query.whereEqualTo("joiner_activity", mActiAvObject);//条件查询

        query.findInBackground(new FindCallback<AVObject>() {
            public void done(List<AVObject> avObjects, AVException e) {
                if (e == null) {
                    mJoinerAdapter.clear();
                    mJoinerAdapter.addAll(avObjects);
                    Log.i("mActivityId", mActivityId);
                    Log.d("成功", "查询到" + avObjects.size() + " 条符合条件的数据");
                } else {
                    Log.d("失败", "查询错误: " + e.getMessage());
                }
            }
        });
    }

}
