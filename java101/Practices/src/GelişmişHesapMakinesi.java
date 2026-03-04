import java.util.Scanner;

public class GelişmişHesapMakinesi {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int secim;

        // Kullanıcıdan işlem seçimini al
        Scanner scanner = new Scanner(System.in);
        System.out.print("""
                1- Toplama
                2- Çıkarma
                3- Çarpma
                4- Bölme
                5- Üs Alma
                6- Faktöriyel
                7- Mod Alma
                8- Dikdörtgen Alan ve Çevre Hesabı\n
                Seçiminizi yapın: """);
        secim = scanner.nextInt();

        // Seçilen işleme göre ilgili metodu çağır
        switch (secim) {
            case 1 -> toplama();
            case 2 -> cikarma();
            case 3 -> carpma();
            case 4 -> bolme();
            case 5 -> usAlma();
            case 6 -> faktoriyel();
            case 7 -> modAlma();
            case 8 -> dikdortgenHesap();
            default -> System.out.println("Geçersiz seçim. Lütfen 1-8 arasında bir sayı girin.");
        }
    }

    // Toplama, çıkarma, çarpma, bölme, üs alma, faktöriyel, mod alma ve dikdörtgen hesaplama işlemleri için metodlar
    static void toplama() {
        int sayi1, sayi2;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Birinci sayıyı girin: ");
        sayi1 = scanner.nextInt();
        System.out.print("İkinci sayıyı girin: ");
        sayi2 = scanner.nextInt();
        System.out.println("Sonuç: " + (sayi1 + sayi2));
    }

    static void cikarma() {
        int sayi1, sayi2;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Birinci sayıyı girin: ");
        sayi1 = scanner.nextInt();
        System.out.print("İkinci sayıyı girin: ");
        sayi2 = scanner.nextInt();
        System.out.println("Sonuç: " + (sayi1 - sayi2));
    }

    static void carpma() {
        double sayi1, sayi2;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Birinci sayıyı girin: ");
        sayi1 = scanner.nextDouble();
        System.out.print("İkinci sayıyı girin: ");
        sayi2 = scanner.nextDouble();
        System.out.println("Sonuç: " + (sayi1 * sayi2));
    }

    static void bolme() {
        double sayi1, sayi2;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Birinci sayıyı girin: ");
        sayi1 = scanner.nextDouble();
        System.out.print("İkinci sayıyı girin: ");
        sayi2 = scanner.nextDouble();
        if (sayi2 != 0) {
            System.out.println("Sonuç: " + (sayi1 / sayi2));
        } else {
            System.out.println("Hata: Bir sayı sıfıra bölünemez.");
        }
    }

    static void usAlma() {
        int sayi, us;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Taban sayıyı girin: ");
        sayi = scanner.nextInt();
        System.out.print("Üs değerini girin: ");
        us = scanner.nextInt();
        System.out.println("Sonuç: " + Math.pow(sayi, us));
    }

    static void faktoriyel() {
        int sayi;
        long faktoriyel = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Faktöriyelini hesaplamak istediğiniz sayıyı girin: ");
        sayi = scanner.nextInt();
        for (int i = 1; i <= sayi; i++) {
            faktoriyel *= i;
        }
        System.out.println("Sonuç: " + faktoriyel);
    }

    static void modAlma() {
        int sayi1, sayi2;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Birinci sayıyı girin: ");
        sayi1 = scanner.nextInt();
        System.out.print("İkinci sayıyı girin: ");
        sayi2 = scanner.nextInt();
        if (sayi2 != 0) {
            System.out.println("Sonuç: " + (sayi1 % sayi2));
        } else {
            System.out.println("Hata: Bir sayı sıfıra bölünemez.");
        }
    }

    static void dikdortgenHesap() {
        int uzunKenar, kisaKenar;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Dikdörtgenin uzun kenarını girin: ");
        uzunKenar = scanner.nextInt();
        System.out.print("Dikdörtgenin kısa kenarını girin: ");
        kisaKenar = scanner.nextInt();
        int alan = uzunKenar * kisaKenar;
        int cevre = 2 * (uzunKenar + kisaKenar);
        System.out.println("Dikdörtgenin Alanı: " + alan);
        System.out.println("Dikdörtgenin Çevresi: " + cevre);
    }
}
