import java.util.Scanner;

public class SinifGecmeKontrol {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int mat, fizik, turkce, kimya, tarih;

        // Kullanıcıdan notları al
        Scanner inp = new Scanner(System.in);

        System.out.print("Matematik notunuzu giriniz: ");
        mat = inp.nextInt();

        System.out.print("Fizik notunuzu giriniz: ");
        fizik = inp.nextInt();

        System.out.print("Türkçe notunuzu giriniz: ");
        turkce = inp.nextInt();

        System.out.print("Kimya notunuzu giriniz: ");
        kimya = inp.nextInt();

        System.out.print("Tarih notunuzu giriniz: ");
        tarih = inp.nextInt();

        // Ortalama hesapla
        double ortalama = (mat + fizik + kimya + turkce + tarih) / 5.0;

        // Sınıf geçme durumunu kontrol et ve sonucu ekrana yazdır
        if (ortalama >= 55) {
            System.out.println("Tebrikler! Sınıfı geçtiniz. Ortalama: " + ortalama);
        } else {
            System.out.println("Maalesef sınıfta kaldınız. Ortalama: " + ortalama);
        }
    }
}
