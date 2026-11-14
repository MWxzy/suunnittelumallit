package flyweight.rpgmap;

import java.util.Random;

public class WildernessMap extends Map {

    public WildernessMap(int width, int height) {
        this(width, height, new Random());
    }

    public WildernessMap(int width, int height, Random random) {
        super(width, height, random);
    }

    @Override
    public Tile createTile() {
        return switch (getRandom().nextInt(3)) {
            case 0 -> new SwampTile();
            case 1 -> new WaterTile();
            default -> new ForestTile();
        };
    }
}
