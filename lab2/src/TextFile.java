package lab2.src;

import java.io.*;
class TextFile extends FileItem {
    public TextFile(String name, String path) {
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

    public int getWordCount() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            int wordCount = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+"); // Split on whitespace
                wordCount += words.length;
            }
            reader.close();
            return wordCount;
        } catch (IOException e) {
            return -1; // Error
        }
    }

    public int getCharacterCount() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            int charCount = 0;
            while ((reader.read()) != -1) {
                charCount++;
            }
            reader.close();
            return charCount;
        } catch (IOException e) {
            return -1; // Error
        }
    }

    @Override
    public String getFileInfo() {
        return super.getFileInfo() + "\nLine Count: " + getLineCount() + "\nWord Count: " + getWordCount() + "\nCharacter Count: " + getCharacterCount();
    }
}