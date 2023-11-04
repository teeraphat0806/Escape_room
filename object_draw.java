import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class object_draw {
    public BufferedImage image;
    public int x;
    public int y;
    public int tile_size1;
    public int tile_size2;
    public void get_object_draw(BufferedImage image,int x,int y,int tile_size1,int tile_size2){
        this.image = image;
        this.x = x;
        this.y = y;
        this.tile_size1 = tile_size1;
        this.tile_size2 = tile_size2;
    }

}
