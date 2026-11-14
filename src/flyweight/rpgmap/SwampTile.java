package flyweight.rpgmap;

public class SwampTile extends Tile {

    @Override
    public TileType getTileType() {
        return TileType.SWAMP;
    }

    @Override
    public void action() {
        System.out.println("You sink into the swamp.");
    }
}
