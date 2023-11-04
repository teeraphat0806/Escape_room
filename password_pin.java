import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.lang.Math.*;

public class password_pin {
    public boolean pass = false;
    public boolean draws = false;
    public int passworded;
    private BufferedImage[] image = new BufferedImage[2];
    private int[] chars = {-1,-1,-1,-1};
    private int password;
    private int tilesize;
    private int screenwidth;
    private int screenheight;
    private Color text_color = Color.white;
    password_pin(int password,int screenwidth,int screenheight,int tilesize){
        initial_image();
        this.tilesize = tilesize;
        this.screenheight = screenheight;
        this.screenwidth = screenwidth;
        this.password = Math.abs(password);
    }

    public void setDraws(boolean draws){
        this.draws = draws;
    }
    public void setText_color(Color text_color){this.text_color = text_color;}
    public void initial_image(){
        image = new BufferedImage[2];
        try{
            image[0] = ImageIO.read(getClass().getResourceAsStream("/scene/pin_codelock.png"));
            image[1] = ImageIO.read(getClass().getResourceAsStream("/scene/pin_codeunlock.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        if(pass && draws){
            g2.drawImage(image[1],200,120,tilesize*8,tilesize*9,null);
        }else if(!pass && draws){
            g2.drawImage(image[0],200 ,120,tilesize*8,tilesize*9,null);
        }

        if(draws){
            Font font= new Font("Courier", Font.PLAIN, 50);
            g2.setFont(font);
            g2.setColor(text_color);
            for(int i=0;i<4;i++) {
                if(chars[i]!=-1) {
                    g2.drawString(String.valueOf(chars[i]), (tilesize * 5 + 35)+(75*i), tilesize * 8 + 10);
                }else{
                    break;
                }
            }
            Font fonts= new Font("SansSerif", Font.BOLD, 25);
            g2.setFont(fonts);
            g2.setColor(new Color(255,255,255,200));
            g2.drawString(" Q  TO EXIT", screenwidth-160, screenheight-40);
        }

    }
    public void setPassword(int password){
        this.password = password;
    }
    public void setPass(boolean pass){
        this.pass = pass;text_color = Color.green;
    }
    public void clear(){
        for (int i = 0; i < 4; i++) {
            this.chars[i] = -1;
        }
    }
    public void update(KeyHandler keyH){

        if(!this.pass) {
            int i;
            int j;
            passworded = 0;
            for (i = 0; i < 4; i++) {
                if (this.chars[i] == -1 && keyH.chars != -1) {
                    this.chars[i] = keyH.chars;
                    break;
                }
            }if(keyH.back_space){
                for (j = 0; j < 4; j++) {
                    if (this.chars[j] == -1) {
                        break;
                    }
                }
                if(j < 4  && j > 0){
                    this.chars[j-1] = -1;
                }
            }
            if (chars[3] != -1) {
                for (i = 0; i < 4; i++) {
                    passworded += (int) (this.chars[i] * Math.pow(10, 3 - i));
                }
                if (passworded == password) {
                    this.pass = true;
                    this.text_color = Color.green;
                } else {
                    for (i = 0; i < 4; i++) {

                        this.chars[i] = -1;
                    }
                }

            }

        }


    }
}
