package org.buildwithraghu.lowleveldesign.unixfilesystem;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class File {

    private final boolean isDirectory;
    private final int size;
    private final String owner;
    private final String filename;
    private final Set<File> entries = new HashSet<>();

    public File(boolean isDirectory, int size, String owner, String filename) {
        this.isDirectory = isDirectory;
        this.size = size;
        this.owner = owner;
        this.filename = filename;
    }

    public Object extract(final FileAttribute attributeName) {
        switch (attributeName) {
            case SIZE -> { return size; }
            case OWNER -> { return owner; }
            case IS_DIRECTORY -> { return isDirectory; }
            case FILENAME -> { return filename; }
        }
        throw new IllegalArgumentException("Invalid filter criteria type");
    }

    public void addEntry(File entry) {
        entries.add(entry);
    }
}
