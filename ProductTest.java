import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("P001", "Laptop", "A high-performance laptop", 999.99);
    }

    @Test
    void testGetID() {
        assertEquals("P001", product.getID());
    }

    @Test
    void testGetName() {
        assertEquals("Laptop", product.getName());
    }

    @Test
    void testGetDescription() {
        assertEquals("A high-performance laptop", product.getDescription());
    }

    @Test
    void testGetCost() {
        assertEquals(999.99, product.getCost());
    }

    @Test
    void testToCSV() {
        assertEquals("P001,Laptop,A high-performance laptop,999.99", product.toCSV());
    }

    @Test
    void testToJSON() {
        assertTrue(product.toJSON().contains("\"name\":\"Laptop\""));
        assertTrue(product.toJSON().contains("\"description\":\"A high-performance laptop\""));
    }

    @Test
    void testToXML() {
        assertTrue(product.toXML().contains("<name>Laptop</name>"));
        assertTrue(product.toXML().contains("<description>A high-performance laptop</description>"));
    }

    @Test
    void testEquals() {
        Product clone = new Product("P001", "Laptop", "A high-performance laptop", 999.99);
        assertEquals(product, clone);
    }
}
