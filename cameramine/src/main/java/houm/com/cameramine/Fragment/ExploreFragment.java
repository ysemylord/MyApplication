package houm.com.cameramine.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import houm.com.cameramine.AppAlbumActivity;
import houm.com.cameramine.BaseApplication;
import houm.com.cameramine.PhotoActivity;
import houm.com.cameramine.R;

/**
 * Created by Administrator on 2015/12/3.
 */
public class ExploreFragment extends Fragment {
    @Bind(R.id.app_photo)
    Button mAppPhoto;
    @Bind(R.id.album)
    Button mAlbum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        ButterKnife.bind(this, view);
        mAppPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent=new Intent(getActivity(), PhotoActivity.class);
                intent.putExtra("dirName",((BaseApplication) getActivity().getApplication()).getPicDir());
                startActivity(intent);
            }
        });

        mAlbum.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AppAlbumActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
