import java.util.Scanner;

public class EbobveEkok {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int n1, n2, ebob, ekok;

        // Kullanıcıdan iki sayı bilgisini al
        Scanner inp = new Scanner(System.in);
        System.out.print("Birinci sayıyı giriniz: ");
        n1 = inp.nextInt();
        System.out.print("İkinci sayıyı giriniz: ");
        n2 = inp.nextInt();

        // EBOB ve EKOK hesaplaması için geçici değişkenler oluştur
        int a = n1;
        int b = n2;

        // EBOB hesaplama
        while (b != 0) {
            int kalan = a % b;
            a = b;
            b = kalan;
        }

        ebob = a;

        // EKOK hesaplama
        ekok = (n1 * n2) / ebob;

        // Sonuçları ekrana yazdır
        System.out.println("EBOB: " + ebob);
        System.out.println("EKOK: " + ekok);
    }
}
