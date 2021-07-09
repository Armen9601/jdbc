package jdbcexample.manager;

import jdbcexample.db.DBConnectionProvider;
import model.Users;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private Connection connection = DBConnectionProvider.getProvider().getConnection();

    public void addUsers(Users users) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO users(name,surname,email,password) VALUES ('%s','%s','%s','%s');",
                    users.getName(), users.getSurName(), users.getEmail(), users.getPassword());
            System.out.println(query);
            statement.executeUpdate(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateUsers(Users users) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE  users set name='%s',surname='%s',email='%s',password='%s' where id= " + users.getId(),
                    users.getName(), users.getSurName(), users.getEmail(), users.getPassword());

            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int anInt = generatedKeys.getInt(1);
                users.setId(anInt);
            }

            System.out.println(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Users> getUsers() {
        String sql = "SELECT * FROM USERS";
        List<Users> usersList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Users users = Users.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surName(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .build();
                usersList.add(users);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usersList;
    }public Users getUsersBYId(int id) {

        String sql = "SELECT * FROM USERS where id="+id;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Users.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .surName(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .password(resultSet.getString(5))
                        .build();
            }
            } catch(SQLException throwables){
                throwables.printStackTrace();
            }

        return null;
    }

    public void deleteUsers(int id) {
        String sql = "Delete from users where id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
