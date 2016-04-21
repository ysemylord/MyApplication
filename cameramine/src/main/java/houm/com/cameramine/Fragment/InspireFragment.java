package houm.com.cameramine.Fragment;

import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import houm.com.cameramine.ActivityDetailActivity;
import houm.com.cameramine.R;
import houm.com.cameramine.adapter.ActivityAdapter;

/**
 * Created by Administrator on 2015/12/3.
 */
public class InspireFragment extends Fragment {

    @Bind(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    ActivityAdapter mActivityAdapter;
    LinearLayoutManager mLinearLayoutManager;

    public static final String activity_av_object="houm.com.cameramine.Fragment.InspireFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inspire, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView.setLayoutManager(mLinearLayoutManager = new LinearLayoutManager(getActivity()));
        mActivityAdapter = new ActivityAdapter(getActivity());
        mRecyclerView.setAdapter(mActivityAdapter);

        mActivityAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                AVObject avObject=mActivityAdapter.getItem(position);
                Intent intent=new Intent(getActivity(), ActivityDetailActivity.class);
                intent.putExtra(InspireFragment.activity_av_object,avObject.toString());
                startActivity(intent);

            }
        });

        refresh();
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visiblePostoin = mLinearLayoutManager.findFirstVisibleItemPosition();
                AVObject avObject= mActivityAdapter.getItem(visiblePostoin);
                String imageUrl=avObject.getAVFile("cover_img_1").getUrl();
                Log.i("imageUrl",imageUrl);
            }
        });
        return view;
    }

    private void refresh() {
        final AVQuery<AVObject> query = new AVQuery<AVObject>("activity");
        SwipeRefreshLayout.OnRefreshListener refreshListener = null;

        mRecyclerView.setRefreshListener(refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                query.findInBackground(new FindCallback<AVObject>() {
                    public void done(List<AVObject> avObjects, AVException e) {
                        if (e == null) {
                            mActivityAdapter.clear();
                            Log.i("成功", "查询到" + avObjects.size() + " 条符合条件的数据");
                            mActivityAdapter.addAll(avObjects);
                        } else {
                            Log.i("失败", "查询错误: " + e.getMessage());
                        }
                    }
                });
            }
        });
        refreshListener.onRefresh();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
