import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rnd = new Random();
        boolean check = true;
        String[][] mineMatris;
        int placedMines = 0;

        while (check) {
            System.out.println("Oyun matrisinin satır ve sütun sayılarını giriniz: ");
            int row = input.nextInt();
            int col = input.nextInt();
            if (row >= 2 && col >= 2) {
                mineMatris = new String[row][col];
                for (int i = 0; i < mineMatris.length; i++) {
                    for (int j = 0; j < mineMatris[i].length; j++) {
                        mineMatris[i][j] = "- ";
                    }
                }
                int mine = ((row * col) / 4);
                // aynı mayının üstüne yerleştirilmesini önlemek için
                while (placedMines < mine) {
                        int rndRow = rnd.nextInt(row);
                        int rndCol = rnd.nextInt(col);
                        if (!mineMatris[rndRow][rndCol].equals("* ")) {
                            mineMatris[rndRow][rndCol] = "* ";
                            placedMines++;
                        }
                }

                check = false;
                System.out.println("Mayınların Konumu");
                System.out.println("===========================");
                printMatris(mineMatris);
                startGame(mineMatris, input);
            } else {
                System.out.println("Satır ve sütun sayısı 2'den büyük olmalılıdır.");
            }

        }

    }

    static void printMatris(String[][] matris) {
        for (int i = 0; i < matris.length; i++) {
            for (int j = 0; j < matris[0].length; j++) {
                if (matris[i][j] == null) {
                    matris[i][j] = "- ";
                }
                System.out.print(matris[i][j]);
            }
            System.out.println();
        }

    }

    static boolean isContain(String[] coordinates, String val) {
        for (String s : coordinates) {
            if (s.equals(val)) {
                return true;
            }
        }
        return false;
    }

    static void startGame(String[][] mineMatris, Scanner input) {
        int slcRow;
        int slcCol;
        boolean checkCrdnt = true;
        String[][] gameMatris = new String[mineMatris.length][mineMatris[0].length];

        String[] coordinates = new String[0]; // Başlangıçta boş
        int index = 0;

        System.out.println("Mayın Tarlası Oyuna Hoşgeldiniz !");
        printMatris(gameMatris);

        while (checkCrdnt) {
            System.out.print("Satır Giriniz : ");
            slcRow = input.nextInt();
            System.out.print("Sütun Giriniz : ");
            slcCol = input.nextInt();
            String crd = slcRow + "" + slcCol;
            System.out.println("===========================");
            if (!isContain(coordinates, crd)) {
                if ((slcRow > 0 && slcCol > 0) && (slcRow <= gameMatris.length && slcCol <= gameMatris[0].length)) {
                    // Koşul doğruysa yapılacak işlemler
                    coordinates = Arrays.copyOf(coordinates, coordinates.length + 1);
                    coordinates[index] = crd;
                    index++;

                    if (!playGame(gameMatris, mineMatris, slcRow, slcCol)) {
                        System.out.println("Game Over!! ===========================");
                        checkCrdnt = false; // Mayına bastı, oyun bitti.
                    } else {

                        for (int i = 0; i < gameMatris.length; i++) {
                            for (int j = 0; j < gameMatris[0].length; j++) {
                                if (!mineMatris[i][j].equals("* ")&& gameMatris[i][j].equals("- ")) {
                                    checkCrdnt = true; // Hala açılmamış hücre var, oyun devam etmeli
                                    printMatris(gameMatris);
                                    break; // İç döngüyü kır
                                }
                                else {
                                      checkCrdnt=false;
                                }
                            }
                            if (checkCrdnt) break; // Eğer "-" bulunduysa dış döngüyü de kır
                        }
                    
                        if (!checkCrdnt) { // Eğer hiç "-" kalmadıysa kazanmış demektir
                        for (int i = 0; i < mineMatris.length; i++) {
                            for (int j = 0; j < mineMatris[0].length; j++) {
                                if (mineMatris[i][j] != "* ") {
                                    mineMatris[i][j] = calculateDangerCount(mineMatris, i, j) + " ";
                                }
            
                            }
                        }

                            printMatris(mineMatris);
                            System.out.println("TEBRİKLER!! ===========================");
                        }
                    }
                    
                } else {
                    System.out.println("Koordinat Bulunamadı! ");
                }

            } else {
                System.out.println("Bu koordinat daha önce seçildi, başka bir koordinat girin");
            }

        }

    }

    static String calculateDangerCount(String[][] mineMatris, int slcRowNew, int slcColNew) {
        int danger = 0;
        for (int a = -1; a < 2; a++) {
            for (int b = -1; b < 2; b++) {
                if ((slcRowNew + a >= 0 && slcRowNew + a < mineMatris.length) && // Satır sınırları içinde
                        (slcColNew + b >= 0 && slcColNew + b < mineMatris[0].length)) { // Sütun sınırları
                    if (mineMatris[slcRowNew + a][slcColNew + b].equals("* ")) {
                        danger++; // Mayın varsa danger sayısını artır
                    }
                }

            }
        }
        return String.valueOf(danger);
    }

    static boolean playGame(String[][] gameMatris, String[][] mineMatris, int slcRow, int slcCol) {
        int slcRowNew = slcRow - 1;
        int slcColNew = slcCol - 1;

        if (mineMatris[slcRowNew][slcColNew].equals("* ")) {

            for (int i = 0; i < mineMatris.length; i++) {
                for (int j = 0; j < mineMatris[0].length; j++) {
                    if (mineMatris[i][j] != "* ") {
                        mineMatris[i][j] = calculateDangerCount(mineMatris, i, j) + " ";
                    }

                }
            }
            printMatris(mineMatris);
            return false; // Mayına basarsan oyun biter!

        } else {

            /*
             * slcrow=-1
             * 
             * mineMatris[slcRow-1][slcCol-1] //Sol üst çapraz
             * mineMatris[slcRow-1][slcCol] //üst
             * mineMatris[slcRow-1][slcCol+1] //Sağ üst çapraz
             * 
             * slcrow=1
             * mineMatris[slcRow+1][slcCol-1] //Sol alt çapraz
             * 
             * mineMatris[slcRow+1][slcCol] //alt
             * mineMatris[slcRow+1][slcCol+1] //Sağ alt çapraz
             * 
             * 
             * slcrow=0
             * mineMatris[slcRow][slcCol-1] //sol
             * mineMatris[slcRow][slcCol+1] //sağ
             */

            gameMatris[slcRowNew][slcColNew] = calculateDangerCount(mineMatris, slcRowNew, slcColNew) + " ";

            return true;
        }
    }

}