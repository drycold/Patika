package view;

import business.UserController;
import core.Helper;
import entity.User;
import java.awt.*;
import javax.swing.*;

/**
 * Uygulama için giriş ekranını sağlayan JFrame sınıfı.
 * Kullanıcı e-posta ve parola ile giriş yapar.
 */
public class LoginUI extends JFrame {

    private JPanel container;
    private JPanel pnl_top;
    private JLabel lbl_title;
    private JPanel pnl_bottom;
    private JTextField fld_mail;
    private JTextField fld_password;
    private JLabel lbl_mail;
    private JLabel lbl_password;
    private JButton btn_login;
    private UserController userController;

    /**
     * LoginUI yapıcı metodu. Bileşenleri ayarlar ve oturum açma düğmesine dinleyici bağlar.
     */
    public LoginUI() {

        this.userController = new UserController();

        // Formu penceresine ekler ve temel pencere ayarlarını yapar.
        this.add(container);
        this.setTitle("Müşteri yönetim Sistemi");
        this.setSize(400, 400);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // Giriş butonuna tıklanıldığında çalışacak işlem.
        this.btn_login.addActionListener(e -> {
            JTextField[] checkList = {this.fld_password, this.fld_mail};

            // Zorunlu alanların dolu olup olmadığını kontrol eder.
            if (Helper.isFieldListEmpty(checkList)) {
                Helper.showMessage("fill");
            } else if (!Helper.isEmailValid(this.fld_mail)) {
                Helper.showMessage("Lütfen geçerli bir email adresi giriniz");
            } else {
                // Kullanıcıyı veritabanında arar.
                User user = this.userController.findByLogin(this.fld_mail.getText(), this.fld_password.getText());
                if (user == null) {
                    Helper.showMessage("Kullanıcı bulunamadı");
                } else {
                    // Giriş başarılıysa giriş ekranını kapatır ve paneli açar.
                    this.dispose();
                    new DashboardUI(user);
                }
            }
        });
    }

}
