package lab2;

import java.io.*;

class ProgramFile extends FileItem {
    public ProgramFile(String name, String path) {
        super(name, path);
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
                if (line.trim().startsWith("class ")) {
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
                if (line.trim().startsWith("public") && line.trim().contains("(") && line.trim().endsWith(")")) {
                    methodCount++;
                }
            }
            reader.close();
            return methodCount;
        } catch (IOException e) {
            return -1; // Error
        }
    }

    @Override
    public String getFileInfo() {
        return super.getFileInfo() + "\nLine Count: " + getLineCount() + "\nClass Count: " + getClassCount() + "\nMethod Count: " + getMethodCount();
    }
}
