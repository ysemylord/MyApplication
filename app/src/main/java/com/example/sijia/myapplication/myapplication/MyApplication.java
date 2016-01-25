package com.example.sijia.myapplication.myapplication;

import android.app.Application;
import android.graphics.Bitmap;

import com.example.sijia.myapplication.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * MyApplication
 *
 * @author minking
 */
//TODO 调用 使用了此自定义的Application 并修改了配置
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化ImageLoader
        @SuppressWarnings("deprecation")
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.ic_launcher) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                        // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build(); // 创建配置过得DisplayImageOption对象

        Runtime rt = Runtime.getRuntime();
        long maxMemory = rt.maxMemory();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(options)//默认的option
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new LruMemoryCache((int) (maxMemory / 10)))
                .memoryCacheSize((int) (maxMemory / 10))//内存最大缓存
                .denyCacheImageMultipleSizesInMemory()//设置不允许内存缓存缓存一张图片的多个尺寸。
                .discCacheSize(50 * 1240 * 1240)//硬盘最大缓存
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }

}
