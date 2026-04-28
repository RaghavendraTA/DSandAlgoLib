package org.buildwithraghu.lowleveldesign.unixfilesystem.predicates;

import org.buildwithraghu.lowleveldesign.unixfilesystem.File;

public interface Predicate {
    boolean isMatch(File inputFile);
}
