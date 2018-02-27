package com.zjy.entities;

/**
 * Created by Administrator on 2018/2/27.
 */
public class TableColumnInfo {
    /**
     *
     */
    private String tableName;
    private String columnName;
    private String dataType;
    private String colComments;
    private String nullable;
    private String tabComments;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColComments() {
        return colComments;
    }

    public void setColComments(String colComments) {
        this.colComments = colComments;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    /**
     * 获取
     * @return
     */
    public String getTabComments() {
        return tabComments;
    }

    /**
     * 设置
     * @param tabComments
     */
    public void setTabComments(String tabComments) {
        this.tabComments = tabComments;
    }
}
