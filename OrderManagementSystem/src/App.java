
import business.UserController;
import core.Helper;
import entity.User;
import view.DashboardUI;
import view.LoginUI;

/**
 * Uygulamanın giriş sınıfı. Bu sınıfın main() metodu programın başlangıç noktasını sağlar.
 * Bu örnekte oturum açma arayüzü doğrudan bir kullanıcıyla başlatılıyor.
 */
public class App {

    /**
     * Uygulamayı başlatır.
     *
     * @param args Komut satırı argümanları, bu örnekte kullanılmıyor.
     * @throws Exception Tema ayarları veya UI oluşturma sırasında oluşabilecek genel hatalar için.
     */
    public static void main(String[] args) throws Exception {
        // Uygulama için Nimbus görünümünü uygular.
        Helper.setThemes();

        LoginUI loginUI = new LoginUI();
    }
}
