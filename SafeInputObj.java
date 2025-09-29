import java.util.Scanner;
import java.util.regex.Pattern;

public class SafeInputObj {
    private Scanner pipe;

    public SafeInputObj() {
        this.pipe = new Scanner(System.in);
    }

    public SafeInputObj(Scanner scanner) {
        this.pipe = scanner;
    }

    public String getNonZeroLenString(String prompt) {
        String retString = "";
        do {
            System.out.print(prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);
        return retString;
    }

    public int getInt(String prompt) {
        int retValue = 0;
        boolean done = false;
        do {
            System.out.print(prompt + ": ");
            if (pipe.hasNextInt()) {
                retValue = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                pipe.nextLine();
            }
        } while (!done);
        return retValue;
    }

    public double getDouble(String prompt) {
        double retValue = 0;
        boolean done = false;
        do {
            System.out.print(prompt + ": ");
            if (pipe.hasNextDouble()) {
                retValue = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                pipe.nextLine();
            }
        } while (!done);
        return retValue;
    }

    public String getRegExString(String prompt, String pattern) {
        String retString = "";
        boolean gotAValue = false;
        do {
            System.out.print(prompt + ": ");
            retString = pipe.nextLine();
            if (Pattern.matches(pattern, retString)) {
                gotAValue = true;
            } else {
                System.out.println("Invalid input: does not match pattern.");
            }
        } while (!gotAValue);
        return retString;
    }

    public boolean getYNConfirm(String prompt) {
        String response;
        boolean valid = false;
        boolean result = false;
        do {
            System.out.print(prompt + " [Y/N]: ");
            response = pipe.nextLine().trim().toUpperCase();
            if (response.equals("Y")) {
                valid = true;
                result = true;
            } else if (response.equals("N")) {
                valid = true;
                result = false;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        } while (!valid);
        return result;
    }

    public int getRangedInt(String prompt, int low, int high) {
        int retValue = 0;
        boolean done = false;
        do {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                retValue = pipe.nextInt();
                pipe.nextLine();
                if (retValue >= low && retValue <= high) {
                    done = true;
                } else {
                    System.out.println("Input out of range.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                pipe.nextLine();
            }
        } while (!done);
        return retValue;
    }

    public double getRangedDouble(String prompt, double low, double high) {
        double retValue = 0;
        boolean done = false;
        do {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                retValue = pipe.nextDouble();
                pipe.nextLine();
                if (retValue >= low && retValue <= high) {
                    done = true;
                } else {
                    System.out.println("Input out of range.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                pipe.nextLine();
            }
        } while (!done);
        return retValue;
    }
}
