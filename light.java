import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class light {
    public int x;
    public int y;
    public boolean light_activate = false;
    private double speed_frame;
    private int tilesize;
    private BufferedImage[] image;
    private double sprite=0;
    private ArrayList<Integer> light_flop = new ArrayList<Integer>();
    private remove_background removeBackground = new remove_background();
    light(double x,double y,double speed_frame,String light_flip_flop,int tilesize){
        this.tilesize = tilesize;
        this.x = (int)x;
        this.y = (int)y;
        this.speed_frame = speed_frame;
        for(int i=0; i<light_flip_flop.length();i++){
            if(light_flip_flop.charAt(i) == '0'){
                light_flop.add(0);
            } else if (light_flip_flop.charAt(i) == '1') {
                light_flop.add(1);
            }
        }
        initial_image();
    }
    public void initial_image(){
        image = new BufferedImage[2];
        try{
            image[0] = ImageIO.read(getClass().getResourceAsStream("/scene/light_dh.png"));
            image[1] = ImageIO.read(getClass().getResourceAsStream("/scene/light_have.png"));
            image[0] = removeBackground.removeBackground(image[0],Color.WHITE);
            image[1] = removeBackground.removeBackground(image[1],Color.WHITE);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(entity en, Graphics2D g2){
        g2.drawImage(image[(int)sprite],x-en.x,y+en.y,tilesize,tilesize,null);
        if((int)sprite == 1){
            light_effect(g2,en);
        }
    }
    public void update(){
        if(sprite < 2){
            sprite += speed_frame;
        }
        if(sprite >= 2){
            sprite = 0;
        }

    }
    public void Light_flip(entity en, Graphics2D g2){
        if(light_activate){
            if(sprite < light_flop.size()){
                sprite += speed_frame;
            }
            if(sprite >= light_flop.size()){
                sprite = 0;
                light_activate = false;
            }
            g2.drawImage(image[light_flop.get((int)sprite)],x-en.x,y+en.y,tilesize,tilesize,null);
            if(light_flop.get((int)sprite) == 1){
                light_effect(g2,en);
            }
        }else{
            g2.drawImage(image[0],x-en.x,y+en.y,tilesize,tilesize,null);
        }

    }
    public void setLight_activate(boolean activate){
        light_activate = activate;
    }
    public void light_effect(Graphics2D g2,entity en) {


        // Define the center and radius of the gradient circle
        Point2D center = new Point2D.Float(x-en.x +15, y+en.y+10);
        float radius = 100;

        // Define the gradient colors and positions
        Color[] colors = { new Color(255, 50, 0, 150), new Color(255, 0, 0, 0) }; // Yellow to transparent red
        float[] fractions = { 0.0f, 1.0f };

        // Create the RadialGradientPaint
        RadialGradientPaint paint = new RadialGradientPaint(center, radius, fractions, colors);

        // Set the drawing color to the gradient
        g2.setPaint(paint);

        // Draw a filled ellipse to create the gradient circle
        g2.fillOval((int)(center.getX() - radius), (int)(center.getY() - radius), (int)(2 * radius), (int)(2 * radius));
    }
}
