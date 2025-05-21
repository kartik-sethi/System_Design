package Dao;

import Models.EmployeeDo;

public class EmployeeDaoImpl implements EmployeesDao {

    @Override
    public void create(String client, EmployeeDo obj) throws Exception {
        System.out.println("Created new row in employee table");
    }

    @Override
    public void delete(String client, String employeeId) throws Exception {
        System.out.println("deleted employee with employeeID: " + employeeId);
    }

    @Override
    public EmployeeDo get(String client, String employeeId) {
        System.out.println("Fetched employee with employeeID: " + employeeId);
        return new EmployeeDo();
    }
}
