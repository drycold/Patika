import java.util.Scanner;

public class Atm {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        String userName, password;
        int secim, right = 3;
        double balance = 1500.0;
        
        while (right > 0) {

            // Kullanıcıdan kullanıcı adı ve şifre bilgilerini al
            Scanner inp = new Scanner(System.in);
            System.out.print("Kullanıcı adınızı giriniz: ");
            userName = inp.nextLine();
            System.out.print("Parolanızı giriniz: ");
            password = inp.nextLine();

            // Kullanıcı adı ve şifre doğrulama
            if (userName.equals("patika") && password.equals("dev123")) {
                System.out.println("Merhaba, Kodluyoruz Bankasına Hoşgeldiniz!");

                // Kullanıcı doğru giriş yaptıktan sonra işlemler menüsünü göster
                do {
                    System.out.println("1- Para yatırma\n" +
                                       "2- Para çekme\n" +
                                       "3- Bakiye sorgulama\n" +
                                       "4- Çıkış yap");
                    System.out.print("Lütfen yapmak istediğiniz işlemi seçiniz: ");
                    secim = inp.nextInt();

                    // İşlem seçimine göre ilgili işlemi gerçekleştir
                    switch (secim) {
                        case 1:
                            System.out.print("Yatırmak istediğiniz tutarı giriniz: ");
                            double yatirilanTutar = inp.nextDouble();
                            balance += yatirilanTutar;
                            break;
                        case 2:
                            System.out.print("Çekmek istediğiniz tutarı giriniz: ");
                            double cekilenTutar = inp.nextDouble();
                            if (cekilenTutar > balance) {
                                System.out.println("Yetersiz bakiye!");
                            } else {
                                balance -= cekilenTutar;
                            }
                            break;
                        case 3:
                            System.out.println("Bakiyeniz: " + balance);
                            break;
                        case 4:
                            System.out.println("Çıkış yapılıyor...");
                            break;
                        default:
                            System.out.println("Geçersiz seçim, lütfen tekrar deneyiniz.");
                    }
                } while (secim != 4);
                break;

            // Kullanıcı adı veya şifre yanlış ise kalan hakkı azalt ve mesaj göster
            } else {
                right--;
                System.out.println("Hatalı kullanıcı adı veya şifre! Kalan hakkınız: " + right);
            }
            
        }
    }
}
