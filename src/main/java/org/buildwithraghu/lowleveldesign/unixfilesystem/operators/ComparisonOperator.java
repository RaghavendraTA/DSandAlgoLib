package org.buildwithraghu.lowleveldesign.unixfilesystem.operators;

public interface ComparisonOperator<T> {
    boolean isMatch(T attributeValue, T expectedValue);
}
