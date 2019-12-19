package com.zjy.baseframework;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author junyi.zeng@dmall.com
 * @date 2019-12-19 09:24:06
 */
public class TranslateUtil {

//    <dependency>
//        <groupId>com.github.nobodxbodon</groupId>
//        <artifactId>zhconverter</artifactId>
//        <version>0.0.5</version>
//    </dependency>
//
//    <dependency>
//        <groupId>com.github.houbb</groupId>
//        <artifactId>opencc4j</artifactId>
//        <version>1.0.2</version>
//    </dependency>

    public static void readOldFileAndWriteNewFileWithFilePath(String filePath) {
        // 简体转繁体 
        try {
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), "UTF-8"));
            StringBuffer strBuffer = new StringBuffer();

            for (String temp = null; (temp = bufRead.readLine()) != null; temp = null) {
                Pattern pattern = Pattern.compile("[\u4e00-\u9fcc]+");
                if (pattern.matcher(temp).find()) {
                    temp = getChinese(temp);
                }
                strBuffer.append(temp);
                strBuffer.append(System.getProperty("line.separator"));
            }
            System.out.println(strBuffer.toString());
            bufRead.close();
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8")));
            // PrintWriter printWriter = new PrintWriter(filePath);
            printWriter.write(strBuffer.toString());
            printWriter.flush();
            printWriter.close();
          /*  File f = new File(filePath);
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8");
            BufferedWriter writer=new BufferedWriter(write);
            writer.write(strBuffer.toString());
            writer.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getChinese(String paramValue) {
        String regex = "([\u4e00-\u9fa5]+)";
        String replacedStr = paramValue;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher matcher = p.matcher(paramValue);
        if (matcher.find()) {
            System.out.println("----------" + paramValue);
//            String traditionalStr = ZhConverterUtil.convertToTraditional(strs);
            String traditionalStr = "";
         /*   ZHConverter converter2 = ZHConverter.getInstance(ZHConverter.TRADITIONAL);
            String traditionalStr = converter2.convert(matcher.group(0));*/
            //replacedStr = replacedStr.replace(matcher.group(0), traditionalStr);
            replacedStr = traditionalStr;
            // System.out.println("转换前" + traditionalStr+"-------------转换后" + replacedStr);
        }
        return replacedStr;
    }


    /**
     * 迭代遍历传入的根文件夹，获取每一级文件夹的每个文件
     * 并把文件名称以字符串形式装在数组返回
     * path：根文件夹路径
     * listFileName：用于返回文件路径的数组，由于这个是迭代方法采用外部传入该数组
     */
    public static ArrayList<String> traverseFolder2(String path, ArrayList<String> listFileName) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return null;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder2(file2.getAbsolutePath(), listFileName);
                    } else {
                        String sbsolutePath = file2.getAbsolutePath();
                        if (sbsolutePath.endsWith(".jsp") || sbsolutePath.endsWith(".tpl") || sbsolutePath.endsWith(".vue") || sbsolutePath.endsWith(".js") || sbsolutePath.endsWith(".vm") || sbsolutePath.endsWith(".java")) {
                            listFileName.add(file2.getAbsolutePath());
                        }
                        System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return listFileName;
    }
}
