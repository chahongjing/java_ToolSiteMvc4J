package com.zjy.baseframework;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/2/14.
 */
public class ZipHelper {

    /**
     * 压缩文件
     * @param file 文件路径
     * @return
     */
    public static String zip(String file) throws Exception {
        File f = new File(file);
        String outputPath = file;
        if(f.isDirectory()) {
            outputPath = Paths.get(f.getPath().toString(), f.getName() + ".zip").toString();
        } else {
            String prefix = f.getName().substring(f.getName().lastIndexOf("."));
            if (prefix != null)
                outputPath = outputPath.replace(prefix, "") + ".zip";
        }

        return zip(file, outputPath);
    }

    /**
     * 压缩文件
     * @param file 文件路径
     * @param zipFilePath 压缩文件存放地址
     * @return
     */
    public static String zip(String file, String zipFilePath) throws Exception {
        return zip(file, zipFilePath, null);
    }

    /**
     * 压缩文件
     * @param file 文件路径
     * @param zipFilePath 压缩文件存放地址
     * @param password 密码
     * @return
     */
    public static String zip(String file, String zipFilePath, String password) throws Exception {
        return zip(new String[]{file}, zipFilePath, password);
    }

    /**
     * 压缩文件
     * @param files 文件路径列表
     * @param zipFilePath 压缩文件存放地址
     * @return
     */
    public static String zip(String[] files, String zipFilePath) throws Exception {
        return zip(files, zipFilePath, null);
    }

    /**
     * 压缩文件
     * @param files 文件路径列表
     * @param zipFilePath 压缩文件存放地址
     * @param password 密码
     * @return
     */
    public static String zip(String[] files, String zipFilePath, String password) throws Exception {
        File dest = new File(zipFilePath);
        if(dest.isDirectory()){
            throw new Exception("目标文件不是有效的压缩文件！");
        }
        ZipParameters parameters = new ZipParameters();
        // 压缩方式
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        // 压缩级别
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        if (!(password == null || password == "")) {
            parameters.setEncryptFiles(true);
            // 加密方式
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
            parameters.setPassword(password.toCharArray());
        }
        try {
            if(!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            ZipFile zipFile = new ZipFile(zipFilePath);
            for (String item : files) {
                File file = new File(item);
                if (file.exists()) {
                    if (file.isDirectory()) {
                        zipFile.addFolder(file, parameters);
                    } else {
                        zipFile.addFile(file, parameters);
                    }
                }
            }
            zipFile.setComment("abc");
            return zipFilePath;
        } catch (ZipException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解压文件
     * @param zipPath 压缩文件路径
     */
    public static void unzip(String zipPath) {
        File file = new File(zipPath);
        unzip(zipPath, file.getParent().toString());
    }

    /**
     * 解压文件
     * @param zipPath 压缩文件路径
     * @param outputPath 解压目录
     * @return
     */
    public static File[] unzip(String zipPath, String outputPath) {
        return unzip(zipPath, outputPath, null);
    }

    /**
     * 解压文件
     * @param zipPath 压缩文件路径
     * @param outputPath 解压目录
     * @param password 密码
     * @return
     */
    public static File[] unzip(String zipPath, String outputPath, String password) {
        try {
            ZipFile zipFile = new ZipFile(zipPath);
            zipFile.setFileNameCharset("UTF-8");
            if (!zipFile.isValidZipFile()) {
                throw new ZipException("压缩文件不合法,可能被损坏.");
            }
            File destDir = new File(outputPath);
            if (destDir.isDirectory() && !destDir.exists()) {
                destDir.mkdir();
            }
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(password.toCharArray());
            }
            zipFile.extractAll(outputPath);
            List<FileHeader> headerList = zipFile.getFileHeaders();
            List<File> extractedFileList = new ArrayList<File>();
            for (FileHeader fileHeader : headerList) {
                if (!fileHeader.isDirectory()) {
                    extractedFileList.add(new File(destDir, fileHeader.getFileName()));
                }
            }
            File[] extractedFiles = new File[extractedFileList.size()];
            extractedFileList.toArray(extractedFiles);
            return extractedFiles;
        } catch (ZipException e) {
            e.printStackTrace();
        }
        return new File[0];
    }

    /**
     * 从压缩文件中删除指定的目录
     * @param file 压缩文件路径
     * @param removeDir 要删除的目录，如a/b/
     * @throws ZipException
     */
    public static void removeZipDir(String file, String removeDir) throws ZipException {
        // 创建ZipFile并设置编码
        ZipFile zipFile = new ZipFile(file);
        zipFile.setFileNameCharset("UTF-8");

        // 给要删除的目录加上路径分隔符
        if (!removeDir.endsWith(File.separator)) removeDir += File.separator;

        // 如果目录不存在, 直接返回
        FileHeader dirHeader = zipFile.getFileHeader(removeDir);
        if (null == dirHeader) return;

        // 遍历压缩文件中所有的FileHeader, 将指定删除目录下的子文件名保存起来
        List headersList = zipFile.getFileHeaders();
        List<String> removeHeaderNames = new ArrayList<>();
        for (int i = 0, len = headersList.size(); i < len; i++) {
            FileHeader subHeader = (FileHeader) headersList.get(i);
            if (subHeader.getFileName().startsWith(dirHeader.getFileName())
                    && !subHeader.getFileName().equals(dirHeader.getFileName())) {
                removeHeaderNames.add(subHeader.getFileName());
            }
        }
        // 遍历删除指定目录下的所有子文件, 最后删除指定目录(此时已为空目录)
        for (String headerNameString : removeHeaderNames) {
            zipFile.removeFile(headerNameString);
        }
        zipFile.removeFile(dirHeader);
    }
}
