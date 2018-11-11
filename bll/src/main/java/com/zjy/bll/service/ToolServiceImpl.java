package com.zjy.bll.service;

import com.zjy.baseframework.DbHelperNew;
import com.zjy.baseframework.enums.DbType;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.ToolDao;
import com.zjy.entities.TableColumnInfo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/2/27.
 */
@Service
public class ToolServiceImpl extends BaseService<ToolDao, TableColumnInfo> implements ToolService {

    private static Map<String, Class<?>> oracleTypeMap;

    @Override
    public String getTableInfo(DbType dbType, String url, String user, String password, String tableName) {
        Class<?> fieldType;
        String newLine = "\r\n";
        String colComments = "";
        String colName = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sbHeader = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        StringBuilder sbGetterSetter = new StringBuilder();
        List<String> packages = new ArrayList<>();
        String fieldPackage;
        sbHeader.append("package com.zjy.xxx;" + newLine);
        sbHeader.append(newLine);

        sb.append("/**" + newLine);
        sb.append(" * 创建人：Administrator" + newLine);
        sb.append(" * 创建日期：" + sdf.format(new Date()) + newLine);
        sb.append(" */" + newLine);
        sb.append("public class " + Objects.toString(tableName, "") + " {" + newLine);
        List<TableColumnInfo> list = getTableInfo(dbType.getDriver(), url, user, password, tableName);
        if (list == null) {
            list = new ArrayList<>();
        }
        for (TableColumnInfo columnInfo : list) {
            fieldType = getFieldType(dbType, columnInfo);
            fieldPackage = getTypePackage(fieldType.getSimpleName());
            if (!org.apache.commons.lang3.StringUtils.isBlank(fieldPackage) && !packages.contains(fieldPackage)) {
                packages.add(fieldPackage);
                sbHeader.append("import " + fieldPackage + ";" + newLine);
            }
            colComments = Objects.toString(columnInfo.getColComments(), "");
            colName = columnInfo.getColumnName().substring(0, 1).toLowerCase() + columnInfo.getColumnName().substring(1);
            sb.append("    /**" + newLine);
            sb.append("     * " + colComments + newLine);
            sb.append("     */" + newLine);
            sb.append("    private " + fieldType.getSimpleName() + " " + colName + ";" + newLine);

            sbGetterSetter.append(newLine);
            sbGetterSetter.append("    /**" + newLine);
            sbGetterSetter.append("     * 获取" + colComments + newLine);
            sbGetterSetter.append("     * @return" + newLine);
            sbGetterSetter.append("     */" + newLine);
            sbGetterSetter.append("    public " + fieldType.getSimpleName() + " get" + columnInfo.getColumnName() + "() {" + newLine);
            sbGetterSetter.append("        return " + colName + ";" + newLine);
            sbGetterSetter.append("    }" + newLine);
            sbGetterSetter.append(newLine);
            sbGetterSetter.append("    /**" + newLine);
            sbGetterSetter.append("     * 设置" + colComments + newLine);
            sbGetterSetter.append("     * @param " + colName + newLine);
            sbGetterSetter.append("     */" + newLine);
            sbGetterSetter.append("    public void set" + columnInfo.getColumnName() + "(" + fieldType.getSimpleName() + " " + colName + ") {" + newLine);
            sbGetterSetter.append("        this." + colName + " = " + colName + ";" + newLine);
            sbGetterSetter.append("    }" + newLine);
        }
        sb.insert(0, sbHeader.toString() + newLine);
        sb.append(sbGetterSetter.toString() + newLine);
        sb.append("}" + newLine);
        return sb.toString();
    }

