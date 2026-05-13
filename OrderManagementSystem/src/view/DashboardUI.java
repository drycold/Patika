package view;

import business.BasketController;
import business.CartController;
import business.CustomerController;
import business.ProductController;
import core.Helper;
import core.Item;
import entity.Basket;
import entity.Cart;
import entity.Customer;
import entity.Product;
import entity.User;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Dashboard ekranını sağlayan JFrame sınıfı.
 * Bu ekran, kullanıcının giriş yaptıktan sonra müşteri kayıtlarını görmesini,
 * eklemesini, güncellemesini ve silmesini sağlar.
 */
public class DashboardUI extends JFrame {
    private JPanel container;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTabbedPane tab_menu;
    private JPanel pnl_customer;
    private JScrollPane scrl_customer;
    private JTable tbl_customer;
    private JPanel pnl_customer_filter;
    private JTextField fld_f_customer_name;
    private JComboBox cmb_f_customer_type;
    private JButton btn_customer_filter;
    private JButton btn_customer_filter_reset;
    private JButton btn_customer_new;
    private JLabel lbl_f_customer_name;
    private JLabel lbl_f_customer_type;
    private JPanel pnl_product;
    private JScrollPane scrl_product;
    private JTable tbl_product;
    private JPanel pnl_product_filter;
    private JTextField fld_f_product_name;
    private JTextField fld_f_product_code;
    private JComboBox<Item> cmb_f_product_stock;
    private JButton btn_product_filter;
    private JButton btn_product_filter_reset;
    private JButton btn_product_new;
    private JLabel lbl_f_product_name;
    private JLabel lnl_f_product_code;
    private JLabel lbl_f_product_stock;
    private JPanel pnl_basket;
    private JPanel pnl_basket_top;
    private JScrollPane scrl_basket;
    private JComboBox<Item> cmb_basket_customer;
    private JButton btn_basket_reset;
    private JButton btn_basket_new;
    private JLabel lbl_basket_price;
    private JLabel lbl_basket_count;
    private JTable tbl_basket;
    private JTable tbl_cart;
    private JScrollPane scrl_cart;
    private User user;
    private final CustomerController customerController;
    private final ProductController productController;
    private final BasketController basketController;
    private final CartController cartController;

    private final DefaultTableModel tmdl_customer = new DefaultTableModel();
    private final DefaultTableModel tmdl_product = new DefaultTableModel();
    private final DefaultTableModel tmdl_basket = new DefaultTableModel();
    private final DefaultTableModel tmdl_cart = new DefaultTableModel();

    private final JPopupMenu popup_customer = new JPopupMenu();
    private final JPopupMenu popup_product = new JPopupMenu();

    /**
     * Dashboard UI yapıcı metodu.
     *
     * @param user Giriş yapan kullanıcı nesnesi.
     */
    public DashboardUI(User user) {
        this.user = user;
        this.customerController = new CustomerController();
        this.productController = new ProductController();
        this.basketController = new BasketController();
        this.cartController = new CartController();
        
        if (user == null) {
            // Kullanıcı bilgisi yoksa hata gösterir ve pencereyi kapatır.
            Helper.showMessage("error");
            dispose();
        }

        this.add(container);
        this.setTitle("Müşteri Yönetim Sistemi");
        this.setSize(1000, 500);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.lbl_welcome.setText("Hoşgeldin : " + this.user.getName());

        // Çıkış butonuna tıklanınca login ekranına geri döner.
        this.btn_logout.addActionListener(e -> {
            dispose();
            new LoginUI();
        });

        // CUSTOMER TAB
        loadCustomerTable(null);
        loadCustomerPopupMenu();
        loadCustomerButtonEvent();
        this.cmb_f_customer_type.setModel(new DefaultComboBoxModel<>(Customer.TYPE.values()));
        this.cmb_f_customer_type.setSelectedItem(null);

        // PRODUCT TAB
        loadProductTable(null);
        loadProductPopupMenu();
        loadProductButtonEvent();
        // Ürün stok filtresi için combo box öğelerini ekler
        this.cmb_f_product_stock.addItem(new Item(1, "Stokta var"));
        this.cmb_f_product_stock.addItem(new Item(2, "Stokta yok"));
        this.cmb_f_product_stock.setSelectedItem(null);

        // BASKET TAB
        loadBasketTable();
        loadBasketButtonEvent();
        loadBasketCustomerCombo();

        // CART TAB
        loadCartTable();

    }

