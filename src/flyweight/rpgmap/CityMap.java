package flyweight.rpgmap;

import java.util.Random;

public class CityMap extends Map {

    public CityMap(int width, int height) {
        this(width, height, new Random());
    }

    public CityMap(int width, int height, Random random) {
        super(width, height, random);
    }

    @Override
    public Tile createTile() {
        return switch (getRandom().nextInt(3)) {
            case 0 -> new RoadTile();
            case 1 -> new ForestTile();
            default -> new BuildingTile();
        };
    }
}
