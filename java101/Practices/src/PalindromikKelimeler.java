import java.util.Scanner;

public class PalindromikKelimeler {
    public static void main(String[] args) {

        Scanner inp = new Scanner(System.in);
        System.out.println("Kelime giriniz : ");
        String kelime = inp.next();

        System.out.println(isPalindrome(kelime));
    }

    static boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
