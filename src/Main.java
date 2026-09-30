import java.io.*;
import java.util.*;

/*
 * STUDENT PERFORMANCE ELECTRONIC SYSTEM
 * -------------------------------------
 * Concepts used:
 * 1. OOP
 * 2. ArrayList
 * 3. HashMap
 * 4. Queue
 * 5. Stack
 * 6. Searching
 * 7. Sorting
 * 8. File Handling
 */

public class Main {

    // =========================
    // STUDENT CLASS
    // =========================
    static class Student {

        private int id;
        private String name;
        private String department;
        private int[] marks;

        public Student(int id, String name,
                       String department, int[] marks) {

            this.id = id;
            this.name = name;
            this.department = department;
            this.marks = marks;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public int[] getMarks() {
            return marks;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public void setMarks(int[] marks) {
            this.marks = marks;
        }

        // Calculate total
        public int getTotal() {

            int total = 0;

            for (int mark : marks) {
                total += mark;
            }

            return total;
        }

        // Calculate average
        public double getAverage() {

            return (double) getTotal() / marks.length;
        }

        // Calculate grade
        public String getGrade() {

            double average = getAverage();

            if (average >= 90)
                return "A+";
            else if (average >= 80)
                return "A";
            else if (average >= 70)
                return "B";
            else if (average >= 60)
                return "C";
            else if (average >= 50)
                return "D";
            else
                return "F";
        }

        // Check pass/fail
        public boolean isPass() {

            for (int mark : marks) {

                if (mark < 40) {
                    return false;
                }
            }

            return true;
        }

        // Performance description
        public String getPerformance() {

            double average = getAverage();

            if (average >= 90)
                return "Excellent";
            else if (average >= 80)
                return "Very Good";
            else if (average >= 70)
                return "Good";
            else if (average >= 60)
                return "Average";
            else if (average >= 50)
                return "Needs Improvement";
            else
                return "Poor";
        }

        public String toString() {

            return "ID: " + id +
                    " | Name: " + name +
                    " | Department: " + department +
                    " | Total: " + getTotal() +
                    " | Average: " +
                    String.format("%.2f", getAverage()) +
                    " | Grade: " + getGrade() +
                    " | Result: " +
                    (isPass() ? "PASS" : "FAIL");
        }
    }

    // =========================
    // DATA STRUCTURES
    // =========================

    // ArrayList for storing students
    static ArrayList<Student> students = new ArrayList<>();

    // HashMap for fast searching
    static HashMap<Integer, Student> studentMap = new HashMap<>();

    // Queue for student evaluation
    static Queue<Student> evaluationQueue = new LinkedList<>();

    // Stack for undo delete
    static Stack<Student> deletedStudents = new Stack<>();

    static Scanner scanner = new Scanner(System.in);

    static final String FILE_NAME = "students.txt";


    // =========================
    // MAIN METHOD
    // =========================

    public static void main(String[] args) {

        loadData();

        System.out.println();
        System.out.println("==============================================");
        System.out.println("     STUDENT PERFORMANCE ELECTRONIC SYSTEM");
        System.out.println("==============================================");

        while (true) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    displayStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    undoDelete();
                    break;

                case 7:
                    sortStudents();
                    break;

                case 8:
                    findTopper();
                    break;

                case 9:
                    displayPassedStudents();
                    break;

                case 10:
                    displayFailedStudents();
                    break;

                case 11:
                    displayQueue();
                    break;

                case 12:
                    generateReport();
                    break;

                case 13:
                    subjectAnalysis();
                    break;

                case 14:
                    saveData();
                    break;

                case 15:
                    saveData();

                    System.out.println();
                    System.out.println("Thank you for using the system!");
                    System.out.println("Program terminated.");

                    return;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }
    }


    // =========================
    // MENU
    // =========================

    static void displayMenu() {

        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println("                 MAIN MENU");
        System.out.println("----------------------------------------------");

        System.out.println("1.  Add Student");
        System.out.println("2.  View All Students");
        System.out.println("3.  Search Student");
        System.out.println("4.  Update Student");
        System.out.println("5.  Delete Student");
        System.out.println("6.  Undo Delete");
        System.out.println("7.  Sort Students by Performance");
        System.out.println("8.  Find Topper");
        System.out.println("9.  Display Passed Students");
        System.out.println("10. Display Failed Students");
        System.out.println("11. Display Evaluation Queue");
        System.out.println("12. Generate Student Report");
        System.out.println("13. Subject-wise Analysis");
        System.out.println("14. Save Data");
        System.out.println("15. Exit");

        System.out.println("----------------------------------------------");
    }


    // =========================
    // ADD STUDENT
    // =========================

    static void addStudent() {

        System.out.println();
        System.out.println("========== ADD STUDENT ==========");

        int id = readInt("Enter Student ID: ");

        if (studentMap.containsKey(id)) {

            System.out.println(
                    "Student ID already exists!"
            );

            return;
        }

        scanner.nextLine();

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        int[] marks = new int[5];

        System.out.println();
        System.out.println("Enter marks for 5 subjects:");

        for (int i = 0; i < 5; i++) {

            marks[i] =
                    readMark("Subject " + (i + 1) + ": ");
        }

        Student student =
                new Student(
                        id,
                        name,
                        department,
                        marks
                );

        students.add(student);

        studentMap.put(id, student);

        evaluationQueue.offer(student);

        System.out.println();
        System.out.println(
                "Student added successfully!"
        );
    }


