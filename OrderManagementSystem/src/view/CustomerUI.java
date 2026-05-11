package view;

import business.CustomerController;
import core.Helper;
import entity.Customer;
import java.awt.*;
import javax.swing.*;

/**
 * Müşteri ekleme ve düzenleme penceresini sağlayan JFrame sınıfı.
 * Hem yeni müşteri ekleme hem de mevcut müşteri düzenleme işlemlerini yönetir.
 */
public class CustomerUI extends JFrame {
    private JPanel container;
    private JLabel lbl_title;
    private JLabel lbl_name;
    private JTextField fld_customer_name;
    private JLabel lbl_type;
    private JComboBox<Customer.TYPE> cmb_customer_type;
    private JLabel lbl_customer_phone;
    private JTextField fld_customer_phone;
    private JTextField fld_customer_mail;
    private JLabel lbl_customer_mail;
    private JLabel lbl_customer_address;
    private JTextArea tarea_customer_address;
    private JButton btn_customer_save;
    private Customer customer;
    private CustomerController customerController;

    /**
     * Müşteri ekleme/düzenleme ekranını başlatır.
     *
     * @param customer Düzenlenecek müşteri nesnesi. Eğer ID 0 ise yeni müşteri ekleme modu açılır.
     */
    public CustomerUI(Customer customer) {
        this.customer = customer;
        this.customerController = new CustomerController();

        this.add(container);
        this.setTitle("Müşteri Ekle/Düzenle");
        this.setSize(300, 500);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // Müşteri türü açılır listesini enum değerleriyle doldur.
        this.cmb_customer_type.setModel(new DefaultComboBoxModel<>(Customer.TYPE.values()));

        if (this.customer.getId() == 0) {
            // Yeni müşteri ekleme modu.
            this.lbl_title.setText("Müşteri Ekle");
        } else {
            // Mevcut müşteri düzenleme modu ve alanları mevcut değerlerle doldurma.
            this.lbl_title.setText("Müşteri Düzenle");
            this.fld_customer_name.setText(this.customer.getName());
            this.cmb_customer_type.getModel().setSelectedItem(this.customer.getType());
            this.fld_customer_phone.setText(this.customer.getPhone());
            this.fld_customer_mail.setText(this.customer.getMail());
            this.tarea_customer_address.setText(this.customer.getAddress());
        }

        // Kaydet butonuna tıklanınca çalışacak işlem.
        this.btn_customer_save.addActionListener(e -> {
            JTextField[] checkList = {this.fld_customer_name, this.fld_customer_phone};
            if (Helper.isFieldListEmpty(checkList)) {
                Helper.showMessage("fill");
            } else  if(!Helper.isFieldEmpty(this.fld_customer_mail) && !Helper.isEmailValid(this.fld_customer_mail)) {
                Helper.showMessage("Lütfen geçerli bir e-posta adresi giriniz");
            } else {
                boolean result = false;
                this.customer.setName(this.fld_customer_name.getText());
                this.customer.setType((Customer.TYPE) this.cmb_customer_type.getSelectedItem());
                this.customer.setPhone(this.fld_customer_phone.getText());
                this.customer.setMail(this.fld_customer_mail.getText());
                this.customer.setAddress(this.tarea_customer_address.getText());

                if (this.customer.getId() == 0) {
                    // Müşteri olmayan ID ile kaydetme işlemi gerçekleştirir.
                    result = this.customerController.save(this.customer);
                } else {
                    // Mevcut kaydı günceller.
                    result = this.customerController.update(this.customer);
                }

                if (result) {
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }

}
