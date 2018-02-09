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
    public static void download(String path, HttpServletResponse response) {
        try {
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8.displayName()));
            String mimeType = new MimetypesFileTypeMap().getContentType(new File(filename));
            response.setContentType(mimeType);

            //创建输出流
            OutputStream out = response.getOutputStream();
            //创建缓冲区
            byte buffer[] = new byte[1024];
            int len;
            FileInputStream in = new FileInputStream(file);
            //循环将输入流中的内容读取到缓冲区当中
            while ((len = in.read(buffer)) > 0) {
                //输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            //关闭文件输入流
            in.close();
            out.flush();
            //关闭输出流
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
