public class MatrisTranspozu {
    public static void main(String[] args) {

        // MAtrislerin tanımlanması
        int[][] arr = { { 1, 2, 3 }, { 4, 5, 6 } };
        int[][] trp = new int[arr[0].length][arr.length];

        // Matrisin traspozunun elde edilmesi
        for (int i = 0; i < trp.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                trp[i][j] = arr[j][i];
            }
        }

        // Sonucun yazdırma
        printMatrix("Matris: ", arr);
        printMatrix("Transpoze: ", trp);
    }

    // Sonucu yazdıran fonsiyon
    static void printMatrix(String title, int[][] matris) {
        System.out.println(title);
        for (int[] row : matris) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

    }
}