    // =========================
    // DISPLAY ALL STUDENTS
    // =========================

    static void displayStudents() {

        System.out.println();
        System.out.println("========== ALL STUDENTS ==========");

        if (students.isEmpty()) {

            System.out.println("No students available.");

            return;
        }

        for (Student student : students) {

            System.out.println(student);
        }
    }


    // =========================
    // SEARCH STUDENT
    // =========================

    static void searchStudent() {

        System.out.println();
        System.out.println("========== SEARCH STUDENT ==========");

        int id = readInt("Enter Student ID: ");

        // HashMap searching
        Student student = studentMap.get(id);

        if (student == null) {

            System.out.println("Student not found!");

        } else {

            displayDetailedStudent(student);
        }
    }


    // =========================
    // UPDATE STUDENT
    // =========================

    static void updateStudent() {

        System.out.println();
        System.out.println("========== UPDATE STUDENT ==========");

        int id = readInt("Enter Student ID: ");

        Student student = studentMap.get(id);

        if (student == null) {

            System.out.println("Student not found!");

            return;
        }

        scanner.nextLine();

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new department: ");
        String department = scanner.nextLine();

        int[] marks = new int[5];

        System.out.println("Enter new marks:");

        for (int i = 0; i < 5; i++) {

            marks[i] =
                    readMark("Subject " + (i + 1) + ": ");
        }

        student.setName(name);
        student.setDepartment(department);
        student.setMarks(marks);

        System.out.println();
        System.out.println(
                "Student updated successfully!"
        );
    }


    // =========================
    // DELETE STUDENT
    // =========================

    static void deleteStudent() {

        System.out.println();
        System.out.println("========== DELETE STUDENT ==========");

        int id = readInt("Enter Student ID: ");

        Student student = studentMap.get(id);

        if (student == null) {

            System.out.println("Student not found!");

            return;
        }

        students.remove(student);

        studentMap.remove(id);

        deletedStudents.push(student);

        System.out.println(
                "Student deleted successfully!"
        );
    }


    // =========================
    // UNDO DELETE
    // =========================

    static void undoDelete() {

        System.out.println();
        System.out.println("========== UNDO DELETE ==========");

        if (deletedStudents.isEmpty()) {

            System.out.println(
                    "Nothing to undo."
            );

            return;
        }

        Student student = deletedStudents.pop();

        students.add(student);

        studentMap.put(
                student.getId(),
                student
        );

        System.out.println(
                "Delete operation undone successfully!"
        );
    }


    // =========================
    // SORT STUDENTS
    // =========================

    static void sortStudents() {

        System.out.println();
        System.out.println(
                "========== STUDENT RANKING =========="
        );

        if (students.isEmpty()) {

            System.out.println(
                    "No students available."
            );

            return;
        }

        // Sorting using Comparator
        students.sort(
                Comparator.comparingDouble(
                        Student::getAverage
                ).reversed()
        );

        System.out.println();

        int rank = 1;

        for (Student student : students) {

            System.out.println(
                    "Rank " + rank +
                            " -> " +
                            student.getName() +
                            " | Average: " +
                            String.format(
                                    "%.2f",
                                    student.getAverage()
                            ) +
                            " | Grade: " +
                            student.getGrade()
            );

            rank++;
        }
    }


    // =========================
    // FIND TOPPER
    // =========================

    static void findTopper() {

        System.out.println();
        System.out.println("========== TOPPER ==========");

        if (students.isEmpty()) {

            System.out.println(
                    "No students available."
            );

            return;
        }

        Student topper = students.get(0);

        for (Student student : students) {

            if (student.getAverage()
                    > topper.getAverage()) {

                topper = student;
            }
        }

        displayDetailedStudent(topper);
    }


    // =========================
    // PASSED STUDENTS
    // =========================

    static void displayPassedStudents() {

        System.out.println();
        System.out.println(
                "========== PASSED STUDENTS =========="
        );

        boolean found = false;

        for (Student student : students) {

            if (student.isPass()) {

                System.out.println(student);

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No passed students."
            );
        }
    }


    // =========================
    // FAILED STUDENTS
    // =========================

    static void displayFailedStudents() {

        System.out.println();
        System.out.println(
                "========== FAILED STUDENTS =========="
        );

        boolean found = false;

        for (Student student : students) {

            if (!student.isPass()) {

                System.out.println(student);

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No failed students."
            );
        }
    }


    // =========================
    // QUEUE
    // =========================

    static void displayQueue() {

        System.out.println();
        System.out.println(
                "========== EVALUATION QUEUE =========="
        );

        if (evaluationQueue.isEmpty()) {

            System.out.println(
                    "Queue is empty."
            );

            return;
        }

        for (Student student : evaluationQueue) {

            System.out.println(
                    student.getId() +
                            " - " +
                            student.getName()
            );
        }
    }


