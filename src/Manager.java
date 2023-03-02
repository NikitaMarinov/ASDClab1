
import org.jetbrains.annotations.NotNull;

import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Manager {

    private static File file;
    private static Scanner sc = new Scanner(System.in);

    public static List<Student> readFromFIle() throws IOException {
        List<Student> list = new LinkedList<>();
        System.out.println("Enter '1' for sorted list or '2' for unsorted list:");
        String s = sc.nextLine();

        while (!s.equals("1") && !s.equals("2")){
            System.out.println("You entered incorrect value.Enter '1' or '2':");
            s = sc.nextLine();
        }

        if(s.equals("1")){
            file = new File("C:\\IntellijProjects\\Lab1_ASDC\\src\\DataBaseSorted.txt");

        } else {
            file = new File("C:\\IntellijProjects\\Lab1_ASDC\\src\\DataBasenNonSorted.txt");
        }
        try (Reader bufferedReader = new BufferedReader(new FileReader(new File(file.toURI())));) {
            // try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\MyLesson\\src\\ASDCLab1\\DataBase.txt"));) {
            String headerOfFile = ((BufferedReader) bufferedReader).readLine();
            String line = ((BufferedReader) bufferedReader).readLine();
            String[] lineArray = new String[6];
            while (line != null) {
                lineArray = line.split(",");
                list.add(new Student(
                                Integer.parseInt(lineArray[0]),
                                lineArray[1],
                                lineArray[2],
                                Integer.parseInt(lineArray[3]),
                                Integer.parseInt(lineArray[4]),
                                Integer.parseInt(lineArray[5])
                        )
                );
                line = ((BufferedReader) bufferedReader).readLine();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public static void showStudentArrayList(@NotNull List<Student> list) {
        for (Student st: list) {
            System.out.println(st.toString());
        }
    }

    //Последовательный поиск
    public static Student consistentSearch(@NotNull List<Student> list, int id){
        long startTime = System.nanoTime();
        long endTime;
        for (Student student : list) {
            if (student.getId() == id) {
                endTime = System.nanoTime();
                System.out.println("Time of execution: " + (endTime - startTime)+ " Nano Seconds");
                return student;
            }
        }
        endTime = System.nanoTime();
        System.out.println("Time of execution: " + (endTime - startTime)+ " Nano Seconds" );
        return new Student();

    }

    public  static Student consistentSearch(@NotNull List<Student> list, String firstName, String lastName){
        long startTime = System.nanoTime();
        long endTime;
        for (Student student : list) {
            if (student.getFirstName().equals(firstName)) {
                if (student.getLastName().equals(lastName)) {
                    endTime = System.nanoTime();
                    System.out.println("Time of execution: " + (endTime - startTime) + " Nano Seconds");
                    return student;
                }
            }
        }
        endTime = System.nanoTime();
        System.out.println("Time of execution: " + (endTime - startTime) + " Nano Seconds" );
        return new Student();
    }

    public static Student binarySearch (@NotNull List<Student> list, int ID){
        long startTime = System.nanoTime();
        long endTime;
        int start = 0;
        int end = list.size() - 1;
        while (start <= end){
            int middle = (start + end) / 2;
            if(list.get(middle).getId() == ID) {
                endTime = System.nanoTime();
                System.out.println("Time of execution: " + (endTime - startTime) + " Nano Seconds");
                return list.get(middle);
            } else if(list.get(middle).getId() < ID) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        endTime = System.nanoTime();
        System.out.println("Time of execution: " + (endTime - startTime) + " Nano Seconds");
        return new Student();
    }
    public static Student interpolationSearch (@NotNull List<Student> list, int ID) {
        long startTime = System.nanoTime();
        long endTime;
        int start = 0;
        int end = list.size() - 1;
        int middle;
        while(list.get(start).getId() < ID && list.get(end).getId()>ID){
            middle = start + ((ID - list.get(start).getId())*(end - start)) / (list.get(end).getId() - list.get(start).getId());
        if(list.get(middle).getId() < ID){
            start = middle + 1;
        } else if(list.get(middle).getId() > ID){
            end = middle + 1;
        } else {
            endTime = System.nanoTime();
            System.out.println("Time of execution: " + (endTime - startTime) + " Nano Seconds");
            return list.get(middle);
        }
        }

        if(list.get(end).getId()== ID){
            endTime = System.nanoTime();
            System.out.println("Time of execution: " + (endTime - startTime) + " Nano Seconds");
            return list.get(end);
        }
        if(list.get(start).getId() == ID) {
            endTime = System.nanoTime();
            System.out.println("Time of execution: " + (endTime - startTime) + " Nano Seconds");
            return list.get(start);
        }
        endTime = System.nanoTime();
        System.out.println("Time of execution: " + (endTime - startTime) + " Nano Seconds");
        return new Student();
    }

    public static Student fibonacciSearch(@NotNull List<Student> list, int ID){
        long startTime = System.nanoTime();
        long endTime;

        int i,q,p,m;
        int k = 0;
        int listSize = list.size();
        boolean stop = false;

        //k - index of element in the Fibonacci row
        // Нам необходимо найти такое целое число в ряде Фибонначи, что k+1 член ряда больше или равен
        // кол-ву элементов массива + 1.
        // То есть если у нас 7 элементов в массиве, k+1 это 7 элемент ряда, что равно 13 это больше 11(кол-во элементов+1)
        while (getFibonacciNumber(k + 1) < listSize + 1) {
            k++;
        }

        m = (int) (getFibonacciNumber(k + 1) - (listSize + 1));
        i = (int) (getFibonacciNumber(k) - m);
        p = (int) getFibonacciNumber(k - 1);
        q = (int) getFibonacciNumber(k - 2);




        int result = -1;
        while (!stop) {
            if (i < 0) {
                if (p == 1)
                    stop = true;
                i = i + q;
                p = p - q;
                q = q - p;
            } else if (i >= listSize) {
                if (q == 0)
                    stop = true;
                i = i - q;
                int temp = q;
                q = p - q;
                p = temp;;
            } else if (ID < list.get(i).getId()) {
                if (q == 0)
                    stop = true;
                i = i - q;
                int temp = q;
                q = p - q;
                p = temp;
            } else if (ID > list.get(i).getId()) {
                if (p == 1)
                    stop = true;
                i = i + q;
                p = p - q;
                q = q - p;
            }
            else if (list.get(i).getId() == ID) {
                result = i;
                break;
            }
        }
        endTime = System.nanoTime();
        System.out.println("Time of execution: " + (endTime - startTime) + " Nano Seconds");
        return list.get(result);

    }

    private static long getFibonacciNumber(int num) {
        long firstNumber = 0;
        long secondNumber = 1;
        for (int i = 0; i < num; i++) {
            long temp = secondNumber;
            secondNumber += firstNumber;
            firstNumber = temp;
        }
        return firstNumber;
    }



}
