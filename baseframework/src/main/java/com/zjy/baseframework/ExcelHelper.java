package com.zjy.baseframework;

import com.zjy.baseframework.enums.FileSuffix;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.zjy.baseframework.enums.FileSuffix.XLSX;

public class ExcelHelper<T> {
    // region 变量
    private static final int MaxSheetRow = 65535;
    // endregion

    // region excle转list
    public static <T> List<T> excelToList(InputStream in, String sheetName, Class<T> clazz, LinkedHashMap<String, String> headers) {
        List<T> list = new ArrayList<T>();
        Workbook workbook;
        try {
            workbook = WorkbookFactory.create(in);
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            System.out.println("在excel中未找到对应的sheet:" + sheetName);
            return list;
        }

        Row row;
        Cell cell;
        String fieldName;
        int fieldIndex;
        Map<String, Integer> fieldsMap = new HashMap<>();
        // 处理列和字段对应关系
        row = sheet.getRow(0);
        if (row == null) return list;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            fieldName = "";
            fieldIndex = 0;
            for (int i = 0; i < row.getLastCellNum(); i++) {
                cell = row.getCell(i);
                if (StringUtils.isBlank(cell.getStringCellValue())) continue;
                if (cell.getStringCellValue().trim().equals(entry.getValue())) {
                    fieldName = entry.getKey();
                    fieldIndex = i;
                }
            }
            if (StringUtils.isBlank(fieldName)) {
                throw new IllegalArgumentException("未找到列名【" + entry.getKey() + "-" + entry.getValue() + "】");
            }
            fieldsMap.put(fieldName, fieldIndex);
        }
        // 将行转为对象
        T entity;
        Object cellValue;
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            //新建要转换的对象
            try {
                entity = clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return list;
            }
            row = sheet.getRow(i);
            //给对象中的字段赋值
            for (Map.Entry<String, Integer> entry : fieldsMap.entrySet()) {
                cell = row.getCell(entry.getValue());
                cellValue = getCellValue(cell);
                // 给对象赋值
                try {
                    setFieldValueByName(entry.getKey(), cellValue, entity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            list.add(entity);
        }
        return list;
    }
    // endregion

    // region list转excel
    public static <T> void listToExcelNew(List<T> list, LinkedHashMap<String, String> headers, String sheetName, String filePath) {
        LinkedHashMap<String, List<T>> map = new LinkedHashMap<>();
        map.put(sheetName, list);
        listToExcelNew(map, headers, filePath);
    }

    public static <T> void listToExcelNew(LinkedHashMap<String, List<T>> sheetList, LinkedHashMap<String, String> headers, String filePath) {
        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("filePath不能为空！");
        }
        Workbook workbook;
        BufferedOutputStream os = null;
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.mkdirs();
            }
            if (filePath.toLowerCase().endsWith(XLSX.getValue())) {
                workbook = new XSSFWorkbook();
            } else {
                workbook = new HSSFWorkbook();
            }
            os = new BufferedOutputStream(new FileOutputStream(filePath));
            for (Map.Entry<String, List<T>> entry : sheetList.entrySet()) {
                listToExcelNew(workbook, entry.getValue(), headers, entry.getKey());
            }
            os.flush();
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    public static <T> void listToExcelNew(List<T> list, LinkedHashMap<String, String> headers, String sheetName, OutputStream os, FileSuffix suffix) {
        Workbook workbook;
        if (suffix != null && suffix.equals(XLSX)) {
            workbook = new XSSFWorkbook();
        } else {
            workbook = new HSSFWorkbook();
        }
        try {
            listToExcelNew(workbook, list, headers, sheetName);
            workbook.write(os);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> void listToExcelNew(Workbook workbook, List<T> list, LinkedHashMap<String, String> headers, String sheetName) {
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("数据源中没有任何数据!");
        }
        if (StringUtils.isBlank(sheetName)) {
            throw new IllegalArgumentException("请输入sheet名称");
        }

        int sheetNum = (int) Math.ceil(list.size() / new Integer(MaxSheetRow - 1).doubleValue());
        Sheet[] sheets = new Sheet[sheetNum];
        // sheet名称
        if (sheetNum == 1) {
            sheets[0] = getSheet(workbook, sheetName);
        } else {
            for (int i = 0; i < sheets.length; i++) {
                sheets[i] = getSheet(workbook, sheetName + (i + 1));
            }
        }
        List<T> subList;
        for (int i = 0; i < sheets.length; i++) {
            if (i == sheets.length - 1) {
                subList = list.subList(i * (MaxSheetRow - 1), list.size());
            } else {
                subList = list.subList(i * (MaxSheetRow - 1), (i + 1) * (MaxSheetRow - 1));
            }
            fillSheet(sheets[i], subList, headers);
        }
    }
    // endregion

    // region 辅助函数
    private static <T> void fillSheet(Sheet sheet, List<T> list, LinkedHashMap<String, String> headers) {
        // 设置标题
        setHeader(CellUtil.getRow(0, sheet), headers);

        // 定义存放字段名称的数组
        String[] fieldNames = new String[headers.size()];

        //填充数组
        int count = 0;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            fieldNames[count] = entry.getKey();
            count++;
        }

        //填充内容
        int rowNo = 1;
        Row row;
        Cell cell;
        for (T item : list) {
            row = CellUtil.getRow(rowNo, sheet);
            for (int i = 0; i < fieldNames.length; i++) {
                Object objValue = getFieldValueByNameSequence(fieldNames[i], item);
                cell = CellUtil.getCell(row, i);
                setCellValue(cell, objValue);
            }
            rowNo++;
        }
    }

