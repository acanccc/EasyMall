package easymall.component;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;

import com.google.common.cache.CacheLoader;

import cn.afterturn.easypoi.cache.manager.POICacheManager;

/**
 * 自定义EasyPoiFixedCacheLoader，以替换EasyPoi的默认CacheLoader实现
 * 解决图片路径解析的bug
 * 参考博客：https://blog.csdn.net/qq_34988540/article/details/83050187
 * 
 * @author	passerbyYSQ
 * @date	2020-12-29 15:26:04
 */
public class EasyPoiFixedCacheLoader extends CacheLoader<String, byte[]> {

	@Override
	public byte[] load(String imagePath) throws Exception {
		InputStream is = POICacheManager.getFile(imagePath);
        BufferedImage bufferImg = ImageIO.read(is);
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferImg,
            		// lastIndexOf 为修改源码的地方
                    imagePath.substring(imagePath.lastIndexOf(".") + 1, imagePath.length()),
                    byteArrayOut);
            return byteArrayOut.toByteArray();
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(byteArrayOut);
        }
	}

}
