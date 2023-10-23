package lab2;

import java.util.Date;

class ImageFile extends FileItem {
    public ImageFile(String name, String path) {
        super(name, path);
    }

    @Override
    public String checkStatus(Date snapshotTime) {
        return "No Change";
    }
}