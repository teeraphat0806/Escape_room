import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class pin_door {
    private int x;
    private int y;
    private int tilesize;
    private BufferedImage[] image;
    private double sprite=0;
    private double speed_frame;
    pin_door(double x,double y,int tilesize,double speed_frame){
        this.x = (int)x;
        this.y = (int)y;
        this.tilesize = tilesize;
        this.speed_frame = speed_frame;
        initial_image();
    }
    public void initial_image(){
        image = new BufferedImage[5];
        try{
            image[0] = ImageIO.read(getClass().getResourceAsStream("/scene/code_tablet1.png"));
            image[1] = ImageIO.read(getClass().getResourceAsStream("/scene/code_tablet2.png"));
            image[2] = ImageIO.read(getClass().getResourceAsStream("/scene/code_tablet4.png"));
            image[3] = ImageIO.read(getClass().getResourceAsStream("/scene/code_tablet3.png"));
            image[4] = ImageIO.read(getClass().getResourceAsStream("/scene/code_tablet5.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(sprite >= 4){
            sprite = 0;
        }else{
            sprite += speed_frame;
        }
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void draw(entity en, Graphics2D g2){
        g2.drawImage(image[(int)sprite],x-en.x,y+en.y,tilesize-15,tilesize-15,null);
    }
}
