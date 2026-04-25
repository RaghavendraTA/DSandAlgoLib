package org.buildwithraghu.lowleveldesign.loggingframework.strategies.appender;

import org.buildwithraghu.lowleveldesign.loggingframework.models.LogMessage;
import org.buildwithraghu.lowleveldesign.loggingframework.strategies.formatter.LogFormatter;
import org.buildwithraghu.lowleveldesign.loggingframework.strategies.formatter.SimpleTextFormatter;

public class ConsoleAppender implements LogAppender {
    private LogFormatter formatter;

    public ConsoleAppender() {
        this.formatter = new SimpleTextFormatter();
    }

    @Override
    public void append(LogMessage logMessage) {
        System.out.println(formatter.format(logMessage));
    }

    @Override
    public void close() {

    }

    @Override
    public LogFormatter getFormatter() {
        return formatter;
    }

    @Override
    public void setFormatter(LogFormatter formatter) {
        this.formatter = formatter;
    }
}
