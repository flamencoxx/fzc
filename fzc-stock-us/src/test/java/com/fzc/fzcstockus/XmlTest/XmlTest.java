package com.fzc.fzcstockus.XmlTest;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.XmlUtil;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.PigsTest.MPFDC.MPFDCHeader;
import com.fzc.fzcstockus.PigsTest.MPFDC.MPFDCPmtdtl;
import com.fzc.fzcstockus.PigsTest.MPFDC.MPFResponseXml;
import com.fzc.fzcstockus.PigsTest.MPFOC.MPFOCHeader;
import com.fzc.fzcstockus.PigsTest.MPFOC.MPFOCPmtdtl;
import com.fzc.fzcstockus.PigsTest.MPFOC.MPFOutClearXml;
import com.fzc.fzcstockus.model.StockUsImport;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockUsImportService;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2022/1/20 9:55
 */
@SpringBootTest
public class XmlTest {

    public static final String XMLSTR = ".xml";

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;

    @Autowired
    private StockUsImportService stockUsImportService;

    @Test
    public void test1() {
        String code = "IBM";
        StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(code);
        Console.log(stock);
    }

    @Test
    public void test2() {
        String code = "IBM";
        StockUsImport stock = stockUsImportService.getById(4);
        Console.log(stock.toString());
        String filename = "test1Xml";
        File file = new File("C:\\ioTest\\" + filename + XMLSTR);
        try {
            XmlUtil.writeObjectAsXml(file, stock);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        String filename = "test1Xml";
        File file = new File("C:\\ioTest\\" + filename + XMLSTR);
        int i = 0;
        List<Child> list = Lists.newArrayList();
        while (i < 10) {
            Child child = new Child();
            child.setChildName("f" + i);
            child.setChileAge(i);
            list.add(child);
            i++;
        }
        XmlRootNode xmlRootNode = new XmlRootNode();
        xmlRootNode.setName("fzc");
        xmlRootNode.setAge(11);
        xmlRootNode.setChildrenList(list);
        xmlRootNode.setType("testing");
        try {
            String xmlString = XmlTool.objectToXML(xmlRootNode);
            String xmlString2 = null;
            try {
                xmlString2 = XmlTools.beanToXmlStr(xmlRootNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            XmlTool.objectToXML(xmlRootNode,file.getName());
            Console.log(xmlString);
//            Console.log("===============================");
//            Console.log(xmlString2);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void outCleanFiletest() {
        MPFOCHeader MPFOCHeader = new MPFOCHeader();
        MPFOCHeader.setFileId("1");
        MPFOCHeader.setOwFileName("2");
        MPFOCHeader.setTotalAmt("3");
        MPFOCHeader.setTotalCnt("4");
        MPFOCHeader.setCcy("5");
        MPFOCHeader.setSbmmbr("6");
        MPFOCHeader.setGendate("7");
        MPFOCPmtdtl MPFOCPmtdtl = new MPFOCPmtdtl();
        MPFOCPmtdtl.setPmtref("1");
        MPFOCPmtdtl.setDestbank("2");
        MPFOCPmtdtl.setDestbrch("3");
        MPFOCPmtdtl.setDestacno("4");
        MPFOCPmtdtl.setDestacname("5");
        MPFOCPmtdtl.setDestmbr("6");
        MPFOCPmtdtl.setTrancode("7");
        MPFOCPmtdtl.setTranamt("8");
        MPFOCPmtdtl.setOrigbank("9");
        MPFOCPmtdtl.setOrigbrch("10");
        MPFOCPmtdtl.setOrigacno("11");
        MPFOCPmtdtl.setOrigacname("12");
        MPFOCPmtdtl.setOrigmbr("13");
        MPFOutClearXml mpXml = new MPFOutClearXml();
        mpXml.setHeader(MPFOCHeader);
        mpXml.setPmtdtl(MPFOCPmtdtl);
        try {
            String xmlString = XmlTool.objectToXML(mpXml);
            String filename = "test1Xml";
            File file = new File("C:\\ioTest\\" + filename + XMLSTR);
            Console.log(xmlString);
            Document doc = XmlUtil.parseXml(xmlString);
            XmlUtil.toFile(doc, file.getPath());
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void responseFileTest() {
        MPFDCHeader header = new MPFDCHeader();
        header.setFileId("1.responseFileTest");
        header.setOwfilename("2");
        header.setGendate("3");
        header.setClrdate("4");
        header.setCcy("5");
        header.setRcvmbr("6");
        header.setRespcode("7");
        header.setRespdesc("8");
        header.setTotalsubcnt("9");
        header.setTotalacpcnt("10");
        header.setTotalrejcnt("11");
        MPFDCPmtdtl pmtdtl = new MPFDCPmtdtl();
        pmtdtl.setPmtref("1");
        pmtdtl.setDestbank("2");
        pmtdtl.setDestbrch("3");
        pmtdtl.setDestacno("4");
        pmtdtl.setDestacname("5");
        pmtdtl.setDestmbr("6");
        pmtdtl.setTrancode("7");
        pmtdtl.setTranamt("8");
        pmtdtl.setOrigbank("9");
        pmtdtl.setOrigbrch("10");
        pmtdtl.setOrigacno("11");
        pmtdtl.setOrigacname("12");
        pmtdtl.setOrigmbr("13");
        pmtdtl.setReasoncode("14");
        pmtdtl.setReasondesc("15");
        MPFResponseXml mpXml = new MPFResponseXml();
        mpXml.setMpfdcHeader(header);
        mpXml.setMpfdcPmtdtl(pmtdtl);
        try {
            String xmlString = XmlTool.objectToXML(mpXml);
            String filename = "test1Xml";
            File file = new File("C:\\ioTest\\" + filename + XMLSTR);
            Console.log(xmlString);
            Document doc = XmlUtil.parseXml(xmlString);
            XmlUtil.toFile(doc, file.getPath());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readStringTest() throws Exception {
        MPFResponseXml mpXml = new MPFResponseXml();
        String filename = "test1Xml";
        File file = new File("C:\\ioTest\\" + filename + XMLSTR);
        mpXml = XmlTools.readString(MPFResponseXml.class,file.getPath());
        assert mpXml != null;
        Console.log(mpXml.toString());
    }

    @Test
    public void test4(){
        MPFDCHeader header = new MPFDCHeader();
        header.setFileId("1.fzc haha");
        header.setOwfilename("2");
        header.setGendate("3");
        header.setClrdate("4");
        header.setCcy("5");
        header.setRcvmbr("6");
        header.setRespcode("7");
        header.setRespdesc("8");
        header.setTotalsubcnt("9");
        header.setTotalacpcnt("10");
        header.setTotalrejcnt("11");
        MPFDCPmtdtl pmtdtl = new MPFDCPmtdtl();
        pmtdtl.setPmtref("1");
        pmtdtl.setDestbank("2");
        pmtdtl.setDestbrch("3");
        pmtdtl.setDestacno("4");
        pmtdtl.setDestacname("5");
        pmtdtl.setDestmbr("6");
        pmtdtl.setTrancode("7");
        pmtdtl.setTranamt("8");
        pmtdtl.setOrigbank("9");
        pmtdtl.setOrigbrch("10");
        pmtdtl.setOrigacno("11");
        pmtdtl.setOrigacname("12");
        pmtdtl.setOrigmbr("13");
        pmtdtl.setReasoncode("14");
        pmtdtl.setReasondesc("15");
        MPFResponseXml mpXml = new MPFResponseXml();
        mpXml.setMpfdcHeader(header);
        mpXml.setMpfdcPmtdtl(pmtdtl);
        String filename = "test1Xml";
        File file = new File("C:\\ioTest\\" + filename + XMLSTR);
        try {
            XmlTools.beanToXmlFile(mpXml,file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFile() {
        String filename = "test1Xml";
        File file = new File("C:\\ioTest\\" + filename + XMLSTR);
        File file1 = file.getParentFile();
        Path path = Paths.get(file.getPath(),filename);
        Console.log(path);
        Console.log(file.getPath());
        Console.log(file1.getPath());
    }

    @Test
    public void interfaceTest1(){
        MPFOCHeader header = new MPFOCHeader();
        header.setFileId("1");
        header.setOwFileName("2");
        header.setGendate("3");
        header.setCcy("4");
        header.setSbmmbr("5");
        header.setTotalCnt("6");
        header.setTotalAmt("77");
        MPFOCPmtdtl pmtdtl = new MPFOCPmtdtl();
        pmtdtl.setPmtref("f1");
        pmtdtl.setDestbank("f2");
        pmtdtl.setDestbrch("3");
        pmtdtl.setDestacno("4");
        pmtdtl.setDestacname("5");
        pmtdtl.setDestmbr("6");
        pmtdtl.setTrancode("7");
        pmtdtl.setTranamt("8");
        pmtdtl.setOrigbank("9");
        pmtdtl.setOrigbrch("10");
        pmtdtl.setOrigacno("11");
        pmtdtl.setOrigacname("12");
        pmtdtl.setOrigmbr("13");
        MPFOutClearXml mp = new MPFOutClearXml();
        mp.setHeader(header);
        mp.setPmtdtl(pmtdtl);
        File file = new File("C:\\ioTest");
        String memberCode = "1234";
        String batchCode = "66";
        boolean res = false;
        res = XmlTools.writeMPFOCXmlToFile(mp, file, memberCode, batchCode);
        Console.log(res);
    }

    @Test
    public void interfaceTest2(){
        MPFDCHeader header = new MPFDCHeader();
        header.setFileId("1.responseTest");
        header.setOwfilename("2");
        header.setGendate("3");
        header.setClrdate("4");
        header.setCcy("5");
        header.setRcvmbr("6");
        header.setRespcode("7");
        header.setRespdesc("8");
        header.setTotalsubcnt("9");
        header.setTotalacpcnt("10");
        header.setTotalrejcnt("11");
        MPFDCPmtdtl pmtdtl = new MPFDCPmtdtl();
        pmtdtl.setPmtref("1.response");
        pmtdtl.setDestbank("2");
        pmtdtl.setDestbrch("3");
        pmtdtl.setDestacno("4");
        pmtdtl.setDestacname("5");
        pmtdtl.setDestmbr("6");
        pmtdtl.setTrancode("7");
        pmtdtl.setTranamt("8");
        pmtdtl.setOrigbank("9");
        pmtdtl.setOrigbrch("10");
        pmtdtl.setOrigacno("11");
        pmtdtl.setOrigacname("12");
        pmtdtl.setOrigmbr("13");
        pmtdtl.setReasoncode("14");
        pmtdtl.setReasondesc("15");
        MPFResponseXml mp = new MPFResponseXml();
        mp.setMpfdcHeader(header);
        mp.setMpfdcPmtdtl(pmtdtl);
        File file = new File("C:\\ioTest");
        String memberCode = "1234";
        String batchCode = "66";
        boolean res;
        res = XmlTools.writeMPFDCXmlToFile(mp, file, memberCode, batchCode);
        Console.log(res);
    }
}
