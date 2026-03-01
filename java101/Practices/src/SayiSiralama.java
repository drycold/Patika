import java.util.Scanner;

public class SayiSiralama {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int a, b, c;

        // Kullanıcıdan sayıları al
        Scanner inp = new Scanner(System.in);

        System.out.print("Birinci sayıyı giriniz: ");
        a = inp.nextInt();

        System.out.print("İkinci sayıyı giriniz: ");
        b = inp.nextInt();

        System.out.print("Üçüncü sayıyı giriniz: ");
        c = inp.nextInt();

        // Sayıları sırala ve ekrana yazdır
        if (a < b && a < c) {
            if (b < c) {
                System.out.println("Sıralama: " + a + " < " + b + " < " + c);
            } else {
                System.out.println("Sıralama: " + a + " < " + c + " < " + b);
            }
        } else if (b < a && b < c) {
            if (a < c) {
                System.out.println("Sıralama: " + b + " < " + a + " < " + c);
            } else {
                System.out.println("Sıralama: " + b + " < " + c + " < " + a);
            }
        } else {
            if (a < b) {
                System.out.println("Sıralama: " + c + " < " + a + " < " + b);
            } else {
                System.out.println("Sıralama: " + c + " < " + b + " < " + a);
            }
        }
    }
}