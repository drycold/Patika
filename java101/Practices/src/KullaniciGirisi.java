import java.util.Scanner;

public class KullaniciGirisi {
    public static void main(String[] args) {
        
        String username, password;

        Scanner inp = new Scanner(System.in);

        System.out.print("Kullanıcı adınızı giriniz: ");
        username = inp.nextLine();

        System.out.print("Şifrenizi giriniz: ");
        password = inp.nextLine();

        if (username.equals("patika") && password.equals("java123")) {
            System.out.println("Giriş başarılı!");
        } else {
            System.out.println("Giriş başarısız! Şifrenizi sıfırlamak ister misiniz? (Evet/Hayır)");
            String resetChoice = inp.nextLine();

            if (resetChoice.equalsIgnoreCase("Evet")) {
                System.out.print("Yeni şifrenizi giriniz: ");
                String newPassword = inp.nextLine();

                if (newPassword.equals(password)) {
                    System.out.println("Yeni şifre eski şifreyle aynı olamaz. Lütfen farklı bir şifre giriniz.");
                } else {
                    password = newPassword;
                    System.out.println("Şifre başarıyla sıfırlandı!");
                }
            } else {
                System.out.println("Şifre sıfırlama işlemi iptal edildi.");
            }
        }
    }
}
