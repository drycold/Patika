package dao;

import core.Database;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Kullanıcı tablosuna erişim sağlayan DAO sınıfı.
 * Bu sınıf, kullanıcı kimlik doğrulaması ve listeleme işlemlerini yapar.
 */
public class UserDao {
    private final Connection connection;

    /**
     * Singleton Database bağlantısını alır.
     */
    public UserDao() {
        this.connection = Database.getInstance();
    }

    /**
     * Kullanıcının mail ve şifresine göre veritabanında arama yapar.
     *
     * @param mail Kullanıcının e-posta adresi.
     * @param password Kullanıcının şifresi.
     * @return Kullanıcı bulunduysa User nesnesi, bulunamadıysa null.
     */
    public User findByLogin(String mail, String password){
        User user = null;
        String query = "SELECT * FROM user WHERE mail = ? AND password = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, mail);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                user = match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Kullanıcı tablosundaki tüm kayıtları getirir.
     *
     * @return Kullanıcı listesi.
     */
    public ArrayList<User> findAll(){
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()){
                users.add(match(rs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();    
        }

        return users;
    }

    /**
     * ResultSet satırını User nesnesine çevirir.
     *
     * @param rs Veritabanı sorgu sonucu.
     * @return Kullanıcı nesnesi.
     */
    public User match(ResultSet rs) throws SQLException{
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setMail(rs.getString("mail"));
        user.setPassword(rs.getString("password"));

        return user;
    }
}
