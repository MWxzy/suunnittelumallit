package state;

public class MasterState extends State {
    public MasterState(GameCharacter character) {
        super(character);
    }

    @Override
    public void train() {
        System.out.println("You are a Master. The game is over.");
    }

    @Override
    public void meditate() {
        System.out.println("You are a Master. The game is over.");
    }

    @Override
    public void fight() {
        System.out.println("You are a Master. The game is over.");
    }

    @Override
    public String getAvailableActions() {
        return "none (game over)";
    }

    @Override
    public String getLevelName() {
        return "Master";
    }
}