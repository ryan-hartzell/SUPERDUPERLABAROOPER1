import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Ryan Hartzell on 9/19/2017.
 */
public class schoolsearch {
    public static void main(String[] argv) {
        FileReader schoolFile;

        try {
            schoolFile = new FileReader("students.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Can't find students.txt file");
            return;
        }

        HashSet<Student> students = parseFile(new Scanner(schoolFile));
        interactiveLoop(students);
    }

    private static HashSet<Student> parseFile(Scanner in) {
        HashSet<Student> set = new HashSet<>();
        Scanner lineScanner;
        Student temp;
        String line, fn, ln, tfn, tln;
        int cl, bu, gr;
        double gpa;


        while (in.hasNextLine()) {
            line = in.nextLine();
            lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");

            ln = lineScanner.next();
            fn = lineScanner.next();
            gr = lineScanner.nextInt();
            cl = lineScanner.nextInt();
            bu = lineScanner.nextInt();
            gpa = lineScanner.nextDouble();
            tln = lineScanner.next();
            tfn = lineScanner.next();

            temp = new Student(ln, fn, gr, cl, bu, gpa, tln, tfn);
            set.add(temp);
        }

        return set;
    }

    private static void interactiveLoop(HashSet<Student> students) {
        Scanner s = new Scanner(System.in);
        System.out.print("> ");

        while (s.hasNextLine()) {
            Scanner line = new Scanner(s.nextLine());
            if (!line.hasNext()) {
                System.out.print("> ");
                continue;
            }

            switch (line.next()) {
                case "Average:":
                case "A:":
                    int grade;

                    if (line.hasNext() && line.hasNextInt()) {
                        grade = line.nextInt();
                    }
                    else {
                        System.out.println("Incorrect command");
                        System.out.println("Usage: A[verage]: <number>");
                        break;
                    }

                    if (line.hasNext()) {
                        System.out.println("Incorrect command");
                        System.out.println("Usage: A[verage]: <number>");
                    } else {
                        average(students, grade);
                    }
                    break;
                case "Bus:":
                case "B:":
                    int bus;

                    if (line.hasNext() && line.hasNextInt()) {
                        bus = line.nextInt();
                    }
                    else {
                        System.out.println("Incorrect command");
                        System.out.println("Usage: B[us]: <number>");
                        break;
                    }

                    if (line.hasNext()) {
                        System.out.println("Incorrect command");
                        System.out.println("Usage: B[us]: <number>");
                    } else {
                        busStudents(students, bus);
                    }
                    break;
                case "Grade:":
                case "G:":
                    int targetGrade;
                    String extreme = "";

                    if (line.hasNext() && line.hasNextInt()) {
                        targetGrade = line.nextInt();
                    }
                    else {
                        System.out.println("Incorrect command");
                        System.out.println("Usage: G[rage]: <number> [[H]|[L]]");
                        break;
                    }

                    if (line.hasNext()) {
                        extreme = line.next();
                    }
                    if (line.hasNext()) {
                        System.out.println("Incorrect command");
                        System.out.println("Usage: G[rage]: <number> [[H]|[L]]");
                    } else {
                        gradeStudents(students, targetGrade, extreme);
                    }
                    break;
                case "Info":
                case "I":
                    if (line.hasNext()) {
                        System.out.println("Incorrect command");
                        System.out.println("Usage: I[nfo]");
                    } else {
                        getInfo(students);
                    }
                    break;
                case "Quit":
                case "Q":
                    return;
                case "Student:":
                case "S:":
                    String lastname = line.next();
                    boolean showBus = false;

                    if (line.hasNext()) {
                        String busString = line.next();
                        if (busString.equals("Bus") || busString.equals("B"))
                            showBus = true;
                        else {
                            System.out.println("Incorrect command");
                            System.out.println("Usage: S[tudent]: <lastname>");
                            break;
                        }
                    }

                    if (line.hasNext()) {
                        System.out.println("Incorrect command");
                        System.out.println("Usage: S[tudent]: <lastname>");
                    } else {
                        getStudent(students, lastname, showBus);
                    }
                    break;
                case "Teacher:":
                case "T:":
                    String tLastName;

                    if (line.hasNext()) {
                        tLastName = line.next();
                    }
                    else {
                        System.out.println("Incorrect command");
                        System.out.println("Usage: T[eacher]: <lastname>");
                        break;
                    }

                    if (line.hasNext()) {
                        System.out.println("Incorrect command");
                        System.out.println("Usage: T[eacher]: <lastname>");
                    }
                    else {
                        getTeacher(students, tLastName);
                    }
                    break;
                default:
                    System.out.println("Unrecognized command");
                    System.out.println("Usage:");
                    System.out.println("\tA[verage]: <number>");
                    System.out.println("\tB[us]: <number>");
                    System.out.println("\tG[rage]: <number> [[H]|[L]]");
                    System.out.println("\tI[nfo]");
                    System.out.println("\tS[tudent]: <lastname>");
                    System.out.println("\tT[eacher]: <lastname>");

            }
            System.out.print("> ");
        }
    }

