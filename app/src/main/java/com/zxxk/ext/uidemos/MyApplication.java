package com.zxxk.ext.uidemos;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.telephony.TelephonyManager;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by shitao on 2015/10/10.
 */
public class MyApplication extends Application {

    private static MyApplication mInstance;


    public static MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        initImageLoader(getApplicationContext());
    }



    /**
     * 可设置不同 OPTION1, OPTION2, etc供不同的页面加载网络图片;
     * 详细配置参考：http://www.yq1012.com/android/2053.html
     *              http://www.cnblogs.com/kissazi2/p/3886563.html
     */
    public final static DisplayImageOptions OPTION = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher) //设置图片在下载期间显示的图片
            .showImageForEmptyUri(R.mipmap.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.mipmap.ic_launcher)  //设置图片加载/解码过程中错误时候显示的图片
            .cacheInMemory(true)//设置下载的图片是否缓存在内存中
            .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
            .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示
            .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
            .build();//构建完成

    /**
     * 初始化Universal_Image_Loader 参考：
     * https://github.com/nostra13/Android-Universal-Image-Loader
     * @param context
     */
    private void initImageLoader(Context context) {

        File cacheDir = StorageUtils.getOwnCacheDirectory(this,this.getPackageName()
                +"/imageloader/cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(16 * 1024 * 1024))
                .memoryCacheSize(16 * 1024 * 1024)
                .discCacheSize(100 * 1024 * 1024)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100).writeDebugLogs().build();
        ImageLoader.getInstance().init(config);

    }


    /**
     * 获取手机imei号
     * @return
     */
    public String getDeviceNo() {
        TelephonyManager tm = (TelephonyManager)
                getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }


    /**
     * 获取手机号码
     * @return
     */
    public String getVersionNo() {

        PackageManager manager = getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(),
                    PackageManager.GET_META_DATA);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
