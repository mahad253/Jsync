package model;

import java.nio.file.Path;

public class Profile {
    private Path sourcePath;
    private Path targetPath;

    public Profile() {
    }

    public Profile(Path sourcePath, Path targetPath) {
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
    }

    public Path getSourcePath() {
        return sourcePath;
    }

    public Path getTargetPath() {
        return targetPath;
    }

    public void setSourcePath(Path sourcePath) {
        this.sourcePath = sourcePath;
    }

    public void setTargetPath(Path targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "sourcePath=" + sourcePath +
                ", targetPath=" + targetPath +
                '}';
    }
}