    // =========================
    // STUDENT REPORT
    // =========================

    static void generateReport() {

        System.out.println();
        System.out.println(
                "========== STUDENT REPORT =========="
        );

        int id = readInt("Enter Student ID: ");

        Student student = studentMap.get(id);

        if (student == null) {

            System.out.println(
                    "Student not found!"
            );

            return;
        }

        displayDetailedStudent(student);
    }


    // =========================
    // DETAILED STUDENT
    // =========================

    static void displayDetailedStudent(
            Student student) {

        System.out.println();
        System.out.println(
                "=============================================="
        );

        System.out.println(
                "          STUDENT PERFORMANCE REPORT"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Student ID   : " +
                        student.getId()
        );

        System.out.println(
                "Name         : " +
                        student.getName()
        );

        System.out.println(
                "Department   : " +
                        student.getDepartment()
        );

        System.out.println(
                "----------------------------------------------"
        );

        int[] marks = student.getMarks();

        for (int i = 0; i < marks.length; i++) {

            System.out.println(
                    "Subject " +
                            (i + 1) +
                            "      : " +
                            marks[i]
            );
        }

        System.out.println(
                "----------------------------------------------"
        );

        System.out.println(
                "Total        : " +
                        student.getTotal()
        );

        System.out.printf(
                "Average      : %.2f%n",
                student.getAverage()
        );

        System.out.println(
                "Grade        : " +
                        student.getGrade()
        );

        System.out.println(
                "Performance   : " +
                        student.getPerformance()
        );

        System.out.println(
                "Result       : " +
                        (student.isPass()
                                ? "PASS"
                                : "FAIL")
        );

        System.out.println(
                "=============================================="
        );
    }


    // =========================
    // SUBJECT ANALYSIS
    // =========================

    static void subjectAnalysis() {

        System.out.println();
        System.out.println(
                "========== SUBJECT-WISE ANALYSIS =========="
        );

        if (students.isEmpty()) {

            System.out.println(
                    "No students available."
            );

            return;
        }

        for (int subject = 0; subject < 5; subject++) {

            int total = 0;

            int highest = 0;

            int lowest = 100;

            for (Student student : students) {

                int mark =
                        student.getMarks()[subject];

                total += mark;

                if (mark > highest) {
                    highest = mark;
                }

                if (mark < lowest) {
                    lowest = mark;
                }
            }

            double average =
                    (double) total / students.size();

            System.out.println();

            System.out.println(
                    "Subject " +
                            (subject + 1)
            );

            System.out.println(
                    "Average : " +
                            String.format(
                                    "%.2f",
                                    average
                            )
            );

            System.out.println(
                    "Highest : " +
                            highest
            );

            System.out.println(
                    "Lowest  : " +
                            lowest
            );
        }
    }


    // =========================
    // SAVE DATA
    // =========================

    static void saveData() {

        try {

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(FILE_NAME)
                    );

            for (Student student : students) {

                writer.print(
                        student.getId()
                );

                writer.print("|");

                writer.print(
                        student.getName()
                );

                writer.print("|");

                writer.print(
                        student.getDepartment()
                );

                writer.print("|");

                int[] marks =
                        student.getMarks();

                for (int i = 0;
                     i < marks.length;
                     i++) {

                    writer.print(marks[i]);

                    if (i < marks.length - 1) {
                        writer.print(",");
                    }
                }

                writer.println();
            }

            writer.close();

            System.out.println(
                    "Data saved successfully!"
            );

        } catch (IOException e) {

            System.out.println(
                    "Error while saving data."
            );
        }
    }


    // =========================
    // LOAD DATA
    // =========================

    static void loadData() {

        File file =
                new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(FILE_NAME)
                    );

            String line;

            while ((line = reader.readLine())
                    != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts =
                        line.split("\\|");

                if (parts.length != 4) {
                    continue;
                }

                int id =
                        Integer.parseInt(parts[0]);

                String name =
                        parts[1];

                String department =
                        parts[2];

                String[] markStrings =
                        parts[3].split(",");

                int[] marks =
                        new int[markStrings.length];

                for (int i = 0;
                     i < markStrings.length;
                     i++) {

                    marks[i] =
                            Integer.parseInt(
                                    markStrings[i]
                            );
                }

                Student student =
                        new Student(
                                id,
                                name,
                                department,
                                marks
                        );

                students.add(student);

                studentMap.put(
                        id,
                        student
                );

                evaluationQueue.offer(
                        student
                );
            }

            reader.close();

        } catch (Exception e) {

            System.out.println(
                    "Error while loading data."
            );
        }
    }


    // =========================
    // INPUT INTEGER
    // =========================

    static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return scanner.nextInt();

            } catch (InputMismatchException e) {

                System.out.println(
                        "Please enter a valid number."
                );

                scanner.nextLine();
            }
        }
    }


    // =========================
    // INPUT MARK
    // =========================

    static int readMark(String message) {

        while (true) {

            int mark = readInt(message);

            if (mark >= 0 &&
                    mark <= 100) {

                return mark;
            }

            System.out.println(
                    "Marks must be between 0 and 100."
            );
        }
    }
}