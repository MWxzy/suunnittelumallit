package state;

public class GameCharacter {
    private String name;
    private int experience;
    private int health;
    private State state;

    public GameCharacter(String name) {
        this.name = name;
        this.experience = 0;
        this.health = 100;
        this.state = new NoviceState(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public void addExperience(int amount) {
        experience += amount;
    }

    public void addHealth(int amount) {
        health = Math.max(0, Math.min(100, health + amount));
    }

    public int getExperience() {
        return experience;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public void train() {
        state.train();
    }

    public void meditate() {
        state.meditate();
    }

    public void fight() {
        state.fight();
    }

    public String getAvailableActions() {
        return state.getAvailableActions();
    }

    public String getLevelName() {
        return state.getLevelName();
    }

    public void displayStatus() {
        System.out.println("\n=== " + name + " ===");
        System.out.println("Level: " + getLevelName());
        System.out.println("Experience: " + experience);
        System.out.println("Health: " + health);
        System.out.println("Available actions: " + getAvailableActions());
    }
}