public class Cat extends Pet {
    // Instance variables
    int miceCaught;
    private static final int defaultMiceCaught = 0;

    // Constructors
    public Cat(String name, double health, int painLevel, int miceCaught) {
        super(name, health, painLevel);
        if (miceCaught < 0) {
            miceCaught = 0;
        }
        this.miceCaught = miceCaught;
    }

    public Cat(String name, double health, int painLevel) {
        this(name, health, painLevel, defaultMiceCaught);
    }

    public int getMiceCaught() {
        return this.miceCaught;
    }

    public int treat() {
        int minutesToHeal;
        if (this.miceCaught < 4) {
            minutesToHeal = (int) Math.ceil((this.getPainLevel() * 2) / this.getHealth());
        }
        else if (this.miceCaught > 7) {
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
        String text = "meow ".repeat(this.miceCaught).trim();
        if (this.painLevel > 5) {
            text = text.toUpperCase();
        }
        System.out.println(text);
    }

    public boolean equals(Object o) {
        if (o instanceof Cat) {
            Cat someCat = (Cat) o;
            boolean equalMiceCaught = this.miceCaught == someCat.getMiceCaught();
            if (equalMiceCaught && super.equals(someCat)) {
                return true;
            }
        }
        return false;
    }
}
