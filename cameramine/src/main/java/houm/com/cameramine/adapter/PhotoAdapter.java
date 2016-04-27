package houm.com.cameramine.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

import houm.com.cameramine.R;

/**
 * Created by xuyaf on 2016/4/20.
 */
public class PhotoAdapter extends RecyclerArrayAdapter<File> {

    public PhotoAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(parent);
    }
}

class PhotoViewHolder extends BaseViewHolder<File> {
    private ImageView mImageView;

    public PhotoViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_photo_layout);
        mImageView = $(R.id.image);
    }

    @Override
    public void setData(File data) {
       /* Bitmap bitmap = Util.decodeBitmap(data.getAbsolutePath());
        mImageView.setImageBitmap(bitmap);*/
        ImageLoader.getInstance().displayImage("file://" + data.getAbsolutePath(), mImageView);
    }
}
