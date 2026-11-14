package flyweight.rpgmap;

public class RoadTile extends Tile {

    @Override
    public TileType getTileType() {
        return TileType.ROAD;
    }

    @Override
    public void action() {
        System.out.println("You walk along the road.");
    }
}
