package org.buildwithraghu.lowleveldesign.loggingframework.strategies.appender;

import org.buildwithraghu.lowleveldesign.loggingframework.models.LogMessage;
import org.buildwithraghu.lowleveldesign.loggingframework.strategies.formatter.LogFormatter;

public interface LogAppender {
    void append(LogMessage logMessage);
    void close();
    LogFormatter getFormatter();
    void setFormatter(LogFormatter formatter);
}
