import java.util.Scanner;

public class Kuvvet {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int sayi;

        // Kullanıcıdan sayı bilgisini al
        Scanner inp = new Scanner(System.in);
        System.out.print("Sayı giriniz: ");
        sayi = inp.nextInt();
        
        // 4'ün ve 5'in kuvvetlerini ekrana yazdır
        System.out.print("4'ün kuvvetleri: ");
        for (int i = 4; i <= sayi; i*=4) {
            System.out.print(i + " ");
        }

        System.out.print("\n5'in kuvvetleri: ");
        for (int j = 5; j <= sayi; j*=5) {
            System.out.print(j + " ");
        }
    }
}
