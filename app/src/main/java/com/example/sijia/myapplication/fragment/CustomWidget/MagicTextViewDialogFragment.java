package com.example.sijia.myapplication.fragment.CustomWidget;

import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;

import com.example.sijia.myapplication.R;
import com.example.sijia.myapplication.fragment.BaseDialogFragment;
import com.personal.assistlibrary.customwidget.MagicTextView;

/**
 * Created by xuyaf on 2016/3/15.
 */
public class MagicTextViewDialogFragment extends BaseDialogFragment {

    Handler mHandler=new Handler();
    @Override
    protected int getResourse() {
        return R.layout.fragment_magic_textview;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewGroup containerView= (ViewGroup) getView();
        MagicTextView magicTextView= (MagicTextView) containerView.findViewById(R.id.magic_tv);
        magicTextView.setValue(120);
        magicTextView.begin();


    }


}
