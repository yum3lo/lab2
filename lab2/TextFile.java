package lab2;

import java.io.File;
//import java.text.SimpleDateFormat;
import java.util.Date;

class TextFile extends FileItem {
    public TextFile(String name, String path) {
        super(name, path);
    }

    @Override
    public String checkStatus(Date snapshotTime) {
        File file = new File(path);
        Date lastModified = new Date(file.lastModified());
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        if (lastModified.before(snapshotTime)) {
            return "No Change";
        } else {
            return "Changed";
        }
    }
}