package dao;

import core.Database;
import entity.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Sipariş kayıtları için veri erişim sınıfı.
 * Cart tablosunda CRUD işlemlerini gerçekleştirir.
 */
public class CartDao {
    private final Connection connection;
    private final CustomerDao customerDao;
    private final ProductDao productDao;

    /**
     * CartDao oluşturucusu. Veritabanı bağlantısını ve gerekli DAO'ları başlatır.
     */
    public CartDao() {
        this.connection = Database.getInstance();
        this.customerDao = new CustomerDao();
        this.productDao = new ProductDao();
    }

    /**
     * Veritabanındaki tüm sipariş kayıtlarını getirir.
     * @return Cart nesnelerinin listesi
     */
    public ArrayList<Cart> findAll(){
        ArrayList<Cart> carts = new ArrayList<>();
        String query = "SELECT * FROM cart";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()){
                carts.add(match(rs));
            }

        } catch (SQLException e) {
            // Hata olması durumunda loglamayı sağlar.
            e.printStackTrace();
        }

        return carts;
    }

    /**
     * Yeni sipariş kaydı oluşturur.
     * @param cart Kaydedilecek sipariş nesnesi
     * @return Kaydetme başarılıysa true
     */
    public boolean save(Cart cart){
        String query = "INSERT INTO cart (customer_id, product_id, price, date, note) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, cart.getCustomerId());
            pr.setInt(2, cart.getProductId());
            pr.setInt(3, cart.getPrice());
            pr.setDate(4, java.sql.Date.valueOf(cart.getDate()));
            pr.setString(5, cart.getNote());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * ResultSet satırını Cart nesnesine dönüştürür.
     * @param rs Veritabanı sonucu
     * @return Dönüştürülmüş Cart nesnesi
     * @throws SQLException SQL hatası varsa fırlatılır
     */
    public Cart match(ResultSet rs) throws SQLException{
        Cart cart = new Cart();
        cart.setId(rs.getInt("id"));
        cart.setCustomerId(rs.getInt("customer_id"));
        cart.setProductId(rs.getInt("product_id"));
        cart.setPrice(rs.getInt("price"));
        cart.setDate(rs.getDate("date").toLocalDate());
        cart.setNote(rs.getString("note"));
        cart.setCustomer(this.customerDao.getById(cart.getCustomerId()));
        cart.setProduct(this.productDao.getById(cart.getProductId()));

        return cart;
    }
}
