public class Frog {
    // Instance variables
    private String name;
    private int age;
    private double tongueSpeed;
    private boolean isFroglet;

    // Static constants
    private static String species = "Rare Pepe";
    private static final int DEFAULT_AGE = 5;
    private static final double DEFAULT_TONGUE_SPEED = 5.0;

    // Constructors
    public Frog(String name, int age, double tongueSpeed) {
        this.name = name;
        this.age = age;
        this.tongueSpeed = tongueSpeed;
        if (age > 1 && age < 7) {
            this.isFroglet = true;
        }
        else {
            this.isFroglet = false;
        }
    }

    public Frog(String name, double ageInYears, double tongueSpeed) {
        this(name, (int) (ageInYears * 12), tongueSpeed);

    }

    public Frog(String name) {
        this(name, DEFAULT_AGE, DEFAULT_TONGUE_SPEED);
    }

    public void grow(int months) {
        for (int i = 0; i < months; i++) {
            if (this.age < 12) {
                this.tongueSpeed++;
            }
            else if (age >= 30) {
                this.tongueSpeed--;
                if (this.tongueSpeed < DEFAULT_TONGUE_SPEED) {
                    this.tongueSpeed = DEFAULT_TONGUE_SPEED;
                }
            }
            this.age++;
        }
        if (this.age > 1 && this.age < 7) {
            this.isFroglet = true;
        }
        else {
            this.isFroglet = false;
        }
    }

    public void grow() {
        this.grow(1);
    }

    public void eat(Fly someFly) {
        if (someFly.isDead()) {
            return;
        }
        if (this.tongueSpeed > someFly.getSpeed()) {
            if (someFly.getMass() >= this.age * 0.5) {
                this.grow();
            }
            someFly.setMass(0);
        }
        else {
            someFly.grow(1);
        }
    }
    
    public String toString() {
        String frogType = (this.isFroglet) ? "froglet!" : "frog.";
        return String.format(
            "My name is %s and I'm a rare %s I'm %d months old and my tongue has a speed of %.2f.",
            this.name, frogType, this.age, this.tongueSpeed
        );
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public double getTongueSpeed() {
        return this.tongueSpeed;
    }

    public boolean getIsFroglet() {
        return this.isFroglet;
    }

    public static String getSpecies() {
        return species;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
        if (this.age > 1 && this.age < 7) {
            this.isFroglet = true;
        }
        else {
            this.isFroglet = false;
        }
    }

    public void setTongueSpeed(double tongueSpeed) {
        this.tongueSpeed = tongueSpeed;
    }

    public static void setSpecies(String speciesType) {
        species = speciesType;
    }
}
