/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 *
 * @author nelio_saturnino
 */
public class UserDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public UserDAO() {
        try {
            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException | ClassNotFoundException e) {
            //
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                //
            }
        }
    }

    public void addUser(User user) {
        try {
            if (connection.isClosed()) {
                connection = ConnectionManager.getConnection();
            }

            preparedStatement = connection.
                    prepareStatement("insert into users(name,email,password) values (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));

            for (String phone : user.getPhones()) {
                preparedStatement = connection.prepareStatement("insert into phones(number,user_id) values (?, ?)");
                preparedStatement.setString(1, phone);
                preparedStatement.setInt(2, user.getId());
                preparedStatement.executeUpdate();
            }

            connection.commit();

            preparedStatement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            //
            try {
                connection.rollback();
            } catch (SQLException ex) {
                //
            }
        } finally {
            closeAll();
        }
    }

    public void deleteUser(int id) {
        try {
            if (connection.isClosed()) {
                connection = ConnectionManager.getConnection();
            }

            preparedStatement = connection
                    .prepareStatement("delete from phones where user_id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = connection
                    .prepareStatement("delete from users where id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            connection.commit();

            preparedStatement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            //
            try {
                connection.rollback();
            } catch (SQLException ex) {
                //
            }
        } finally {
            closeAll();
        }
    }

    public void updateUser(User user) {
        try {
            if (connection.isClosed()) {
                connection = ConnectionManager.getConnection();
            }

            preparedStatement = connection
                    .prepareStatement("update users set name=?, email=?, password=?"
                    + "where id=?");

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();

            List<String> newUserPhones = user.getPhones();
            List<String> oldUserPhones = getUserPhones(user.getId());

            for (String oldPhone : oldUserPhones) {
                if (!newUserPhones.contains(oldPhone)) {
                    deleteUserPhone(user.getId(), oldPhone);
                }
            }

            for (String newPhone : newUserPhones) {
                if (!oldUserPhones.contains(newPhone)) {
                    addUserPhone(user.getId(), newPhone);
                }
            }

            connection.commit();

            preparedStatement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            //
            try {
                connection.rollback();
            } catch (SQLException ex) {
                //
            }
        } finally {
            closeAll();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            if (connection.isClosed()) {
                connection = ConnectionManager.getConnection();
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from users");

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhones(getUserPhones(user.getId()));
                users.add(user);
            }

            statement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            //
        } finally {
            closeAll();
        }
        return users;
    }

    public User getUserById(int id) {
        User user = new User();
        try {
            if (connection.isClosed()) {
                connection = ConnectionManager.getConnection();
            }

            preparedStatement = connection.
                    prepareStatement("select * from users where id=?");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhones(getUserPhones(user.getId()));
            }

            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            //
        } finally {
            closeAll();
        }
        return user;
    }

    public User getUserByEmail(String email) {
        User user = new User();
        try {
            if (connection.isClosed()) {
                connection = ConnectionManager.getConnection();
            }

            preparedStatement = connection.
                    prepareStatement("select * from users where email=?");

            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setPhones(getUserPhones(user.getId()));
            }

            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            //
        } finally {
            closeAll();
        }
        if (user.getEmail() != null) {
            return user;
        } else {
            return null;
        }
    }

    public List<String> getUserPhones(int id) {
        List<String> phones = new ArrayList<>();
        try {
            if (connection.isClosed()) {
                connection = ConnectionManager.getConnection();
            }

            PreparedStatement phonePreparedStatement = connection
                    .prepareStatement("select number from phones where user_id=?");
            phonePreparedStatement.setInt(1, id);

            ResultSet phoneResultSet = phonePreparedStatement.executeQuery();

            while (phoneResultSet.next()) {
                phones.add(phoneResultSet.getString("number"));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            //
        }
        return phones;
    }

    public void addUserPhone(int userId, String number) {
        try {
            if (connection.isClosed()) {
                connection = ConnectionManager.getConnection();
            }

            PreparedStatement phonePreparedStatement = connection
                    .prepareStatement("insert into phones (number, user_id) values(?,?)");
            phonePreparedStatement.setString(1, number);
            phonePreparedStatement.setInt(2, userId);
            phonePreparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException | ClassNotFoundException ex) {
            //
        }
    }

    public void deleteUserPhone(int userId, String number) {
        try {
            if (connection.isClosed()) {
                connection = ConnectionManager.getConnection();
            }

            PreparedStatement phonePreparedStatement = connection
                    .prepareStatement("delete from phones where user_id=? and number=?");
            phonePreparedStatement.setInt(1, userId);
            phonePreparedStatement.setString(2, number);
            phonePreparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException | ClassNotFoundException ex) {
            //
        }
    }

    public void closeAll() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            //
        }
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            //
        }
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            //
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            //
        }
    }
}