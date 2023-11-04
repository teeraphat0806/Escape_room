import java.awt.*;
import java.awt.image.BufferedImage;

public class remove_background {
    public BufferedImage removeBackground(BufferedImage image, Color colorToRemove) {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (image.getRGB(x, y) == colorToRemove.getRGB()) {
                    image.setRGB(x, y, 0x00FFFFFF); // Make the pixel transparent
                }
            }
        }
        return image;
    }
}
