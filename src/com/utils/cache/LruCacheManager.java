package com.utils.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class LruCacheManager {
   
	private LruCache<String, Bitmap> lruCache;
	public LruCacheManager(){
		this((int)Runtime.getRuntime().maxMemory()/1024/8);
	}
	//设置自定义大小的LruCache
	public LruCacheManager(int maxSize){
		lruCache=new LruCache<String, Bitmap>(maxSize*1024){

			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getByteCount()/1024;
			}
			
		};
	}
    /**
     * 写入索引key对应的缓存
     * @param key 索引
     * @param bitmap 缓存内容
     * @return 写入结果
     */
	public Bitmap putCache(String key,Bitmap bitmap){
		Bitmap bitmapValue=getCache(key);
		if(bitmapValue==null){
			if(lruCache!=null&&bitmap!=null)
			bitmapValue= lruCache.put(key, bitmap);
		}
		return bitmapValue;
	}
	/**
	 * 获取缓存
	 * @param key 索引key对应的缓存
	 * @return  缓存
	 */
	public Bitmap getCache(String key){ 
		if(lruCache!=null){
			return lruCache.get(key);
		}
		return null;
	}
	
	public void deleteCache(){
		if(lruCache!=null)
		lruCache.evictAll();
	}
	
	public void removeCache(String key){
		if(lruCache!=null)
		lruCache.remove(key);
	}
	
	public int size(){
		int size=0;
		if(lruCache!=null)
			size+=lruCache.size();
		return size;
	}
}
