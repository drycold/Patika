import java.util.Scanner;

public class BurcBulucu {
    public static void main(String[] args) {
        
        // Değişkenleri tanımla
        int ay, gun;

        // Kullanıcıdan doğum tarihi bilgilerini al
        Scanner inp = new Scanner(System.in);

        System.out.print("Doğduğunuz ayı giriniz (1-12): ");
        ay = inp.nextInt();
        
        System.out.print("Doğduğunuz günü giriniz (1-31): ");
        gun = inp.nextInt();

        // Doğum tarihine göre burç belirle ve ekrana yazdır
        switch (ay) {
            case 1:
                if (gun >= 1 && gun <= 31) {
                    if (gun < 22) {
                        System.out.println("Oğlak burcusunuz.");
                    } else {
                        System.out.println("Kova burcusunuz.");
                    }
                } else {
                    System.out.println("Geçersiz gün girdiniz.");
                }    
                break;
            case 2:
                if (gun >= 1 && gun <= 29) {
                    if (gun < 20) {
                        System.out.println("Kova burcusunuz.");
                    } else {
                        System.out.println("Balık burcusunuz.");
                    }
                } else {
                    System.out.println("Geçersiz gün girdiniz.");
                }
                break;
            case 3:
                if (gun >= 1 && gun <= 31) {
                    if (gun < 21) {
                        System.out.println("Balık burcusunuz.");
                    } else {
                        System.out.println("Koç burcusunuz.");
                    }
                } else {
                    System.out.println("Geçersiz gün girdiniz.");
                }
                break;
            case 4:
                if (gun >= 1 && gun <= 30) {
                    if (gun < 21) {
                        System.out.println("Koç burcusunuz.");
                    } else {
                        System.out.println("Boğa burcusunuz.");
                    }
                } else {
                    System.out.println("Geçersiz gün girdiniz.");
                }
                break;
            case 5:
                if (gun >= 1 && gun <= 31) {
                    if (gun < 22) {
                        System.out.println("Boğa burcusunuz.");
                    } else {
                        System.out.println("İkizler burcusunuz.");
                    }
                } else {
                    System.out.println("Geçersiz gün girdiniz.");
                }
                break;
            case 6:
                if (gun >= 1 && gun <= 30) {
                    if (gun < 23) {
                        System.out.println("İkizler burcusunuz.");
                    } else {
                        System.out.println("Yengeç burcusunuz.");
                    }
                } else {
                    System.out.println("Geçersiz gün girdiniz.");
                }
                break;
            case 7:
                if (gun >= 1 && gun <= 31) {
                    if (gun < 23) {
                        System.out.println("Yengeç burcusunuz.");
                    } else {
                        System.out.println("Aslan burcusunuz.");
                    }
                } else {
                    System.out.println("Geçersiz gün girdiniz.");
                }
                break;
            case 8:
                if (gun >= 1 && gun <= 31) {
                    if (gun < 23) {
                        System.out.println("Aslan burcusunuz.");
                    } else {
                        System.out.println("Başak burcusunuz.");
                    }
                } else {
                    System.out.println("Geçersiz gün girdiniz.");
                }
                break;
            case 9:
                if (gun >= 1 && gun <= 30) {
                    if (gun < 23) {
                        System.out.println("Başak burcusunuz.");
                    } else {
                        System.out.println("Terazi burcusunuz.");
                    }
                } else {
                    System.out.println("Geçersiz gün girdiniz.");
                }
                break;
            case 10:
                if (gun >= 1 && gun <= 31) {
                    if (gun < 23) {
                        System.out.println("Terazi burcusunuz.");
                    } else {
                        System.out.println("Akrep burcusunuz.");
                    }
                } else {
                    System.out.println("Geçersiz gün girdiniz.");
                }
                break;
            case 11:
                if (gun >= 1 && gun <= 30) {
                    if (gun < 22) {
                        System.out.println("Akrep burcusunuz.");
                    } else {
                        System.out.println("Yay burcusunuz.");
                    }
                } else {
                    System.out.println("Geçersiz gün girdiniz.");
                }
                break;
            case 12:
                if (gun >= 1 && gun <= 31) {
                    if (gun < 22) {
                        System.out.println("Yay burcusunuz.");
                    } else {
                        System.out.println("Oğlak burcusunuz.");
                    }
                } else {
                    System.out.println("Geçersiz gün girdiniz.");
                }
                break;
            default:
                System.out.println("Geçersiz ay girdiniz.");
        }
    }
}
