public class NotOrtalamasıHesaplama {
    public static void main(String[] args) {

        // Değişkenleri tanımla
        int mat, fizik, kimya, turkce, tarih, muzik;

        // Kullanıcıdan notları al
        java.util.Scanner inp = new java.util.Scanner(System.in);

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
        double ortalama = (mat + fizik + kimya + turkce + tarih + muzik) / 6.0;

        // Sonucu belirle
        String sonuc = ortalama >= 60 ? "Sınıfı Geçti" : "Sınıfta Kaldı";

        // Sonuçları yazdır
        System.out.println("Ortalamanız : " + ortalama);
        System.out.println(sonuc);
    }
}
