package com.fzc.fzcstockus.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author flamenco.xxx
 * @date 2021/12/1 11:36
 */

public class ImageUtil {
    public static void writeImageToDisk(byte[] img, String fileName){
        try {
            File file = new File("C:\\images\\" + fileName);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(img);
            fops.flush();
            fops.close();
            System.out.println("图片已经写入到C盘");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据地址获得数据的字节流
     * @param strUrl 网络连接地址
     * @return
     */
    public static byte[] getImageFromNetByUrl(String strUrl){
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            byte[] btImg = readInputStream(inStream);
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 从输入流中获取数据
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
