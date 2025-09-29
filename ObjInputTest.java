public class ObjInputTest {
    public static void main(String[] args) {
        SafeInputObj in = new SafeInputObj();

        String name = in.getNonZeroLenString("Enter your name");
        int age = in.getRangedInt("Enter your age", 1, 120);
        double gpa = in.getRangedDouble("Enter your GPA", 0.0, 4.0);
        String email = in.getRegExString("Enter a UC email", "^[A-Za-z0-9._%+-]+@ucmail\\.uc\\.edu$");
        boolean confirm = in.getYNConfirm("Do you want to continue");

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("GPA: " + gpa);
        System.out.println("UC Email: " + email);
        System.out.println("Confirmed: " + confirm);
    }
}
