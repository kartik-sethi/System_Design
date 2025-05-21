package Proxy;

import Dao.*;
import Models.EmployeeDo;

public class EmployeeDaoProxy implements EmployeesDao {
    EmployeesDao employeeDao;

    public EmployeeDaoProxy() {
        employeeDao = new EmployeeDaoImpl();
    }

    @Override
    public void create(String client, EmployeeDo obj) throws Exception {
        if (client.equals("ADMIN")) {
            employeeDao.create(client, obj);
            return;
        }
        throw new Exception("Access Denied");
    }

    @Override
    public void delete(String client, String employeeId) throws Exception {
        if (client.equals("ADMIN")) {
            employeeDao.delete(client, employeeId);
        }
        throw new Exception("Access Denied");
    }

    @Override
    public EmployeeDo get(String client, String employeeId) throws Exception {
        if (client.equals("ADMIN")) {
            employeeDao.get(client, employeeId);
        }
        throw new Exception("Access Denied");
    }
}