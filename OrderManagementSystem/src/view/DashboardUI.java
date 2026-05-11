package view;

import business.CustomerController;
import core.Helper;
import entity.Customer;
import entity.User;
import java.awt.*;
import java.awt.event.*;
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
    private User user;
    private final CustomerController customerController;
    private final DefaultTableModel tmdl_customer = new DefaultTableModel();
    private final JPopupMenu popup_customer = new JPopupMenu();

    /**
     * Dashboard UI yapıcı metodu.
     *
     * @param user Giriş yapan kullanıcı nesnesi.
     */
    public DashboardUI(User user) {
        this.user = user;
        this.customerController = new CustomerController();
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

        // Tabloyu doldurur ve popup menu ile yeni müşteri ekleme işlemini hazırlar.
        loadCustomerTable(null);
        loadCustomerPopupMenu();
        loadCustomerButtonEvent();
        this.cmb_f_customer_type.setModel(new DefaultComboBoxModel<>(Customer.TYPE.values()));
        this.cmb_f_customer_type.setSelectedItem(null);

    }

    /**
     * Yeni müşteri ekleme butonuna tıklama işlemini ayarlar.
     */
    private void loadCustomerButtonEvent() {
        this.btn_customer_new.addActionListener(e -> {
            CustomerUI customerUI = new CustomerUI(new Customer());
            customerUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent windowEvent) {
                    // Müşteri ekleme penceresi kapandığında tabloyu yeniler.
                    loadCustomerTable(null);
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
                }
            });
        });
        
        this.popup_customer.add("Sil").addActionListener(e -> {
            int selectedId = Integer.parseInt(this.tbl_customer.getValueAt(this.tbl_customer.getSelectedRow(), 0).toString());
            if (Helper.confirm("sure")){
                if (this.customerController.delete(selectedId)) {
                    Helper.showMessage("done");
                    loadCustomerTable(null);
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
