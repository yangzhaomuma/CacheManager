# CacheManager
LruCache（内存缓存）与DiskLruCache（硬盘缓存）统一框架。

这是一个整合了内存缓存与硬盘缓存的图片缓存框架。
一、默认简略使用方式如下：
/**
	 * 初始化缓存管理
	 * @param context 上下文
	 */
CacheManager.init(Context context);
/**
	 * 获取索引key对应的缓存内容
	 * @param key 缓存索引key
	 * @return  key索引对应的Bitmap数据
	 */
CacheManager.get(String key)

/**
	 * 索引key对应的bitmap写入缓存
	 * @param key 缓存索引
	 * @param bitmap bitmap格式数据
	 */
CacheManager.put(String key,Bitmap bitmap)

CacheManager中还提供其他方法：
delete：删除缓存
size：获取缓存大小
deleteFile：删除某文件下的缓存
close：关闭缓存
setCacheModel：选择模式
二、模式选择
有三种模式可供选择，可通过setCacheModel方式选择开启内存缓存或者硬盘缓存或者默认的二者都开启。

三、可扩展
有需要的，可以在CacheManager中自行添加其他方式的缓存，目前只支持图片缓存。

