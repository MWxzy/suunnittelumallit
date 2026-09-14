package state;

public class ExpertState extends State {
    public ExpertState(GameCharacter character) {
        super(character);
    }

    @Override
    public void train() {
        character.addExperience(20);
        System.out.println("Training... +20 XP");
        checkLevelUp();
    }

    @Override
    public void meditate() {
        character.addHealth(15);
        System.out.println("Meditating... +15 HP");
    }

    @Override
    public void fight() {
        character.addExperience(30);
        character.addHealth(-20);
        System.out.println("Fighting... +30 XP, -20 HP");
        checkLevelUp();
    }

    @Override
    public String getAvailableActions() {
        return "train, meditate, fight";
    }

    @Override
    public String getLevelName() {
        return "Expert";
    }

    private void checkLevelUp() {
        if (character.getExperience() >= 300) {
            System.out.println("Congratulations! You've reached Master level!");
            character.setState(new MasterState(character));
        }
    }
}