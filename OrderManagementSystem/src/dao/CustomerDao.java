package dao;

import core.Database;
import entity.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Customer tablosu ile ilişkilendirilen veri erişim sınıfı.
 * CRUD (Create, Read, Update, Delete) işlemlerini doğrudan veritabanına uygular.
 */
public class CustomerDao {
    private final Connection connection;

    /**
     * Database singleton örneğinden bağlantıyı alır.
     */
    public CustomerDao() {
        this.connection = Database.getInstance();
    }

    /**
     * Tüm müşteri kayıtlarını listeler.
     *
     * @return Veritabanındaki tüm müşterilerin listesi.
     */
    public ArrayList<Customer> findAll(){
        ArrayList<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()){
                customers.add(match(rs));
            }

        } catch (SQLException e) {
            // Hata oluşursa izleme için yazdırır.
            e.printStackTrace();
        }

        return customers;
    }

    /**
     * Yeni müşteri kaydı ekler.
     *
     * @param customer Kaydedilecek müşteri nesnesi.
     * @return Ekleme başarılıysa true, hatada false döner.
     */
    public boolean save(Customer customer){
        String query = "INSERT INTO customer (name, type, phone, mail, address) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, customer.getName());
            pr.setString(2, customer.getType().name());
            pr.setString(3, customer.getPhone());
            pr.setString(4, customer.getMail());
            pr.setString(5, customer.getAddress());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Belirtilen ID'ye sahip müşteri kaydını getirir.
     *
     * @param id Aranan müşterinin ID'si.
     * @return Müşteri bulunduysa nesne, bulunamadıysa null.
     */
    public Customer getById(int id){
        Customer customer = null;
        String query = "SELECT * FROM customer WHERE id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                customer = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    /**
     * Var olan müşteri kaydını günceller.
     *
     * @param customer Güncellenmiş müşteri bilgileri.
     * @return Güncelleme başarılıysa true, değilse false.
     */
    public boolean update(Customer customer){
        String query = "UPDATE customer SET name = ?, type = ?, phone = ?, mail = ?, address = ? WHERE id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, customer.getName());
            pr.setString(2, customer.getType().name());
            pr.setString(3, customer.getPhone());
            pr.setString(4, customer.getMail());
            pr.setString(5, customer.getAddress());
            pr.setInt(6, customer.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Belirtilen ID'ye sahip müşteri kaydını siler.
     *
     * @param id Silinecek müşteri ID'si.
     * @return Silme başarılıysa true, değilse false.
     */
    public boolean delete(int id){
        String query = "DELETE FROM customer WHERE id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * ResultSet satırını Customer nesnesine dönüştürür.
     *
     * @param rs Veritabanından dönen ResultSet.
     * @return Oluşturulan Customer nesnesi.
     */
    public Customer match(ResultSet rs) throws SQLException{
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setName(rs.getString("name"));
        customer.setMail(rs.getString("mail"));
        customer.setPhone(rs.getString("phone"));
        customer.setAddress(rs.getString("address"));
        customer.setType(Customer.TYPE.valueOf(rs.getString("type")));

        return customer;
    }
}
