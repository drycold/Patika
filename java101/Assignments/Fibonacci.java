import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        
        int n1 = 0, n2 = 1, toplam, adet, sayac = 0;

        // Fibonacci dizisinin eleman sayısını kullanıcıdan al
        Scanner scanner = new Scanner(System.in);
        System.out.print("Fibonacci dizisinin eleman sayısını girin: ");
        adet = scanner.nextInt();
        
        // Fibonacci dizisini oluşturma ve ekrana yazdırma
        System.out.println("Fibonacci dizisi: ");
        
        while (sayac < adet) { 
            
            System.out.println(n1 + " + " + n2 + " = " + (n1 + n2));

            toplam = n1 + n2;
            n1 = n2;
            n2 = toplam;

            sayac++;
        }
    }
}
