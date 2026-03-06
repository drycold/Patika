public class Ring {

    Fighter fighter1;
    Fighter fighter2;
    int minWeight;
    int maxWeight;

    public Ring(Fighter fighter1, Fighter fighter2, int minWeight, int maxWeight) {
        this.fighter1 = fighter1;
        this.fighter2 = fighter2;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    public void run() {
        if (checkWeight()) {
            while (fighter1.health > 0 && fighter2.health > 0) {
                System.out.println("======== YENİ ROUND ===========");
                if (Math.random() < 0.5) {
                    fighter2.health = fighter1.hit(fighter2);
                    if (isWin()) {
                        break;
                    }
                } else {
                    fighter1.health = fighter2.hit(fighter1);
                    if (isWin()) {
                        break;
                    }
                }
                
                System.out.println(fighter1.name + " Kalan Can \t:" + fighter1.health);
                System.out.println(fighter2.name + " Kalan Can \t:" + fighter2.health);
            }

        } else {
            System.out.println("Sporcuların ağırlıkları uyuşmuyor.");
        }
    }

    public boolean checkWeight() {
        return (fighter1.weight >= minWeight && fighter1.weight <= maxWeight) &&
                (fighter2.weight >= minWeight && fighter2.weight <= maxWeight);
    }

    public boolean isWin() {
        if (fighter1.health == 0) {
            System.out.println("Maçı Kazanan : " + fighter2.name);
            return true;
        } else if (fighter2.health == 0) {
            System.out.println("Maçı Kazanan : " + fighter1.name);
            return true;
        }
        return false;
    }

    public void printScore() {
        System.out.println("------------");
        System.out.println(fighter1.name + " Kalan Can \t:" + fighter1.health);
        System.out.println(fighter2.name + " Kalan Can \t:" + fighter2.health);
    }
}
