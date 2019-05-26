package com.zjy.bll.common;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.LoggingEvent;
import com.zjy.baseframework.StackTraceElementHelper;
import com.zjy.entities.enums.LogLevel;
import com.zjy.entities.UserInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

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
        UserInfo user = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        String userId = null;
        if (user != null) {
            userId = user.getUserId();
        }
        userId = Objects.toString(userId, StringUtils.EMPTY);
        insertStatement.setString(1, UUID.randomUUID().toString().replace("-", StringUtils.EMPTY));
        insertStatement.setString(2, userId);
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
        if(ArrayUtils.isNotEmpty(canshus) && canshus[canshus.length - 1] instanceof Method) {
            Method method = (Method)canshus[canshus.length - 1];
            stmt.setString(3, method.getDeclaringClass().getName());
            stmt.setString(4, method.getName());
        } else {
            stmt.setString(3, caller.getFileName());
            stmt.setString(4, caller.getMethodName());
        }
        stmt.setInt(5, LogLevel.getByName(loggingEvent.getLevel().toString()).getValue());
        stmt.setString(6, asStringTruncatedToNumber(loggingEvent.getFormattedMessage(), 1300));
        stmt.setTimestamp(7, new Timestamp(loggingEvent.getTimeStamp()));
    }

    private String buildInsertSQL() {
        //拼接insert sql
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
        sqlBuilder.append("CAOZUORIZHI").append(" (");
        sqlBuilder.append("logID").append(", ");
        sqlBuilder.append("userID").append(", ");
        sqlBuilder.append("controller").append(", ");
        sqlBuilder.append("method").append(", ");
        sqlBuilder.append("logLevel").append(", ");
        sqlBuilder.append("content").append(", ");
        sqlBuilder.append("createdOn").append(") ");
        sqlBuilder.append("VALUES (?, ?, ? ,?, ?, ?, ?)");
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
