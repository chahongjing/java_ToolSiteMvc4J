package com.zjy.baseframework;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by chahongjing on 2017/3/14.
 */
public class StreamHelper {
    static final int BUFFER_SIZE = 4096;

    /**
     * 将InputStream转换成String
     *
     * @param in InputStream
     * @return String
     * @throws Exception
     */
    public static String inputStreamToString(InputStream in) throws Exception {
        return inputStreamToString(in, StandardCharsets.UTF_8.name());
    }

    /**
     * 将InputStream转换成某种字符编码的String
     *
     * @param in
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String inputStreamToString(InputStream in, String encoding) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        while (in.read(data) != -1)
            outStream.write(data);

        return new String(outStream.toByteArray(), encoding);
    }

    /**
     * 将String转换成InputStream
     *
     * @param in
     * @return
     * @throws Exception
     */
    public static InputStream stringToInputStream(String in) throws Exception {
        return stringToInputStream(in, StandardCharsets.UTF_8.name());
    }

    /**
     * 将String转换成InputStream
     *
     * @param in
     * @param encoding
     * @return
     * @throws Exception
     */
    public static InputStream stringToInputStream(String in, String encoding) throws Exception {
        return new ByteArrayInputStream(in.getBytes(encoding));
    }

    /**
     * 将InputStream转换成byte数组
     *
     * @param in InputStream
     * @return byte[]
     * @throws IOException
     */
    public static byte[] inputStreamToByte(InputStream in) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        while (in.read(data) != -1)
            outStream.write(data);

        return outStream.toByteArray();
    }

    /**
     * 将byte数组转换成InputStream
     *
     * @param in
     * @return
     * @throws Exception
     */
    public static InputStream byteToInputStream(byte[] in) {
        return new ByteArrayInputStream(in);
    }

    /**
     * 将byte数组转换成String
     *
     * @param in
     * @return
     * @throws Exception
     */
    public static String byteToString(byte[] in) {
        return new String(in);
    }

    /**
     * byte转outputStream
     *
     * @param in 字节数组
     * @return
     * @throws IOException
     */
    public static OutputStream byteToOutputStream(byte[] in) throws IOException {
        OutputStream output = new ByteArrayOutputStream();
        output.write(in);
        return output;
    }

    /**
     * byte转文件
     *
     * @param in       字节信息
     * @param filePath 文件信息
     * @return
     * @throws IOException
     */
    public static File byteToFile(byte[] in, String filePath) {
        File file = new File(filePath);

        try (OutputStream output = new FileOutputStream(file);
             BufferedOutputStream bufferedOutput = new BufferedOutputStream(output)){
            bufferedOutput.write(in);
            bufferedOutput.flush();
            output.flush();
        } catch (Exception ex) {
        }
        return file;
    }

    /**
     * ByteArrayOutputStream 转 ByteArrarInputStream
     * @param out
     * @return
     */
    public ByteArrayInputStream parse(ByteArrayOutputStream out) {
        return new ByteArrayInputStream(out.toByteArray());
    }

    /**
     * ByteArrarInputStream 转 ByteArrayOutputStream
     * @param in
     * @return
     * @throws Exception
     */
    public ByteArrayOutputStream parse(InputStream in) throws Exception {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        int ch;
        while ((ch = in.read()) != -1) {
            swapStream.write(ch);
        }
        return swapStream;
    }
}
