import java.util.Scanner;

public class UsluSayiHesaplama {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int sayi, us, sonuc = 1;

        // Kullanıcıdan sayı ve üs bilgilerini al
        Scanner inp = new Scanner(System.in);
        System.out.print("Sayı giriniz: ");
        sayi = inp.nextInt();
        System.out.print("Üs giriniz: ");
        us = inp.nextInt();

        // Üs alma işlemi
        for (int i = 1; i <= us; i++) {
            sonuc *= sayi;
        }

        // Sonucu ekrana yazdır
        System.out.println(sayi + " üzeri " + us + " = " + sonuc);
    }
}
