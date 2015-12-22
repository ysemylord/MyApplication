package houm.com.cameramine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import houm.com.cameramine.Fragment.CameraFragment;
import houm.com.cameramine.Fragment.ExploreFragment;
import houm.com.cameramine.Fragment.InspireFragment;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.rg)
    RadioGroup mRG;
    @Bind(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    FragmentManager mFraMan = null;
    final String INSPIRETAG = "inspire";
    final String CAMERATAG = "camera";
    final String EXPLORETAG = "explore";
    @Bind(R.id.camera_tab)
    RadioButton mCameraTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFraMan = getSupportFragmentManager();
        mRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                showFragment(checkedId);
            }
        });
        mCameraTab.setChecked(true);
    }

    private Fragment showFragment(int checkedId) {
        Fragment fragment = null;
        String tag = checkedIdToTag(checkedId);
        fragment = mFraMan.findFragmentByTag(tag);
        FragmentTransaction transaction = mFraMan.beginTransaction();
        hideFragment(transaction);
        if (fragment == null) {
            fragment = checkedIdToFragment(checkedId);
            transaction.add(R.id.fragment_container, fragment, tag);
            Log.i("addFragment", tag);
        } else {
            transaction.show(fragment);
            Log.i("showFragment", tag);
        }
        transaction.commit();
        return fragment;
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mFraMan.getFragments() == null) return;
        for (Fragment fragment : mFraMan.getFragments()) {
            transaction.hide(fragment);
        }
    }

    /**
     * 通过选中的RadioButton的id，找到Fragment的Tag
     *
     * @param rbid
     * @return
     */
    private String checkedIdToTag(int rbid) {
        switch (rbid) {
            case R.id.inspire_tab:
                return INSPIRETAG;
            case R.id.camera_tab:
                return CAMERATAG;
            case R.id.explore_tab:
                return EXPLORETAG;
        }
        return null;
    }

    /**
     * 通过选中的RadioButton的id，找到Fragment
     *
     * @param rbId
     * @return
     */
    private Fragment checkedIdToFragment(int rbId) {
        Fragment fragment = null;
        switch (rbId) {
            case R.id.inspire_tab:
                return new InspireFragment();
            case R.id.camera_tab:
                return new CameraFragment();
            case R.id.explore_tab:
                return new ExploreFragment();
        }
        return fragment;
    }

}
