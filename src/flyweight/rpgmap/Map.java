package flyweight.rpgmap;

import java.util.Random;

public abstract class Map {

    private final int width;
    private final int height;
    private final Random random;
    private final Tile[][] tiles;

    protected Map(int width, int height, Random random) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Map dimensions must be positive");
        }
        this.width = width;
        this.height = height;
        this.random = random;
        this.tiles = new Tile[height][width];
        generate();
    }

    public abstract Tile createTile();

    public void generate() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                tiles[row][col] = createTile();
            }
        }
    }

    public void display() {
        for (int row = 0; row < height; row++) {
            StringBuilder line = new StringBuilder(width);
            for (int col = 0; col < width; col++) {
                line.append(tiles[row][col].getCharacter());
            }
            System.out.println(line);
        }
    }

    public Tile getTile(int row, int col) {
        if (row < 0 || row >= height || col < 0 || col >= width) {
            throw new IndexOutOfBoundsException(
                    "Tile (" + row + ", " + col + ") is outside the map");
        }
        return tiles[row][col];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTileCount() {
        return width * height;
    }

    protected Random getRandom() {
        return random;
    }
}
