package contactService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {
    private ContactService service;

    @BeforeEach
    void setUp() {
        service = new ContactService();
    }

    @Test
    void testAddContactSuccess() {
        Contact matthew = new Contact("MATTHEW001", "Matthew", "Levi", "0987654321", "Capernaum");
        service.addContact(matthew);
        assertNotNull(service.getContact("MATTHEW001"));
    }

    @Test
    void testAddNullContactThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(null);
        });
    }

    @Test
    void testAddDuplicateContact() {
        Contact c1 = new Contact("MATTHEW001", "Matthew", "Levi", "0987654321", "Capernaum");
        Contact c2 = new Contact("MATTHEW001", "Matthew", "Saint", "1111111111", "Jerusalem");
        service.addContact(c1);
        assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(c2);
        });
    }

    @Test
    void testDeleteContactSuccess() {
        Contact thomas = new Contact("THOMAS0001", "Thomas", "Didymus", "1231231234", "Galilee");
        service.addContact(thomas);
        service.deleteContact("THOMAS0001");
        assertNull(service.getContact("THOMAS0001"));
    }

    @Test
    void testDeleteNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact("INVALIDID");
        });
    }

    @Test
    void testDeleteNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact(null);
        });
    }

    @Test
    void testUpdateContactSuccess() {
        Contact james = new Contact("JAMES00001", "James", "Zebedee", "5555555555", "Jerusalem");
        service.addContact(james);
        service.updateContact("JAMES00001", "Saint", "James", "9999999999", "Spain");

        Contact updated = service.getContact("JAMES00001");
        assertEquals("Saint", updated.getFirstName());
        assertEquals("James", updated.getLastName());
        assertEquals("9999999999", updated.getPhone());
        assertEquals("Spain", updated.getAddress());
    }

    @Test
    void testUpdateContactPartial() {
        Contact james = new Contact("JAMES00001", "James", "Zebedee", "5555555555", "Jerusalem");
        service.addContact(james);
        service.updateContact("JAMES00001", null, null, "9999999999", "Spain");

        Contact updated = service.getContact("JAMES00001");
        assertEquals("James", updated.getFirstName());
        assertEquals("Zebedee", updated.getLastName());
        assertEquals("9999999999", updated.getPhone());
        assertEquals("Spain", updated.getAddress());
    }

    @Test
    void testUpdateNonExistentContact() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact("NOBODY0001", "Name", "Last", "1234567890", "Address");
        });
    }

    @Test
    void testUpdateNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateContact(null, "Name", "Last", "1234567890", "Address");
        });
    }
}