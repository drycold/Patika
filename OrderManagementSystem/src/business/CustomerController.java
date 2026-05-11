package business;

import core.Helper;
import dao.CustomerDao;
import entity.Customer;
import java.util.ArrayList;

/**
 * Müşteri işlemlerini yöneten kontrol sınıfı.
 * Bu sınıf, servis katmanından DAO katmanına çağrıları yönlendirir ve
 * temel iş mantığını içerir.
 */
public class CustomerController {

    // Veri erişim objesi, müşteri işlemleri burada gerçek veritabanı komutlarına çevrilir.
    private final CustomerDao customerDao = new CustomerDao();

    /**
     * Veritabanındaki tüm müşterileri getirir.
     *
     * @return Müşteri listesi.
     */
    public ArrayList<Customer> findAll() {
        return this.customerDao.findAll();
    }

    /**
     * Yeni bir müşteri kaydeder.
     *
     * @param customer Kaydedilecek müşteri nesnesi.
     * @return Kaydetme işlemi başarılıysa true, değilse false.
     */
    public boolean save(Customer customer) {
        return this.customerDao.save(customer);
    }

    /**
     * Verilen ID'ye sahip müşteriyi getirir.
     *
     * @param id Aranan müşteri ID'si.
     * @return Müşteri bulunduysa nesne, bulunamadıysa null.
     */
    public Customer getById(int id) {
        return this.customerDao.getById(id);
    }

    /**
     * Mevcut bir müşterinin bilgilerini günceller.
     *
     * @param customer Güncellenmiş müşteri bilgileri.
     * @return Güncelleme başarılıysa true, değilse false.
     */
    public boolean update(Customer customer) {
        if (this.getById(customer.getId()) == null) {
            // Güncelleme yapmadan önce müşteri var mı diye kontrol eder.
            Helper.showMessage(customer.getId() + " ID'li müşteri bulunamadı");
            return false;
        }
        return this.customerDao.update(customer);
    }

    /**
     * Belirli bir müşteri kaydını siler.
     *
     * @param id Silinecek müşterinin ID'si.
     * @return Silme başarılıysa true, değilse false.
     */
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            // Silme yapmadan önce müşteri var mı diye kontrol eder.
            Helper.showMessage(id + " ID'li müşteri bulunamadı");
            return false;
        }
        return this.customerDao.delete(id);
    }

    /**
     * Müşteri adı ve tipi kullanarak müşterileri filtreler.
     * WHERE koşullarıyla özel SQL sorgusu oluşturur ve sonuç döndürür.
     *
     * @param name Filtrelenecek müşteri adı (kısmi eşleşme), boş string göz ardı edilir.
     * @param type Filtrelenecek müşteri tipi (PERSON veya COMPANY), null göz ardı edilir.
     * @return Filtre kriterlerine uyan müşteri listesi.
     */
    public ArrayList<Customer> filter(String name, Customer.TYPE type) {
        // Temel SELECT sorgusu oluşturur.
        String query = "SELECT * FROM customer";

        // Filtre kriterlerini tutacak liste.
        ArrayList<String> whereList = new ArrayList<>();

        // Adı verilmişse LIKE operatörüyle kısmi eşleşme ekler.
        if (name.length() > 0) {
            whereList.add("name LIKE '%" + name + "%'");
        }
        // Tipi verilmişse WHERE koşuluna ekler.
        if (type != null) {
            whereList.add("type = '" + type + "'");
        }
        // Eğer kriterler varsa WHERE cümlesi oluşturur.
        if (!whereList.isEmpty()) {
            String whereQuery = String.join(" AND ", whereList);
            query += " WHERE " + whereQuery;
        }
        return this.customerDao.query(query);
    }
}
