package service;

import model.User;

import java.sql.SQLException;

public interface IUserService extends IService<User>{
    boolean deleteUser(int id);
    void createUser(User user);
    User findByCountry(String country);
    public User getUserById(int id);

    public void insertUserStore(User user) throws SQLException;

}
