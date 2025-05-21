package Models;

public class EmployeeDo {
    private final String id;
    private final String name;

    public EmployeeDo() {
        this.id = "id";
        this.name = "Employee name";
    }

    public EmployeeDo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}