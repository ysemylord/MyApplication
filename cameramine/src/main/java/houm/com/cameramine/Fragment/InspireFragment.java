package houm.com.cameramine.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import houm.com.cameramine.R;

/**
 * Created by Administrator on 2015/12/3.
 */
public class InspireFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inspire, container, false);
        return view;
    }
}
