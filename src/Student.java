/**
 * Created by Ryan Hartzell on 9/19/2017.
 */
public class Student {
    private String lastName;
    private String firstName;
    private int grade;
    private int classroom;
    private int bus;
    private double gpa;
    private String tLastName;
    private String tFirstName;

    public Student(String ln, String fn, int gr, int cl, int bu, double gpa, String tln, String tfn) {
        this.lastName = ln;
        this.firstName = fn;
        this.grade = gr;
        this.classroom = cl;
        this.bus = bu;
        this.gpa = gpa;
        this.tLastName = tln;
        this.tFirstName = tfn;
    }


}
