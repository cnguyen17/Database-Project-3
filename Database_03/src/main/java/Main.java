/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Main
 * Student(s) Name(s): Echglene Woy & Calvin Nguyen
 */
import java.util.Scanner;
import java.util.List;


public class Main {
    private static int OPTION_ENROLL = 1;
    private static int OPTION_DROP   = 2;
    private static int OPTION_LIST   = 3;
    private static int OPTION_EXIT   = 4;

    public static void main(String[] args) {
        Controller controller = new Controller();
        Scanner sc = new Scanner(System.in);

        /*TODO Testing methods
        ------------------------------------------------------------------------------------------------------------
        */
        // testing getStudent method
//        int id = 4;
//        String name = "suh";
//        Student student = new Student();
//        student.setId(id);
//        student.setName(name);
//        controller.addStudent(student);
//        Student student1 = controller.getStudent(1);
//        System.out.println(student1);

        //TODO testing getCourses method
//        List<Course> courses = controller.getCourses();
//        for (Course course : courses)
//            System.out.println(course);

        //TODO getStudentsEnrolled Method
//        String course = "CS3810";
//        List<Student> studentsEnrolled = controller.getStudentsEnrolled(course);
//        for (Student student : studentsEnrolled) {
//            System.out.println(student);
//        }

        //TODO Testing dropStudent Method
//        int id = 2;
//        String code ="CS3810";
//        controller.dropStudent(code,id);

        //TODO Testing EnrollStudent Method
//        int id = 1;
//        String code ="CS1030";
//        controller.enrollStudent(code,id);


        // TODO testing getStudents method
//        int id = 4;
//        String name = "suh";
//        Student student = new Student();
//        student.setId(id);
//        student.setName(name);
//        controller.addStudent(student);
//        Student student1 = controller.getStudent(1);
//        System.out.println(student1);


        //TODO  testing addStudent method
//        Student student = new Student();
//        int id = 4;
//        student.setId(id);
//        student.setName("test");
//        controller.addStudent(student);
//        Student student2 =  controller.getStudent(4);
//        System.out.println(student2);


        while (true) {
            List<Course> courses = controller.getCourses();
            System.out.println("\n");
            System.out.println(
                    String.format("%-7s| %-40s| %-15s| %-3s | %-6s | %-3s",
                            "Code", "Title", "Instructor", "Max", "Actual", "Remain")
            );
            for (Course course : courses)
                System.out.println(course);
            System.out.print("[1:enroll|2:drop|3:list|4:exit]? ");
            int option = Integer.parseInt(sc.nextLine());
            if (option == OPTION_EXIT)
                break;
            System.out.print("course? ");
            String course = sc.nextLine();
            if (option == OPTION_LIST) {
                List<Student> students = controller.getStudentsEnrolled(course);
                if (students.isEmpty())
                    System.out.println("No student is currently enrolled in this class!");
                else
                    for (Student student: students)
                        System.out.println(student);
            }
            else {
                System.out.print("student id? ");
                int id = Integer.parseInt(sc.nextLine());
                Student student = controller.getStudent(id);
                if (student == null) {
                    System.out.print("student name? ");
                    String name = sc.nextLine();
                    student = new Student();
                    student.setId(id);
                    student.setName(name);
                    controller.addStudent(student);
                }
                else
                    System.out.println("student name: " + student.getName());
                if (option == OPTION_ENROLL) {
                    if (controller.enrollStudent(course, id))
                        System.out.println("Success!");
                    else
                        System.out.println("Failure!");
                }
                else {
                    if (controller.dropStudent(course, id))
                        System.out.println("Success!");
                    else
                        System.out.println("Failure!");
                }
            } // end else
        } // end while
    } // end main
} // end class