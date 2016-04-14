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
public class ActivityAdapter extends RecyclerArrayAdapter<AVObject> {
    public ActivityAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PersonViewHolder(viewGroup);
    }
}

class PersonViewHolder extends BaseViewHolder<AVObject> {
    private ImageView mCoverImage;
    private TextView mJoinerTV;
    private TextView mRewardTV;


    public PersonViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_challenge);
        mCoverImage=$(R.id.cover);
        mJoinerTV = $(R.id.joiner);
        mRewardTV=$(R.id.rewarde);

    }


    @Override
    public void setData( AVObject activity) {
        mJoinerTV.setText(activity.getInt("votes")+"");
        mRewardTV.setText(activity.getString("reward"));
        AVFile imageAVF=activity.getAVFile("cover_img_1");
        String url= imageAVF.getUrl();
        ImageLoader.getInstance().displayImage(url,mCoverImage);
    }

}
