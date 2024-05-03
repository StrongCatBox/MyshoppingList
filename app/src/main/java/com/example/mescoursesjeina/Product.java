package com.example.mescoursesjeina;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private String department;

    public Product() {
        // Constructeur par défaut
    }

    public Product(String name, int quantity, String department) {
        this.name = name;
        this.quantity = quantity;
        this.department = department;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
