package com.personal.assistlibrary.customwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.personal.assistlibrary.R;

/**
 * Created by xyb on 2016/3/24.
 * 商品进入购物车的效果
 */
public class CartAnimationView extends ImageView {
    Context mContext;
    AnimationListener mAnimationListener;
    AnimationSet mAnimation;
    View mToView;

    public CartAnimationView(Context context) {
        super(context);
        mContext = context;

    }


    public CartAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public CartAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }


    public void startAnimation() {
        setVisibility(View.VISIBLE);
        startAnimation(mAnimation);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mAnimationListener != null) {
                    mAnimationListener.onAnimationEnd();
                    setVisibility(View.GONE);
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 向外提供动画完成后的接口
     */
    public interface AnimationListener {

        public void onAnimationEnd();
    }

    /**
     * 设置AnimationListener
     *
     * @param animationListener
     */
    public void setAnimationListener(AnimationListener animationListener) {
        mAnimationListener = animationListener;
    }

    public void setView(View view) {
        mToView = view;
        initAniamtion(mContext);

    }

    /**
     * 初始化动画
     * @param context
     */
    private void initAniamtion(Context context) {
        //mAnimation = (AnimationSet) AnimationUtils.loadAnimation(context, R.anim.cart_animation);

        //因为飞到的位置可能会变，所以使用代码动态设置代码
        mAnimation = new AnimationSet(true);

        //加载动画资源--原地变大
        AnimationSet animainSetOne = (AnimationSet) AnimationUtils.loadAnimation(context, R.anim.cart_animation_one);


        /*TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0.0f, Animation.RELATIVE_TO_PARENT, 0.75f,
                Animation.ABSOLUTE, 0.0f, Animation.RELATIVE_TO_PARENT, -1.1f);*/

        //Scale与Translate混合使用的时候，偏移量由相对于自身，变成了相对于父控件（或者是全屏幕吧），很奇怪
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, mToView.getLeft(),
                Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE, mToView.getTop());
        translateAnimation.setStartOffset(600);
        translateAnimation.setDuration(700);

        AnimationSet animainSet2 = (AnimationSet) AnimationUtils.loadAnimation(context, R.anim.cart_animation_2);

        AnimationSet animainSetTow = new AnimationSet(true);
        animainSetTow.addAnimation(translateAnimation);
        for (int i = 0; i < animainSet2.getAnimations().size(); i++) {
            animainSetTow.addAnimation(animainSet2.getAnimations().get(i));
        }

        mAnimation.addAnimation(animainSetOne);
        mAnimation.addAnimation(animainSetTow);


    }

}
