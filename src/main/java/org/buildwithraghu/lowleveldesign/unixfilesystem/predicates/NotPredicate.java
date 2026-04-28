package org.buildwithraghu.lowleveldesign.unixfilesystem.predicates;

import org.buildwithraghu.lowleveldesign.unixfilesystem.File;

public class NotPredicate implements CompositePredicate {

    private final Predicate operand;

    public NotPredicate(final Predicate operand) {
        this.operand = operand;
    }

    @Override
    public boolean isMatch(File inputFile) {
        return !operand.isMatch(inputFile);
    }
}
