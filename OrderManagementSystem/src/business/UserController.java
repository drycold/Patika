package business;

import dao.UserDao;
import entity.User;

public class UserController {
    private final UserDao userDao = new UserDao();

    public User findByLogin(String mail, String password) {
        if (mail == null || mail.isEmpty()) return null;
        return this.userDao.findByLogin(mail, password);
    }
}
