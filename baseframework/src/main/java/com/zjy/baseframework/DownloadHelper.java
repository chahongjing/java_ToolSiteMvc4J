package com.zjy.baseframework;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.*;

/**
 * Created by chahongjing on 2017/2/14.
 */
public class DownloadHelper {
    public static void download(String path, HttpServletResponse response) {
        try {
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();

            // 以流的形式下载文件。
            InputStream fis = null;
            fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader(HttpHeaders.CONTENT_LENGTH, "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
