import java.awt.*;

public class dark_effect {
    private int x,y,width,height;
    private double speed_effect;
    public double alpha;
    public boolean draw = false;

    dark_effect(int x,int y,int width,int height,int alpha,double speed_effect){
        this.x = x;
        this.y = y;
        this.alpha = alpha;
        this.width = width;
        this.height = height;
        this.speed_effect = speed_effect;
    }
    public void setX(int x){
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setDraw(boolean draw){
        this.draw = draw;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setAlpha(double alpha){
        this.alpha = alpha;
    }
    public void setSpeed_effect(double speed_effect){this.speed_effect = speed_effect;}
    public void draw_dark(Graphics2D g2){
        if(draw){
            g2.setColor(new Color(0,0,0,(int )alpha));
            g2.fillRect(x,y,width,height);
        }
    }
    public void draw_bright(Graphics2D g2){
        if(draw){
            g2.setColor(new Color(255,255,255,(int )alpha));
            g2.fillRect(x,y,width,height);
        }
    }
    public void update_dark(int limit_alpha){
        if(limit_alpha < alpha){
            alpha += speed_effect;
        }
        if(limit_alpha >= alpha){
            alpha = limit_alpha;
        }
    }
    public void update_light(int limit_alpha){
        if(limit_alpha < alpha){
            alpha -= speed_effect;
        }

        if(limit_alpha >= alpha){
            alpha = limit_alpha;
        }

    }
}
