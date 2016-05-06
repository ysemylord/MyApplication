package com.example.sijia.myapplication.TestActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sijia.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TranslateAndScaleMixActivity extends AppCompatActivity {

    @Bind(R.id.image)
    ImageView mImage;
    @Bind(R.id.come_here)
    Button mComeHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_and_scale_mix);
        ButterKnife.bind(this);
    }

    public void start(View view) {
        float startScale = 1f;
        float endScale = startScale*0.4f;
        int startOff=0;
        int duratoin=700;

        ScaleAnimation scaleAnimation = new ScaleAnimation(
                startScale, endScale,
                startScale, endScale,
                0.5f,0.5f);
        scaleAnimation.setStartOffset(startOff);
        scaleAnimation.setDuration(duratoin);

        int mImageLocation[] = new int[2];
        int mComeHereLocation[] = new int[2];
        mImage.getLocationOnScreen(mImageLocation);
        mComeHere.getLocationOnScreen(mComeHereLocation);

        float toXdelate=(mComeHereLocation[0] - mImageLocation[0])+mComeHere.getWidth()/2f;
        float toYdelate=(mComeHereLocation[1] - mImageLocation[1])-mComeHere.getHeight()/2f;
        TranslateAnimation translateAnimation = new TranslateAnimation(
                0, (toXdelate) ,
                0, (toYdelate));
        translateAnimation.setStartOffset(startOff);
        translateAnimation.setDuration(duratoin);

        AlphaAnimation alphaAnimation=new AlphaAnimation(1f,0.5f);
        alphaAnimation.setStartOffset(startOff);
        alphaAnimation.setDuration(duratoin);

        AnimationSet animationSet = new AnimationSet(true);


        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);


        final AnimationSet animationSet2=new AnimationSet(true);

        TranslateAnimation translateAnimation2 = new TranslateAnimation(
                0, -mComeHere.getWidth()/8f ,
                0, +mComeHere.getHeight()/2f);
        translateAnimation2.setStartOffset(startOff+700);
        translateAnimation2.setDuration(300);
        animationSet2.addAnimation(translateAnimation2);




        AnimationSet animationSetHe=new AnimationSet(true);
        animationSetHe.addAnimation(animationSet);
        animationSetHe.addAnimation(animationSet2);

        mImage.startAnimation(animationSetHe);



    }
}