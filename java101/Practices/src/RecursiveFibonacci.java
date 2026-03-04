import java.util.Scanner;

public class RecursiveFibonacci {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int n;

        // Kullanıcıdan Fibonacci dizisinin eleman sayısını al
        Scanner scanner = new Scanner(System.in);
        System.out.print("Fibonacci dizisinin eleman sayısını girin: ");
        n = scanner.nextInt();

        // Fibonacci dizisini oluşturma ve ekrana yazdırma
        System.out.println("Fibonacci dizisi: ");
        for (int i = 0; i < n; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }

    // Fibonacci sayısını hesaplayan özyinelemeli metod
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
