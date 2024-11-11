package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String birthDate;
    private List<InterestPoint> interestPoints; // Lista para almacenar los puntos de interés del usuario

    // Constructor vacío requerido para la deserialización
    public User() {
        this.interestPoints = new ArrayList<>();
    }

    // Constructor completo
    public User(String id, String firstName, String lastName, String email, String birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.interestPoints = new ArrayList<>();
    }

    // Getters y setters para cada campo
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    // Método para añadir un punto de interés al usuario
    public void addInterestPoint(InterestPoint point) {
        this.interestPoints.add(point);
    }

    // Método para obtener la lista de puntos de interés del usuario
    public List<InterestPoint> getInterestPoints() {
        return this.interestPoints;
    }
}
