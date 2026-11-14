package flyweight.rpgmap;

public class BuildingTile extends Tile {

    @Override
    public TileType getTileType() {
        return TileType.BUILDING;
    }

    @Override
    public void action() {
        System.out.println("You enter the building.");
    }
}