    private static void setHeader(Row row, Map<String, String> headers) {
        int i = 0;
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            Cell cell = CellUtil.getCell(row, i);
            cell.setCellValue(entry.getValue());
            i++;
        }
    }

    private static Sheet getSheet(Workbook book, String sheetName) {
        Sheet sheet = book.getSheet(sheetName);
        if (sheet == null) {
            sheet = book.createSheet(sheetName);
        }
        return sheet;
    }

    private static Object getCellValue(Cell cell) {
        Object value = null;
        if (cell == null) return value;
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    value = cell.getDateCellValue();
                } else {
                    value = cell.getNumericCellValue();
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_ERROR:
                value = cell.getErrorCellValue();
                break;
        }
        return value;
    }

    private static void setCellValue(Cell cell, Object value) {
        if (value == null) return;
        Class clazz = value.getClass();
        if (clazz == String.class) {
            cell.setCellValue(value.toString());
        } else if (clazz == Integer.class || clazz == int.class) {
            cell.setCellValue(Integer.parseInt(value.toString()));
        } else if (clazz == Float.class || clazz == float.class) {
            cell.setCellValue(Float.parseFloat(value.toString()));
        } else if (clazz == Double.class || clazz == double.class) {
            cell.setCellValue(Double.parseDouble(value.toString()));
        } else if (clazz == Date.class) {
            Workbook wb = cell.getSheet().getWorkbook();
            CreationHelper creationHelper = wb.getCreationHelper();
            CellStyle dateStyle = wb.createCellStyle();
            dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellStyle(dateStyle);
            cell.setCellValue((Date) value);
        } else if (clazz == Long.class || clazz == long.class) {
            cell.setCellValue(Long.parseLong(value.toString()));
        } else if (clazz == Boolean.class || clazz == boolean.class) {
            cell.setCellValue(Boolean.parseBoolean(value.toString()));
        } else if (clazz == Byte.class || clazz == byte.class) {
            cell.setCellValue(Byte.parseByte(value.toString()));
        } else if (clazz == Character.class || clazz == char.class) {
            cell.setCellValue(value.toString());
        } else if (clazz == Short.class || clazz == short.class) {
            cell.setCellValue(Short.parseShort(value.toString()));
        } else {
            cell.setCellValue(value.toString());
        }
    }

    /**
     * @param fieldNameSequence 带路径的属性名或简单属性名
     * @param o                 对象
     * @return 属性值
     * @throws Exception
     * @MethodName : getFieldValueByNameSequence
     * @Description :
     * 根据带路径或不带路径的属性名获取属性值
     * 即接受简单属性名，如userName等，又接受带路径的属性名，如student.department.name等
     */
    private static Object getFieldValueByNameSequence(String fieldNameSequence, Object o) {
        Object value = null;
        //将fieldNameSequence进行拆分
        String[] attributes = fieldNameSequence.split("\\.");
        if (attributes.length == 1) {
            value = getFieldValueByName(fieldNameSequence, o);
        } else {
            //根据属性名获取属性对象
            Object fieldObj = getFieldValueByName(attributes[0], o);
            String subFieldNameSequence = fieldNameSequence.substring(fieldNameSequence.indexOf(".") + 1);
            value = getFieldValueByNameSequence(subFieldNameSequence, fieldObj);
        }
        return value;
    }

    /**
     * @param fieldName 字段名
     * @param o         对象
     * @return 字段值
     * @MethodName : getFieldValueByName
     * @Description : 根据字段名获取字段值
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        Object value = null;
        try {
            Field field = getFieldByName(fieldName, o.getClass());
            if (field != null) {
                field.setAccessible(true);
                value = field.get(o);
            } else {
                throw new NoSuchFieldException(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
            }
        } catch (Exception ex) {
        }
        return value;
    }

    /**
     * @param fieldName 字段名
     * @param clazz     包含该字段的类
     * @return 字段
     * @MethodName : getFieldByName
     * @Description : 根据字段名获取字段
     */
    private static Field getFieldByName(String fieldName, Class<?> clazz) {
        //拿到本类的所有字段
        Field[] selfFields = clazz.getDeclaredFields();

        //如果本类中存在该字段，则返回
        for (Field field : selfFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        //否则，查看父类中是否存在此字段，如果有则返回
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != null && superClazz != Object.class) {
            return getFieldByName(fieldName, superClazz);
        }

        //如果本类和父类都没有，则返回空
        return null;
    }

    /**
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @param o          对象
     * @MethodName : setFieldValueByName
     * @Description : 根据字段名给对象的字段赋值
     */
    private static void setFieldValueByName(String fieldName, Object fieldValue, Object o) throws Exception {
        Field field = getFieldByName(fieldName, o.getClass());
        if (field != null) {
            field.setAccessible(true);
            //获取字段类型
            Class<?> fieldType = field.getType();
            //根据字段类型给字段赋值
            if (Date.class == fieldType) {
                field.set(o, fieldValue);
            } else {
                field.set(o, fieldValue);
            }
        } else {
            throw new Exception(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
        }
    }
    // endregion

    // region 其它方法
    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
     *
     * @param title   表格标题名
     * @param headers 表格属性列名数组
     * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *                javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    @SuppressWarnings("unchecked")
    public static <T> void exportExcel(String title, List<String> headers, Collection<T> dataset, OutputStream out, String pattern) {
        // 声明一个工作薄
        Workbook workbook = new HSSFWorkbook();
        // 生成一个表格
        Sheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        // 生成一个样式
        CellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        Font font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        CellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        Font font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

//        // 声明一个画图的顶级管理器
//        Comment comment = sheet.getCellComment(1, 2);
//        // 设置注释内容
//        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
//        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
//        comment.setAuthor("leno");

        // 产生表格标题行
        Row row = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(style);
            RichTextString text = new HSSFRichTextString(headers.get(i));
            cell.setCellValue(text);
        }

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields = t.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(style2);
                Field field = fields[i];
                String fieldName = field.getName();
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                try {
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName, new Class[0]);
                    Object value = getMethod.invoke(t, new Object[0]);
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    if (value instanceof String) {
                        cell.setCellValue((String) value);
                    } else if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);
                    } else if (value instanceof Float) {
                        cell.setCellValue((Float) value);
                    } else if (value instanceof Double) {
                        cell.setCellValue((Double) value);
                    } else if (value instanceof Long) {
                        cell.setCellValue((Long) value);
                    } else if (value instanceof Boolean) {
                        boolean bValue = (Boolean) value;
                        textValue = "男";
                        if (!bValue) {
                            textValue = "女";
                        }
                        cell.setCellValue(textValue);
                    } else if (value instanceof Date) {
//                        Date date = (Date) value;
//                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//                        textValue = sdf.format(date);
                        cell.setCellValue((Date) value);
                    } else if (value instanceof byte[]) {
                        // 有图片时，设置行高为60px;
                        row.setHeightInPoints(60);
                        // 设置图片所在列宽度为80px,注意这里单位的一个换算
                        sheet.setColumnWidth(i, (short) (35.7 * 80));
                        // sheet.autoSizeColumn(i);
                        byte[] bsValue = (byte[]) value;
                        ClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6,
                                index);
                        anchor.setAnchorType(2);
                        //patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        cell.setCellValue(value.toString());
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } finally {
                    // 清理资源
                }
            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param list      数据源
     * @param fieldMap  类的英文属性和Excel中的中文列名的对应关系
     * @param sheetName sheet名称
     * @param sheetSize 每个工作表中记录的最大个数
     * @param response  使用response可以导出到浏览器
     * @param <T>       list的类型
     * @throws Exception
     */
    public static <T> void listToExcel(List<T> list, LinkedHashMap<String, String> fieldMap, String sheetName, int sheetSize, HttpServletResponse response
    ) throws Exception {

        //设置默认文件名为当前时间：年月日时分秒
        String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).toString();

        //设置response头信息
        response.reset();
        response.setContentType("application/vnd.ms-excel");        //改成输出excel文件
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".xls");

        //创建工作簿并发送到浏览器
        try {

            OutputStream out = response.getOutputStream();
            /// listToExcel(list, fieldMap, sheetName, sheetSize, out);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置工作表自动列宽和首行加粗
     *
     * @param sheet
     * @param extraWith
     */
    private static void setColumnAutoSize(Sheet sheet, int extraWith) {
        //获取本列的最宽单元格的宽度
        Row row = sheet.getRow(0);
        if (row == null) {
            row = sheet.createRow(0);
        }
        for (int i = 0; i < row.getLastCellNum(); i++) {
            int colWith = 0;
            for (int j = 0; j < sheet.getLastRowNum(); j++) {
                Row irow = sheet.getRow(j);
                if (irow == null) {
                    irow = sheet.createRow(j);
                }
                Cell cell = irow.getCell(i);
                if (cell == null) {
                    cell = irow.createCell(i);
                }
                String content = cell.getStringCellValue().toString();
                int cellWith = content.length();
                if (colWith < cellWith) {
                    colWith = cellWith;
                }
            }
            //设置单元格的宽度为最宽宽度+额外宽度
            //sheet.setColumnView(i, colWith + extraWith);
        }

    }
    // endregion
}