    /**
     * Tüm sipariş kayıtlarını Cart tablosuna yükler.
     * Tabloyu önce temizler, sonra veritabanındaki tüm kayıtları ekler.
     */
    private void loadCartTable() {
        Object[] colCart = {"ID", "Müşteri Adı", "Ürün Adı", "Fiyat", "Sipariş Tarihi", "Not"};

        ArrayList<Cart> carts = this.cartController.findAll();
        // Tabloyu temizler
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_cart.getModel();
        clearModel.setRowCount(0);

        this.tmdl_cart.setColumnIdentifiers(colCart);
        // Siparişleri tabloya ekler
        for (Cart cart : carts) {
            Customer customer = this.customerController.getById(cart.getCustomerId());
            Product product = this.productController.getById(cart.getProductId());
            Object[] rowObjects = {cart.getId(), customer.getName(), product.getName(), cart.getPrice(), cart.getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), cart.getNote()};

            this.tmdl_cart.addRow(rowObjects);
        }
        this.tbl_cart.setModel(this.tmdl_cart);
        this.tbl_cart.getTableHeader().setReorderingAllowed(false);
        this.tbl_cart.getColumnModel().getColumn(0).setMaxWidth(50);
        this.tbl_cart.setEnabled(false);
    }

    /**
     * Sepet sekmesi için buton olaylarını bağlar.
     * Bu metot, sepeti temizleme ve sipariş oluşturma işlemlerinin kontrolünü sağlar.
     */
    private void loadBasketButtonEvent() {

        this.btn_basket_reset.addActionListener(e -> {
            if(Helper.confirm("sure")){
                if (this.basketController.clear()) {
                    Helper.showMessage("done");
                    loadBasketTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

        this.btn_basket_new.addActionListener(e -> {
            Item selectedCustomer = (Item) cmb_basket_customer.getSelectedItem();
            if (selectedCustomer == null) {
                Helper.showMessage("Lütfen bir müşteri seçiniz.");
            } else {
                Customer customer = this.customerController.getById(selectedCustomer.getKey());
                ArrayList<Basket> baskets = this.basketController.findAll();
                if(customer.getId() == 0){
                    Helper.showMessage("Böyle bir müşteri bulunamadı.");
                } else if (baskets.isEmpty()) {
                     Helper.showMessage("Sepetiniz boş. Lütfen ürün ekleyiniz.");
                } else {
                    CartUI cartUI = new CartUI(customer);
                    cartUI.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent windowEvent) {
                            loadBasketTable();
                            loadProductTable(null);
                        }
                    });
                }
            }
        });
    }

    /**
     * Sepet için müşteri seçimi açılır listesini doldurur.
     * Mevcut müşterileri ComboBox'a ekler ve varsayılan seçimi kaldırır.
     */
    private void loadBasketCustomerCombo() {
        ArrayList<Customer> customers = this.customerController.findAll();
        this.cmb_basket_customer.removeAllItems();
        for (Customer customer : customers) {
            int comboKey = customer.getId();
            String comboValue = customer.getName() + " (" + customer.getType() + ")";
            this.cmb_basket_customer.addItem(new Item(comboKey, comboValue));
        }

        this.cmb_basket_customer.setSelectedItem(null);
    }

    /**
     * Sepet içeriğini yükler ve tabloya yansıtır.
     * Toplam fiyat ve ürün adedi bilgisini de günceller.
     */
    private void loadBasketTable() {
        Object[] colBasket = {"ID", "Ürün Adı", "Ürün Kodu", "Fiyat", "Stok"};

        ArrayList<Basket> baskets = this.basketController.findAll();
        // Tabloyu temizler
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_basket.getModel();
        clearModel.setRowCount(0);

        this.tmdl_basket.setColumnIdentifiers(colBasket);
        int totalPrice = 0;
        // Sepet ürünleri tabloya ekler
        for (Basket basket : baskets) {
            Product product = this.productController.getById(basket.getProductId());
            Object[] rowObjects = {basket.getId(), product.getName(), product.getCode(), product.getPrice(), product.getStock()};

            this.tmdl_basket.addRow(rowObjects);
            totalPrice += product.getPrice();
        }
        this.lbl_basket_price.setText(String.valueOf(totalPrice) + " TL");
        this.lbl_basket_count.setText(String.valueOf(baskets.size()) + " Adet");

        this.tbl_basket.setModel(this.tmdl_basket);
        this.tbl_basket.getTableHeader().setReorderingAllowed(false);
        this.tbl_basket.getColumnModel().getColumn(0).setMaxWidth(50);
        this.tbl_basket.setEnabled(false); // Sepet tablosunu düzenlenemez yapar
    }

    /**
     * Ürün sekmesi üzerindeki buton olaylarını bağlar.
     * Bu metot, yeni ürün ekleme, filtreleme ve filtreyi temizleme işlemlerini yönetir.
     */
    private void loadProductButtonEvent() {
        // Yeni ürün ekleme butonu olayı
        this.btn_product_new.addActionListener(e -> {
            ProductUI productUI = new ProductUI(new Product());
            productUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent windowEvent) {
                    // Ürün ekleme penceresi kapandığında tabloyu yeniler.
                    loadProductTable(null);
                }
            });
        });

        // Ürün filtreleme butonu olayı
        this.btn_product_filter.addActionListener(e -> {
            ArrayList<Product> filteredProducts = this.productController.filter(
                    this.fld_f_product_name.getText(),
                    this.fld_f_product_code.getText(),
                    (Item) this.cmb_f_product_stock.getSelectedItem()
            );
            loadProductTable(filteredProducts);
        });

        // Filtre sıfırlama butonu olayı
        this.btn_product_filter_reset.addActionListener(e -> {
            this.fld_f_product_name.setText(null);
            this.fld_f_product_code.setText(null);
            this.cmb_f_product_stock.setSelectedItem(null);
            loadProductTable(null);
        });
    }

    private void loadProductPopupMenu() {
        // Tabloya sağ tıklama olayı ekler
        this.tbl_product.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int selectedRow = tbl_product.rowAtPoint(e.getPoint());
                    tbl_product.setRowSelectionAllowed(true);
                    tbl_product.setRowSelectionInterval(selectedRow, selectedRow);
                }
            }
        });

        this.popup_product.add("Sepete Ekle").addActionListener(e -> {
            int selectedId = Integer.parseInt(this.tbl_product.getValueAt(this.tbl_product.getSelectedRow(), 0).toString());
            Product basketProduct = this.productController.getById(selectedId);
            if (basketProduct.getStock() > 0) {
                Basket basket = new Basket(basketProduct.getId());
                if (this.basketController.save(basket)) {
                    Helper.showMessage("done");
                    loadBasketTable();
                } else {
                    Helper.showMessage("error");
                }
            } else {
                Helper.showMessage("Bu ürün stokta yok!");
            }
        });

        // Popup menüye güncelleme seçeneği ekler
        this.popup_product.add("Güncelle").addActionListener(e -> {
            int selectedId = Integer.parseInt(this.tbl_product.getValueAt(this.tbl_product.getSelectedRow(), 0).toString());
            ProductUI productUI = new ProductUI(this.productController.getById(selectedId));
            productUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent windowEvent) {
                    loadProductTable(null);
                    loadBasketTable();
                }
            });
        });

        // Popup menüye silme seçeneği ekler
        this.popup_product.add("Sil").addActionListener(e -> {
            int selectedId = Integer.parseInt(this.tbl_product.getValueAt(this.tbl_product.getSelectedRow(), 0).toString());
            if (Helper.confirm("sure")){
                if (this.productController.delete(selectedId)) {
                    Helper.showMessage("done");
                    loadProductTable(null);
                    loadBasketTable();
                } else {
                    Helper.showMessage("error");
                }
            }

        });

        this.tbl_product.setComponentPopupMenu(this.popup_product);

    }

    /**
     * Ürünleri tabloya yükler.
     * Parametre olarak filtrelenmiş ürün listesi alır, null ise tüm ürünleri getirir.
     * @param products Görüntülenecek ürün listesi veya null
     */
    private void loadProductTable(ArrayList<Product> products) {
        Object[] colNames = {"ID", "Ürün Adı", "Ürün Kodu", "Fiyat", "Stok"};

        // Eğer filtre uygulanmamışsa tüm ürünleri getir
        if (products == null) {
            products = this.productController.findAll();
        }

        // Tabloyu temizler
        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_product.getModel();
        clearModel.setRowCount(0);

        this.tmdl_product.setColumnIdentifiers(colNames);

        // Ürünleri tabloya ekler
        for (Product product : products) {
            Object[] rowObjects = {product.getId(), product.getName(), product.getCode(), product.getPrice(), product.getStock()};
            this.tmdl_product.addRow(rowObjects);
        }
        this.tbl_product.setModel(this.tmdl_product);
        this.tbl_product.getTableHeader().setReorderingAllowed(false);
        this.tbl_product.getColumnModel().getColumn(0).setMaxWidth(50);
        // Tabloyu etkin bırak böylece sağ tıklama popup menüsü çalışabilsin.
        this.tbl_product.setEnabled(true);
    }

    /**
     * Müşteri sekmesi için buton olaylarını tanımlar.
     * Yeni müşteri ekleme, filtreleme ve filtreyi sıfırlama işlemlerini yönetir.
     */
    private void loadCustomerButtonEvent() {
        this.btn_customer_new.addActionListener(e -> {
            CustomerUI customerUI = new CustomerUI(new Customer());
            customerUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent windowEvent) {
                    // Müşteri ekleme penceresi kapandığında tabloyu yeniler.
                    loadCustomerTable(null);
                    loadBasketCustomerCombo();
                }
            });

        });

        this.btn_customer_filter.addActionListener(e -> {
            ArrayList<Customer> filteredCustomers = this.customerController.filter(
                    this.fld_f_customer_name.getText(),
                    (Customer.TYPE) this.cmb_f_customer_type.getSelectedItem()
            );
            loadCustomerTable(filteredCustomers);
        });

        btn_customer_filter_reset.addActionListener(e -> {
            loadCustomerTable(null);          
            this.fld_f_customer_name.setText(null);
            this.cmb_f_customer_type.setSelectedItem(null);

        });

    }

    /**
     * Müşteri tablosu için sağ tıklama popup menüsünü hazırlar.
     */
    /**
     * Müşteri tablosu için sağ tıklama menüsünü hazırlar.
     * Menüde müşteri güncelleme ve silme işlemleri yer alır.
     */
    private void loadCustomerPopupMenu() {

        this.tbl_customer.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int selectedRow = tbl_customer.rowAtPoint(e.getPoint());
                    tbl_customer.setRowSelectionAllowed(true);
                    tbl_customer.setRowSelectionInterval(selectedRow, selectedRow);
                }
            }
        });

        this.popup_customer.add("Güncelle").addActionListener(e -> {
            int selectedId = Integer.parseInt(this.tbl_customer.getValueAt(this.tbl_customer.getSelectedRow(), 0).toString());
            Customer editedCustomer = this.customerController.getById(selectedId);
            CustomerUI customerUI = new CustomerUI(editedCustomer);
            customerUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent windowEvent) {
                    loadCustomerTable(null);
                    loadBasketCustomerCombo();
                }
            });
        });
        
        this.popup_customer.add("Sil").addActionListener(e -> {
            int selectedId = Integer.parseInt(this.tbl_customer.getValueAt(this.tbl_customer.getSelectedRow(), 0).toString());
            if (Helper.confirm("sure")){
                if (this.customerController.delete(selectedId)) {
                    Helper.showMessage("done");
                    loadCustomerTable(null);
                    loadBasketCustomerCombo();
                } else {
                    Helper.showMessage("error");
                }
            }
            
        });

        this.tbl_customer.setComponentPopupMenu(this.popup_customer);
    }

    /**
     * Müşteri tablosunu doldurur.
     *
     * @param customers Önceden filtrelenmiş müşteri listesi, null ise tüm müşteriler gösterilir.
     */
    private void loadCustomerTable(ArrayList<Customer> customers) {
        Object[] colNames = {"ID", "Ad Soyad", "Müşteri Tipi", "Telefon", "Email", "Adres"};

        if (customers == null) {
            customers = this.customerController.findAll();
        }

        DefaultTableModel clearModel = (DefaultTableModel) this.tbl_customer.getModel();
        clearModel.setRowCount(0);

        this.tmdl_customer.setColumnIdentifiers(colNames);

        for (Customer customer : customers) {
            Object[] rowObjects = {customer.getId(), customer.getName(), customer.getType(), customer.getPhone(), customer.getMail(), customer.getAddress()};
            this.tmdl_customer.addRow(rowObjects);
        }
        this.tbl_customer.setModel(this.tmdl_customer);
        this.tbl_customer.getTableHeader().setReorderingAllowed(false);
        this.tbl_customer.getColumnModel().getColumn(0).setMaxWidth(50);
        // Tabloyu etkin bırak böylece sağ tıklama popup menüsü çalışabilsin.
        this.tbl_customer.setEnabled(true);
    }


}
