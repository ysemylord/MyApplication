package com.example.sijia.myapplication;

import android.app.Application;
import android.os.Build;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.sijia.myapplication.util.Util;
import com.example.sijia.myapplication.util.VolleyQuenueInstence;
import com.orhanobut.logger.Logger;

import java.security.MessageDigest;


/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    public void test(){
        String clinet="ios+324235435435";
        String str=MD5(clinet+"+ggCJall=3");
        Log.i("md5",str);

/*

        Map<String,String> maps=new HashMap<>();
        maps.put("路径1","2014.3.1");
        maps.put("路径2","2014.3.1");
        maps.put("路径3","2014.3.2");
        maps.put("路径4","2014.3.3");

        Map <String,List<String>> res=new HashMap<>();
        maps.entrySet().size();
        for(Map.Entry entry:maps.entrySet()) {
            String keyPath = (String) entry.getKey();
            String keyTime = (String) entry.getValue();
            List list = res.get(keyTime);
            if (list == null) {
                list = new ArrayList();
                list.add(keyPath);
                res.put(keyTime, list);
            } else {
                list.add(keyPath);
            }
        }
*/



            Util.outScreenInfo(getContext());

        int currentapiVersion=android.os.Build.VERSION.SDK_INT;
        if(currentapiVersion>= Build.VERSION_CODES.JELLY_BEAN){
              Log.i("mes",">=");

        }else{
            Log.i("mes","=<");
        }

      /*   float i=5f/2;
        Log.i("value",i+"");

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS");
        String time=sdf.format(new Date());
        Log.i("time",time);*/
    /*    String path=Environment.getDownloadCacheDirectory().getAbsolutePath();
        Log.i("path", path);

        Log.i("DataDirectory()", Environment.getDataDirectory().getAbsolutePath());
        Log.i("DownloadCacheDirectory)", Environment.getDownloadCacheDirectory().getAbsolutePath());
        Log.i("ExternalStorageDir", Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.i("getRootDirectory", Environment.getRootDirectory().getAbsolutePath());
        Log.i("getExternalFilesDir)", Environment.getExternalStorageDirectory().getAbsolutePath());

        Log.i("Context--ExternalCacheDir", getContext().getExternalCacheDir().getAbsolutePath());//
        Log.i("Context---CacheDir", getContext().getCacheDir().getAbsolutePath());*/
    }
    public void testLogger(){
        Logger.init("xybtag");
        Log.i("tag", "message");
        Logger.i("123", "456");
        Logger.i("message");
        Logger.t("mytag").i("message");
        StringRequest stringRequest=new StringRequest(Request.Method.GET, "http://www.ccclsc.com/app/index.php?act=goods&op=goods_list",
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                           Logger.json(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        VolleyQuenueInstence.getInstance(getContext()).add(stringRequest);
        //Logger.json();

    }

    public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}