package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getInstance;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }
    public void createUsersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS users
                (
                    id       SERIAL PRIMARY KEY,
                    name     VARCHAR(64) NOT NULL,
                    lastName VARCHAR(64) NOT NULL,
                    age      TINYINT     NOT NULL
                );
                """;
        try (var connection = getInstance().openConnection();
             var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sql = """
                DROP TABLE IF EXISTS users;
                """;
        try (var connection = getInstance().openConnection();
             var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = """
                INSERT INTO users (name, lastName, age)
                VALUES (?, ?, ?)
                """;
        try (var connection = getInstance().openConnection();
             var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setString(1, name);
            prepareStatement.setString(2, lastName);
            prepareStatement.setByte(3, age);
            prepareStatement.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = """
                DELETE
                FROM users
                WHERE id = ?;
                """;
        try (var connection = getInstance().openConnection();
             var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setLong(1, id);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = """
                SELECT id,
                       name,
                       lastName,
                       age
                FROM users;
                """;
        try (var connection = getInstance().openConnection();
             var prepareStatement = connection.prepareStatement(sql)) {
            var resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;

    }

    public void cleanUsersTable() {
        String sql = """
                TRUNCATE users;
                """;
        try (var connection = getInstance().openConnection();
             var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
