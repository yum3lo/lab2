package lab2;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;

class Snapshot {
    private Date snapshotTime;
    private Map<String, FileItem> files;

    public Snapshot(Date snapshotTime) {
        this.snapshotTime = snapshotTime;
        this.files = new HashMap<>();
    }

    public void addFile(FileItem file) {
        files.put(file.name, file);
    }

    public FileItem getFileByName(String name) {
        return files.get(name);
    }

    public void updateSnapshotTime(Date snapshotTime) {
        this.snapshotTime = snapshotTime;
    }

    public void listChanges() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Created Snapshot at: " + sdf.format(snapshotTime));
        for (FileItem file : files.values()) {
            boolean changed = file.hasChanged(snapshotTime);
            System.out.println(file.name + " - " + (changed ? "Changed" : "No Change"));
        }
    }
}
