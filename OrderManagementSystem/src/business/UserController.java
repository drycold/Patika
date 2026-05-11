package business;

import dao.UserDao;
import entity.User;

/**
 * Kullanıcı işlemlerini yöneten kontrol sınıfı.
 * Kullanıcının giriş doğrulama işlemleri bu sınıf üzerinden geçer.
 */
public class UserController {

    // Kullanıcı veritabanı sorgularını gerçekleştiren DAO nesnesi.
    private final UserDao userDao = new UserDao();

    /**
     * Girilen e-posta ve şifreye göre kullanıcı arar.
     *
     * @param mail Kullanıcının e-posta adresi.
     * @param password Kullanıcının şifresi.
     * @return Giriş bilgileri doğruysa User nesnesi, yanlışsa null.
     */
    public User findByLogin(String mail, String password) {
        if (mail == null || mail.isEmpty()) return null;
        return this.userDao.findByLogin(mail, password);
    }
}
