package com.example.sijia.myapplication.FormatAdapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sijia.myapplication.R;
import com.example.sijia.myapplication.model.Product;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;


public class TestSimpleRecyclerViewAdapter extends SimpleRecyclerViewAdapter<Product.DatasEntity.GoodsListEntity> {


    public TestSimpleRecyclerViewAdapter(Context context, List<Product.DatasEntity.GoodsListEntity> list) {
        super(context, list);
    }

    @Override
    public int getResourece() {
        return R.layout.layout_item_test;
    }

    @Override
    public void setDate(SimpleViewHolder holder, Product.DatasEntity.GoodsListEntity data) {
        TextView nameTV = holder.getView(R.id.name);
        ImageView imageView = holder.getView(R.id.image);
        nameTV.setText(data.getGoods_name());
        ImageLoader.getInstance().displayImage(data.getGoods_image_url(), imageView);
    }
}
