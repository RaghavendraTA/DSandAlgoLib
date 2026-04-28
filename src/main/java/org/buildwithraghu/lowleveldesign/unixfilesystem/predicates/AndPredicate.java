package org.buildwithraghu.lowleveldesign.unixfilesystem.predicates;

import org.buildwithraghu.lowleveldesign.unixfilesystem.File;

import java.util.List;

public class AndPredicate implements CompositePredicate {

    private final List<Predicate> operands;

    public AndPredicate(final List<Predicate> operands) {
        this.operands = operands;
    }

    @Override
    public boolean isMatch(File inputFile) {
        return operands.stream().allMatch(predicate -> predicate.isMatch(inputFile));
    }
}
