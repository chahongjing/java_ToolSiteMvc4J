package com.zjy.bll.common;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.LoggingEvent;
import com.zjy.baseframework.StackTraceElementHelper;
import com.zjy.bll.common.shiro.ShiroRealmUtils;
import com.zjy.bll.enums.LogLevel;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/12/22.
 */
public class DbAppender extends DBAppenderBase<LoggingEvent> {
    private String insertSql;

    @Override
    public void start() {
        insertSql = buildInsertSQL();
        super.start();
    }

    @Override
    protected String getInsertSQL() {
        return insertSql;
    }

    @Override
    protected void subAppend(LoggingEvent loggingEvent, Connection connection, PreparedStatement insertStatement) throws Throwable {
        insertStatement.setString(1, null == ShiroRealmUtils.getCurrentUserId() ? StringUtils.EMPTY : ShiroRealmUtils.getCurrentUserId());
        bindLoggingEventWithInsertStatement(insertStatement, loggingEvent);
        int updateCount = insertStatement.executeUpdate();
        if (updateCount != 1) {
            addWarn("Failed to insert loggingEvent");
        }
    }

    private void bindLoggingEventWithInsertStatement(PreparedStatement stmt, LoggingEvent loggingEvent) throws SQLException {
        StackTraceElement caller = StackTraceElementHelper.extractFirstCaller(loggingEvent.getCallerData());
        if (caller == null) caller = CallerData.naInstance();
        Object[] canshus = loggingEvent.getArgumentArray();
        int length = canshus == null ? 0 : canshus.length;
        stmt.setString(2, caller.getFileName());
        stmt.setString(3, caller.getMethodName());
        stmt.setInt(4, LogLevel.getByName(loggingEvent.getLevel().toString()).getValue());
        stmt.setString(5, asStringTruncatedToNumber(loggingEvent.getFormattedMessage(), 1300));
        stmt.setTimestamp(6, new Timestamp(loggingEvent.getTimeStamp()));

    }

    private String buildInsertSQL() {
        //拼接insert sql
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
        sqlBuilder.append("CAOZUORIZHI").append(" (");
        sqlBuilder.append("userID").append(", ");
        sqlBuilder.append("controller").append(", ");
        sqlBuilder.append("method").append(", ");
        sqlBuilder.append("logLevel").append(", ");
        sqlBuilder.append("content").append(", ");
        sqlBuilder.append("createdOn").append(") ");
        sqlBuilder.append("VALUES (?, ?, ? ,?, ?, ?)");
        return sqlBuilder.toString();
    }

    public static String asStringTruncatedToNumber(String o, int number) {
        if (o == null) {
            return null;
        }
        if (o.length() <= number) {
            return o;
        } else {
            return o.substring(0, number);
        }
    }
}
