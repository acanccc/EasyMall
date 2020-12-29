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
 * ���EasyPoi����ͼƬ���ز�����������
 * �ο����ͣ�https://blog.csdn.net/breakaway_01/article/details/103895099
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

            //�ж��Ƿ��������ַ
            if (url.startsWith("http")) {
                URL urlObj = new URL(url);
                URLConnection urlConnection = urlObj.openConnection();
                urlConnection.setConnectTimeout(3 * 1000);
                urlConnection.setReadTimeout(60 * 1000);
                urlConnection.setDoInput(true);
                urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
                fileis = urlConnection.getInputStream();

            } else {
                //���þ���·����ѯ,�ٲ�ѯ���·��
                try {
                    fileis = new FileInputStream(url);
                } catch (FileNotFoundException e) {
                    //��ȡ��Ŀ�ļ�
                    fileis = FileLoaderImpl.class.getClassLoader().getResourceAsStream(url);
//                    if (fileis == null) {
//                        //�����������ļ�·��
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
        LOGGER.error(fileis + "���·���ļ�û���ҵ�,���ѯ");
        return null;
    }
}