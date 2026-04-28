package org.buildwithraghu.lowleveldesign.unixfilesystem;

import java.util.*;

public class FileSearch {

    public List<File> search(File root, FileSearchCriteria criteria) {
        final List<File> result = new ArrayList<>();
        final ArrayDeque<File> recursionStack = new ArrayDeque<>();
        recursionStack.add(root);

        while(!recursionStack.isEmpty()) {
            File next = recursionStack.pop();
            if (criteria.isMatch(next)) {
                result.add(next);
            }
            for(File entry: next.getEntries()) {
                recursionStack.push(entry);
            }
        }

        return result;
    }
}