    private static void average(HashSet<Student> students, int grade) {
        Iterator<Student> iter = students.iterator();
        double sum = 0;
        int numInGrade = 0;

        while (iter.hasNext()) {
            Student s = iter.next();
            if (s.getGrade() == grade) {
                sum += s.getGPA();
                numInGrade++;
            }
        }

        // If there are no results, print 0. Otherwise print result
        System.out.println(numInGrade == 0 ? 0 : (sum / numInGrade));
    }

    private static void busStudents(HashSet<Student> students, int bus) {
        Iterator<Student> iter = students.iterator();
        boolean any = false;

        while (iter.hasNext()) {
            Student s = iter.next();
            if (s.getBus() == bus) {
                if (!any) {
                    any = true;
                }
                System.out.print(s.getLastName() + ",");
                System.out.print(s.getFirstName() + ",");
                System.out.print(s.getGrade() + ",");
                System.out.println(s.getClassroom());
            }
        }

        if (!any) {
            System.out.println("No results found");
        }
    }

    private static void gradeStudents(HashSet<Student> students, int grade, String extreme) {
        Iterator<Student> iter = students.iterator();
        Student studentEx = null;

        while (iter.hasNext()) {
            Student s = iter.next();
            if (s.getGrade() == grade) {
                if (extreme.equals("")) {
                    System.out.print(s.getLastName() + ",");
                    System.out.println(s.getFirstName());
                } else {
                    if (studentEx == null) {
                        studentEx = s;
                    } else if (extreme.equals("H") || extreme.equals("High")) {
                        if (studentEx.getGPA() < s.getGPA()) {
                            studentEx = s;
                        }
                    } else if (extreme.equals("L") || extreme.equals("Low")) {
                        if (studentEx.getGPA() > s.getGPA()) {
                            studentEx = s;
                        }
                    }
                }
            }
        }

        if (studentEx != null) {
            System.out.print(studentEx.getLastName() + ",");
            System.out.print(studentEx.getFirstName() + ",");
            System.out.print(studentEx.getBus() + ",");
            System.out.print(studentEx.getGPA() + ",");
            System.out.print(studentEx.gettLastName() + ",");
            System.out.println(studentEx.gettLastName());
        }
    }

    private static void getInfo(HashSet<Student> students) {
        int[] numInGrade = new int[7];
        Iterator<Student> iter = students.iterator();

        while (iter.hasNext()) {
            Student s = iter.next();
            numInGrade[s.getGrade()]++;
        }

        for (int i = 0; i < numInGrade.length; i++) {
            System.out.println(i + ": " + numInGrade[i]);
        }
    }

    private static void getStudent(HashSet<Student> students, String lastname, boolean bus) {
        Iterator<Student> iter = students.iterator();

        while (iter.hasNext()) {
            Student s = iter.next();

            if (s.getLastName().equals(lastname)) {
                System.out.print(s.getLastName() + ",");
                System.out.print(s.getFirstName() + ",");
                if (!bus) {
                    System.out.print(s.getGrade() + ",");
                    System.out.print(s.getClassroom() + ",");
                    System.out.print(s.gettLastName() + ",");
                    System.out.println(s.gettFirstName());
                }
                else {
                    System.out.println(s.getBus());
                }

            }
        }
    }

    private static void getTeacher(HashSet<Student> students, String lastname) {
        Iterator<Student> iter = students.iterator();

        while (iter.hasNext()) {
            Student s = iter.next();

            if (s.gettLastName().equals(lastname)) {
                System.out.print(s.getLastName() + ",");
                System.out.println(s.getFirstName());
            }
        }
    }
}