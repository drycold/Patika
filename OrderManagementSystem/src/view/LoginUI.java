package view;

import business.UserController;
import core.Helper;
import entity.User;
import java.awt.*;
import javax.swing.*;

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

    public LoginUI() {

        this.userController = new UserController();
        
        this.add(container);
        this.setTitle("Müşteri yönetim Sistemi");
        this.setSize(400, 400);

        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;

        this.setLocation(x, y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        this.btn_login.addActionListener(e -> {
            JTextField[] checkList = {this.fld_password, this.fld_mail};
            if (Helper.isFieldListEmpty(checkList)) {
                Helper.showMessage("fill");
            } else if (!Helper.isEmailValid(this.fld_mail)) {
                Helper.showMessage("Lütfen geçerli bir email adresi giriniz");
            } else {
                User user = this.userController.findByLogin(this.fld_mail.getText(), this.fld_password.getText());
                if (user == null) {
                    Helper.showMessage("Kullanıcı bulunamadı");
                } else {
                    this.dispose();
                    DashboardUI dashboardUI = new DashboardUI(user);
                }
            }
        });
    }

}
