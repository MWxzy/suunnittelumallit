package flyweight.rpgmap;

public abstract class Tile {

    public abstract TileType getTileType();

    public final char getCharacter() {
        return getTileType().getCharacter();
    }

    public final String getType() {
        return getTileType().getType();
    }

    public final String getDescription() {
        return getTileType().getDescription();
    }

    public abstract void action();
}
