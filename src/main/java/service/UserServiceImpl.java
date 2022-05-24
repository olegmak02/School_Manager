package service;

import dao.UserDaoImpl;
import domain.User;

import java.sql.SQLException;
import java.util.List;


public class UserServiceImpl {
    private UserDaoImpl userDao = new UserDaoImpl();

    
    public List<User> findAll() throws SQLException {
        return userDao.getUsers();
    }

    
    public Long save(User user) throws SQLException {
        Long id = user.getId();
        try {
            if (id != null) {
                userDao.update(user);
            } else {
                id = userDao.create(user);
            }
        } catch (SQLException e) {
            throw e;
        }
        return id;
    }

    
    public User findById(Long id) throws SQLException {
        try {
            return userDao.read(id);
        } catch (SQLException e) {
            throw e;
        }
    }

    
    public User findByLoginAndPass(String login, String password) throws SQLException {
        return userDao.getByLoginAndPass(login, password);
    }

    
}
