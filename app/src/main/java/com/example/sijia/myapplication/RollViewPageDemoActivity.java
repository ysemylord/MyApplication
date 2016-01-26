package com.example.sijia.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.IconHintView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * 地址
 * http://p.codekk.com/detail/Android/Jude95/RollViewPager
 * gdfg
 */
public class RollViewPageDemoActivity extends AppCompatActivity {
    private RollPagerView mRollPagerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_view_page_demo);
        mRollPagerView = (RollPagerView) findViewById(R.id.roll_view_pager);
        //mRollPagerView.setAdapter(new TestLoopAdapter(mRollPagerView));
        List<String> urls = new ArrayList<>();
        urls.add("http://img.kumi.cn/photo/7c/dc/41/7cdc416ad550156a.jpg");
        urls.add("http://img.kumi.cn/photo/74/0c/8f/740c8f277d42a43b.jpg");
        urls.add("http://img.kumi.cn/photo/e3/36/a8/e336a8b2e38b82f0.jpg");
        mRollPagerView.setAdapter(new TestFormantLoopAdapter(mRollPagerView, urls));
        mRollPagerView.setPlayDelay(1000);
        mRollPagerView.setAnimationDurtion(500);
        mRollPagerView.setHintView(new IconHintView(this, R.drawable.abc_btn_check_to_on_mtrl_015, R.drawable.abc_btn_check_to_on_mtrl_000));

        //设置指示器
        //mRollViewPager.setHintView(new ColorPointHintView(this, Color.YELLOW,Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);
    }

    class TestLoopAdapter extends LoopPagerAdapter {
        private int[] imgs = {
                R.drawable.ptr_rotate_arrow,
                R.mipmap.ic_launcher,
                R.mipmap.splash
        };


        public TestLoopAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        protected int getRealCount() {
            return imgs.length;
        }
    }

    class TestFormantLoopAdapter extends LoopPagerAdapter {

        private List<String> mImageUrls = null;/* {
               http://img.kumi.cn/photo/7c/dc/41/7cdc416ad550156a.jpg,
                "http://img.kumi.cn/photo/74/0c/8f/740c8f277d42a43b.jpg",
                "http://img.kumi.cn/photo/e3/36/a8/e336a8b2e38b82f0.jpg"
        };*/

        public TestFormantLoopAdapter(RollPagerView viewPager, List<String> imgs) {
            super(viewPager);
            mImageUrls = imgs;
        }


        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            // view.setImageResource(imgs[position]);
            //创建默认的ImageLoader配置参数
            ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                    .createDefault(RollViewPageDemoActivity.this);

            //Initialize ImageLoader with configuration.//
            ImageLoader.getInstance().init(configuration);
            ImageLoader.getInstance().displayImage(mImageUrls.get(position), view);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        protected int getRealCount() {
            return mImageUrls.size();
        }
    }
}
