package lab2.src;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.regex.*;
import java.util.Date;

class ProgramFile extends FileItem {
    private Date createdDate; // Define the createdDate field
    private Date updatedDate;

    public ProgramFile(String name, String path) {
        super(name, path);
        this.createdDate = retrieveCreationDate(path);
        this.updatedDate = new Date(new File(path).lastModified());
    }

    public int getLineCount() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            int lineCount = 0;
            while (reader.readLine() != null) {
                lineCount++;
            }
            reader.close();
            return lineCount;
        } catch (IOException e) {
            return -1; // Error
        }
    }

    public int getClassCount() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            int classCount = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (isClassLine(line)) {
                    classCount++;
                }
            }
            reader.close();
            return classCount;
        } catch (IOException e) {
            return -1; // Error
        }
    }

    public int getMethodCount() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            int methodCount = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (isMethodLine(line)) {
                    methodCount++;
                }
            }
            reader.close();
            return methodCount;
        } catch (IOException e) {
            return -1; // Error
        }
    }

    private Date retrieveCreationDate(String path) {
        try {
            Path filePath = FileSystems.getDefault().getPath(path);
            BasicFileAttributes attrs = java.nio.file.Files.readAttributes(filePath, BasicFileAttributes.class);
            return new Date(attrs.creationTime().toMillis());
        } catch (IOException e) {
            return new Date(0);
        }
    }

    private boolean isClassLine(String line) {
        // Identifying class declarations in Java and Python
        String pattern = "(class|def)\\s+\\w+\\s*\\(";
        return Pattern.compile(pattern).matcher(line).find();
    }

    private boolean isMethodLine(String line) {
        // Identifying method definitions in Java and Python
        String pattern = "(public|private|protected)?\\s+(void|\\w+)\\s+\\w+\\s*\\(";
        return Pattern.compile(pattern).matcher(line).find();
    }

    @Override
    public String getFileInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createdDateString = sdf.format(createdDate);

        String fileInfo = "File name: " + name +
                "\nFile extension: " + getFileExtension() +
                "\nCreated Date: " + createdDateString; // Include only createdDate

        if (!createdDate.equals(updatedDate)) {
            String updatedDateString = sdf.format(updatedDate);
            fileInfo += "\nUpdated Date: " + updatedDateString;
        }

        fileInfo += "\nLine Count: " + getLineCount() +
                "\nClass Count: " + getClassCount() +
                "\nMethod Count: " + getMethodCount();

        return fileInfo;
    }
}