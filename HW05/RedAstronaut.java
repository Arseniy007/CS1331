import java.util.Arrays;

public class RedAstronaut extends Player implements Impostor {
    // Instance variables
    private String skill;

    // Constructors
    public RedAstronaut(String name, int susLevel, String skill) {
        super(name, susLevel);
        this.skill = skill.toLowerCase();
    }

    public RedAstronaut(String name) {
        this(name, 15, "experienced");
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
            if (this.equals(players[i]) || players[i].isFrozen()) {
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
    
    public void freeze(Player p) {
        if (p.isFrozen() || this.isFrozen() || p instanceof RedAstronaut) {
            return;
        }
        if (this.compareTo(p) < 0) {
            p.setFrozen(true);
        }
        else {
            this.setSusLevel(this.getSusLevel() * 2);
        }
        super.gameOver();
    }

    public void sabotage(Player p) {
        if (p.isFrozen() || this.isFrozen() || p instanceof RedAstronaut) {
            return;
        }
        int newSusLevel;
        if (this.getSusLevel() < 20) {
            newSusLevel = (int)(p.getSusLevel() * 1.5);
        }
        else {
            newSusLevel = (int)(p.getSusLevel() * 1.25);
        }
        p.setSusLevel(newSusLevel);
    }

    public boolean equals(Object o) {
        if (super.equals(o)) {
            if (o instanceof RedAstronaut) {
                RedAstronaut oAstronaut = (RedAstronaut) o;
                if (this.skill == oAstronaut.getSkill()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        String text = super.toString();
        text = text + String.format("I'm an %s player", this.skill);
        if (this.getSusLevel() > 15) {
            return text.toUpperCase();
        }
        return text;
    }

    // Getters
    public String getSkill() {
        return this.skill;
    }
}
