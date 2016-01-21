package com.example.sijia.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by xu on 2016/1/20.
 */
public abstract class SimpleRecyclerViewAdapter2<T> extends RecyclerView.Adapter<SimpleRecyclerViewAdapter2.SimpleViewHolder> implements View.OnClickListener{

    private Context mContext;
    private List<T> mList;
    private int mResourceId;
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;
    public SimpleRecyclerViewAdapter2(Context context, List<T> list, int resourceId) {
        mContext = context;
        mList = list;
        mResourceId=resourceId;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(mResourceId, parent, false);
        SimpleViewHolder viewHolder = new SimpleViewHolder(view);
        return viewHolder;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(SimpleViewHolder holder,  int position) {
            if(mOnRecyclerViewItemClickListener!=null){
                holder.itemView.setOnClickListener(this);
                holder.itemView.setTag(mList.get(position));
            }
    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener<T> onRecyclerViewItemClickListener){
            mOnRecyclerViewItemClickListener=onRecyclerViewItemClickListener;
    }
    @Override
    public void onClick(View v) {
        if (mOnRecyclerViewItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnRecyclerViewItemClickListener.onItemClick(v,v.getTag());
        }
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

    //define interface
    public  static interface OnRecyclerViewItemClickListener<T> {
        void onItemClick(View view,  T data);
    }

}
