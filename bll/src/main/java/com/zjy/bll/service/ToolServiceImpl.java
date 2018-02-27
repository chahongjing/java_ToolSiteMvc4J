package com.zjy.bll.service;

import com.zjy.baseframework.DbHelperNew;
import com.zjy.bll.common.BaseService;
import com.zjy.bll.dao.ToolDao;
import com.zjy.entities.TableColumnInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/2/27.
 */
@Service
public class ToolServiceImpl extends BaseService<ToolDao, TableColumnInfo> implements ToolService {
    @Override
    public String getTableInfo(String type, String url, String user, String password, String tableName) {
        String fieldType = "String";
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
        List<TableColumnInfo> list = getTableInfo(url, user, password, tableName);
        if(list == null) {
            list = new ArrayList<>();
        }
        for (TableColumnInfo columnInfo : list) {
            fieldType = getFieldType(columnInfo.getDataType());
            fieldPackage = getTypePackage(fieldType);
            if(!org.apache.commons.lang3.StringUtils.isBlank(fieldPackage) && !packages.contains(fieldPackage)) {
                packages.add(fieldPackage);
                sbHeader.append("import " + fieldPackage + ";" + newLine);
            }
            colComments = Objects.toString(columnInfo.getColComments(), "");
            colName = columnInfo.getColumnName().substring(0, 1).toLowerCase() + columnInfo.getColumnName().substring(1);
            sb.append("    /**" + newLine);
            sb.append("     * " + colComments + newLine);
            sb.append("     */" + newLine);
            sb.append("    private " + fieldType + " " + colName + ";" + newLine);

            sbGetterSetter.append(newLine);
            sbGetterSetter.append("    /**" + newLine);
            sbGetterSetter.append("     * 获取" + colComments + newLine);
            sbGetterSetter.append("     * @return" + newLine);
            sbGetterSetter.append("     */" + newLine);
            sbGetterSetter.append("    public " + fieldType + " get" + columnInfo.getColumnName() + "() {" + newLine);
            sbGetterSetter.append("        return " + columnInfo.getColumnName() + ";" + newLine);
            sbGetterSetter.append("    }" + newLine);
            sbGetterSetter.append(newLine);
            sbGetterSetter.append("    /**" + newLine);
            sbGetterSetter.append("     * 设置" + colComments + newLine);
            sbGetterSetter.append("     * @param " + columnInfo.getColumnName() + newLine);
            sbGetterSetter.append("     */" + newLine);
            sbGetterSetter.append("    public void set" + columnInfo.getColumnName() + "(" + fieldType + " " + columnInfo.getColumnName() + ") {" + newLine);
            sbGetterSetter.append("        this." + columnInfo.getColumnName() + " = " + columnInfo.getColumnName() + ";" + newLine);
            sbGetterSetter.append("    }" + newLine);
        }
        sb.insert(0, sbHeader.toString() + newLine);
        sb.append(sbGetterSetter.toString() + newLine);
        sb.append("}" + newLine);
        return sb.toString();
    }

    public List<TableColumnInfo> getTableInfo(String url, String user, String password, String tableName) {
        String sql = "SELECT INITCAP(tabCol.TABLE_NAME) as tableName, INITCAP(tabCol.COLUMN_NAME) as columnName, tabCol.DATA_TYPE as dataType," +
                "            colCom.COMMENTS AS colComments, tabCol.nullable, tabCom.COMMENTS AS tabComments," +
                "            tabCol.DATA_LENGTH as dataLength, tabCol.DATA_PRECISION as dataPrecision," +
                "            tabCol.DATA_SCALE as dataScale" +
                "      FROM USER_TAB_COLUMNS tabCol" +
                "      LEFT JOIN USER_COL_COMMENTS colCom ON UPPER(tabCol.TABLE_NAME) = UPPER(colCom.TABLE_NAME) AND UPPER(tabCol.COLUMN_NAME) = UPPER(colCom.COLUMN_NAME)" +
                "      LEFT JOIN USER_TAB_COMMENTS tabCom ON UPPER(tabCol.TABLE_NAME) = UPPER(tabCom.TABLE_NAME)" +
                "     WHERE UPPER(tabCol.TABLE_NAME) = UPPER(:tableName)" +
                "     ORDER BY tabCol.COLUMN_ID";
        Connection conn = DbHelperNew.getConnection(url, user, password);
        return DbHelperNew.getList(conn, sql, TableColumnInfo.class, tableName);
    }

    private String getFieldType(String typeStr) {
        String type = null;
        switch (typeStr.toUpperCase()) {
            case "":
                break;
            default:
                type = "String";
                break;
        }
        return type;
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
}
