import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class image_show {
    public int x;
    public int y;
    public BufferedImage image;
    public BufferedImage[] images;
    public int width;
    public int height;
    public double sprite = 0;
    public boolean draw = false;
    public boolean loop = false;
    public remove_background removeBackground = new remove_background();

    image_show(int x,int y,int width,int height,String image_file,Color remove_backgroundcolor){
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;
        if(remove_backgroundcolor!= null){
            initial_image(remove_backgroundcolor,image_file);}
        else{
            initial_image(image_file);
        }
    }
    image_show(int x,int y,int width,int height,String[] image_file,Color remove_backgroundcolor){
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;
        if(remove_backgroundcolor!= null){
            initial_image(remove_backgroundcolor,image_file);}
        else{
            initial_image(image_file);
        }
    }
    public void initial_image(Color remove_backgroundcolor,String image_file){
        try{
            image = ImageIO.read(getClass().getResourceAsStream(image_file));
            image = removeBackground.removeBackground(image,remove_backgroundcolor);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void initial_image(String image_file){
        try{
            image = ImageIO.read(getClass().getResourceAsStream(image_file));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void initial_image(String[] image_file){
        images = new BufferedImage[image_file.length];
        try{
            for(int i=0;i<image_file.length;i++){
                images[i] = ImageIO.read(getClass().getResourceAsStream(image_file[i]));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void initial_image(Color remove_backgroundcolor,String[] image_file){
        images = new BufferedImage[image_file.length];
        try{
            for(int i=0;i<image_file.length;i++){
                images[i] = ImageIO.read(getClass().getResourceAsStream(image_file[i]));
                images[i] = removeBackground.removeBackground(images[i],remove_backgroundcolor);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void update(double sprite_speed,boolean loops){
        if(loops){
            if(sprite < images.length){
                sprite += sprite_speed;
            }
            if(sprite >= images.length){
                loop =true;
                sprite = 0;
            }
        }else{
            if(sprite < images.length){
                sprite += sprite_speed;
            }
            if(sprite >= images.length){
                loop =true;
                sprite = images.length-1;
            }
        }
    }

    public void draw(Graphics2D g2){
        if(draw) {
            g2.drawImage(image, x , y , this.width, this.height, null);
        }
    }
    public void draw_sprite(Graphics2D g2){
        if(draw) {
            g2.drawImage(images[(int)sprite],x , y , this.width, this.height, null);
        }
    }
    public void draw_sprite(Graphics2D g2,entity en){
        if(draw) {
            g2.drawImage(images[(int)sprite],x-en.x , y+en.y , this.width, this.height, null);
        }
    }
    public void setDraw(boolean draw){
        this.draw = draw;
    }
    public void setSprite(double sprite){
        this.sprite = sprite;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y =y ;
    }
    public void setWidth(double width){
        this.width = (int)width;
    }
    public void setHeight(double height){
        this.height = (int)height;
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }

}
