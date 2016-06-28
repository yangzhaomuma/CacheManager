package com.example.cache;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.utils.cache.CacheManager;
import com.utils.network.NetworkUtil;

public class GridAdapter extends ArrayAdapter<String>{

	private Context context;
	private int resource;
	private List<String> objects;
	private ImageView img;
	private View view=null;
	private Set<CusTask> tasks;
	
	public GridAdapter(Context context, int resource, List<String> objects) {
		super(context, resource, objects);
		this.context=context;
		this.resource=resource;
		this.objects=objects;
		tasks=new HashSet<GridAdapter.CusTask>();
	}
   int i=0;
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		String urlString=objects.get(position);
		
		if(convertView!=null){
			view=convertView;
		}else {
			view=LayoutInflater.from(context).inflate(resource, null);
		}
		 img=(ImageView)view.findViewById(R.id.img);
		 img.setImageResource(R.drawable.empty_photo);
		 img.setTag(urlString);
		//TODO
		 Log.e("Cache",String.valueOf(i++));
		Bitmap bitmap=CacheManager.get(urlString);
		if(bitmap!=null){
		img.setImageBitmap(bitmap);
		}else {
			CusTask task=new CusTask();
			tasks.add(task);
			task.execute(urlString);
		}
		return view;
	}

	private class CusTask extends AsyncTask<String, Void, Bitmap>{

		private String url;
		@Override
		protected Bitmap doInBackground(String... params) {
			url=params[0];
			Bitmap bitmap= NetworkUtil.downloadBitmap(url);
			if(bitmap!=null)
			CacheManager.put(url, bitmap);
			return bitmap;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			 // 根据Tag找到相应的ImageView控件，将下载好的图片显示出来。  
            ImageView imageView = (ImageView) view.findViewWithTag(url);  
            if (imageView != null && result != null) {  
            	
                imageView.setImageBitmap(result);  
                tasks.remove(this);
            }  
		}
		
	}
	
}
