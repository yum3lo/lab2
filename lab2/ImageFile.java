package lab2;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

class ImageFile extends FileItem {
    public ImageFile(String name, String path) {
        super(name, path);
    }

    public String getImageSize() {
        File file = new File(path);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            if (bufferedImage != null) {
                int width = bufferedImage.getWidth();
                int height = bufferedImage.getHeight();
                return width + "x" + height;
            } else {
                return "Image could not be read.";
            }
        } catch (IOException e) {
            return "Error reading the image.";
        }
    }

    @Override
    public String getFileInfo() {
        return super.getFileInfo() + "\nImage Size: " + getImageSize();
    }
}
