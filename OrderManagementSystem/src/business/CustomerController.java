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
}
