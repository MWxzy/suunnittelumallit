package flyweight.rpgmap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class Game extends Application {

    private static final int MAP_WIDTH = 32;
    private static final int MAP_HEIGHT = 20;
    private static final double TILE_SIZE = 24;

    @Override
    public void start(Stage stage) {
        Map map = createMap();
        TileGraphicFactory factory = new TileGraphicFactory();
        MapRenderer renderer = new MapRenderer(factory, TILE_SIZE);

        Canvas canvas = new Canvas(renderer.getCanvasWidth(map), renderer.getCanvasHeight(map));
        renderer.render(map, canvas.getGraphicsContext2D());

        System.out.println("Generated " + map.getClass().getSimpleName() + ":");
        map.display();
        System.out.println("Tiles: " + map.getTileCount());
        System.out.println("Draw requests: " + factory.getRequestCount());
        System.out.println("Shared tile graphics: " + factory.getGraphicCount());
        System.out.println("Cached types: " + factory.getCachedTypes());

        Label label = new Label(map.getClass().getSimpleName()
                + " - " + map.getTileCount() + " tiles, "
                + factory.getGraphicCount() + " shared graphics");
        label.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

        VBox root = new VBox(8, label, canvas);
        root.setStyle("-fx-padding: 10; -fx-background-color: #1a202c; -fx-alignment: center;");

        stage.setTitle("RPG Map Renderer - Flyweight");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public Map createMap() {
        Random random = new Random();
        if (random.nextBoolean()) {
            return new CityMap(MAP_WIDTH, MAP_HEIGHT, random);
        }
        return new WildernessMap(MAP_WIDTH, MAP_HEIGHT, random);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
