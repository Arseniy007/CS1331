public class Fly {
    // Instance variables
    private double mass;
    private double speed;

    // Static constants
    private static final double DEFAULT_MASS = 5.0;
    private static final double DEFAULT_SPEED = 10.0;

    // Constructors
    public Fly(double mass, double speed) {
        this.mass = mass;
        this.speed = speed;
    }

    public Fly(double mass) {
        this(mass, DEFAULT_SPEED);
    }

    public Fly() {
        this(DEFAULT_MASS);
    }

    // Getters
    public double getMass() {
        return this.mass;
    }

    public double getSpeed() {
        return this.speed;
    }

    // Setters
    public void setMass(double newMass) {
        this.mass = newMass;
    }

    public void setSpeed(double newSpeed) {
        this.speed = newSpeed;
    }

    public String toString() {
        if (this.isDead()) {
            return String.format("I'm dead, but I used to be a fly with a speed of %.2f.", this.speed);
        }
        return String.format("I'm a speedy fly with %.2f speed and %.2f mass.", this.speed, this.mass);
    }

    public void grow(int addedMass) {
        for (int i = 0; i < addedMass; i++) {
            if (mass < 20) {
                this.speed++;
            }
            else {
                this.speed -= 0.5;
            }
            this.mass++;
        }
    }

    public boolean isDead() {
        return (mass == 0);
    }    
}
