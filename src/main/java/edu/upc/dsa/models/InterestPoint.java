package edu.upc.dsa.models;

public class InterestPoint {
    private int x;
    private int y;
    private String type;

    // Constructor vacío
    public InterestPoint() {}

    // Constructor con parámetros
    public InterestPoint(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    // Getters y Setters
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
