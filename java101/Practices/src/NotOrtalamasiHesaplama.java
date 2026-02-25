
import java.util.Scanner;

public class NotOrtalamasiHesaplama {
    public static void main(String[] args) {

        // Değişkenleri tanımla
        int mat, fizik, kimya, turkce, tarih, muzik;

        // Ortalama ve sonuç değişkenlerini tanımla
        double ortalama;
        String sonuc;

        // Kullanıcıdan notları al
        Scanner inp = new Scanner(System.in);

        System.out.print("Matematik Notunuz : ");
        mat = inp.nextInt();

        System.out.print("Fizik Notunuz : ");
        fizik = inp.nextInt();

        System.out.print("Kimya Notunuz : ");
        kimya = inp.nextInt();

        System.out.print("Türkçe Notunuz : ");
        turkce = inp.nextInt();

        System.out.print("Tarih Notunuz : ");
        tarih = inp.nextInt();

        System.out.print("Müzik Notunuz : ");
        muzik = inp.nextInt();

        // Ortalama hesapla
        ortalama = (mat + fizik + kimya + turkce + tarih + muzik) / 6.0;

        // Sonucu belirle
        sonuc = ortalama >= 60 ? "Sınıfı Geçti" : "Sınıfta Kaldı";
        

        // Sonuçları yazdır
        System.out.println("Ortalamanız : " + ortalama);
        System.out.println(sonuc);
    }
}
