import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Ryan Hartzell on 9/19/2017.
 */
public class schoolsearch {
    public static void main (String[] argv) {
        FileReader schoolFile;

        try {
            schoolFile = new FileReader("students.txt");
        }
        catch (FileNotFoundException e) {
            System.out.println("Can't find yo");
            return;
        }

        Scanner s = new Scanner(schoolFile);
        HashSet students = parseFile(s);

        System.out.println("Success! Set of " + students.size() + " students");
    }

    private static HashSet parseFile (Scanner in) {
        HashSet set = new HashSet();
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
            if (!set.add(temp)) {
                System.out.println(fn + " " + ln + " already in system.");
            }
        }

        return set;
    }
}
