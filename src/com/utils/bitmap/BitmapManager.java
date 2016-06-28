package com.utils.bitmap;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapManager {
	
    /**
     * 图片采样
     * @param context
     * @param resourceId
     * @return
     */
    private static BitmapFactory.Options getOptions(Resources resources,int resourceId){
    	BitmapFactory.Options options=new BitmapFactory.Options();
    	options.inJustDecodeBounds=true;
    	BitmapFactory.decodeResource(resources, resourceId, options);
    	return options;
    }
    
    
    private static BitmapFactory.Options getOptions(Resources resources,String filePath){
    	BitmapFactory.Options options=new BitmapFactory.Options();
    	options.inJustDecodeBounds=true;
    	BitmapFactory.decodeFile(filePath, options);
    	return options;
    }
    
    
    private static BitmapFactory.Options getOptions(InputStream is){
    	BitmapFactory.Options options=new BitmapFactory.Options();
    	options.inJustDecodeBounds=true;
    	BitmapFactory.decodeStream(is, null, options);
    	return options;
    }
    
    
  
    
    
    
	/**
	 * 图片缩小比率
	 * @param mOptions 
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	private static int getSampleSize(BitmapFactory.Options mOptions,int reqWidth,int reqHeight){
		int outWidth= mOptions.outWidth;
		int outHeight=mOptions.outHeight;
		int sampleSize=1;
		
		if(outWidth>reqWidth||outHeight>reqHeight){
			int widthRatio=Math.round((float)outWidth/(float)reqWidth);
			int heightRatio=Math.round((float)outHeight/(float)reqHeight);
			sampleSize=widthRatio>heightRatio?heightRatio:widthRatio;
		}
		return sampleSize;
	}
	/**
	 * 获取bitmap
	 * @param resourceId
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static Bitmap decodeSimpleBitmapFromResource(Resources resources,int resourceId,int reqWidth,int reqHeight){
		BitmapFactory.Options options=getOptions(resources, resourceId);
		options.inJustDecodeBounds=false;
		options.inSampleSize=getSampleSize(options, reqWidth, reqHeight);
		Bitmap bitmap= BitmapFactory.decodeResource(resources, resourceId, options);
		return bitmap;
	}
	
	public static Bitmap decodeSimpleBitmapFromFile(Resources resources,String filePath,int reqWidth,int reqHeight){
		BitmapFactory.Options options=getOptions(resources, filePath);
		options.inJustDecodeBounds=false;
		options.inSampleSize=getSampleSize(options, reqWidth, reqHeight);
		Bitmap bitmap= BitmapFactory.decodeFile(filePath, options);
		return bitmap;
	}
	
	public static Bitmap decodeSimpleBitmapFromStream(InputStream is,int reqWidth,int reqHeight){
		Bitmap bitmap= null;
		BitmapFactory.Options options=getOptions(is);
		options.inJustDecodeBounds=false;
		options.inSampleSize=getSampleSize(options, reqWidth, reqHeight);
		try {
			is.reset();
			 bitmap= BitmapFactory.decodeStream(is, null, options);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bitmap;
	}
}
