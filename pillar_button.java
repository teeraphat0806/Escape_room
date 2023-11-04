import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class pillar_button {
    public boolean click = false;
    private int x;
    private int y;
    private int tilesize;
    private BufferedImage image,images;
    private remove_background removeBackground = new remove_background();
    pillar_button(double x,double y,int tilesize){
        this.x = (int)x;
        this.y = (int)y;
        this.tilesize = tilesize;
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/scene/pillar.png"));
            images = ImageIO.read(getClass().getResourceAsStream("/scene/pillar_click.png"));
            image = removeBackground.removeBackground(image,Color.WHITE);
            images= removeBackground.removeBackground(images,Color.WHITE);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setClick(boolean click){
        this.click = click;
    }
    public object_draw draw(entity en){
        object_draw obj = new object_draw();
        if(click){
            obj.get_object_draw(images,x - en.x,y+en.y,tilesize,tilesize*2);
        }else{
            obj.get_object_draw(image,x - en.x,y+en.y,tilesize,tilesize*2);
        }
        return obj;
    }

}
