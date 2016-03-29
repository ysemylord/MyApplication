package com.personal.assistlibrary.customwidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.personal.assistlibrary.R;

/**
 * Created by xyb on 2016/3/24.
 * 商品进入购物车的效果
 * 使用方式
 * setView()设置要靠近的View
 * setBitmap()设置要显示的View
 * setAnimationListener() 设置动画监听
 * startAnimation（） 开始动画
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
                mToView.setDrawingCacheEnabled(false);

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
     *
     * @param context
     */
    private void initAniamtion(Context context) {
        //mAnimation = (AnimationSet) AnimationUtils.loadAnimation(context, R.anim.cart_animation);

        //因为飞到的位置可能会变，所以使用代码动态设置代码
        mAnimation = new AnimationSet(true);

        //加载动画资源set1--原地变大
        AnimationSet animainSetOne = (AnimationSet) AnimationUtils.loadAnimation(context, R.anim.cart_animation_one);

        //代码实现AniSet2

        final long duration = 600;
        final long startOffset = 700;
        final float scaleFactor = 0.4f;//scale动画的缩放因子

        //因为translate的偏移距离根据当前View与mToView的距离而定，所以需要Translate需要代码设置，
        //又因为Translate动画与Scale动画混用时，平移距离与Scale的缩放有关，所以Scale动画也需要代码设置

        //缩放
        float startScale=1f;
        float endScale=startScale*scaleFactor;
       // float endScale=0.8f;

        ScaleAnimation scaleAnimation = new ScaleAnimation(startScale, endScale,startScale, endScale, 0.5f, 0.5f);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setStartOffset(startOffset);

        //平移


        int locationSelf[] = new int[2];
        int locationToView[] = new int[2];
        getLocationOnScreen(locationSelf);
        mToView.getLocationOnScreen(locationToView);
        float toXDetal = locationToView[0] - locationSelf[0]+ Math.abs(mToView.getWidth()/2f-getWidth()/2f);
        float toYDetal = locationToView[1] - locationSelf[1]+mToView.getHeight()/2f;
        TranslateAnimation translateAnimation = new TranslateAnimation(
                0.0f, (toXDetal) * (1f / endScale),
                0.0f, (toYDetal) * (1f / endScale)
        );
        translateAnimation.setStartOffset(duration);
        translateAnimation.setDuration(startOffset);


        //透明
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0.1f);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setStartOffset(startOffset);

        //AniSet2
        AnimationSet animainSetTow = new AnimationSet(true);
        animainSetTow.addAnimation(translateAnimation);
        animainSetTow.addAnimation(scaleAnimation);
        animainSetTow.addAnimation(alphaAnimation);

        mAnimation.addAnimation(animainSetOne);
        mAnimation.addAnimation(animainSetTow);

    }

    public void setBitmap(Bitmap bitmap) {

        //最总显示的bitmap
        Bitmap needBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(needBitmap);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));

        BitmapShader bitmapShader = new BitmapShader(small(bitmap, getWidth() * 1f / bitmap.getWidth(), getHeight() * 1f / bitmap.getHeight()), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        //画图
        Paint bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);
        bitmapPaint.setShader(bitmapShader);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, bitmapPaint);

  /*      //画边角的圆 锯齿效果很明显
        Paint strokPaint=new Paint();
        strokPaint.setStrokeCap(Paint.Cap.ROUND);
        strokPaint.setStrokeJoin(Paint.Join.ROUND);
        strokPaint.setAntiAlias(true);
        strokPaint.setStrokeWidth(1);
        strokPaint.setStyle(Paint.Style.STROKE);
        strokPaint.setColor(Color.BLUE);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, strokPaint);*/


        setImageBitmap(needBitmap);

    }

    /**
     * 通过imageview设置bitmap
     *
     * @param view
     */
    public void setBitmap(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();
        setBitmap(bitmap);
        view.setDrawingCacheEnabled(false);
    }


    private Bitmap small(Bitmap bitmap, float scalex, float scaley) {
        Matrix matrix = new Matrix();
        matrix.postScale(scalex, scaley); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }
}
