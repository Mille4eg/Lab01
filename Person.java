public class Person {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String title;
    private final int yob;

    public Person(String id, String firstName, String lastName, String title, int yob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.yob = yob;
    }

    public String getID() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public int getYOB() {
        return yob;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s) %s %d", firstName, lastName, id, title, yob);
    }
}
