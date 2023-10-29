package lab2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
class FileItem {
    protected String name;
    protected String path;

    public FileItem(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public boolean hasChanged(Date snapshotTime) {
        File file = new File(path);
        Date lastModified = new Date(file.lastModified());
        return lastModified.after(snapshotTime);
    }

    public String getFileInfo() {
        return "File name: " + name + "\nFile extension: " + getFileExtension() + "\nCreated Date: " + getCreationDate() + "\nUpdated Date: " + getLastModifiedDate();
    }

    public String getFileExtension() {
        int lastDotIndex = name.lastIndexOf('.');
        return (lastDotIndex > 0) ? name.substring(lastDotIndex + 1) : "";
    }

    public String getCreationDate() {
        File file = new File(path);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(file.lastModified()));
    }

    public String getLastModifiedDate() {
        File file = new File(path);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(file.lastModified()));
    }
}