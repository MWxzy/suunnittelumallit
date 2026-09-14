package state;

public class IntermediateState extends State {
    public IntermediateState(GameCharacter character) {
        super(character);
    }

    @Override
    public void train() {
        character.addExperience(15);
        System.out.println("Training... +15 XP");
        checkLevelUp();
    }

    @Override
    public void meditate() {
        character.addHealth(10);
        System.out.println("Meditating... +10 HP");
    }

    @Override
    public void fight() {
        System.out.println("You can't fight yet. Train and meditate to advance.");
    }

    @Override
    public String getAvailableActions() {
        return "train, meditate";
    }

    @Override
    public String getLevelName() {
        return "Intermediate";
    }

    private void checkLevelUp() {
        if (character.getExperience() >= 150) {
            System.out.println("Congratulations! You've advanced to Expert level!");
            character.setState(new ExpertState(character));
        }
    }
}