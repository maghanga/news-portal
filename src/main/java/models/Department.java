package models;

public class Department {
    private String name;
    private String description;
    private int totalEmployees;
    private int id;

    public Department(String name, String description, int totalEmployees) {
        this.name = name;
        this.description = description;
        this.totalEmployees = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
