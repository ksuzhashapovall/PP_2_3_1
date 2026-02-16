package web.dao;

import web.model.User;
import java.util.List;

public interface UserDao {
    void add(User user);
    void update(User user);
    void delete(Long id);
    User getById(Long id);
    List<User> getAll();
}