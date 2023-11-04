import java.awt.*;
import java.util.ArrayList;
public class draw_title {
    public String word;
    public ArrayList<String> words = new ArrayList<String>();
    public int x;
    public int y;
    public boolean dialog = false;
    public boolean flip_flop = false;
    public int alpha=0;

    private int xx;
    private int yy;
    private Font fonts;
    private Color font_color;
    private double animation = 0;
    private boolean draw=false;
    private boolean limit_movement = false;
    private double word_animation = 0;
    draw_title(int x,int y,String word,Font font,Color font_color){
        this.x =x;
        this.y=y;
        this.xx = x;
        this.yy = y;
        this.word=word;
        this.font_color = font_color;
        this.fonts =font;
    }
    draw_title(int x,int y,String[] word,Font font,Color font_color){
        int max_length=0;
        this.x =x;
        this.y=y;
        this.xx = x;
        this.yy = y;
        this.font_color = font_color;
        this.fonts =font;
        for(String i:word){
            if(i.length() >max_length){
                max_length = i.length();
            }
        }
        for(String i:word){
            if(i.length() == max_length){
                words.add(i);
            }else{
                int distance = (max_length-i.length())/2;
                String new_s = new String();
                for(int j =0;j<distance;j++){
                    new_s = String.join(""," ",new_s);
                }
                new_s = String.join("",new_s,i);
                words.add(new_s);
            }
        }
        this.word = words.get(0);
    }
    public void add_text(String[] word){
        for(String i : word){
            words.add(i);
        }
    }
    public void change_text(int index){
        word = words.get(index);
    }
    public void animation_dialog(double speed){
        if((int)word_animation >= words.size()-1){
            change_text(words.size()-1);
            dialog = true;
        }
        else if((int)word_animation < words.size()-1){
            word_animation += speed;
            change_text((int)(word_animation));
        }
    }
    public void add_text(String word){
        words.add(word);
    }
    public void draw(Graphics2D g2){
        if(draw) {
            g2.setFont(this.fonts);
            g2.setColor(this.font_color);
            g2.drawString(this.word, this.x, this.y);
        }
    }
    public void draw_movement(Graphics2D g2,entity en){
        if(draw) {
            g2.setFont(this.fonts);
            g2.setColor(this.font_color);
            g2.drawString(this.word, this.x-en.x, this.y+en.y);
        }
    }
    public void setDraw(boolean draw){
        this.draw = draw;
    }
    public void animation_update(int speed_frame,int limit,String movement){
        if(movement == "x"){
            if(this.x >= limit){
                this.limit_movement = true;
            }
            if(this.x < this.xx){
                this.limit_movement = false;
            }
            if(this.limit_movement){
                this.x--;
            }else{
                this.x++;
            }
        }else if(movement == "y"){
            if(this.y >= limit){
                this.limit_movement = true;
            }
            if(this.y < this.yy){
                this.limit_movement = false;
            }
            if(this.limit_movement){
                this.y--;
            }else{
                this.y++;
            }
        }
    }
    public void setY(int y){
        this.y = y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setXX(int x){
        this.xx = x;
    }
    public void setYY(int y){
        this.yy = y;
    }
    public void setWord(String word){
        this.word = word;
    }
    public void setWords(String[] words){
        this.words.clear();
        for(String i :words){
            this.words.add(i);
        }
    }
    public void set_alphafront(int alpha){
        this.font_color = new Color(this.font_color.getRed(),this.font_color.getGreen(),this.font_color.getBlue(),alpha);
    }
    public void setAlpha(int alpha){
        this.alpha = alpha;
        this.animation = alpha;
    }
    public void animation_filpflop(int limit_alpha,double speed){
        if(limit_alpha <= animation){
            flip_flop = true;
        }
        if(limit_alpha > animation && !flip_flop){
            animation += speed;
        }
        if(limit_alpha >= animation && flip_flop){
            animation -= speed;
        }
        if(animation < 0){
            animation = 0;
            flip_flop = false;
        }
        set_alphafront((int)animation);
    }
}
