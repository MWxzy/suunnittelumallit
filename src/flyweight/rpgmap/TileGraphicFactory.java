package flyweight.rpgmap;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TileGraphicFactory {

    public static final int GRAPHIC_SIZE = 64;

    private final Map<String, TileGraphic> graphics = new HashMap<>();
    private int requestCount;

    public TileGraphic getTileGraphic(String type) {
        requestCount++;
        TileGraphic graphic = graphics.get(type);
        if (graphic == null) {
            graphic = createTileGraphic(type);
            graphics.put(type, graphic);
        }
        return graphic;
    }

    public int getGraphicCount() {
        return graphics.size();
    }

    public int getRequestCount() {
        return requestCount;
    }

    public Set<String> getCachedTypes() {
        return Collections.unmodifiableSet(graphics.keySet());
    }

    private TileGraphic createTileGraphic(String type) {
        Canvas canvas = new Canvas(GRAPHIC_SIZE, GRAPHIC_SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        switch (type) {
            case "swamp" -> drawSwamp(gc);
            case "water" -> drawWater(gc);
            case "road" -> drawRoad(gc);
            case "forest" -> drawForest(gc);
            case "building" -> drawBuilding(gc);
            default -> throw new IllegalArgumentException("Unknown tile type: " + type);
        }

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = canvas.snapshot(parameters, null);
        return new TileGraphic(type, image);
    }

    private void drawSwamp(GraphicsContext gc) {
        gc.setFill(Color.web("#4a5d23"));
        gc.fillRect(0, 0, GRAPHIC_SIZE, GRAPHIC_SIZE);
        gc.setFill(Color.web("#3b4a1c"));
        gc.fillOval(6, 38, 30, 18);
        gc.fillOval(34, 10, 24, 14);
        gc.setStroke(Color.web("#8a9a5b"));
        gc.setLineWidth(2);
        gc.strokeLine(14, 52, 10, 34);
        gc.strokeLine(22, 52, 26, 32);
        gc.strokeLine(44, 46, 40, 30);
    }

    private void drawWater(GraphicsContext gc) {
        gc.setFill(Color.web("#2b6cb0"));
        gc.fillRect(0, 0, GRAPHIC_SIZE, GRAPHIC_SIZE);
        gc.setStroke(Color.web("#63b3ed"));
        gc.setLineWidth(3);
        gc.strokeLine(6, 16, 26, 16);
        gc.strokeLine(34, 16, 58, 16);
        gc.strokeLine(2, 32, 22, 32);
        gc.strokeLine(30, 32, 54, 32);
        gc.strokeLine(10, 48, 34, 48);
        gc.strokeLine(42, 48, 62, 48);
    }

    private void drawRoad(GraphicsContext gc) {
        gc.setFill(Color.web("#6b7280"));
        gc.fillRect(0, 0, GRAPHIC_SIZE, GRAPHIC_SIZE);
        gc.setFill(Color.web("#f6e05e"));
        gc.fillRect(28, 6, 8, 18);
        gc.fillRect(28, 34, 8, 18);
    }

    private void drawForest(GraphicsContext gc) {
        gc.setFill(Color.web("#2f855a"));
        gc.fillRect(0, 0, GRAPHIC_SIZE, GRAPHIC_SIZE);
        gc.setFill(Color.web("#5a3e2b"));
        gc.fillRect(28, 42, 8, 16);
        gc.setFill(Color.web("#276749"));
        gc.fillPolygon(new double[]{32, 12, 52}, new double[]{8, 44, 44}, 3);
        gc.setFill(Color.web("#38a169"));
        gc.fillPolygon(new double[]{32, 18, 46}, new double[]{4, 32, 32}, 3);
    }

    private void drawBuilding(GraphicsContext gc) {
        gc.setFill(Color.web("#a0aec0"));
        gc.fillRect(0, 0, GRAPHIC_SIZE, GRAPHIC_SIZE);
        gc.setFill(Color.web("#718096"));
        gc.fillRect(10, 14, 44, 44);
        gc.setFill(Color.web("#f6e05e"));
        gc.fillRect(18, 22, 10, 10);
        gc.fillRect(36, 22, 10, 10);
        gc.fillRect(18, 40, 10, 10);
        gc.fillRect(36, 40, 10, 10);
    }
}
