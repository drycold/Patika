import java.util.Scanner;

public class SicakligaGoreEtkinlik {
    public static void main(String[] args) {
        
        // Değişken tanımla
        int sicaklik;

        // Kullanıcıdan sıcaklık bilgisini al
        Scanner inp = new Scanner(System.in);
        System.out.print("Lütfen hava sıcaklığını giriniz: ");
        sicaklik = inp.nextInt();

        // Sıcaklığa göre etkinlik önerisi yap
        if (sicaklik < 5) {
            System.out.println("Hava çok soğuk, kayak yapabilirsiniz.");
        } else if (sicaklik >= 5 && sicaklik < 15) {
            System.out.println("Hava serin, sinemaya gidebilirsiniz.");
        } else if (sicaklik >= 15 && sicaklik < 25) {
            System.out.println("Hava güzel, pikniğe gidebilirsiniz.");
        } else {
            System.out.println("Hava çok sıcak, yüzmeye gidebilirsiniz.");
        }
    }
}
