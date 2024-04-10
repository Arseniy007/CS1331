public class Dog extends Pet {
    // Instance variables
    double droolRate;
    private static final double defaultDroolRate = 5.0;

    // Constructors
    public Dog(String name, double health, int painLevel, double droolRate) {
        super(name, health, painLevel);
        if (droolRate <= 0) {
            droolRate = 0.5;
        }
        this.droolRate = droolRate;
    }

    public Dog(String name, double health, int painLevel) {
        this(name, health, painLevel, defaultDroolRate);
    }

    // Methods
    public double getDroolRate() {
        return this.droolRate;
    }

    public int treat() {
        int minutesToHeal;
        if (this.droolRate < 3.5) {
            minutesToHeal = (int) Math.ceil((this.getPainLevel() * 2) / this.getHealth());
        }
        else if (this.droolRate > 7.5) {
            minutesToHeal = (int) Math.ceil(this.getPainLevel() / (this.getHealth() * 2));
        }
        else {
            minutesToHeal = (int) Math.ceil(this.getPainLevel() / this.getHealth());
        }
        super.heal();
        return minutesToHeal;
    }

    public void speak() {
        super.speak();
        String text = "bark ".repeat(this.painLevel).trim();
        if (this.painLevel > 5) {
            text = text.toUpperCase();
        }
        System.out.println(text);
    }

    public boolean equals(Object o) {
        if (o instanceof Dog) {
            Dog someDog = (Dog) o;
            boolean equalDroolRates = this.droolRate == someDog.getDroolRate();
            if (equalDroolRates && super.equals(someDog)) {
                return true;
            }
        }
        return false;
    }

}
