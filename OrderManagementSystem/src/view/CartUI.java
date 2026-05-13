package view;

import business.BasketController;
import business.CartController;
import business.ProductController;
import core.Helper;
import entity.Basket;
import entity.Cart;
import entity.Customer;
import entity.Product;
import java.awt.Toolkit;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

/**
 * Sipariş oluşturma ekranını sağlayan arayüz sınıfı.
 * Seçilen müşteri için sepetteki ürünleri siparişe dönüştürür.
 */
public class CartUI extends JFrame {
    private JPanel container;
    private JLabel lbl_title;
    private JLabel lbl_customer_name;
    private JTextField fld_cart_date;
    private JTextArea tarea_cart_note;
    private JButton btn_cart;
    private JLabel lbl_cart_date;
    private JLabel lbl_cart_note;
    private Customer customer;
    private BasketController basketController;
    private CartController cartController;
    private ProductController productController;

    /**
     * CartUI kurucusu. Müşteri seçimi ve sepetteki ürünlerin siparişe dönüştürülmesi için UI öğelerini hazırlar.
     * @param customer Sipariş için seçilmiş Customer nesnesi
     */
    public CartUI(Customer customer) {
        this.customer = customer;
        this.basketController = new BasketController();
        this.cartController = new CartController();
        this.productController = new ProductController();

        this.add(container);
        this.setTitle("Sipariş oluştur");
        this.setSize(300, 350);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x, y);
        this.setVisible(true);
    
        if (customer.getId() == 0) {
            Helper.showMessage("Lütfen geçerli bir müşteri seçiniz.");
            dispose();
        }

        ArrayList<Basket> baskets = this.basketController.findAll();

        if (baskets.isEmpty()) {
            Helper.showMessage("Sepetiniz boş. Lütfen ürün ekleyiniz.");
            dispose();
        }

        this.lbl_customer_name.setText("Müşteri: " + customer.getName());
        
        // Sepete eklenmiş ürünleri siparişe dönüştürmek için buton olayını tanımlar.
        btn_cart.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_cart_date)) {
                Helper.showMessage("fill");
            } else {
                for (Basket basket : baskets) {
                    // Stokta olmayan ürünleri atlar.
                    if (basket.getProduct().getStock() <= 0) continue;

                    Cart cart = new Cart();
                    cart.setCustomerId(customer.getId());
                    cart.setProductId(basket.getProductId());
                    cart.setPrice(basket.getProduct().getPrice());
                    cart.setDate(java.time.LocalDate.parse(fld_cart_date.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                    cart.setNote(tarea_cart_note.getText());
                    if (CartUI.this.cartController.save(cart)) {
                        Product unStockProduct = basket.getProduct();
                        unStockProduct.setStock(unStockProduct.getStock() - 1);
                        this.productController.update(unStockProduct);
                        
                        Helper.showMessage("done");
                        CartUI.this.basketController.clear();
                        CartUI.this.dispose();
                    } else {
                        Helper.showMessage("Sipariş oluşturulurken bir hata oluştu.");
                    }
                }
            }
        });
    }
    
    /**
     * UI oluşturucularını özelleştirir. Tarih alanına varsayılan bugünkü tarihi atar.
     */
    private void createUIComponents() {
        try {
            this.fld_cart_date = new JFormattedTextField(new MaskFormatter("##.##.####"));
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            this.fld_cart_date.setText(dateFormatter.format(java.time.LocalDate.now()));
        } catch (ParseException ex) {
            System.getLogger(CartUI.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
