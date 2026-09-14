package state;

public class NoviceState extends State {
    public NoviceState(GameCharacter character) {
        super(character);
    }

    @Override
    public void train() {
        character.addExperience(10);
        System.out.println("Training... +10 XP");
        checkLevelUp();
    }

    @Override
    public void meditate() {
        System.out.println("You can't meditate yet. Only training is available.");
    }

    @Override
    public void fight() {
        System.out.println("You can't fight yet. Only training is available.");
    }

    @Override
    public String getAvailableActions() {
        return "train";
    }

    @Override
    public String getLevelName() {
        return "Novice";
    }

    private void checkLevelUp() {
        if (character.getExperience() >= 50) {
            System.out.println("Congratulations! You've advanced to Intermediate level!");
            character.setState(new IntermediateState(character));
        }
    }
}