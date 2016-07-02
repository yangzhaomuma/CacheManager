# CacheManager
LruCache（内存缓存）与DiskLruCache（硬盘缓存）统一框架。<br>

这是一个整合了内存缓存与硬盘缓存的图片缓存框架。<br>
一、默认简略使用方式如下：<br>
/**<br>
	 * 初始化缓存管理<br>
	 * @param context 上下文<br>
	 */<br>
CacheManager.init(Context context);<br>
/**<br>
	 * 获取索引key对应的缓存内容<br>
	 * @param key 缓存索引key<br>
	 * @return  key索引对应的Bitmap数据<br>
	 */<br>
CacheManager.get(String key)<br>

/**<br>
	 * 索引key对应的bitmap写入缓存<br>
	 * @param key 缓存索引<br>
	 * @param bitmap bitmap格式数据<br>
	 */<br>
CacheManager.put(String key,Bitmap bitmap)<br>

CacheManager中还提供其他方法：<br>
delete：删除缓存<br>
size：获取缓存大小<br>
deleteFile：删除某文件下的缓存<br>
close：关闭缓存<br>
setCacheModel：选择模式<br>
二、模式选择<br>
有三种模式可供选择，可通过setCacheModel方式选择开启内存缓存或者硬盘缓存或者默认的二者都开启。<br>

三、可扩展<br>
有需要的，可以在CacheManager中自行添加其他方式的缓存，目前只支持图片缓存。<br>

