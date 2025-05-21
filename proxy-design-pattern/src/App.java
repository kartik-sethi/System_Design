
import Dao.EmployeesDao;
import Models.EmployeeDo;
import Proxy.EmployeeDaoProxy;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            EmployeesDao employeesDao = new EmployeeDaoProxy();
            employeesDao.create("ADMIN", new EmployeeDo());
            System.out.println("Create Operation Successful");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
