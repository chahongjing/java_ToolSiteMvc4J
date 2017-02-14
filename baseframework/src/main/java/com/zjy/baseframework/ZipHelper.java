package com.zjy.baseframework;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/2/14.
 */
public class ZipHelper {

    public static String zip(String file) {
        File f = new File(file);
        String prefix = f.getName().substring(f.getName().lastIndexOf("."));
        String outputPath = "";
        if (prefix != null)
            outputPath = file.replace(prefix, "") + ".zip";

        return zip(file, outputPath);
    }

    public static String zip(String file, String zipFilePath) {
        return zip(file, zipFilePath, null);
    }

    public static String zip(String file, String zipFilePath, String password) {
        return zip(new String[]{file}, zipFilePath, password);
    }

    public static String zip(String[] files, String zipFilePath) {
        return zip(files, zipFilePath, null);
    }

    public static String zip(String[] files, String zipFilePath, String password) {
        ZipParameters parameters = new ZipParameters();
        // 压缩方式
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        // 压缩级别
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        if (!(password == null && password == "")) {
            parameters.setEncryptFiles(true);
            // 加密方式
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
            parameters.setPassword(password.toCharArray());
        }
        try {
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
            return zipFilePath;
        } catch (ZipException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void unzip(String zipPath) {
        File file = new File(zipPath);
        unzip(zipPath, file.getParent().toString());
    }

    public static File[] unzip(String zipPath, String outputPath) {
        return unzip(zipPath, outputPath, null);
    }

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

    void removeDirFromZipArchive(String file, String removeDir) throws ZipException {
        // 创建ZipFile并设置编码
        ZipFile zipFile = new ZipFile(file);
        zipFile.setFileNameCharset("GBK");

        // 给要删除的目录加上路径分隔符
        if (!removeDir.endsWith(File.separator)) removeDir += File.separator;

        // 如果目录不存在, 直接返回
        FileHeader dirHeader = zipFile.getFileHeader(removeDir);
        if (null == dirHeader) return;

        // 遍历压缩文件中所有的FileHeader, 将指定删除目录下的子文件名保存起来
        List headersList = zipFile.getFileHeaders();
        List<String> removeHeaderNames = new ArrayList<String>();
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
