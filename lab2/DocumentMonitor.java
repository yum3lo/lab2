package lab2;

import java.util.Date;

public class DocumentMonitor {
    public static void main(String[] args) {
        // Create a snapshot with the current timestamp
        Date snapshotTime = new Date();
        Snapshot snapshot = new Snapshot(snapshotTime);

        // Define the files to be monitored
        FileItem testTextFile = new TextFile("test.txt", "path/to/test.txt");
        FileItem imageFile = new ImageFile("image.png", "path/to/image.png");
        FileItem pythonScriptFile = new ProgramFile("python_script.py", "path/to/python_script.py");

        // Add files to the snapshot
        snapshot.addFile(testTextFile);
        snapshot.addFile(imageFile);
        snapshot.addFile(pythonScriptFile);

        // Interactive command-line loop
        boolean running = true;
        while (running) {
            System.out.println("Enter a command (commit, info <filename>, status, or exit):");
            String command = System.console().readLine();

            if (command.equals("commit")) {
                snapshot.updateSnapshotTime(new Date());
                System.out.println("Snapshot time updated.");
            } else if (command.startsWith("info ")) {
                String fileName = command.substring(5); // Extract the filename
                FileItem file = snapshot.getFileByName(fileName);
                if (file != null) {
                    System.out.println(file.getFileInfo());
                } else {
                    System.out.println("File not found.");
                }
            } else if (command.equals("status")) {
                snapshot.listChanges();
            } else if (command.equals("exit")) {
                running = false;
            }
        }
    }
}