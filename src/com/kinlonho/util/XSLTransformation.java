package com.kinlonho.util;

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.apache.tomcat.websocket.Transformation;
import org.dom4j.Document;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;

public class XSLTransformation {
    private String htmlString;

    public XSLTransformation() {
        htmlString = new String();
    }

    /**
     * 将xml文件通过xsl模板转换成html字符串
     * 
     * @param xmlPath
     *            xml的存储路径
     * @param xslPath
     *            xsl模板的存储路径
     * @return 转换后的hmtl字符串
     */
    public String getHtmlString(String xmlPath, String xslPath) {

        System.out.println("getHtmlString()开始执行...\n");
        // 得到解析器
        SAXReader readerXml = new SAXReader();
        SAXReader readerXsl = new SAXReader();

        try {
            // 得到xml的文件流
            InputStream inStreamXml = this.getClass().getResourceAsStream(xmlPath);
            System.out.println(xmlPath);
            System.out.println(xslPath);

            // 加载xml文件
            Document docXml = readerXml.read(inStreamXml);
            Document docTransformHtml = this.transformDocument(docXml, xslPath);
            htmlString = this.doc2String(docTransformHtml);
            System.out.println("getHtmlString(...)执行成功!\n");

        } catch (Exception e) {
            System.out.println("读取xml文件失败，请检查xml路径\n errorLog:" + e.toString());
        }

        return htmlString;
    }

    /**
     * 通过xsl将xml数据文件转化成html的doc对象
     * 
     * @param docXml
     * @param xslPath
     * @return
     */
    private Document transformDocument(Document docXml, String xslPath) {

        System.out.println("开始执行 transformDocument(...)方法");
        TransformerFactory factory = TransformerFactory.newInstance();
        Document transformerDoc = null;

        try {
            System.out.println("transf:" + xslPath);
            Transformer transformer = factory.newTransformer(new StreamSource(xslPath));
            DocumentSource docSource = new DocumentSource(docXml);
            DocumentResult docResult = new DocumentResult();
            transformer.transform(docSource, docResult);
            transformerDoc = docResult.getDocument();
            System.out.println("transformDocument(...)方法执行成功");

        } catch (Exception e) {
            System.out.println("transformDocument(...)方法执行失败,提示信息[" + e.getMessage() + "]");
        }
        return transformerDoc;
    }

    /**
     * 将doc文档对象转化为html字符串
     * 
     * @param transformDoc
     *            doc文档
     * @return
     */
    private String doc2String(Document docTransformHtml) {

        System.out.println("getHtmlString()开始执行...\n");
        StringWriter strWriter = new StringWriter();
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        outputFormat.setEncoding("GBK");
        outputFormat.setXHTML(true);
        HTMLWriter htmlWriter = new HTMLWriter(strWriter, outputFormat);
        outputFormat.setExpandEmptyElements(false);
        
        try {
            htmlWriter.write(docTransformHtml);
            htmlWriter.flush();
            System.out.println("doc2String(...)执行成功!\n");
        } catch (Exception e) {
            System.out.println("doc2String(...)方法执行失败,提示信息[" + e.getMessage() + "]");
        }

        return strWriter.toString();
    }

}
