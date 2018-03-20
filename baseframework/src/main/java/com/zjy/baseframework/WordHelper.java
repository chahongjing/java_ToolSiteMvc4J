package com.zjy.baseframework;

import com.aspose.words.*;
import com.zjy.baseframework.enums.FileSuffix;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.slf4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by Administrator on 2017/12/7.
 */
public class WordHelper {

    protected static Logger logger = LogHelper.getLogger(WordHelper.class);

    public static void main(String[] args) throws Exception {
        InputStream in1 = null;
        InputStream in2 = null;
        OPCPackage src1Package = null;
        OPCPackage src2Package = null;

        htmlToWord2();

        OutputStream dest = new FileOutputStream("d:\\dest.docx");
        try {
            in1 = new FileInputStream("d:\\tmp.docx");
            in2 = new FileInputStream("d:\\tmp2.docx");
            src1Package = OPCPackage.open(in1);
            src2Package = OPCPackage.open(in2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        XWPFDocument src1Document = new XWPFDocument(src1Package);
        CTBody src1Body = src1Document.getDocument().getBody();
        XWPFDocument src2Document = new XWPFDocument(src2Package);
        CTBody src2Body = src2Document.getDocument().getBody();
        appendBody(src1Body, src2Body);
        src1Document.write(dest);
    }

    private static void appendBody(CTBody src, CTBody append) throws Exception {
        XmlOptions optionsOuter = new XmlOptions();
        optionsOuter.setSaveOuter();
        String appendString = append.xmlText(optionsOuter);
        String srcString = src.xmlText();
        String prefix = srcString.substring(0, srcString.indexOf(">") + 1);
        String mainPart = srcString.substring(srcString.indexOf(">") + 1, srcString.lastIndexOf("<"));
        String sufix = srcString.substring(srcString.lastIndexOf("<"));
        String addPart = appendString.substring(appendString.indexOf(">") + 1, appendString.lastIndexOf("<"));
        CTBody makeBody = CTBody.Factory.parse(prefix + mainPart + addPart + sufix);
        src.set(makeBody);
    }

    public static void htmlToWord2() throws Exception {
//        InputStream bodyIs = new FileInputStream("f:\\1.html");
//        InputStream cssIs = new FileInputStream("f:\\1.css");
//        String body = getContent(bodyIs);
//        String css = getContent(cssIs);
        //拼一个标准的HTML格式文档
        String content = "<html><head><style></style></head><body>顶戴塔顶某基本面基本<img src=\"file:\\\\\\C:\\Users\\Administrator\\Desktop\\abc.png\" ><img src=\"http://rdm.iflytek.com:2000/images/icon901.gif\"><img width=\"96\" height=\"119\" src=\"data:image/.png;base64,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\" alt=''></body></html>";
        InputStream is = new ByteArrayInputStream(content.getBytes("gb2312"));
        OutputStream os = new FileOutputStream("d:\\1.doc");
        inputStreamToWord(is, os);
    }

    /**
     * 把is写入到对应的word输出流os中
     * 不考虑异常的捕获，直接抛出
     *
     * @param is
     * @param os
     * @throws IOException
     */
    private static void inputStreamToWord(InputStream is, OutputStream os) throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem();
        //对应于org.apache.poi.hdf.extractor.WordDocument
        fs.createDocument(is, "WordDocument");
        fs.writeFilesystem(os);
        os.close();
        is.close();
    }

    /**
     * 把输入流里面的内容以UTF-8编码当文本取出。
     * 不考虑异常，直接抛出
     *
     * @param ises
     * @return
     * @throws IOException
     */
    private static String getContent(InputStream... ises) throws IOException {
        if (ises != null) {
            StringBuilder result = new StringBuilder();
            BufferedReader br;
            String line;
            for (InputStream is : ises) {
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
            }
            return result.toString();
        }
        return null;
    }

    // region aspose html，word相关操作
    /**
     * html文件转word文件
     * @param htmlPath html文件路径
     * @param wordPath word文件路径
     */
    public static void htmlFileToWord(String htmlPath, String wordPath) {
        LoadOptions loadOptions = new LoadOptions();
        loadOptions.setLoadFormat(LoadFormat.HTML);
        loadOptions.setEncoding(StandardCharsets.UTF_8);
        int saveFormat;
        if(FileSuffix.DOC.getValue().equalsIgnoreCase(Utils.getExtension(wordPath))) {
            saveFormat = SaveFormat.DOC;
        } else if(FileSuffix.DOCX.getValue().equalsIgnoreCase(Utils.getExtension(wordPath))) {
            saveFormat = SaveFormat.DOCX;
        } else {
            saveFormat = SaveFormat.DOC;
        }
        try {
            Document doc = new Document(htmlPath, loadOptions);
            doc.save(wordPath, SaveOptions.createSaveOptions(saveFormat));
        } catch (Exception e) {
            logger.error("html转word失败！", e);
        }
    }

    /**
     * html片段转word
     * @param htmlBody html内容
     * @param wordPath word文件路径
     */
    public static void htmlToWord(String htmlBody, String wordPath) {
        OutputStream out = null;
        int saveFormat;
        if(FileSuffix.DOC.getValue().equalsIgnoreCase(Utils.getExtension(wordPath))) {
            saveFormat = SaveFormat.DOC;
        } else if(FileSuffix.DOCX.getValue().equalsIgnoreCase(Utils.getExtension(wordPath))) {
            saveFormat = SaveFormat.DOCX;
        } else {
            saveFormat = SaveFormat.DOC;
        }
        try {
            out = new FileOutputStream(new File(wordPath));
            //拼接完整的HTML文档
            StringBuilder sb = new StringBuilder();
            sb.append("<!DOCTYPE html><html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /></head><body>");
            sb.append(htmlBody);
            sb.append("</body></html>");
            Document doc = new Document();
            DocumentBuilder builder = new DocumentBuilder(doc);
            builder.insertHtml(sb.toString());
            doc.save(out, SaveOptions.createSaveOptions(saveFormat));
        } catch (Exception ex) {
            logger.error("html转word失败！", ex);
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    /**
     * word文件转html文件
     * @param wordPath word文件路径
     * @param htmlPath html文件路径
     */
    public static void wordToHtml(String wordPath, String htmlPath) {
        int loadFormat;
        if(FileSuffix.DOC.getValue().equalsIgnoreCase(Utils.getExtension(wordPath))) {
            loadFormat = LoadFormat.DOC;
        } else if(FileSuffix.DOCX.getValue().equalsIgnoreCase(Utils.getExtension(wordPath))) {
            loadFormat = LoadFormat.DOCX;
        } else {
            loadFormat = LoadFormat.DOC;
        }
        LoadOptions loadOptions = new LoadOptions();
        loadOptions.setLoadFormat(loadFormat);
        loadOptions.setEncoding(StandardCharsets.UTF_8);
        try {
            new Document(wordPath, loadOptions).save(htmlPath, SaveFormat.HTML);
        } catch (Exception e) {
            logger.error("word转html失败！", e);
        }
    }

    public static void getLicense() {
        String path = getWebRootAbsolutePath() + "/license.xml";
        InputStream is;
        try {
            is = new FileInputStream(new File(path));
            License license = new License();
            license.setLicense(is);
        } catch (FileNotFoundException e) {
            logger.error("license.xml file not found");
        } catch (Exception e) {
            logger.error("license register failed");
        }
    }
    
    private static String getWebRootAbsolutePath() {
        String folderPath = WordHelper.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        //if (folderPath.indexOf("WEB-INF") > 0) {
        return folderPath.substring(0, (folderPath.indexOf("classes") + "classes".length()));
        //}
    }
    // endregion
}
