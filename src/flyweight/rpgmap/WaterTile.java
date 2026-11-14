package flyweight.rpgmap;

public class WaterTile extends Tile {

    @Override
    public TileType getTileType() {
        return TileType.WATER;
    }

    @Override
    public void action() {
        System.out.println("You wade through the water.");
    }
}
