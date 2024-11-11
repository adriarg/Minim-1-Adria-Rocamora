package edu.upc.dsa;

import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.InterestPoint;
import edu.upc.dsa.models.User;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.exceptions.InterestPointNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class JuegoVirtualManagerTest {

    private JuegoVirtualManager manager;

    @Before
    public void setUp() {
        manager = JuegoVirtualManagerImpl.getInstance();
    }

    @Test
    public void testAddUser() throws UserNotFoundException {
        manager.addUser("1", "John", "Doe", "john.doe@example.com", "1990-01-01");
        User user = manager.getUser("1");
        assertNotNull(user);
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
    }

    @Test
    public void testListUsers() {
        manager.addUser("1", "John", "Doe", "john.doe@example.com", "1990-01-01");
        manager.addUser("2", "Jane", "Smith", "jane.smith@example.com", "1992-05-12");

        List<User> users = manager.listUsers();
        assertEquals(2, users.size());
        assertEquals("Doe", users.get(0).getLastName()); // Verifica orden alfabético
        assertEquals("Smith", users.get(1).getLastName());
    }

    @Test
    public void testAddInterestPoint() {
        manager.addInterestPoint(0, 0, ElementType.DOOR);
        InterestPoint point = manager.getInterestPoint(0, 0); // Obtener el punto con coordenadas 0,0
        assertNotNull(point);
        assertEquals(0, point.getX());
        assertEquals(0, point.getY());
        assertEquals("DOOR", point.getType());
    }

    @Test
    public void testRegisterUserAtInterestPoint() throws UserNotFoundException, InterestPointNotFoundException {
        manager.addUser("1", "John", "Doe", "john.doe@example.com", "1990-01-01");
        manager.addInterestPoint(0, 0, ElementType.DOOR);
        manager.registerUserAtInterestPoint("1", 0, 0);

        User user = manager.getUser("1");
        assertTrue(user.getInterestPoints().contains(manager.getInterestPoint(0, 0)));
    }

    @Test
    public void testGetUsersByInterestPoint() throws UserNotFoundException, InterestPointNotFoundException {
        manager.addUser("1", "John", "Doe", "john.doe@example.com", "1990-01-01");
        manager.addUser("2", "Jane", "Smith", "jane.smith@example.com", "1992-05-12");

        manager.addInterestPoint(0, 0, ElementType.DOOR);
        manager.registerUserAtInterestPoint("1", 0, 0);
        manager.registerUserAtInterestPoint("2", 0, 0);

        List<User> users = manager.getUsersByInterestPoint(0, 0);
        assertEquals(2, users.size());
    }

    @Test(expected = InterestPointNotFoundException.class)
    public void testInterestPointNotFound() throws InterestPointNotFoundException {
        manager.getInterestPoint(999, 999);  // Coordenadas no registradas
    }

    @Test
    public void testGetUserInterestPoints() throws UserNotFoundException, InterestPointNotFoundException {
        manager.addUser("1", "John", "Doe", "john.doe@example.com", "1990-01-01");
        manager.addInterestPoint(0, 0, ElementType.DOOR);
        manager.registerUserAtInterestPoint("1", 0, 0);

        List<InterestPoint> points = manager.getUserInterestPoints("1");
        assertEquals(1, points.size());
        assertEquals(0, points.get(0).getX());
        assertEquals(0, points.get(0).getY());
    }

    @Test
    public void testGetInterestPointsByType() {
        manager.addInterestPoint(0, 0, ElementType.DOOR);
        manager.addInterestPoint(1, 0, ElementType.WALL);
        List<InterestPoint> doorPoints = manager.getInterestPointsByType(ElementType.DOOR);
        assertEquals(1, doorPoints.size());
        assertEquals(0, doorPoints.get(0).getX());
        assertEquals(0, doorPoints.get(0).getY());
    }
}
