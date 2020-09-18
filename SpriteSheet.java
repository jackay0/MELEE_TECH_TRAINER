import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage image;
    private BufferedImage controller;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
        this.controller = image;
    }

    public BufferedImage grabImage(int col, int row, int width, int height) {
        BufferedImage img = image.getSubimage((col * 32) - 32, (row * 32) - 32, width, height);
        return img;

    }
    public BufferedImage grabController(int x, int y, int width, int height) {
        BufferedImage img = controller.getSubimage(x, y, width, height);
        return img;
    }
}
