package lab2.src;

import java.util.Date;

public class DocumentMonitor {
    private Date snapshotTime;
    private FileItem[] files;

    public DocumentMonitor() {
        snapshotTime = new Date();
        files = new FileItem[] {
            new TextFile("text.txt", "C:\\Users\\Professional\\OneDrive\\Documente\\GitHub\\oop_labs\\lab2\\files_track\\text.txt"),
            new ImageFile("img.jpg", "C:\\Users\\Professional\\OneDrive\\Documente\\GitHub\\oop_labs\\lab2\\files_track\\img.jpg"),
            new ProgramFile("lab0.py", "C:\\Users\\Professional\\OneDrive\\Documente\\GitHub\\oop_labs\\lab2\\files_track\\lab0.py")
        };
    }

    public void commit() {
        snapshotTime = new Date();
    }

    public void info(String filename) {
        for (FileItem file : files) {
            if (file.name.equals(filename)) {
                System.out.println(file.getFileInfo());
                if (file.hasChanged(snapshotTime)) {
                    System.out.println("Changed");
                } else {
                    System.out.println("No Change");
                }
                return;
            }
        }
        System.out.println("File not found: " + filename);
    }

    public void status() {
        System.out.println("Created Snapshot at: " + snapshotTime);
        for (FileItem file : files) {
            System.out.print(file.name + " - ");
            if (file.hasChanged(snapshotTime)) {
                System.out.println("Changed");
            } else {
                System.out.println("No Change");
            }
        }
    }

    public static void main(String[] args) {
        DocumentMonitor monitor = new DocumentMonitor();

        boolean running = true;
        while (running) {
            System.out.println("Enter a command (commit, info <filename>, status, or exit):");
            String command = System.console().readLine();

            if (command.equals("commit")) {
                monitor.commit();
            } else if (command.startsWith("info ")) {
                String filename = command.substring(5);
                monitor.info(filename);
            } else if (command.equals("status")) {
                monitor.status();
            } else if (command.equals("exit")) {
                running = false;
            }
        }
    }
}