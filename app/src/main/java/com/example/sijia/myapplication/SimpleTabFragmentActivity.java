package com.example.sijia.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.sijia.myapplication.FormatAdapter.FormatFragmentTabAdapter;
import com.example.sijia.myapplication.FormatAdapter.FragmentTabAdapter;
import com.example.sijia.myapplication.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于简化RadioGroup配上Fragment切换的创建过程的demo
 */
public class SimpleTabFragmentActivity extends FragmentActivity {
    FrameLayout mFrameLayout;
    FragmentTabAdapter fragmentTabAdapter;
    List<Fragment> mFragments;
    List<RadioButton> mRadioButtons;
    RadioGroup mRadioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_tab_fragment);
        mRadioGroup= (RadioGroup) findViewById(R.id.radiogroup);
        mFragments=new ArrayList<>();
        mRadioButtons=new ArrayList<>();

        mFrameLayout= (FrameLayout) findViewById(R.id.content_frame);
        mFragments.add(new MyFragment());
        mFragments.add(new MyFragment());
        mFragments.add(new MyFragment());
        //fragmentTabAdapter=new FragmentTabAdapter(this,mFragments,R.id.content_frame,mRadioGroup);

        mRadioButtons.add((RadioButton) findViewById(R.id.rb_one));
        mRadioButtons.add((RadioButton) findViewById(R.id.rb_two));
        mRadioButtons.add((RadioButton) findViewById(R.id.rb_three));
        new FormatFragmentTabAdapter(this,mFragments,R.id.content_frame,mRadioButtons);
    }


}
