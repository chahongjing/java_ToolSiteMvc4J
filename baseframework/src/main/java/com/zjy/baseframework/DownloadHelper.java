package com.zjy.baseframework;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Created by chahongjing on 2017/2/14.
 */
public class DownloadHelper {
    protected static Logger logger = LogHelper.getLogger(DownloadHelper.class);
    private DownloadHelper() {
    }

    public static void download(String filePath, HttpServletResponse response) throws IOException {
        download(filePath, response, StringUtils.EMPTY);
    }

    public static void download(String filePath, HttpServletResponse response, String fileName) throws IOException {
        File file = new File(filePath).getAbsoluteFile();
        download(file, response, fileName);
    }

    public static void download(File file, HttpServletResponse response) throws IOException {
        download(file, response, file.getName());
    }

    public static void download(File file, HttpServletResponse response, String fileName) throws IOException {
        if (!file.exists()) throw new FileNotFoundException("未找到文件" + file);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        download(bis, StringUtils.isBlank(fileName) ? file.getName() : fileName, response);
        bis.close();
    }

    public static void download(InputStream is, String fileName, HttpServletResponse response) throws IOException {
        if(StringUtils.isBlank(fileName)) throw new IllegalArgumentException("文件名称不能为空！");
        // 创建输出流
        try (OutputStream out = response.getOutputStream()) {
            // 清空response
            response.reset();
            // 设置响应编码
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            // 设置文件名
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=%s", URLEncoder.encode(fileName, StandardCharsets.UTF_8.name())));
            // 设置contentType
            response.setContentType(new MimetypesFileTypeMap().getContentType(new File(fileName)));
            // 数据信息写入响应流中
            IOUtils.copy(is, out);
            // 关闭文件输入流
            out.flush();
        } catch (IOException ex) {
            logger.error("下载失败!", ex);
            throw ex;
        }
    }
}
