package flyweight.rpgmap;

public class ForestTile extends Tile {

    @Override
    public TileType getTileType() {
        return TileType.FOREST;
    }

    @Override
    public void action() {
        System.out.println("You push through the forest.");
    }
}
