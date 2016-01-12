package com.example.sijia.myapplication;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.sijia.myapplication.fragment.MyFragment;

public class ViewPagerActivity extends FragmentActivity {
    ViewPager viewPager = null;
    RadioGroup mRG;
    private LinearLayout mindicotrCon;
    private int mLastPo=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mRG = (RadioGroup) findViewById(R.id.rg);
        mindicotrCon= (LinearLayout) findViewById(R.id.indictor_line);

        viewPagerSetting();
    }


    private void viewPagerSetting() {
        mRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int position = 0;
                for (; position < group.getChildCount(); position++) {
                    if (group.getChildAt(position).getId() == checkedId)
                        break;
                }
                if(mLastPo==position){//避免重复选择
                    mLastPo=position;
                    return;
                }else {
                    mLastPo=position;
                    viewPager.setCurrentItem(position);//设置对应页面
                    Log.i("onCheckedChanged", "设置页面" + position);
                }
            }
        });
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {//改变相应RadioButton
                mRG.check(mRG.getChildAt(position).getId());
                for (int i = 0; i < mindicotrCon.getChildCount(); i++) {
                    if (i == position) {
                        mindicotrCon.getChildAt(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.black_shap));
                    } else {
                        mindicotrCon.getChildAt(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.red_shap));
                    }
                }
                Log.i("onPageSelected","onPageSelected"+" "+ position + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mRG.check(mRG.getChildAt(0).getId());
        mindicotrCon.getChildAt(0).setBackgroundDrawable(getResources().getDrawable(R.drawable.black_shap));
    }

}


class MyAdapter extends FragmentPagerAdapter {


    public MyAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return new MyFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }


}

