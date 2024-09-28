package Lab09.Lab902;

// Main Student class
public class Student {
    private String name;
    private String gpa;

    public Student(String name, String gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    // Method to calculate GPA
    public void calGPA() {
        double Subject = 0;
        double sum = 0;
        for (char gpa : gpa.toCharArray()) {
            Subject++;
            if (gpa == 'A') 
                sum += 4;
            if (gpa == 'B') 
                sum += 3;
            if (gpa == 'C') 
                sum += 2;
            if (gpa == 'D') 
                sum += 1;
            if (gpa == 'F') 
                sum += 0;
        }
        sum = sum / Subject;
        System.out.println(name + " registered " + Subject + " subjects and got GPA " + sum);
    }

    public void show() {
        try {
            for (char c : name.toCharArray()) { 
                if (c >= 48 && c <= 57) {
                    throw new DigitException();
                }
            }
            for (char c : name.toCharArray()) { 
                if (c == ' ') {
                    throw new SpaceException();
                }
            }
            for (char g : gpa.toCharArray()) { 
                if (g != 'A' && g != 'B' && g != 'C' && g != 'D' && g != 'E' && g != 'F' && g != 'I') {
                    throw new GradeException();
                }
            }
            for (char g : gpa.toCharArray()) { 
                if (g == 'I') {
                    throw new IncompleteException();
                }
            }
            calGPA();
        } 
        catch(DigitException e) {
            System.out.println(e);
        } 
        catch(SpaceException e) {
            System.out.println(e);
        } 
        catch(GradeException e) {
            System.out.println(e);
        } 
        catch(IncompleteException e) {
            System.out.println(e);
        }
    }
}