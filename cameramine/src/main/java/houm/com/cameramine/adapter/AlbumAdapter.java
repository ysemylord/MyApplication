package houm.com.cameramine.adapter;


import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import houm.com.cameramine.R;
import houm.com.cameramine.bean.AlbumBean;

/**
 * Created by xuyaf on 2016/4/20.
 */
public class AlbumAdapter extends RecyclerArrayAdapter<AlbumBean> {
    public AlbumAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlbumHolder(parent);
    }
}

class AlbumHolder extends   BaseViewHolder<AlbumBean>{
   private ImageView mImageView;
   private TextView mNameTextView;
    private TextView mNumTextView;

    public AlbumHolder(ViewGroup parent) {
        super(parent, R.layout.item_album_layout);
        mImageView=$(R.id.image);
        mNameTextView =$(R.id.dir_name);
        mNumTextView=$(R.id.file_nums);
    }

    @Override
    public void setData(AlbumBean data) {
       // Bitmap bitmap = Util.decodeBitmap(data.getFirPicUrl());
       // mImageView.setImageBitmap(bitmap);
        ImageLoader.getInstance().displayImage("file://" + data.getFirPicUrl(), mImageView);
        String[] names=data.getDirName().split("/");
        mNameTextView.setText(names[names.length-1]);
        mNumTextView.setText("("+data.getPhotoNums()+")");
    }
}
