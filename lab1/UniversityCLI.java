import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import university.*;

public class UniversityCLI {
    private University university;
    private Scanner scanner;

    public UniversityCLI(University university) {
        this.university = university;
        this.scanner = new Scanner(System.in);
    }

    private void displayMainMenu() {
        System.out.println("TUM Management System - Main Menu");
        System.out.println("Enter 'f' for Faculty Operations");
        System.out.println("Enter 'g' for General Operations");
        System.out.println("Enter 'q' to Quit");
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            char choice = getUserChoice();
            switch (choice) {
                case 'f':
                    runFacultyOperations();
                    break;
                case 'g':
                    runGeneralOperations();
                    break;
                case 'q':
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
        System.out.println("Program terminated.");
    }

    private char getUserChoice() {
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
        return choice.charAt(0);
    }

    private void runFacultyOperations() {
        boolean exit = false;
    
        while (!exit) {
            displayFacultyOperationsMenu();
            char choice = getUserChoice();
    
            switch (choice) {
                case '1':
                    assignStudentToFaculty();
                    break;
                case '2':
                    graduateStudent();
                    break;
                case '3':
                    displayStudents();
                    break;
                case '4':
                    displayGraduates();
                    break;
                case '5':
                    checkStudent();
                    break;
                case '0':
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
    private void displayFacultyOperationsMenu() {
        System.out.println("Faculty Operations Menu");
        System.out.println("1. Create and assign a student to a faculty");
        System.out.println("2. Graduate a student from a faculty");
        System.out.println("3. Display current enrolled students");
        System.out.println("4. Display graduates");
        System.out.println("5. Check if a student belongs to a faculty");
        System.out.println("0. Return to Main Menu");
    }
    
    private void assignStudentToFaculty() {
        System.out.println("Enter student details:");
        System.out.print("First Name: ");
        String firstName = scanner.next();

        System.out.print("Last Name: ");
        String lastName = scanner.next();

        System.out.print("Email: ");
        String email = scanner.next();

        System.out.print("Enrollment Date (YYYY-MM-DD): ");
        String enrollmentDateStr = scanner.next();
        LocalDate enrollmentDate = LocalDate.parse(enrollmentDateStr);

        System.out.print("Date of Birth (YYYY-MM-DD): ");
        String dateOfBirthStr = scanner.next();
        LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);

        System.out.print("Enter Faculty Abbreviation: ");
        String facultyAbbreviation = scanner.next();

        Faculty faculty = university.getFacultyByAbbreviation(facultyAbbreviation);

        if (faculty != null) {
            Student student = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth, false);
            faculty.addStudent(student);
            System.out.println("Student created and assigned to faculty successfully!");
        } else {
            System.out.println("Faculty with abbreviation " + facultyAbbreviation + " not found. Try again.");
        }
    }
    
    private Student findStudentByEmail(String email) {
        for (Faculty faculty : university.getFaculties()) {
            for (Student student : faculty.getStudents()) {
                if (student.getEmail().equals(email)) {
                    return student;
                }
            }
        }
        return null;
    }
    
    private void graduateStudent() {
        System.out.print("Enter the student's email to graduate: ");
        String studentEmail = scanner.next();
        Student studentToGraduate = findStudentByEmail(studentEmail);
    
        if (studentToGraduate != null) {
            if (!studentToGraduate.getIsGraduated()) {
                studentToGraduate.setIsGraduated(true);
                System.out.println("Student with email " + studentEmail + " has been graduated.");
            } else {
                System.out.println("Student with email " + studentEmail + " has already graduated.");
            }
        } else {
            System.out.println("Student with email " + studentEmail + " not found.");
        }
    }      
    
    private void displayStudents() {
        System.out.print("Enter Faculty Abbreviation: ");
        String facultyAbbreviation = scanner.next();
        Faculty faculty = university.getFacultyByAbbreviation(facultyAbbreviation);

        if (faculty != null) {
            List<Student> enrolledStudents = faculty.getStudents();

            if (enrolledStudents.isEmpty()) {
                System.out.println("There are no enrolled students in " + faculty.getName());
            } else {
                System.out.println("Enrolled students in " + faculty.getName() + ":");
                for (Student student : enrolledStudents) {
                    System.out.println("Student Name: " + student.getFullName());
                    System.out.println("Email: " + student.getEmail());
                    System.out.println("Enrollment Date: " + student.getEnrollmentDate());
                    System.out.println("Date of Birth: " + student.getBirthDate());
                    System.out.println("------------------------------");
                }
            }
        } else {
            System.out.println("Faculty with abbreviation " + facultyAbbreviation + " not found.");
        }
    }

    private void displayGraduates() {
        System.out.print("Enter Faculty Abbreviation: ");
        String facultyAbbreviation = scanner.next();
        Faculty faculty = university.getFacultyByAbbreviation(facultyAbbreviation);

        if (faculty != null) {
            List<Student> graduates = getGraduatesForFaculty(faculty);

            if (graduates.isEmpty()) {
                System.out.println("No graduates in " + faculty.getName());
            } else {
                System.out.println("Graduates in " + faculty.getName() + ":");
                for (Student graduate : graduates) {
                    System.out.println("Student Name: " + graduate.getFullName());
                    System.out.println("Email: " + graduate.getEmail());
                    System.out.println("Enrollment Date: " + graduate.getEnrollmentDate());
                    System.out.println("Date of Birth: " + graduate.getBirthDate());
                    System.out.println("------------------------------");
                }
            }
        } else {
            System.out.println("Faculty with abbreviation " + facultyAbbreviation + " not found.");
        }
    }

    private List<Student> getGraduatesForFaculty(Faculty faculty) {
        List<Student> graduates = new ArrayList<>();
        for (Student student : faculty.getStudents()) {
            if (student.getIsGraduated()) {
                graduates.add(student);
            }
        }
        return graduates;
    }

    private void checkStudent() {
        System.out.print("Enter the student's email to check: ");
        String studentEmail = scanner.next();
        Faculty faculty = findFacultyForStudent(studentEmail);
    
        if (faculty != null) {
            System.out.println("Student with email " + studentEmail + " belongs to " + faculty.getName());
        } else {
            System.out.println("Student with email " + studentEmail + " does not belong to any faculty.");
        }
    }
    
    private Faculty findFacultyForStudent(String email) {
        for (Faculty faculty : university.getFaculties()) {
            for (Student student : faculty.getStudents()) {
                if (student.getEmail().equals(email)) {
                    return faculty;
                }
            }
        }
        return null;
    }
    //////////////////////////////////////////////////////////
    private void runGeneralOperations() {
        boolean exit = false;
    
        while (!exit) {
            displayGeneralOperationsMenu();
            char choice = getUserChoice();
    
            switch (choice) {
                case '1':
                    createNewFaculty();
                    break;
                case '2':
                    searchFacultyByStudentEmail();
                    break;
                case '3':
                    displayUniversityFaculties();
                    break;
                case '4':
                    displayFacultiesByField();
                    break;
                case '0':
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    
    private void displayGeneralOperationsMenu() {
        System.out.println("General Operations Menu");
        System.out.println("1. Create a new faculty");
        System.out.println("2. Search what faculty a student belongs to by email");
        System.out.println("3. Display University faculties");
        System.out.println("4. Display all faculties belonging to a field");
        System.out.println("0. Return to Main Menu");
    }
    
    private void createNewFaculty() {
        System.out.println("Enter Faculty Name: ");
        String facultyName = scanner.nextLine();
        System.out.println("Enter Faculty Abbreviation: ");
        String facultyAbbreviation = scanner.nextLine();
        System.out.println("Select Study Field:");
        
        for (StudyField field : StudyField.values()) {
            System.out.println(field.ordinal() +1 + ". " + field);
        }

        int studyFieldChoice = scanner.nextInt();
        StudyField selectedStudyField = StudyField.values()[studyFieldChoice-1];
        Faculty newFaculty = new Faculty(facultyName, facultyAbbreviation, new ArrayList<>(), selectedStudyField);
    
        university.addFaculty(newFaculty);
        System.out.println("New faculty created successfully!");
    }
    
    private void searchFacultyByStudentEmail() {
        System.out.print("Enter student's email: ");
        String studentEmail = scanner.nextLine();
        Faculty faculty = university.getFacultyByStudentEmail(studentEmail);
    
        if (faculty != null) {
            System.out.println("Student with email " + studentEmail + " belongs to " + faculty.getName());
        } else {
            System.out.println("Student with email " + studentEmail + " does not belong to any faculty.");
        }
    }
    
    private void displayUniversityFaculties() {
        ArrayList<Faculty> faculties = university.getFaculties();
    
        if (faculties.isEmpty()) {
            System.out.println("No faculties found.");
        } else {
            System.out.println("List of University Faculties:");
            for (Faculty faculty : faculties) {
                System.out.println("Faculty Name: " + faculty.getName());
                System.out.println("Faculty Abbreviation: " + faculty.getAbbreviation());
                System.out.println("Study Field: " + faculty.getStudyField());
                System.out.println("------------------------------");
            }
        }
    }
    
    private void displayFacultiesByField() {
        System.out.println("Select a Study Field:");
        for (StudyField field : StudyField.values()) {
            System.out.println(field.ordinal() + ". " + field);
        }
    
        int studyFieldChoice = scanner.nextInt();
        StudyField selectedStudyField = StudyField.values()[studyFieldChoice];
        List<Faculty> faculties = university.getFacultiesByStudyField(selectedStudyField);
    
        if (faculties.isEmpty()) {
            System.out.println("No faculties found.");
        } else {
            System.out.println("List of Faculties in the " + selectedStudyField + " Field:");
            for (Faculty faculty : faculties) {
                System.out.println("Faculty Name: " + faculty.getName());
                System.out.println("Faculty Abbreviation: " + faculty.getAbbreviation());
                System.out.println("------------------------------");
            }
        }
    }
    //////////////////////////////////////////////////////////    
    public static void main(String[] args) {
        University university = new University();
        UniversityCLI cli = new UniversityCLI(university);
        cli.run();
    }
}