package com.zjy.baseframework;

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
    public static void download(String filePath, HttpServletResponse response) throws IOException {
        File file = new File(filePath);
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        download(bis, file.getName(), response);
        bis.close();
    }

    public static void download(InputStream is, String fileName, HttpServletResponse response) throws IOException {
        //创建输出流
        try (OutputStream out = response.getOutputStream()) {
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.displayName()));
            String mimeType = new MimetypesFileTypeMap().getContentType(new File(fileName));
            response.setContentType(mimeType);
            //创建缓冲区
            byte buffer[] = new byte[1024];
            int len;
            //循环将输入流中的内容读取到缓冲区当中
            while ((len = is.read(buffer)) > 0) {
                //输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            //关闭文件输入流
            out.flush();
        } catch (IOException ex) {
            throw ex;
        }
    }
}
