package org.buildwithraghu.lowleveldesign.loggingframework.strategies.formatter;

import org.buildwithraghu.lowleveldesign.loggingframework.models.LogMessage;

public interface LogFormatter {
    String format(LogMessage logMessage);
}
