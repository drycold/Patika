package dao;

import core.Database;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Ürün veritabanı işlemlerini yöneten DAO sınıfı
 * Veritabanı bağlantısı üzerinden ürün CRUD işlemlerini gerçekleştirir
 */
public class ProductDao {
    private final Connection connection;

    public ProductDao() {
        this.connection = Database.getInstance();
    }

    /**
     * Tüm ürünleri veritabanından getirir
     * @return Ürün listesi
     */
    public ArrayList<Product> findAll(){
        ArrayList<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()){
                products.add(match(rs));
            }

        } catch (SQLException e) {
            // Hata oluşursa izleme için yazdırır.
            e.printStackTrace();
        }

        return products;
    }

    /**
     * ID'ye göre ürünü veritabanından getirir
     * @param id Ürün ID'si
     * @return Ürün nesnesi veya null
     */
    public Product getById(int id){
        Product product = null;
        String query = "SELECT * FROM product WHERE id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                product = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    /**
     * Yeni ürünü veritabanına kaydeder
     * @param product Kaydedilecek ürün
     * @return İşlem başarılı ise true
     */
    public boolean save(Product product){
        String query = "INSERT INTO product (name, code, price, stock) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, product.getName());
            pr.setString(2, product.getCode());
            pr.setInt(3, product.getPrice());
            pr.setInt(4, product.getStock());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Var olan ürünü veritabanında günceller
     * @param product Güncellenecek ürün
     * @return İşlem başarılı ise true
     */
    public boolean update(Product product){
        String query = "UPDATE product SET name = ?, code = ?, price = ?, stock = ? WHERE id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, product.getName());
            pr.setString(2, product.getCode());
            pr.setInt(3, product.getPrice());
            pr.setInt(4, product.getStock());
            pr.setInt(5, product.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Ürünü veritabanından siler
     * @param id Silinecek ürünün ID'si
     * @return İşlem başarılı ise true
     */
    public boolean delete(int id){
        String query = "DELETE FROM product WHERE id = ?";
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
     * Özel sorgu ile ürünleri getirir
     * @param query Çalıştırılacak SQL sorgusu
     * @return Ürün listesi
     */
    public ArrayList<Product> query(String query){
        ArrayList<Product> products = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()){
                products.add(match(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * ResultSet'ten Product nesnesi oluşturur
     * @param rs Veritabanı sonuç kümesi
     * @return Ürün nesnesi
     * @throws SQLException SQL hatası durumunda
     */
    public Product match(ResultSet rs) throws SQLException{
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setCode(rs.getString("code"));
        product.setPrice(rs.getInt("price"));
        product.setStock(rs.getInt("stock"));

        return product;
    }
}
