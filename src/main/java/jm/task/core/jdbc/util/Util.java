package jm.task.core.jdbc.util;

public class Util {
<<<<<<< HEAD
    private static Util instance;
    public static synchronized Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }
    private Util() {
    }
    private static final String PASSWORD = "root";
    private static final String USERNAME = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/users_data_base";

    static {
        loadDriver();
    }
    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection openConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
=======
    // реализуйте настройку соеденения с БД
>>>>>>> master
}
