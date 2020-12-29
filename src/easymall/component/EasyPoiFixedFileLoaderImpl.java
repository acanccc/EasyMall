package easymall.component;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.afterturn.easypoi.cache.manager.FileLoaderImpl;
import cn.afterturn.easypoi.cache.manager.IFileLoader;

/**
 * 解决EasyPoi网络图片下载并导出的问题
 * 参考博客：https://blog.csdn.net/breakaway_01/article/details/103895099
 * 
 * @author	passerbyYSQ
 * @date	2020-12-29 15:33:22
 */
public class EasyPoiFixedFileLoaderImpl implements IFileLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileLoaderImpl.class);

    @Override
    public byte[] getFile(String url) {
        InputStream fileis = null;
        ByteArrayOutputStream baos = null;
        try {

            //判断是否是网络地址
            if (url.startsWith("http")) {
                URL urlObj = new URL(url);
                URLConnection urlConnection = urlObj.openConnection();
                urlConnection.setConnectTimeout(3 * 1000);
                urlConnection.setReadTimeout(60 * 1000);
                urlConnection.setDoInput(true);
                urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
                fileis = urlConnection.getInputStream();

            } else {
                //先用绝对路径查询,再查询相对路径
                try {
                    fileis = new FileInputStream(url);
                } catch (FileNotFoundException e) {
                    //获取项目文件
                    fileis = FileLoaderImpl.class.getClassLoader().getResourceAsStream(url);
//                    if (fileis == null) {
//                        //最后再拿相对文件路径
//                        String path = PoiPublicUtil.getWebRootPath(url);
//                        fileis = new FileInputStream(path);
//                    }
                }
            }

            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fileis.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(fileis);
            IOUtils.closeQuietly(baos);
        }
        LOGGER.error(fileis + "这个路径文件没有找到,请查询");
        return null;
    }
}