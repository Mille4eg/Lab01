import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person("0001", "Remy", "Linguini", "Chef", 2001);
    }

    @Test
    void testGetID() {
        assertEquals("0001", person.getID());
    }

    @Test
    void testGetFirstName() {
        assertEquals("Remy", person.getFirstName());
    }

    @Test
    void testGetLastName() {
        assertEquals("Linguini", person.getLastName());
    }

    @Test
    void testGetTitle() {
        assertEquals("Chef", person.getTitle());
    }

    @Test
    void testGetYOB() {
        assertEquals(2001, person.getYOB());
    }

    @Test
    void testFullName() {
        assertEquals("Remy Linguini", person.fullName());
    }

    @Test
    void testFormalName() {
        assertEquals("Chef Remy Linguini", person.formalName());
    }

    @Test
    void testGetAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        assertEquals(currentYear - 2001, person.getAge());
    }

    @Test
    void testGetAgeForSpecificYear() {
        assertEquals(20, person.getAge(2021));
    }

    @Test
    void testToCSV() {
        assertEquals("0001,Remy,Linguini,Chef,2001", person.toCSV());
    }

    @Test
    void testToJSON() {
        assertTrue(person.toJSON().contains("\"firstName\":\"Remy\""));
        assertTrue(person.toJSON().contains("\"lastName\":\"Linguini\""));
    }

    @Test
    void testToXML() {
        assertTrue(person.toXML().contains("<firstName>Remy</firstName>"));
        assertTrue(person.toXML().contains("<lastName>Linguini</lastName>"));
    }

    @Test
    void testEquals() {
        Person clone = new Person("0001", "Remy", "Linguini", "Chef", 2001);
        assertEquals(person, clone);
    }
}
