package com.fzc.fzcstockus.XmlTest;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import com.fzc.fzcstockus.PigsTest.MPFDC.MPFResponseXml;
import com.fzc.fzcstockus.PigsTest.MPFOC.MPFOutClearXml;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * @author flamenco.xxx
 * @date 2022/1/20 14:29
 */
public class XmlTools {

    public static final String UTF_8 = "UTF-8";
    public static final String XML_SUFFIX = ".xml";
    public static final String MPF_STR = "MPF";
    public static final String HL_STR = "-";
    public static final String H = "H";
    public static final String RSP = "RSP";


    /**
     *
     * @param mp:xml类
     * @param file:文件夹路径，注意是文件夹。
     * @param memberCode:成员代号。
     * @param batchNum,批量数。
     * 所有入参不能为空或者NULL。
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static boolean writeMPFOCXmlToFile(MPFOutClearXml mp,File file,String memberCode,String batchNum){
        if(StringUtils.isBlank(memberCode)){
            return false;
        }
        if(StringUtils.isEmpty(batchNum)){
            return false;
        }
        if(ObjectUtil.isEmpty(mp)){
            return false;
        }
        if(ObjectUtil.isEmpty(file) || !file.isDirectory() || !FileUtil.exist(file)){
            return false;
        }
        //        String ccy = mp.getHeader().getCcy();
//        if(StringUtils.isBlank(ccy)){
//            return false;
//        }
        Date date = DateUtil.date();
        String format = DateUtil.format(date, "yyyyMMdd");
        String fileName = MPF_STR + HL_STR + memberCode + HL_STR + H + HL_STR + format + HL_STR + batchNum + XML_SUFFIX;
        String filePath = file.getPath();
        Path path = Paths.get(filePath ,fileName);
        Console.log("MPFOutClearXml文件存储到路径:" + path);
        boolean isExist = FileUtil.exist(path.toString());
        if(isExist){
            return false;
        }
        try {
            beanToXmlFile(mp, path.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Console.log("xml文件输出错误。");
        }
        return  true;
    }


    /**
     *
     * @param mp:xml类
     * @param file:文件夹路径，注意是文件夹。
     * @param memberCode:成员代号。
     * @param batchNum,批量数。
     * 所有入参不能为空或者NULL。
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static boolean writeMPFDCXmlToFile(MPFResponseXml mp,File file,String memberCode,String batchNum){
        if(StringUtils.isBlank(memberCode)){
            return false;
        }
        if(StringUtils.isEmpty(batchNum)){
            return false;
        }
        if(ObjectUtil.isEmpty(mp)){
            return false;
        }
        if(ObjectUtil.isEmpty(file) || !file.isDirectory() || !FileUtil.exist(file)){
            return false;
        }
        Date date = DateUtil.date();
        String format = DateUtil.format(date, "yyyyMMdd");
        String fileName = MPF_STR + HL_STR + memberCode + HL_STR + H + HL_STR + format + HL_STR + batchNum + HL_STR + RSP + XML_SUFFIX;
        String filePath = file.getPath();
        Path path = Paths.get(filePath ,fileName);
        Console.log("MPFOutClearXml文件存储到路径:" + path);
        boolean isExist = FileUtil.exist(path.toString());
        if(isExist){
            return false;
        }
        try {
            beanToXmlFile(mp, path.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Console.log("xml文件输出错误。");
        }
        return  true;
    }


    /**
     * 读取 xml 并转换成 实体类
     * @param clazz
     * @param filepath
     * @return
     * @throws FileNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static <T> T readString(Class<T> clazz, String filepath)
            throws FileNotFoundException {
        JAXBContext jc;
        try {
            jc = JAXBContext.newInstance(clazz);
            Unmarshaller um = jc.createUnmarshaller();
            return (T) um.unmarshal(new FileReader(filepath));
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 解析bean 将xml转存到指定路径
     *
     * @param obj
     * @param path
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static void beanToXmlFile(Object obj, String path) throws IOException {
        JAXBContext jc;
        try {
            jc = JAXBContext.newInstance(obj.getClass());
            Marshaller mu = jc.createMarshaller();
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            //JAXB_FORMATTED_OUTPUT 表示输出时 是否将xml格式化
            mu.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //JAXB_ENCODING 表示输出时 xml的字符集
            mu.setProperty(Marshaller.JAXB_ENCODING, UTF_8);
            mu.marshal(obj, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    /**
     * 解析bean 将xml 直接输出@throws IOException
     */
    @SuppressWarnings("unchecked")
    public static String beanToXmlStr(Object obj) throws IOException {
        JAXBContext jc;
        String result=null;
        try {
            jc = JAXBContext.newInstance(obj.getClass());
            Marshaller mu = jc.createMarshaller();
            StringWriter writer=new StringWriter();
            mu.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            mu.marshal(obj, writer);
            result=writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return  result;
    }

}
