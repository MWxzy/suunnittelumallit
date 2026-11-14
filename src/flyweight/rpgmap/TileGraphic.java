package flyweight.rpgmap;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class TileGraphic {

    private final String type;
    private final Image image;

    TileGraphic(String type, Image image) {
        this.type = type;
        this.image = image;
    }

    public void draw(GraphicsContext gc, double x, double y, double size) {
        gc.drawImage(image, x, y, size, size);
    }

    public String getType() {
        return type;
    }

    public Image getImage() {
        return image;
    }
}
