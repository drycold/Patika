
public class AsalSayilar {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int n = 100;

        // Asal sayıları bulma ve ekrana yazdırma
        System.out.println("Asal sayılar: ");
        for (int i = 2; i <= n; i++) {
            boolean asal = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    asal = false;
                    break;
                }
            }
            if (asal) {
                System.out.print(i + " ");
            }
        }
    }
}
