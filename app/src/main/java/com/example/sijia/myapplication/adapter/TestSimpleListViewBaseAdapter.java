package com.example.sijia.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sijia.myapplication.R;
import com.example.sijia.myapplication.model.Product;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by xu on 2016/1/21.
 */
public class TestSimpleListViewBaseAdapter extends SimpleListViewBaseAdapter<Product.DatasEntity.GoodsListEntity> {

    public TestSimpleListViewBaseAdapter(Context context, List<Product.DatasEntity.GoodsListEntity> data) {
        super(context, data);
    }
    @Override
    public View getItemView(int position, View convertView, ViewHolder holder) {
        Product.DatasEntity.GoodsListEntity goodsListEntity = getItem(position);
        TextView mTextView = holder.getView(R.id.name);
        ImageView iamge = holder.getView(R.id.image);
        mTextView.setText(goodsListEntity.getGoods_name());
        ImageLoader.getInstance().displayImage(goodsListEntity.getGoods_image_url(), iamge);
        return convertView;
    }

    @Override
    public int getResourece() {
        return R.layout.layout_item_test;
    }
}