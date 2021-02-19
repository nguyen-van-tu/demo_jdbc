package service;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService{
    protected Connection getConnection(){
        Connection connection = null;
        try {
                Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/list_user",
                    "root",
                    "123456");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                userList.add(new User(id,name,email,country));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    @Override
    public User findByID(int id) {
        User user= null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String country = resultSet.getString("country");
                user = new User(id,name,email,country);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean update(User user) {
        Connection connection = getConnection();
        boolean check = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set name= ?, email= ?, country= ? where id= ?");
            preparedStatement.setInt(4,user.getId());
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());
            check = preparedStatement.executeUpdate() >0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean delete= false;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("delete from users where id= ?");
            preparedStatement.setInt(1,id);
            delete = preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return delete;
    }

    @Override
    public void createUser(User user) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users" + "  (name, email, country) VALUES " + " (?, ?, ?);");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getCountry());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User findByCountry(String country) {
        return null;
    }

    @Override

    public User getUserById(int id) {

        User user = null;

        String query = "{CALL get_user_by_id(?)}";

        // Step 1: Establishing a Connection

        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object

             CallableStatement callableStatement = connection.prepareCall(query);) {

            callableStatement.setInt(1, id);

            // Step 3: Execute the query or update query

            ResultSet rs = callableStatement.executeQuery();

            // Step 4: Process the ResultSet object.

            while (true) {
                try {
                    if (!rs.next()) break;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                String name = rs.getString("name");

                String email = rs.getString("email");

                String country = rs.getString("country");

                user = new User(id, name, email, country);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;

    }



    @Override

    public void insertUserStore(User user) throws SQLException {

        String query = "{CALL insert_user(?,?,?)}";

        // try-with-resource statement will auto close the connection.

        try (Connection connection = getConnection();

             CallableStatement callableStatement = connection.prepareCall(query);) {

            callableStatement.setString(1, user.getName());

            callableStatement.setString(2, user.getEmail());

            callableStatement.setString(3, user.getCountry());

            System.out.println(callableStatement);

            callableStatement.executeUpdate();

        }

    }

}
