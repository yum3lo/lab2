package lab2;

import java.util.Date;
abstract class FileItem {
    protected String name;
    protected String path;

    public FileItem(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public abstract String checkStatus(Date snapshotTime);
}