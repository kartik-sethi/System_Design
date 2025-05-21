package Dao;

import Models.EmployeeDo;

public interface EmployeesDao {
    void create(String client, EmployeeDo obj) throws Exception;

    void delete(String client, String employeeId) throws Exception;

    EmployeeDo get(String client, String employeeId) throws Exception;
}