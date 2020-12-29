package easymall.component;

import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

import cn.afterturn.easypoi.cache.ImageCache;
import cn.afterturn.easypoi.cache.manager.POICacheManager;

/**
 * @author	passerbyYSQ
 * @date	2020-12-29 15:15:41
 */
public class MyContextLoaderListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		super.contextInitialized(event); // ����ȥ��
		
		// ������ʼ����ɺ��滻EasyPoi��ImageCache
		// �Խ��EasyPoiԴ���е�������ͼƬ��·��������bug
		
		EasyPoiFixedCacheLoader cacheLoader = new EasyPoiFixedCacheLoader();
		LoadingCache<String, byte[]> loadingCache = CacheBuilder.newBuilder()
				.expireAfterWrite(1, TimeUnit.DAYS)
                .maximumSize(2000).build(cacheLoader);
		// �滻
		ImageCache.setLoadingCache(loadingCache);
		
		// �滻
		EasyPoiFixedFileLoaderImpl fileLoader = new EasyPoiFixedFileLoaderImpl();
		POICacheManager.setFileLoder(fileLoader);
		
		System.out.println("������ʼ����ϣ�����");
	}
	
	
	
	

}
