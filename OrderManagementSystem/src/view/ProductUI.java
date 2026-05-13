package view;

import business.ProductController;
import core.Helper;
import entity.Product;
import java.awt.*;
import javax.swing.*;

/**
 * Ürün ekleme ve düzenleme arayüzü
 * Ürün bilgilerini girmek ve güncellemek için kullanılır
 */
public class ProductUI extends JFrame{
    private JPanel container;
    private JTextField fld_product_name;
    private JTextField fld_product_code;
    private JTextField fld_product_price;
    private JTextField fld_product_stock;
    private JButton btn_product;
    private JLabel lbl_title;
    private JLabel lbl_product_name;
    private JLabel lbl_product_code;
    private JLabel lbl_product_price;
    private JLabel lbl_product_stock;
    private Product product;
    private ProductController productController;

    /**
     * Ürün arayüzünü başlatır
     * @param product Düzenlenecek ürün (yeni ürün için id=0)
     */
    public ProductUI(Product product){
        this.product = product;
        this.productController = new ProductController();

        this.add(container);
        this.setTitle("Ürün Yönetim Sistemi");
        this.setSize(300, 350);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x, y);
        this.setVisible(true);

        // Ürün ekleme veya düzenleme moduna göre başlık ve alanları doldur
        if(this.product.getId() == 0){
            this.lbl_title.setText("Ürün Ekle");
        } else {
            this.lbl_title.setText("Ürün Düzenle");
            this.fld_product_name.setText(this.product.getName());
            this.fld_product_code.setText(this.product.getCode());
            this.fld_product_price.setText(String.valueOf(this.product.getPrice()));
            this.fld_product_stock.setText(String.valueOf(this.product.getStock()));
        }

        // Kaydet/güncelle butonu için olay dinleyicisi
        btn_product.addActionListener(e -> {
            JTextField[] CheckList = {fld_product_name, fld_product_code, fld_product_price, fld_product_stock};

            if(Helper.isFieldListEmpty(CheckList)) {
                Helper.showMessage("fill");
            } else {
                this.product.setName(fld_product_name.getText());
                this.product.setCode(fld_product_code.getText());
                this.product.setPrice(Integer.parseInt(fld_product_price.getText()));
                this.product.setStock(Integer.parseInt(fld_product_stock.getText()));

                boolean result;
                if(this.product.getId() == 0){
                    result = this.productController.save(this.product);
                } else {
                    result = this.productController.update(this.product);
                }

                if(result){
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }
}
