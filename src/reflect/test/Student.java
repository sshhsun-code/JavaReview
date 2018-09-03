package reflect.test;

public class Student extends Person implements Examination, Breathe{
    // Äê¼¶
    int mGrade = 100;
    
    private int mAge = 12;

    public Student(String aName) {
        super(aName);
    }

    public Student(int grade, String aName) {
        super(aName);
        mGrade = grade;
    }

    private void learn(String course) {
        System.out.println(mName + " learn " + course);
    }

    public void takeAnExamination() {
        System.out.println(" takeAnExamination ");
    }

    public String toString() {
        return " Student :  " + mName;
    }
    
    @Override
    public void breathe() {
    	System.out.println(mName + "is breathing ");
    }
}
