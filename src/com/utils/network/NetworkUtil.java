package com.utils.network;

import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class NetworkUtil {

	 /** 
     * 建立HTTP请求，并获取Bitmap对象。 
     *  
     * @param imageUrl 
     *            图片的URL地址 
     * @return 解析后的Bitmap对象 
     */  
    public static Bitmap downloadBitmap(String imageUrl) {  
        Bitmap bitmap = null;  
        HttpURLConnection con = null;  
        try {  
            URL url = new URL(imageUrl);  
            con = (HttpURLConnection) url.openConnection();  
            con.setConnectTimeout(5 * 1000);  
            con.setReadTimeout(10 * 1000);  
            bitmap = BitmapFactory.decodeStream(con.getInputStream());  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (con != null) {  
                con.disconnect();  
            }  
        }  
        return bitmap;  
    }  
}
