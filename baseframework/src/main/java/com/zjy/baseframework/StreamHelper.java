package com.zjy.baseframework;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Created by chahongjing on 2017/3/14.
 */
public class StreamHelper {
    final static int BUFFER_SIZE = 4096;

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
    public static InputStream byteToInputStream(byte[] in) throws Exception {
        return new ByteArrayInputStream(in);
    }

    /**
     * 将byte数组转换成String
     *
     * @param in
     * @return
     * @throws Exception
     */
    public static String byteToString(byte[] in) throws Exception {
        String s = "";
        byte[] bytes = s.getBytes();
        return new String(in);
    }
}
