import java.util.Arrays;

class BlueAstronaut extends Player implements Crewmate {
    // Instance variables
    private int numTasks;
    private int taskSpeed;

    // Constructors
    public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
        super(name, susLevel);
        this.numTasks = numTasks;
        this.taskSpeed = taskSpeed;
    }

    public BlueAstronaut(String name) {
        this(name, 15, 6, 10);
    }

    // Methods
    public void emergencyMeeting() {
        if (this.isFrozen()) {
            return;
        }
        Player[] players = super.getPlayers();
        Arrays.sort(players);

        int susPlayerIndex = 0;
        int highestSusLevel = -1;
        for (int i = players.length - 1; i > 0; i--) {
            if (players[i].isFrozen()) {
                continue;
            }
            if (players[i].getSusLevel() == highestSusLevel) {
                return;
            }
            if (players[i].getSusLevel() < highestSusLevel) {
                break;
            }
            highestSusLevel = players[i].getSusLevel();
            susPlayerIndex = i;
        }
        players[susPlayerIndex].setFrozen(true);
        super.gameOver();
    }

    public void completeTask() {
        if (this.isFrozen()) {
            return;
        }
        if (this.taskSpeed > 20) {
            this.numTasks -= 2;
        }
        else {
            this.numTasks -= 1;
        }
        if (this.numTasks < 0) {
            this.numTasks = 0;
        }
        if (this.numTasks == 0) {
            System.out.println("I have completed all my tasks");
            int newSusLevel = (int) Math.floor(this.getSusLevel() * 0.50);
            this.setSusLevel(newSusLevel);
        }
    }

    public boolean equals(Object o) {
        if (super.equals(o)) {
            if (o instanceof BlueAstronaut) {
                BlueAstronaut oAstronaut = (BlueAstronaut) o;
                if (this.numTasks == oAstronaut.getNumTask() && this.taskSpeed == oAstronaut.getTaskSpeed()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        String text = super.toString();
        text = text + String.format("I have %d left over.", this.numTasks);
        if (this.getSusLevel() > 15) {
            return text.toUpperCase();
        }
        return text;
    }

    // Getters
    public int getNumTask() {
        return this.numTasks;
    }

    public int getTaskSpeed() {
        return this.taskSpeed;
    }
}