
import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        List<Student> list = new ArrayList<>();

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Next, we use methods that require an unsorted list");
        System.out.println("-----------------------------------------------------------------------------------");
        list = Manager.readFromFIle();
        System.out.println("Our list:");
        Manager.showStudentArrayList(list);

        Student student = new Student(list.get(40));
        System.out.println();
        System.out.println("Standard clone:");
        try {
            Student newStudent = student.clone();
            System.out.println(newStudent);
            System.out.print("Equality test: ");
            System.out.println(newStudent.equals(student));
        } catch (CloneNotSupportedException e) {
            System.out.println("We have a big problem!");
            e.printStackTrace();
        }


        System.out.println();
        Student secondStudent = new Student(list.get(43));
        System.out.println("Self-written clone");
        try {
            Student newStudent = secondStudent.copy();
            System.out.println(newStudent);
            System.out.print("Equality test: ");
            System.out.println(newStudent.equals(secondStudent));
        } catch (Exception e) {
            System.out.println("We have a big problem!");
            e.printStackTrace();
        }


        System.out.println();
        System.out.println("Sequential search(by ID):");
        int ID = 100;
        while (ID > 50 || ID < 0) {
            System.out.print("Enter the ID (0 < ID < 50):");
            ID = sc.nextInt();
        }
            Student student2 = Manager.consistentSearch(list, ID);
            System.out.println(student2.toString());


            System.out.println();
            System.out.println("Sequential search(by first name and last name):");
            Student student3 = Manager.consistentSearch(list, "Elvis", "Maplesden");
            System.out.println(student3.toString());
        System.out.println();
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Next, we use methods that require a sorted list!");
        System.out.println("-----------------------------------------------------------------------------------");

        list = Manager.readFromFIle();
        System.out.println();
        System.out.println("Binary search(by ID):");
        ID = 100;
        while (ID > 50 || ID < 0) {
            System.out.print("Enter the ID (0 < ID < 50):");
            ID = sc.nextInt();
        }
        Student student4 = Manager.binarySearch(list, ID);
        System.out.println(student4.toString());



        System.out.println();
        System.out.println();
        System.out.println("Interpolation Search(by ID):");
        ID = 100;
        while (ID > 50 || ID < 0) {
            System.out.print("Enter the ID (0 < ID < 50):");
            ID = sc.nextInt();
        }
        Student student5 = Manager.interpolationSearch(list, ID);
        System.out.println(student5.toString());


        System.out.println();
        System.out.println("Fibonacci search: ");
        ID = 100;
        while (ID > 50 || ID < 0) {
            System.out.print("Enter the ID (0 < ID < 50):");
            ID = sc.nextInt();
        }
        Student st = Manager.fibonacciSearch(list,ID);
        System.out.println(st.toString());


        Tree tree = new Tree();
        for (Student stud: list) {
            tree.insertNode(stud);

        }

        System.out.println();
        System.out.println("Binary tree search:");
        ID = 100;
        while (ID > 50 || ID < 0) {
            System.out.print("Enter the ID (0 < ID < 50):");
            ID = sc.nextInt();
        }
        System.out.println(tree.findNodeByID(ID));


    }
}

