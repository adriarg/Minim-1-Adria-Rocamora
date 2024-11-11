package edu.upc.dsa;

import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.InterestPoint;
import edu.upc.dsa.models.User;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.exceptions.InterestPointNotFoundException;
import org.apache.log4j.Logger;

import java.util.*;

public class JuegoVirtualManagerImpl implements JuegoVirtualManager {

    private static JuegoVirtualManager instance;
    private HashMap<String, User> users;
    private HashMap<String, InterestPoint> interestPoints;
    final static Logger logger = Logger.getLogger(JuegoVirtualManagerImpl.class);

    private JuegoVirtualManagerImpl() {
        this.users = new HashMap<>();
        this.interestPoints = new HashMap<>();
    }

    public static JuegoVirtualManager getInstance() {
        if (instance == null) instance = new JuegoVirtualManagerImpl();
        return instance;
    }

    @Override
    public void addUser(String id, String firstName, String lastName, String email, String birthDate) {
        User user = new User(id, firstName, lastName, email, birthDate);
        users.put(id, user);
        logger.info("Adding user: " + id);
    }

    @Override
    public List<User> listUsers() {
        List<User> userList = new ArrayList<>(users.values());
        userList.sort(Comparator.comparing(User::getLastName).thenComparing(User::getFirstName));
        return userList;
    }

    @Override
    public User getUser(String id) throws UserNotFoundException {
        if (!users.containsKey(id)) throw new UserNotFoundException("User not found: " + id);
        return users.get(id);
    }

    @Override
    public void addInterestPoint(int x, int y, ElementType type) {
        String key = x + "," + y;
        logger.info("Adding interest point at " + key);
        interestPoints.put(key, new InterestPoint(x, y, type.toString())); // Convertimos ElementType a String
        logger.info("Interest point added at " + key);
    }

    @Override
    public void registerUserAtInterestPoint(String userId, int x, int y) throws UserNotFoundException, InterestPointNotFoundException {
        User user = getUser(userId);
        String key = x + "," + y;
        InterestPoint point = interestPoints.get(key);
        if (point == null) throw new InterestPointNotFoundException("Interest point not found at " + key);
        user.addInterestPoint(point);
        logger.info("User " + userId + " registered at point " + key);
    }

    @Override
    public List<InterestPoint> getUserInterestPoints(String userId) throws UserNotFoundException {
        return getUser(userId).getInterestPoints();
    }

    @Override
    public List<User> getUsersByInterestPoint(int x, int y) throws InterestPointNotFoundException {
        String key = x + "," + y;
        if (!interestPoints.containsKey(key)) throw new InterestPointNotFoundException("Interest point not found at " + key);
        List<User> result = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getInterestPoints().contains(interestPoints.get(key))) result.add(user);
        }
        return result;
    }

    @Override
    public List<InterestPoint> getInterestPointsByType(ElementType type) {
        List<InterestPoint> result = new ArrayList<>();
        for (InterestPoint point : interestPoints.values()) {
            if (point.getType().equals(type.toString())) result.add(point);
        }
        return result;
    }

    @Override
    public InterestPoint getInterestPoint(int x, int y) {
        String key = x + "," + y;
        return interestPoints.get(key);  // Método que devuelve el InterestPoint en base a las coordenadas.
    }

    @Override
    public void clear() {
        users.clear();
        interestPoints.clear();
        logger.info("Cleared all data");
    }
}
