package flyweight.rpgmap;

import javafx.scene.canvas.GraphicsContext;

public class MapRenderer {

    private final TileGraphicFactory graphicFactory;
    private final double tileSize;

    public MapRenderer(TileGraphicFactory graphicFactory, double tileSize) {
        if (tileSize <= 0) {
            throw new IllegalArgumentException("Tile size must be positive");
        }
        this.graphicFactory = graphicFactory;
        this.tileSize = tileSize;
    }

    public void render(Map map, GraphicsContext gc) {
        gc.clearRect(0, 0, getCanvasWidth(map), getCanvasHeight(map));

        for (int row = 0; row < map.getHeight(); row++) {
            for (int col = 0; col < map.getWidth(); col++) {
                Tile tile = map.getTile(row, col);
                TileGraphic graphic = graphicFactory.getTileGraphic(tile.getType());
                graphic.draw(gc, col * tileSize, row * tileSize, tileSize);
            }
        }
    }

    public double getCanvasWidth(Map map) {
        return map.getWidth() * tileSize;
    }

    public double getCanvasHeight(Map map) {
        return map.getHeight() * tileSize;
    }

    public double getTileSize() {
        return tileSize;
    }

    public TileGraphicFactory getGraphicFactory() {
        return graphicFactory;
    }
}
