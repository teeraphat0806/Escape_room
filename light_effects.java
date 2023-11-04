import java.awt.*;
import java.awt.geom.Point2D;

public class light_effects {
    public boolean draws=false;
    private int x;
    private int y;
    private float radius;
    private final float final_radius;
    private Color color_light;
    private Color color_gradient;

    light_effects(int x,int y,float radius,Color color_light,Color color_gradient){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.final_radius = radius;
        this.color_light = color_light;
        this.color_gradient = color_gradient;
    }
    public void setX(int x){
        this.x =x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setRadius(float radius){
        this.radius = radius;
    }
    public void setColor_light(Color color_light){
        this.color_light = color_light;
    }
    public void setColor_gradient(Color color_gradient){
        this.color_gradient = color_gradient;
    }
    public void setDraws(boolean draws){
        this.draws = draws;
    }
    public void draw(Graphics2D g2, entity en) {
        if(draws) {
            // Define the center and radius of the gradient circle
            Point2D center = new Point2D.Float(this.x - en.x, this.y + en.y);
            float radius = this.radius;

            // Define the gradient colors and positions
            Color[] colors = {color_light, color_gradient}; // Yellow to transparent red
            float[] fractions = {0.0f, 1.0f};

            // Create the RadialGradientPaint
            RadialGradientPaint paint = new RadialGradientPaint(center, radius, fractions, colors);

            // Set the drawing color to the gradient
            g2.setPaint(paint);

            // Draw a filled ellipse to create the gradient circle
            g2.fillOval((int) (center.getX() - radius), (int) (center.getY() - radius), (int) (2 * radius), (int) (2 * radius));
        }
    }
    public void draw(Graphics2D g2) {
        if(draws) {
            // Define the center and radius of the gradient circle
            Point2D center = new Point2D.Float(this.x, this.y);


            // Define the gradient colors and positions
            Color[] colors = {color_light, color_gradient}; // Yellow to transparent red
            float[] fractions = {0.0f, 1.0f};

            // Create the RadialGradientPaint
            RadialGradientPaint paint = new RadialGradientPaint(center, radius, fractions, colors);

            // Set the drawing color to the gradient
            g2.setPaint(paint);

            // Draw a filled ellipse to create the gradient circle
            g2.fillOval((int) (center.getX() - radius), (int) (center.getY() - radius), (int) (2 * radius), (int) (2 * radius));
        }
    }
    public void update_animate(float speed){
        if(radius <=5){
            radius +=  speed;
        }
        if(radius >= final_radius){
            radius -=  speed;
        }
    }
}
