package houm.com.cameramine;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by xuyaf on 2016/4/11.
 */
public class BaseApplication extends Application {


    private File picDir;
    public boolean isJoiningActivity;//默认为false
    public File choosedFile;
    public String choosedActivityObjectId;


    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private static BaseApplication baseApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, "ohXfA2SwBxT9EYyrvCG9qEn2-gzGzoHsz", "LNQApV1JjEIc7MEtrRdtcfXK");
        /*AVObject testObject = new AVObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();*/
        initImageLoader(getApplicationContext());
        baseApplication=this;
      // savedemo();
       // guanlian();
    }

    public static BaseApplication getInstance(){
        return baseApplication;
    }
    private void guanlian() {
        AVObject myComment = new AVObject("act_joiner");
        myComment.put("name", "参与者3");
        myComment.put("joiner_activity", AVObject.createWithoutData("activity", "570e4ef5df0eea0064608693"));
        myComment.saveInBackground();
        AVObject myComment2 = new AVObject("act_joiner");
        myComment2.put("name", "参与者4");
        myComment2.put("joiner_activity", AVObject.createWithoutData("activity", "570e4ef5df0eea0064608693"));
        myComment2.saveInBackground();
    }

    public File getPicDir() {
        if(picDir!=null){//Application中想外提供的常量都应该有此判断
            return picDir;
        }
        picDir=new File( getFilesDir(),"picture");
        if(picDir.exists()==false){
            picDir.mkdirs();
        }
        return picDir;
    }

    //注，这里不是单例模式，是为了防止editor为空
    public  SharedPreferences.Editor getEditor(){
        if(editor==null){
            editor=getSharedPreferences("user",Context.MODE_PRIVATE).edit();
        }
        return editor;
    }

    public    SharedPreferences getSharePre(){
        if(sharedPreferences==null){
            sharedPreferences=getSharedPreferences("user",Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    private void savedemo() {
        // 创建活动信息
        AVObject activity = new AVObject("activity");
        activity.put("name", "活动");

// 创建参与者信息
        AVObject myComment = new AVObject("act_joiner");
        myComment.put("name", "参与者1");

        // 创建参与者信息
        AVObject myComment2 = new AVObject("act_joiner");
        myComment.put("name", "参与者2");

// 添加一个关联的活动对象
        myComment.put("joiner_activity", activity);

        myComment2.put("joiner_activity",activity);
// 这将保存两条数据，分别为微博信息和评论信息
        myComment.saveInBackground();
    }

    /**
     * 初始化ImageLoader
     */
    private static void initImageLoader(Context context) {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.defaut)          // image在加载过程中，显示的图片
                .showImageForEmptyUri(R.drawable.defaut)  // empty URI时显示的图片
                .resetViewBeforeLoading(false)  // default
                .delayBeforeLoading(1000)
                .cacheInMemory(true)           // default 缓存至内存
                .cacheOnDisc(true)
                .build()      ;




        File cacheDir = StorageUtils.getOwnCacheDirectory(context,
                "bee_k77/Cache");// 获取到缓存的目录地址
        Log.e("cacheDir", cacheDir.getPath());
        // 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                // max width, max height，即保存的每个缓存文件的最大长宽
                .memoryCacheExtraOptions(480, 800)
                        // Can slow ImageLoader, use it carefully (Better don't use it)设置缓存的详细信息，最好不要设置这个
//				 .discCacheExtraOptions(480, 800, CompressFormat.JPEG, 75, null)
                        // 线程池内加载的数量
                .threadPoolSize(3)
                        // 线程优先级
                .threadPriority(Thread.NORM_PRIORITY - 2)
                /*
				 * When you display an image in a small ImageView
				 *  and later you try to display this image (from identical URI) in a larger ImageView
				 *  so decoded image of bigger size will be cached in memory as a previous decoded image of smaller size.
				 *  So the default behavior is to allow to cache multiple sizes of one image in memory.
				 *  You can deny it by calling this method:
				 *  so when some image will be cached in memory then previous cached size of this image (if it exists)
				 *   will be removed from memory cache before.
				 */
//				.denyCacheImageMultipleSizesInMemory()

                        // You can pass your own memory cache implementation你可以通过自己的内存缓存实现
                        // .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                        // .memoryCacheSize(2 * 1024 * 1024)
                        //硬盘缓存50MB
                .memoryCache(new LruMemoryCache(10* 1024 * 1024))
                .diskCacheSize(50 * 1024 * 1024)
                        //将保存的时候的URI名称用MD5
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                        // 加密
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())//将保存的时候的URI名称用HASHCODE加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheFileCount(100) //缓存的File数量
                .diskCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径
                        // .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                        // .imageDownloader(new BaseImageDownloader(context, 5 * 1000,
                        // 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .defaultDisplayImageOptions(options)
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);// 全局初始化此配置
    }
}
