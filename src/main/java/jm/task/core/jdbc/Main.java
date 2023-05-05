package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import static jm.task.core.jdbc.util.Util.closePool;

public class Main {
    public static void main(String[] args) {
        try{
            Util.getConnect();
            UserService service = new UserServiceImpl();

            service.createUsersTable();

            service.saveUser("Name1", "LastName1", (byte) 20);
            service.saveUser("Name2", "LastName2", (byte) 25);
            service.saveUser("Name3", "LastName3", (byte) 31);
            service.saveUser("Name4", "LastName4", (byte) 38);

            service.removeUserById(1);
            service.getAllUsers().forEach (System.out::println);
        service.cleanUsersTable();
        service.dropUsersTable();
          } finally {
            closePool();
        }
    }
}
