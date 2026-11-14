package flyweight.rpgmap;

public enum TileType {

    SWAMP('S', "swamp", "A murky swamp"),
    WATER('W', "water", "A deep pool of water"),
    ROAD('R', "road", "A paved road"),
    FOREST('F', "forest", "A dense forest"),
    BUILDING('B', "building", "A tall building");

    private final char character;
    private final String type;
    private final String description;

    TileType(char character, String type, String description) {
        this.character = character;
        this.type = type;
        this.description = description;
    }

    public char getCharacter() {
        return character;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
