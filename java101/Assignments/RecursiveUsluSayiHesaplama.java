import java.util.Scanner;

public class RecursiveUsluSayiHesaplama {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int taban, us;

        // Kullanıcıdan taban ve üs değerlerini al
        Scanner scanner = new Scanner(System.in);
        System.out.print("Taban değerini girin: ");
        taban = scanner.nextInt();
        System.out.print("Üs değerini girin: ");
        us = scanner.nextInt();

        // Üslü sayıyı hesaplama ve ekrana yazdırma
        int sonuc = usluSayiHesapla(taban, us);
        System.out.println(taban + " üzeri " + us + " = " + sonuc);
    }

    // Üslü sayıyı hesaplayan recursive metod
    static int usluSayiHesapla(int taban, int us) {
        if (us == 0) {
            return 1;
        } else {
            return taban * usluSayiHesapla(taban, us - 1);
        }
    }
}
