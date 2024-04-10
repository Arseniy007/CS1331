public abstract class Pet {
    // Instance variables
    String name;
    double health;
    int painLevel;

    // Constructor
    public Pet(String name, double health, int painLevel) {
        if (health > 1.0) {
            health = 1.0;
        }
        else if (health < 0.0) {
            health = 0.0;
        }
        if (painLevel > 10) {
            painLevel = 10;
        }
        else if (painLevel < 1) {
            painLevel = 1;
        }
        this.name = name;
        this.health = health;
        this.painLevel = painLevel;
    }
    // Getters
    public String getName() {
        return this.name;
    }

    public double getHealth() {
        return this.health;
    }
    
    public int getPainLevel() {
        return this.painLevel;
    }

    public abstract int treat();

    public void speak() {
        String text = String.format("Hello! My name is %s", this.name);
        if (this.painLevel > 5) {
            text = text.toUpperCase();
        }
        System.out.println(text);
    }

    public boolean equals(Object o) {
        if (o instanceof Pet) {
            Pet somePet = (Pet)o;
            if (this.name == somePet.getName()) {
                return true;
            }
        }
        return false;
    }

    protected void heal() {
        this.health = 1.0;
        this.painLevel = 1;
    }
}
