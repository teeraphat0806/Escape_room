import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class scene {
    private int x;
    private int y;
    private BufferedImage scene;
    private int screen_width;
    private int screen_height;
    scene(double x,double y,int screen_height,int screen_width){
        this.x = (int)x;
        this.y = (int)y;
        this.screen_width = screen_width;
        this.screen_height = screen_height;
    }
    public void setScene(String scene_file){
        try{
            scene = ImageIO.read(getClass().getResourceAsStream(scene_file));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(entity en,Graphics2D g2){
        g2.drawImage(scene,x-en.x,y+en.y,this.screen_width,this.screen_height,null);
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void collide_room(entity en){
        int x = (this.x-en.x);
        int y = (this.y+en.y);
        if(x < -87){
            en.x -= 5;
        }
        if(x > 218){
            en.x += 5;
        }
        if(y < -243){
            en.y += 5;
        }
        if(y > 137){
            en.y -= 5;
        }
    }
    public void collide_room2(entity en){

        int x = (this.x-en.x);
        int y = (this.y+en.y);
        if(en.x >= 67 && en.x <=271 && en.y >= 32 && en.y <= 140){
            if(en.x > 67 && en.x < 80){
                en.x -= 3;
            }
            else if(en.y > 35 ){
                en.y -= 3;
            }
        }
        if(en.x >= -355 && en.x <=-150 && en.y >= 32 && en.y <= 140){
            if(en.x < -150 && en.x > -160){
                en.x += 3;
            }
            else if(en.y > 35 ){
                en.y -= 3;
            }
        }
        if(en.x >= -355 && en.x <=-236 && en.y >= -245 && en.y <= -158){
            if(en.x < -236 && en.x > -246){
                en.x += 3;
            }
            else if(en.y < -158 ){
                en.y += 3;
            }
        }
        if(x < -267){
            en.x -= 5;
        }
        if(x > 351){
            en.x += 5;
        }
        if(y < 136){
            en.y += 5;
        }
        if(y > -240){
            en.y -= 5;
        }
    }
    public void collide_room3(entity en){
        int x = (this.x-en.x);
        int y = (this.y+en.y);

        if(x >= 210 && x <=400 && y >= 0){
            if(x > 210 && x < 215){
                en.x += 3;
            }
            else if(y > 0){
                en.y -= 3;
            }

        }
        if(x >= -320 && x <=3 && y >= 0){
            if(x < 3 && x > -31){
                en.x -= 3;
            }
            else if(y > 0 ){
                en.y -= 3;
            }
        }
        if(x >= -315 && x <=-108 && y <= -190){
            if(x <-108 && x > -115){
                en.x -= 3;
            }else if(y < -190 ){
                en.y += 3;
            }

        }
        if(x < -310){
            en.x -= 5;
        }
        if(x > 390){
            en.x += 5;
        }
        if(y < -246){
            en.y += 5;
        }
        if(y > 135){
            en.y -= 5;
        }
    }
}
