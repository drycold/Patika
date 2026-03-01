import java.util.Scanner;

public class CinZodyagiHesaplama {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int dogumYili;

        // Kullanıcıdan doğum yılı bilgisini al
        Scanner inp = new Scanner(System.in);
        System.out.print("Doğum yılınızı giriniz: ");
        dogumYili = inp.nextInt();

        // Doğum yılına göre Çin Zodyağı burcunu belirle ve ekrana yazdır
        switch (dogumYili%12) {
            case 0:
                System.out.println("Çin Zodyağı Burcu: Maymun");
                break;
            case 1:
                System.out.println("Çin Zodyağı Burcu: Horoz");
                break;
            case 2:
                System.out.println("Çin Zodyağı Burcu: Köpek");
                break;
            case 3:
                System.out.println("Çin Zodyağı Burcu: Domuz");
                break;
            case 4:
                System.out.println("Çin Zodyağı Burcu: Fare");
                break;
            case 5:
                System.out.println("Çin Zodyağı Burcu: Öküz");
                break;
            case 6:
                System.out.println("Çin Zodyağı Burcu: Kaplan");
                break;
            case 7:
                System.out.println("Çin Zodyağı Burcu: Tavşan");
                break;
            case 8:
                System.out.println("Çin Zodyağı Burcu: Ejderha");
                break;
            case 9:
                System.out.println("Çin Zodyağı Burcu: Yılan");
                break;
            case 10:
                System.out.println("Çin Zodyağı Burcu: At");
                break;
            case 11:
                System.out.println("Çin Zodyağı Burcu: Koyun");
                break;
        }
    }
}
