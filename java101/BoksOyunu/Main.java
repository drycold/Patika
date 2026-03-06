public class Main {
    public static void main(String[] args) {

        Fighter ali = new Fighter("Ali", 10, 100, 90, 15);
        Fighter veli = new Fighter("Veli", 20, 85, 85, 10);

        Ring ring = new Ring(ali, veli, 80, 100);
        ring.run();
    }
}
