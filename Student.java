public class Student {

    private String name;
    private String surname;
    private int firstMark;
    private int secondMark;
    private int finalExamMark;
    private double GPA;

    public Student(String name, String surname, int firstMark, int secondMark, int finalExamMark) {
        this.name = name;
        this.surname = surname;
        this.firstMark = firstMark;
        this.secondMark = secondMark;
        this.finalExamMark = finalExamMark;
        this.GPA = (double)25/100*this.firstMark + (double)25/100*this.secondMark + (double)50/100*finalExamMark;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getFirstMark() {
        return this.firstMark;
    }

    public int getSecondMark() {
        return this.secondMark;
    }

    public int getFinalExamMark() {
        return this.finalExamMark;
    }

    public double getGraduationPointAverage(){
        return this.GPA;
    }

    @Override
    public String toString (){
        return this.name + " " + this.surname + " has the following marks: " + this.firstMark + " at English, "
                + this.secondMark + " at Math and " + this.finalExamMark + " at the final exam. GPA is " + this.GPA + ".";
    }
}
