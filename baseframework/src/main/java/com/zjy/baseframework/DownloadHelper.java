package com.zjy.baseframework;

import org.apache.commons.io.IOUtils;

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
    private DownloadHelper() {
    }

    public static void download(String filePath, HttpServletResponse response) throws IOException {
        File file = new File(filePath);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        download(bis, file.getName(), response);
        bis.close();
    }

    public static void download(InputStream is, String fileName, HttpServletResponse response) throws IOException {
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
            throw ex;
        }
    }
}
