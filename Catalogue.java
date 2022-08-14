package Exercitii.StudentCatalogue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Catalogue {

    static Scanner scanner = new Scanner(System.in);

    public static LinkedList<Student> catalogue = new LinkedList<>();

    static void selectOptions() throws IOException {
        boolean exit = false;
        int x;

        while (!exit) {
            x = scanner.nextInt();
            scanner.nextLine();
            switch (x) {
                case 0:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                case 1:
                    createStudent();
                    break;
                case 2:
                    printList();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    sortByName();
                    break;
                case 5:
                    reversedSortByName();
                    break;
                case 6:
                    sortByGraduatePointAverage();
                    break;
                case 7:
                    reversedSortByGraduatePointAverage();
                    break;
                case 8:
                    exportCatalogue();
                    break;
                case 9:
                    importCatalogue();
                    break;
                case 10:
                    printMenu();
                    break;
            }
        }
    }

    private static Student checkForAdding() {

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter first mark: ");
        int firstMark = scanner.nextInt();
        System.out.print("Enter second mark: ");
        int secondMark = scanner.nextInt();
        System.out.print("Enter final exam mark: ");
        int finalExamMark = scanner.nextInt();
        scanner.nextLine();

        for (Student elem : catalogue) {
            if ((elem.getName() + elem.getSurname()).equals(name + surname)) {
                System.out.println("Student already on the list!");
                return null;
            }
        }
        System.out.println("Student successfully added to catalogue!");
        return (new Student(name, surname, firstMark, secondMark, finalExamMark));
    }

    private static void createStudent(){
        catalogue.add(checkForAdding());
        catalogue.remove(null);
    }

    private static Student checkForDelete() {

        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student's surname: ");
        String surname = scanner.nextLine();

        for (Student elem : catalogue) {
            if ((elem.getName() + elem.getSurname()).equals(name + surname)) {
                System.out.println("Student successfully removed from list.");
                return elem;
            }
        }
        System.out.println("Student not on the list!");
        return null;
    }

    private static void deleteStudent() {
        try {
            if (catalogue.isEmpty()) {
                throw new EmptyCatalogueException();
            }
            else {
                    catalogue.remove(checkForDelete());
            }
        }
        catch(EmptyCatalogueException e){
                System.out.println(e);
            }
        }

    private static void printList() {
        if (catalogue.isEmpty()){
            System.out.println("Catalogue is empty!");
        }
        else {
            System.out.println("Printing list...");
            for (Student elem : catalogue) {
                System.out.println(elem);
            }
        }
    }

    private static void sortByName (){
        Comparator<Student> compareByName = (Student s1, Student s2) -> (s1.getName() + " " + s1.getSurname()).compareTo(s2.getName() + " " + s2.getSurname());
        Collections.sort(catalogue, compareByName);
        System.out.println("Sorting list by name:");
        printList();
    }

    private static void reversedSortByName (){
        Comparator<Student> compareByName = (Student s1, Student s2) -> (s1.getName() + " " + s1.getSurname()).compareTo(s2.getName() + " " + s2.getSurname());
        Collections.sort(catalogue, compareByName.reversed());
        System.out.println("Sorting list by name in reversed order:");
        printList();
    }

    private static void sortByGraduatePointAverage (){
        Comparator<Student> compareByGraduatePointAverage = (Student s1, Student s2) -> Double.valueOf(s1.getGraduationPointAverage()).compareTo(s2.getGraduationPointAverage());
        Collections.sort(catalogue, compareByGraduatePointAverage);
        System.out.println("Sorting list by Graduate Point Average (25% first mark, 25% second mark, 50% final exam):");
        printList();
    }

    private static void reversedSortByGraduatePointAverage (){
        Comparator<Student> compareByFinalExamMark = (Student s1, Student s2) -> Double.valueOf(s1.getGraduationPointAverage()).compareTo(s2.getGraduationPointAverage());
        Collections.sort(catalogue, compareByFinalExamMark.reversed());
        System.out.println("Sorting list by Graduate Point Average (25% first mark, 25% second mark, 50% final exam) in reversed order:");
        printList();
    }

    private static void exportCatalogue() throws IOException {
        if(catalogue.isEmpty()){
            System.out.println("Catalogue is empty. Nothing to export!");
        }
        else {
            Files.writeString(Path.of("C:\\Users\\Public\\Documents\\Catalogue.txt"), catalogueAsString());
            System.out.println("Catalogue successfully exported to path: C:\\Users\\Public\\Documents!");
        }
    }

    private static StringBuilder catalogueAsString(){
        String s;
        StringBuilder s1 = new StringBuilder();
        for (Student elem: catalogue){
            s = elem.getName() + ", " + elem.getSurname() + ", "
                    + elem.getFirstMark() + ", " + elem.getSecondMark() + ", "
                    + elem.getFinalExamMark() + ", " + elem.getGraduationPointAverage() + "\n";
            s1.append(s);
        }
        return s1;
    }

    private static void importCatalogue () throws IOException {
        String s = (Files.readString(Path.of("C:\\Users\\Public\\Documents\\Catalogue.txt"), StandardCharsets.UTF_8)).replace(",", "");
        String [] studentArray = s.split("\n");
        for (String elem : studentArray) {
            String [] sSplit = elem.split("\\s+");
                catalogue.add(new Student (sSplit[0], sSplit[1], Integer.parseInt(sSplit[2]), Integer.parseInt(sSplit[3]), Integer.parseInt(sSplit[4])));
        }
        System.out.println("Catalogue successfully imported!");
    }

    static void printMenu(){
        System.out.println("Menu options. Press:\n" +
                "\t1. To add a new student to catalogue\n" +
                "\t2. To print student list\n" +
                "\t3. To delete a certain student\n" +
                "\t4. To sort student list by name\n" +
                "\t5. To sort student list by name in reversed order\n" +
                "\t6. To sort student list by Graduate Point Average (GPA)\n" +
                "\t7. To sort student list by Graduate Point Average (GPA) in reversed order\n" +
                "\t8. To export catalogue to C:\\Users\\Public\\Documents\\\n" +
                "\t9. To import catalogue from C:\\Users\\Public\\Documents\\\n" +
                "\t10. To print menu\n" +
                "\t0. To exit application\n");
    }
}
