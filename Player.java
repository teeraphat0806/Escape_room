import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player {
    public int[] row= {8,8};
    public int[] column={8,9};
    private double speed_frame;
    private int xp;
    private int yp;
    private int tilesize;
    private double sprite = 0;
    private BufferedImage[] front_lde,left_lde,right_lde,back_lde;
    private BufferedImage[] front_w,left_w,right_w,back_w;
    Player(int x,int y,int tilesize,double speed_frame){
        xp = x;
        yp = y;
        this.tilesize = tilesize;
        this.speed_frame = speed_frame;
        getPlayerImage();
    }
    public void getPlayerImage(){
        front_lde = new BufferedImage[2];
        back_lde = new BufferedImage[2];
        left_lde = new BufferedImage[2];
        right_lde = new BufferedImage[2];

        front_w = new BufferedImage[4];
        back_w = new BufferedImage[4];
        left_w = new BufferedImage[4];
        right_w = new BufferedImage[4];
        // idle image
        try{
           front_lde[0] = ImageIO.read(getClass().getResourceAsStream("/player/front1.png"));
           front_lde[1] = ImageIO.read(getClass().getResourceAsStream("/player/front2.png"));
           left_lde[0] = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
           left_lde[1] = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
           right_lde[0] = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
           right_lde[1] = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
           back_lde[0] = ImageIO.read(getClass().getResourceAsStream("/player/back1.png"));
           back_lde[1] = ImageIO.read(getClass().getResourceAsStream("/player/back2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        // walk image
        try{
            front_w[0] = ImageIO.read(getClass().getResourceAsStream("/player/w_front1.png"));
            front_w[1] = ImageIO.read(getClass().getResourceAsStream("/player/w_front2.png"));
            front_w[2] = ImageIO.read(getClass().getResourceAsStream("/player/w_front3.png"));
            front_w[3] = ImageIO.read(getClass().getResourceAsStream("/player/w_front4.png"));
            back_w[0] = ImageIO.read(getClass().getResourceAsStream("/player/w_back1.png"));
            back_w[1] = ImageIO.read(getClass().getResourceAsStream("/player/w_back2.png"));
            back_w[2] = ImageIO.read(getClass().getResourceAsStream("/player/w_back3.png"));
            back_w[3] = ImageIO.read(getClass().getResourceAsStream("/player/w_back4.png"));
            left_w[0] = ImageIO.read(getClass().getResourceAsStream("/player/w_left1.png"));
            left_w[1] = ImageIO.read(getClass().getResourceAsStream("/player/w_left2.png"));
            left_w[2] = ImageIO.read(getClass().getResourceAsStream("/player/w_left3.png"));
            left_w[3] = ImageIO.read(getClass().getResourceAsStream("/player/w_left4.png"));
            right_w[0] = ImageIO.read(getClass().getResourceAsStream("/player/w_right1.png"));
            right_w[1] = ImageIO.read(getClass().getResourceAsStream("/player/w_right2.png"));
            right_w[2] = ImageIO.read(getClass().getResourceAsStream("/player/w_right3.png"));
            right_w[3] = ImageIO.read(getClass().getResourceAsStream("/player/w_right4.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public object_draw draw(entity en){
        BufferedImage image = null;
        if(en.post == "lde"){
            if(sprite >= 1){
                sprite = 0;
            }else{
                sprite += speed_frame;
            }
            switch (en.direction) {
                case "front":
                    image = front_lde[(int) sprite];
                    break;
                case "back":
                    image = back_lde[(int)sprite];
                    break;
                case "left":
                    image = left_lde[(int)sprite];
                    break;
                case "right":
                    image = right_lde[(int)sprite];
                    break;
            };

        }
        if(en.post == "walk"){
            if(sprite >= 3){
                sprite = 0;
            }else{
                sprite += speed_frame;
            }
            switch (en.direction) {
                case "front":
                    image = front_w[(int)sprite];
                    break;
                case "back":
                    image = back_w[(int)sprite];
                    break;
                case "left":
                    image = left_w[(int)sprite];
                    break;
                case "right":
                    image = right_w[(int)sprite];
                    break;
            };

        }
        object_draw obj = new object_draw();
        obj.get_object_draw(image,xp,yp,tilesize*2,tilesize*2);
        return obj;
    }
    public void setX(int x){
        this.xp = x;

    }
    public void setY(int y){
        this.yp = y;
    }
}
