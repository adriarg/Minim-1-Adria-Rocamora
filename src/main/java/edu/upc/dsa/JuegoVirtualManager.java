package edu.upc.dsa;

import edu.upc.dsa.exceptions.InterestPointNotFoundException;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.models.ElementType;
import edu.upc.dsa.models.InterestPoint;
import edu.upc.dsa.models.User;

import java.util.List;

public interface JuegoVirtualManager {

    void addUser(String id, String firstName, String lastName, String email, String birthDate);

    List<User> listUsers();

    User getUser(String id) throws UserNotFoundException;

    void addInterestPoint(int x, int y, ElementType type);

    void registerUserAtInterestPoint(String userId, int x, int y) throws UserNotFoundException, InterestPointNotFoundException;

    List<InterestPoint> getUserInterestPoints(String userId) throws UserNotFoundException;

    List<User> getUsersByInterestPoint(int x, int y) throws InterestPointNotFoundException;

    List<InterestPoint> getInterestPointsByType(ElementType type);

    InterestPoint getInterestPoint(int x, int y); // Añadir este método

    void clear();
}
