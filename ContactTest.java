package contactService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void testValidContactCreation() {
        Contact contact = new Contact("PETER00001", "Simon", "Peter", "0000000012", "Sea of Galilee");
        assertEquals("PETER00001", contact.getContactId());
        assertEquals("Simon", contact.getFirstName());
        assertEquals("Peter", contact.getLastName());
        assertEquals("0000000012", contact.getPhone());
        assertEquals("Sea of Galilee", contact.getAddress());
    }

    // --- ID Tests ---
    @Test
    void testContactIdTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("PETERTHEAPOSTLE", "Simon", "Peter", "0000000012", "Galilee");
        });
    }

    @Test
    void testContactIdNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "Simon", "Peter", "0000000012", "Galilee");
        });
    }

    // --- First Name Tests ---
    @Test
    void testFirstNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("PETER001", "MaryMagdalene", "Saint", "0000000012", "Magdala");
        });
    }

    @Test
    void testFirstNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("PETER001", null, "Peter", "0000000012", "Galilee");
        });
    }

    // --- Last Name Tests ---
    @Test
    void testLastNameTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("JUDAS001", "Judas", "IscariotTheTraitor", "0000000012", "Kerioth");
        });
    }

    @Test
    void testLastNameNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("JUDAS001", "Judas", null, "0000000012", "Kerioth");
        });
    }

    // --- Phone Tests ---
    @Test
    void testPhoneTooShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("LUKE001", "Luke", "Evangelist", "12345", "Antioch");
        });
    }

    @Test
    void testPhoneTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("LUKE001", "Luke", "Evangelist", "12345678901", "Antioch");
        });
    }

    @Test
    void testPhoneWithLetters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("LUKE001", "Luke", "Evangelist", "12345ABCDE", "Antioch");
        });
    }

    @Test
    void testPhoneNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("LUKE001", "Luke", "Evangelist", null, "Antioch");
        });
    }

    // --- Address Tests ---
    @Test
    void testAddressTooLong() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("JOHN001", "John", "Apostle", "1234567890", "A very very very long address in the holy land of Patmos");
        });
    }

    @Test
    void testAddressNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("JOHN001", "John", "Apostle", "1234567890", null);
        });
    }

    // --- Setter Tests (updatable fields) ---
    @Test
    void testSettersSuccess() {
        Contact contact = new Contact("PAUL001", "Saul", "Tarsus", "1111111111", "Damascus");
        contact.setFirstName("Paul");
        contact.setLastName("Apostle");
        contact.setPhone("2222222222");
        contact.setAddress("Rome Italy");

        assertEquals("Paul", contact.getFirstName());
        assertEquals("Apostle", contact.getLastName());
        assertEquals("2222222222", contact.getPhone());
        assertEquals("Rome Italy", contact.getAddress());
    }

    @Test
    void testSetterInvalidValues() {
        Contact contact = new Contact("PAUL001", "Saul", "Tarsus", "1111111111", "Damascus");
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("WayTooLongLastName"));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("123"));
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(null));
    }
}