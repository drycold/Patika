package entity;

/**
 * Müşteri varlık sınıfı. Bu sınıf müşteri bilgilerini saklamak için kullanılır.
 * Veritabanı kayıtları bu nesne üzerinden temsil edilir.
 */
public class Customer {
    private int id;
    private String name;
    private String mail;
    private String phone;
    private String address;
    private TYPE type;

    /**
     * Müşteri türlerini tanımlayan enum.
     */
    public enum TYPE {
        PERSON,
        COMPANY
    }

    /**
     * Varsayılan yapıcı metot. Boş bir müşteri nesnesi oluşturur.
     */
    public Customer() {
    }

    /**
     * ID, ad, e-posta ve telefon bilgileriyle müşteri nesnesi oluşturur.
     *
     * @param id Müşteri ID'si.
     * @param name Müşteri adı.
     * @param mail Müşteri e-postası.
     * @param phone Müşteri telefon numarası.
     */
    public Customer(int id, String name, String mail, String phone) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", type=" + type +
                '}';
    }
}
