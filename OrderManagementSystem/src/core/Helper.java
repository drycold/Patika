package core;

import javax.swing.*;

/**
 * Uygulama genelinde kullanılan yardımcı metotları içeren sınıf.
 * Bu sınıf, görünüm teması, giriş doğrulama, mesaj gösterme gibi ortak işlemleri sağlar.
 */
public class Helper {

    /**
     * Nimbus tema desteğini aktif hale getirir.
     * Java Swing ortamında daha modern bir görünüme geçiş yapar.
     */
    public static void setThemes() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace(System.err);
                }
                break;
            }
        }
    }

    /**
     * Bir text alanının boş olup olmadığını kontrol eder.
     *
     * @param field Kontrol edilecek JTextField.
     * @return Alan boşsa true, değilse false.
     */
    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }

    /**
     * Bir dizi text alanında en az bir alanın boş olup olmadığını kontrol eder.
     *
     * @param fields Kontrol edilecek JTextField dizisi.
     * @return Herhangi bir alan boşsa true, değilse false.
     */
    public static boolean isFieldListEmpty(JTextField[] fields){
        for (JTextField field : fields){
            if (isFieldEmpty(field)) return true;
        }

        return false;
    }

    /**
     * E-posta adresinin basit bir regex kontrolünü yapar.
     *
     * @param fld_mail E-posta adresi içeren JTextField.
     * @return Geçerliyse true, değilse false.
     */
    public static boolean isEmailValid(JTextField fld_mail){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return fld_mail.getText().matches(emailRegex);
    }

    /**
     * JOptionPane butonlarını Türkçe etiketlerle ayarlar.
     * Bu sayede kullanıcıya gösterilen diyaloğun düğmeleri "Tamam", "İptal" vb. olur.
     */
    public static void optionPaneDialogTR(){
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.cancelButtonText", "İptal");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
    }

    /**
     * Kullanıcıya bilgi mesajı gösterir.
     *
     * @param message Gösterilecek mesaj anahtarı veya metni.
     */
    public static void showMessage(String message){

        String msg;
        String title;

        optionPaneDialogTR();
        switch (message){
            case "fill":
                msg = "Lütfen tüm alanları doldurunuz";
                title = "Hata";
                break;
            case "done":
                msg = "İşlem başarılı";
                title = "Sonuç";
                break;
            case "error":
                msg = "Bir hata oluştu";
                title = "Hata";
                break;
            default:
                msg = message;
                title = "Mesaj";
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Kullanıcıdan onay alır.
     *
     * @param message Onay penceresi için mesaj anahtarı veya metni.
     * @return Kullanıcı "Evet" seçtiyse true, aksi halde false.
     */
    public static boolean confirm(String message){
        optionPaneDialogTR();
        String msg;

        if (message.equals("sure")){
            msg = "Bu işlemi gerçekleştirmek istediğinize emin misiniz?";
        } else {
            msg = message;
        }

        return JOptionPane.showConfirmDialog(null, msg, "Onay", JOptionPane.YES_NO_OPTION) == 0;
    }
}