    public List<TableColumnInfo> getTableInfo(String driver, String url, String user, String password, String tableName) {
        String sql = "SELECT INITCAP(tabCol.TABLE_NAME) as tableName, INITCAP(tabCol.COLUMN_NAME) as columnName, tabCol.DATA_TYPE as dataType," +
                "            colCom.COMMENTS AS colComments, tabCol.nullable, tabCom.COMMENTS AS tabComments," +
                "            tabCol.DATA_LENGTH as dataLength, tabCol.DATA_PRECISION as dataPrecision," +
                "            tabCol.DATA_SCALE as dataScale" +
                "      FROM USER_TAB_COLUMNS tabCol" +
                "      LEFT JOIN USER_COL_COMMENTS colCom ON UPPER(tabCol.TABLE_NAME) = UPPER(colCom.TABLE_NAME) AND UPPER(tabCol.COLUMN_NAME) = UPPER(colCom.COLUMN_NAME)" +
                "      LEFT JOIN USER_TAB_COMMENTS tabCom ON UPPER(tabCol.TABLE_NAME) = UPPER(tabCom.TABLE_NAME)" +
                "     WHERE UPPER(tabCol.TABLE_NAME) = UPPER(:tableName)" +
                "     ORDER BY tabCol.COLUMN_ID";
        DbHelperNew.initDriver(driver);
        Connection conn = DbHelperNew.getConnection(url, user, password);
        return DbHelperNew.getList(conn, sql, TableColumnInfo.class, tableName);
    }

    private Class<?> getFieldType(DbType dbType, TableColumnInfo columnInfo) {
        Class<?> type = null;
        switch (dbType) {
            case Oracle:
                type = getOracleFieldType(columnInfo);
                break;
            case Mysql:
                break;
            case SqlServer:
                break;
            default:
                type = getOracleFieldType(columnInfo);
                break;
        }
        if (type == null) type = String.class;
        return type;
    }

    private Class<?> getOracleFieldType(TableColumnInfo columnInfo) {
        String strDbType = columnInfo.getDataType().toUpperCase();
        if ("NUMBER".equals(strDbType)) {
            if (columnInfo.getDataPrecision() == null) {
                strDbType = "NUMBER";
            } else {
                int length = columnInfo.getDataPrecision();
                if (String.valueOf(length).equals("1")) {
                    strDbType = "NUMBER1_1_" + columnInfo.getDataScale().toString();
                } else if (String.valueOf(length).equals("2")) {
                    strDbType = "NUMBER2_2_" + columnInfo.getDataScale().toString();
                } else if (length >= 3 && length <= 4) {
                    strDbType = "NUMBER3_4_" + columnInfo.getDataScale().toString();
                } else if (length >= 5 && length <= 9) {
                    strDbType = "NUMBER5_9_" + columnInfo.getDataScale().toString();
                } else if (length >= 10 && length <= 18) {
                    strDbType = "NUMBER10_18_" + columnInfo.getDataScale().toString();
                } else if (length >= 19 && length <= 38) {
                    strDbType = "NUMBER19_38_" + columnInfo.getDataScale().toString();
                } else {
                    strDbType = "NUMBER";
                }
            }
        }
        Class<?> clazz = getOracleTypeMap().get(strDbType);
//        if(columnInfo.getNullable().equalsIgnoreCase("N")) {
//            if(Arrays.asList(Integer.class, Boolean.class, Byte.class, Short.class, Long.class).contains(clazz)) {
//
//            }
//        }
        return clazz;
    }

    private String getTypePackage(String typeStr) {
        String type = "";
        switch (typeStr.toUpperCase()) {
            case "":
                break;
            default:
                type = "";
                break;
        }
        return type;
    }

    private Map<String, Class<?>> getOracleTypeMap() {
        if (this.oracleTypeMap == null) {
            Map<String, Class<?>> map = new HashMap<>();
            map.put("CHAR", String.class);
            map.put("VARCHAR2", String.class);
            map.put("NUMBER", Integer.class);
            map.put("NUMBER1_1_0", Boolean.class);
            map.put("NUMBER2_2_0", Byte.class);
            map.put("NUMBER3_4_0", Short.class);
            map.put("NUMBER5_9_0", Integer.class);
            map.put("NUMBER10_18_0", Long.class);
            map.put("NUMBER19_38_0", BigDecimal.class);
            map.put("DATE", Date.class);
            map.put("TIMESTAMP", Date.class);
            this.oracleTypeMap = map;
        }
        return this.oracleTypeMap;
    }


}
