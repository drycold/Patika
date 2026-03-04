import java.util.Scanner;

public class RecursiveAsalSayiBulma {
    public static void main(String[] args) {
        
        int sayi;

        // Kullanıcıdan bir sayı al
        Scanner scanner = new Scanner(System.in);
        System.out.print("Bir sayı girin: ");
        sayi = scanner.nextInt();

        // Asal sayı kontrolü yapma ve ekrana yazdırma
        if (isAsal(sayi, sayi - 1)) {
            System.out.println(sayi + " asal sayıdır.");
        } else {
            System.out.println(sayi + " asal sayı değildir.");
        }
    }

    // Asal sayı kontrolü için recursive metod
    public static boolean isAsal(int sayi, int bolen) {
        if (sayi < 2) {
            return false;
        }
        if (bolen == 1) {
            return true;
        }
        if (sayi % bolen == 0) {
            return false;
        }
        return isAsal(sayi, bolen - 1);
    }
}
