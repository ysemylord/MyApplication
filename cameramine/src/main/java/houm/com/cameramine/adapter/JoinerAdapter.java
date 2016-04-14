package houm.com.cameramine.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import houm.com.cameramine.R;


/**
 * Created by Administrator on 2016/4/6.
 */
public class JoinerAdapter extends RecyclerArrayAdapter<AVObject> {
    public JoinerAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
        return new JoinerViewHolder(viewGroup);
    }
}

class JoinerViewHolder extends BaseViewHolder<AVObject> {
    private ImageView mJoinerImage;


    public JoinerViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_layout_joiner);
        mJoinerImage=$(R.id.joiner_image);

    }


    @Override
    public void setData( AVObject activity) {
        AVFile imageAVF=activity.getAVFile("Image");
        String url= imageAVF.getUrl();
        ImageLoader.getInstance().displayImage(url,mJoinerImage);
    }

}
