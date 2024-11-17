package edu.upc.dsa;

import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.InterestPoint;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.exceptions.InterestPointNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JuegoVirtualManagerTest {

    private JuegoVirtualManager manager;

    @Before
    public void setUp() {
        this.manager = JuegoVirtualManagerImpl.getInstance();
    }

    @After
    public void tearDown() {
        this.manager.clear(); // Método que deberá limpiar los datos para cada prueba
    }

    @Test
    public void testAddUser() throws UserNotFoundException {
        manager.addUser("1", "John", "Doe", "john.doe@example.com", "1990-01-01");
        assertNotNull(manager.getUser("1"));
    }

    @Test
    public void testListUsersAlphabetically() {
        manager.addUser("1", "John", "Doe", "john.doe@example.com", "1990-01-01");
        manager.addUser("2", "Alice", "Smith", "alice.smith@example.com", "1992-02-02");
        assertEquals("Doe", manager.listUsers().get(0).getLastName());
    }

    @Test
    public void testGetUserInfo() throws UserNotFoundException {
        manager.addUser("1", "John", "Doe", "john.doe@example.com", "1990-01-01");
        assertEquals("John", manager.getUser("1").getFirstName());
    }

    @Test
    public void testAddInterestPoint() throws InterestPointNotFoundException {
        manager.addInterestPoint(0, 0, ElementType.DOOR);
        assertNotNull(manager.getInterestPoint(0, 0));
    }

    @Test
    public void testRegisterUserAtInterestPoint() throws UserNotFoundException, InterestPointNotFoundException {
        manager.addUser("1", "John", "Doe", "john.doe@example.com", "1990-01-01");
        manager.addInterestPoint(0, 0, ElementType.DOOR);
        manager.registerUserAtInterestPoint("1", 0, 0);
        assertTrue(manager.getUserInterestPoints("1").contains(manager.getInterestPoint(0, 0)));
    }

    @Test
    public void testGetUserInterestPoints() throws UserNotFoundException, InterestPointNotFoundException {
        manager.addUser("1", "John", "Doe", "john.doe@example.com", "1990-01-01");
        manager.addInterestPoint(0, 0, ElementType.DOOR);
        manager.registerUserAtInterestPoint("1", 0, 0);
        assertEquals(1, manager.getUserInterestPoints("1").size());
    }

    @Test
    public void testListUsersByInterestPoint() throws UserNotFoundException, InterestPointNotFoundException {
        manager.addUser("1", "John", "Doe", "john.doe@example.com", "1990-01-01");
        manager.addInterestPoint(0, 0, ElementType.DOOR);
        manager.registerUserAtInterestPoint("1", 0, 0);
        assertTrue(manager.getUsersByInterestPoint(0, 0).contains(manager.getUser("1")));
    }

    @Test
    public void testGetInterestPointsByType() {
        manager.addInterestPoint(0, 0, ElementType.DOOR);
        manager.addInterestPoint(1, 1, ElementType.WALL);
        assertEquals(1, manager.getInterestPointsByType(ElementType.DOOR).size());
    }
}
