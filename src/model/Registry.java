package model;

import java.util.HashMap;
import java.util.Map;

public class Registry {
    private Map<String, FileEntry> fileEntries;

    public Registry() {
        this.fileEntries = new HashMap<>();
    }

    public void addEntry(String filePath, FileEntry entry) {
        fileEntries.put(filePath, entry);
    }

    public FileEntry getEntry(String filePath) {
        return fileEntries.get(filePath);
    }

    public boolean contains(String filePath) {
        return fileEntries.containsKey(filePath);
    }

    public Map<String, FileEntry> getAllEntries() {
        return fileEntries;
    }

    public void clear() {
        fileEntries.clear();
    }

    @Override
    public String toString() {
        return "Registry{" +
                "fileEntries=" + fileEntries +
                '}';
    }
}
