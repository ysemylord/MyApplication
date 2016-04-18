package houm.com.cameramine;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import houm.com.cameramine.Fragment.CameraFragment;
import houm.com.cameramine.Fragment.ExploreFragment;
import houm.com.cameramine.Fragment.InspireFragment;

public class MainActivity extends ActionBarActivity {

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
    @Bind(R.id.inspire_tab)
    RadioButton mInspireTab;
    @Bind(R.id.tl_custom)
    Toolbar mTlCustom;
    @Bind(R.id.explore_tab)
    RadioButton exploreTab;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.login_btn)
    Button mLoginBtn;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        mFraMan = getSupportFragmentManager();
        mRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                showFragment(checkedId);
            }
        });
        // mCameraTab.setChecked(true);
        mInspireTab.setChecked(true);
    }

    private void init() {
        mTlCustom.setTitle("Toolbar");//设置Toolbar标题
        mTlCustom.setTitleTextColor(Color.parseColor("#ffffff")); //设置标题颜色
        setSupportActionBar(mTlCustom);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mTlCustom, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intetn = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intetn);
            }
        });
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
