import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
public class light_door {
    public boolean pass = false;
    private int x;
    private int y;
    private int tilesize;
    private BufferedImage[] image;
    private remove_background removeBackground = new remove_background();
    light_door(double x,double y,int tilesize){
        this.x = (int)x;
        this.y = (int)y;
        this.tilesize = tilesize;
        initial_image();
    }
    public void setPass(boolean pass){
        this.pass = pass;
    }
    public void light_effect(Graphics2D g2,Color color,entity en,float radius) {


        // Define the center and radius of the gradient circle
        Point2D center = new Point2D.Float(x-en.x +40, y+en.y);


        // Define the gradient colors and positions
        Color[] colors = { color, new Color(255, 0, 0, 0) }; // Yellow to transparent red
        float[] fractions = { 0.0f, 1.0f };

        // Create the RadialGradientPaint
        RadialGradientPaint paint = new RadialGradientPaint(center, radius, fractions, colors);

        // Set the drawing color to the gradient
        g2.setPaint(paint);

        // Draw a filled ellipse to create the gradient circle
        g2.fillOval((int)(center.getX() - radius), (int)(center.getY() - radius), (int)(2 * radius), (int)(2 * radius));
    }

    public void initial_image(){
        image = new BufferedImage[2];
        try{
            image[0] = ImageIO.read(getClass().getResourceAsStream("/scene/light_doorred.png"));
            image[1] = ImageIO.read(getClass().getResourceAsStream("/scene/light_doorgreen.png"));
            image[0] = removeBackground.removeBackground(image[0],Color.WHITE);
            image[1] = removeBackground.removeBackground(image[1],Color.WHITE);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(entity en, Graphics2D g2){
        if(pass){
            g2.drawImage(image[1],x-en.x,y+en.y,tilesize*2+10,tilesize-15,null);

        }else {
            g2.drawImage(image[0], x - en.x, y + en.y, tilesize*2+10, tilesize-15, null);

        }
    }
    public void light_effect(entity en, Graphics2D g2){
        if(pass){
            light_effect(g2,new Color (0,255,0,80) ,en,200);
            light_effect(g2,new Color (0,255,0,35) ,en,500);
        }else{
            light_effect(g2,new Color (255,0,0,80),en,200);
            light_effect(g2,new Color (255,0,0,35) ,en,500);
        }
    }

}
