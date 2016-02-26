package com.example.sijia.myapplication.FormatAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xu on 2016/1/20.
 * 对Recycler.Adpater的简化 RecyclerView.Adapter
 *
 * @see com.example.sijia.myapplication.RecyclerViewDemoActivity
 */
public abstract class SimpleRecyclerViewAdapter<T> extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.SimpleViewHolder> {

    private Context mContext;
    private List<T> mList;


    public SimpleRecyclerViewAdapter(Context context, List<T> list) {
        mContext = context;
        mList = list;

    }




    /**
     * 创建ViewHolder并且，view会交由LayoutManaer管理
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getResourece(), parent, false);
        SimpleViewHolder viewHolder = new SimpleViewHolder(view);

        return viewHolder;
    }


    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        setDate(holder, mList.get(position));
    }

    public abstract  int getResourece();

    public abstract void setDate(SimpleViewHolder holder, T data);

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> views = new SparseArray<View>();
        private View itemView;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        public <T extends View> T getView(int resId) {
            View v = views.get(resId);
            if (null == v) {
                v = itemView.findViewById(resId);
                views.put(resId, v);
            }
            return (T) v;
        }
    }


}
