package org.buildwithraghu.lowleveldesign.unixfilesystem;

import org.buildwithraghu.lowleveldesign.unixfilesystem.predicates.Predicate;

public class FileSearchCriteria {

    private final Predicate predicate;

    public FileSearchCriteria(final Predicate predicate) {
        this.predicate = predicate;
    }

    public boolean isMatch(File inputFile) {
        return predicate.isMatch(inputFile);
    }
}
